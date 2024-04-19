package hello.login.web.item.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

  public static final String SESSION_COOKIE_NAME = "mySessionId";
  private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

  /**
   * 세션 생성
   * 1. sessionId 생성 (임의의 추정 불가능한 랜덤값)
   * 2. 세션 저장소에 sessionId와 보관할 값 저장
   * 3. sessionId로 응답 쿠키를 생성하여 클라이언트에게 전달
   */
  public void createSession(Object value, HttpServletResponse response) {

    //sessionId 생성하고 값을 세션에 저장
    String sessionId = UUID.randomUUID().toString();
    sessionStore.put(sessionId, value);

    //쿠키 생성
    Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
    response.addCookie(mySessionCookie);
  }

  /**
   * 세션 조회
   */
  public Object getSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

    if (sessionCookie == null) {
      return null;
    }
    return sessionStore.get(sessionCookie.getValue());
  }

  public Cookie findCookie(HttpServletRequest request, String cookieName) {
    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(cookieName))
        .findFirst()
        .orElse(null);
  }

  /**
   * 세션 만료
   */
  public void expireSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie != null) {
      sessionStore.remove(sessionCookie.getValue());
    }
  }
}

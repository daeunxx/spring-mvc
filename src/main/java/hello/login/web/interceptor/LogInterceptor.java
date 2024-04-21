package hello.login.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

  public static final String LOG_ID = "logId";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestURI = request.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    request.setAttribute(LOG_ID, uuid);

    //@RequestMapping: HandlerMethod 리턴
    //정적 리소스: ResourceHttpRequestHandler 리턴
    if (handler instanceof HandlerMethod) {
      //호출할 컨트롤러 메서드의 모든 정보 포함
      HandlerMethod handlerMethod = (HandlerMethod) handler;
    }

    log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.info("postHandle [{}]", modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    String requestURI = request.getRequestURI();
    String uuid = (String) request.getAttribute(LOG_ID);

    log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);

    if (ex != null) {
      log.error("after Completion error", ex);
    }
  }
}

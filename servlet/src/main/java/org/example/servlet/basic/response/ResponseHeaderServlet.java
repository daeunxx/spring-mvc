package org.example.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // [status-line]
    response.setStatus(HttpServletResponse.SC_OK);

    // [response-header]
    setContent(response);
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");

    setCookie(response);
    setRedirect(response);

    response.getWriter().write("ok");
  }

  private void setContent(HttpServletResponse response) {
//    response.setHeader("Content-type", "text/plain;charset=utf-8");
//    response.setHeader("Content-Length", "2");
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    response.setContentLength(2); // 생략 가능(자동 생성)
  }

  private void setCookie(HttpServletResponse response) {
//    response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600);
    response.addCookie(cookie);
  }

  private void setRedirect(HttpServletResponse response) throws IOException {
    // Status Code 302
    // Location: /basic/hello-form.html
//    response.setStatus(HttpServletResponse.SC_FOUND);
//    response.setHeader("Location", "/basic/hello-form.html");
    response.sendRedirect("/basic/hello-form.html");
  }
}

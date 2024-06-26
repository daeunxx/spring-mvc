package org.example.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;

//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

  @Override
  public void customize(ConfigurableWebServerFactory factory) {
    ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
    ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
    //런타임 예외뿐만 아니라 자식 타입도 아래 에러페이지로 호출
    ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

    factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
  }
}

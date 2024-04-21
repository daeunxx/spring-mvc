package org.example.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ServletExController {

  @GetMapping("/error-ex")
  public void errorEx() {
    //런타임에러는 500 에러
    throw new RuntimeException("예외 발생");
  }

  @GetMapping("/error-404")
  public void error404(HttpServletResponse response) throws IOException {
    response.sendError(404, "404 오류");
  }

  @GetMapping("/error-500")
  public void error500(HttpServletResponse response) throws IOException {
    response.sendError(500, "500 오류");
  }
}

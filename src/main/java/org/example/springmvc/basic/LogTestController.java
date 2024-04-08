package org.example.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String test = "test";

    // 아래로 갈수록 심각한 에러
    log.trace("trace log={}", test);
    log.debug("debug log={}", test);
    log.info("info log={}", test);
    log.warn("warn log={}", test);
    log.error("error log={}", test);

    return "ok";
  }
}

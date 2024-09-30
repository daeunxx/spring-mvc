package org.example.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.basic.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

  // 반환 타입이 없으면서 응답에 직접 값을 넣으면 view 조회하지 않음
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    log.info("username={}, age={}", username, age);

    response.getWriter().write("ok");
  }

  // @ResponseBody 는 @RestController 와 같은 역할
  // view 가 아니라 String 그대로 노출
  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
      @RequestParam("username") String username,
      @RequestParam("age") int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
      @RequestParam String username,
      @RequestParam int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  // /request-param-required?username= -> 파라미터 이름만 있고 값이 없는 경우, 빈 문자로 통과
  // age 값이 없을 경우, int 타입에 null 값을 넣기 때문에 IllegalStateException 발생
  @ResponseBody
  @RequestMapping("/request-param-required-primitive")
  public String requestParamRequiredPrimitive(
      @RequestParam(required = true) String username,
      @RequestParam(required = false) int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-required-reference")
  public String requestParamRequiredReference(
      @RequestParam(required = true) String username,
      @RequestParam(required = false) Integer age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  // 빈 문자에도 defaultValue 적용
  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
      @RequestParam(required = true, defaultValue = "guest") String username,
      @RequestParam(required = false, defaultValue = "-1") int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    log.info("paramMap={}", paramMap);
    log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v1")
  public String modelAttributeV1(@ModelAttribute HelloData helloData) {
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    log.info("helloData={}", helloData);

    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v2")
  public String modelAttributeV2(HelloData helloData) {
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    log.info("helloData={}", helloData);

    return "ok";
  }
}

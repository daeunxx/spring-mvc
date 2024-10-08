package org.example.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

  @RequestMapping("/response-view-v1")
  public ModelAndView responseViewV1() {
    return new ModelAndView("/response/hello")
        .addObject("data", "hello");
  }

  @RequestMapping("/response-view-v2")
  public String responseViewV2(Model model) {
    model.addAttribute("data", "hello!!");
    return "/response/hello";
  }

  // 경로의 이름과 API url 이 같으면 html 호출
  @RequestMapping("/response/hello")
  public void responseViewV3(Model model) {
    model.addAttribute("data", "hello-html");
  }
}

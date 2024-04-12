package org.example.thymeleafbasic.basic;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

  @GetMapping("/text-basic")
  public String textBasic(Model model) {
    model.addAttribute("data", "Hello <b>Spring</b>");
    return "basic/text-basic";
  }

  @GetMapping("/text-unescaped")
  public String textUnescaped(Model model) {
    model.addAttribute("data", "Hello <b>Spring</b>");
    return "basic/text-unescaped";
  }

  @GetMapping("/variable")
  public String variable(Model model) {
    User userA = new User("userA", 10);
    User userB = new User("userB", 10);

    List<User> list = new ArrayList<>();
    list.add(userA);
    list.add(userB);

    Map<String, User> map = new HashMap<>();
    map.put(userA.getUsername(), userA);
    map.put(userB.getUsername(), userB);

    model.addAttribute("user", userA);
    model.addAttribute("users", list);
    model.addAttribute("userMap", map);

    return "/basic/variable";
  }

  @GetMapping("/basic-objects")
  public String basicObjects(Model model, HttpServletRequest request,
      HttpServletResponse response, HttpSession session) {
    session.setAttribute("sessionData", "Hello Session");

    model.addAttribute("request", request);
    model.addAttribute("response", response);
    model.addAttribute("session", session);
    model.addAttribute("servletContext", request.getServletContext());
    return "basic/basic-objects";
  }

  @GetMapping("/date")
  public String date(Model model) {
    model.addAttribute("localDateTime", LocalDateTime.now());
    return "/basic/date";
  }

  @Component("helloBean")
  static class HelloBean {
    public String hello(String data) {
      return "Hello " + data;
    }
  }

  @GetMapping("/link")
  public String link(Model model) {
    model.addAttribute("param1", "data1");
    model.addAttribute("param2", "data2");
    return "basic/link";
  }

  @GetMapping("/literal")
  public String literal(Model model) {
    model.addAttribute("data", "Spring!");
    return "basic/literal";
  }

  @GetMapping("/operation")
  public String operation(Model model) {
    model.addAttribute("nullData", null);
    model.addAttribute("data", "spring!");
    return "basic/operation";
  }

  @GetMapping("/attribute")
  public String attribute() {
    return "basic/attribute";
  }

  @GetMapping("/each")
  public String each(Model model) {
    addUsers(model);
    return "basic/each";
  }

  @GetMapping("/condition")
  public String condition(Model model) {
    addUsers(model);
    return "basic/condition";
  }

  private void addUsers(Model model) {
    List<User> list = new ArrayList<>();
    list.add(new User("userA", 10));
    list.add(new User("userB", 20));
    list.add(new User("userC", 30));

    model.addAttribute("users", list);
  }

  @Data
  private class User {

    private String username;
    private int age;

    public User(String username, int age) {
      this.username = username;
      this.age = age;
    }
  }
}

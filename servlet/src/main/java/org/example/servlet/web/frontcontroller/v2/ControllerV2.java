package org.example.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.servlet.web.frontcontroller.MyView;

public interface ControllerV2 {

  MyView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException;
}

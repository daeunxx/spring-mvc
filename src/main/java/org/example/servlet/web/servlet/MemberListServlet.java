package org.example.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.example.servlet.domain.member.Member;
import org.example.servlet.domain.member.MemberRepository;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Member> members = memberRepository.findAll();

    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");

    PrintWriter printWriter = response.getWriter();
    printWriter.write("<html>");
    printWriter.write("<head>");
    printWriter.write(" <meta charset=\"UTF-8\">");
    printWriter.write(" <title>Title</title>");
    printWriter.write("</head>");
    printWriter.write("<body>");
    printWriter.write("<a href=\"/index.html\">메인</a>");
    printWriter.write("<table>");
    printWriter.write(" <thead>");
    printWriter.write(" <th>id</th>");
    printWriter.write(" <th>username</th>");
    printWriter.write(" <th>age</th>");
    printWriter.write(" </thead>");
    printWriter.write(" <tbody>");

    for (Member member : members) {
      printWriter.write(" <tr>");
      printWriter.write(" <td>" + member.getId() + "</td>");
      printWriter.write(" <td>" + member.getUsername() + "</td>");
      printWriter.write(" <td>" + member.getAge() + "</td>");
      printWriter.write(" </tr>");
    }
    printWriter.write(" </tbody>");
    printWriter.write("</table>");
    printWriter.write("</body>");
    printWriter.write("</html>");
  }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.servlet.domain.member.Member" %>
<%@ page import="org.example.servlet.domain.member.MemberRepository" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();

  // JSP에서 자동으로 서블릿으로 변환되며 사용돼서 request와 response 자동 지원
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));  // getParameter의 응답결과는 항상 문자

  Member member = new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>

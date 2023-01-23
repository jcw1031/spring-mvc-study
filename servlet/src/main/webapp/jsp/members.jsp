<%--
  Created by IntelliJ IDEA.
  User: jcw
  Date: 2023/01/23
  Time: 6:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.woopaca.servlet.domain.member.MemberRepository" %>
<%@ page import="com.woopaca.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("    <tr>\n");
            out.write("        <td>" + member.getId() + "</td>\n");
            out.write("        <td>" + member.getUsername() + "</td>\n");
            out.write("        <td>" + member.getAge() + "</td>\n");
            out.write("    </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>


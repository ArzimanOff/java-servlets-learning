<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 09.02.2025
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Билеты к рейсу:</h1>
<ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
        <li> ${ticket.seatNo} ${ticket.passengerName} </li>
    </c:forEach>
</ul>
</body>
</html>

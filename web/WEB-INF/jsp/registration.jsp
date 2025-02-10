<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 10.02.2025
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="userName"> Name:
        <input type="text" name="userName" id="userName">
    </label>
    <br>
    <label for="birthday"> Birthday:
        <input type="date" name="birthday" id="birthday">
    </label>
    <br>
    <label for="email"> Email:
        <input type="text" name="email" id="email">
    </label>
    <br>
    <label for="password"> Password:
        <input type="password" name="password" id="password">
    </label>
    <br>
    <label for="role">
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label>
    <br>
    <label>
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}"> ${gender}
            <br>
        </c:forEach>

    </label>
    <br>
    <button type="submit"> Отправить! </button>
    <br>

    <c:if test="${not empty requestScope.errors}">
        <div style="color: crimson">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.code} ${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>

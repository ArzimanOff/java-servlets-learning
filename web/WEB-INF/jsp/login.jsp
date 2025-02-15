<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 13.02.2025
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email"> Email:
        <input type="text" name="email" id="email" value="${param.email}" required>
    </label><br>
    <label for="password"> Password:
        <input type="password" name="password" id="password" required>
    </label>
    <button type="submit">Login</button>
    <a href="${pageContext.request.contextPath}/registration">Нет аккаунта? Зарегистрироваться</a>

    <c:if test="${param.error != null}">
        <div style="color: crimson">
            <span> Данные для входа не верны! </span>
        </div>
    </c:if>

</form>
</body>
</html>

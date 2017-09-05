<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 27.08.2017
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="../CSS/loginStyle.css" media="screen">

    <title>Авторизация.</title>
</head>
<body>
<div  class="container">
    <section>
        <form action="/login" method="POST">
            <fieldset id="frame">
                <h1>Авторизация.</h1>
                <div>
                    <input id="username" name="username" type="text" placeholder="Логин" autofocus required>
                    <input id="password" name="password" type="password" placeholder="Пароль" required>
                    <p>
                    <c:if test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                    <%--<%--%>
                        <%--if(null!=request.getAttribute("errorMessage")){--%>
                            <%--out.println(request.getAttribute("errorMessage"));--%>
                        <%--}--%>
                    <%--%>--%>
                    </p>
                </div>
                <div id="action_buttons">
                    <button id="button-enter">Войти</button>
                    <%--<a href="">Регистрация</a>--%>
                </div>
            </fieldset>
        </form>
    </section>
</div>
</body>
</html>

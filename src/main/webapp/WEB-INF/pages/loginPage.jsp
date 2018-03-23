<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <script src="js/jquery-3.2.1.js"></script>
        <script src="js/login.js"></script>
        <!--<script src="bootstrap/js/bootstrap.min.js"></script>-->
        <link rel='stylesheet' href="bootstrap/css/bootstrap.css" type='text/css' media='all'>
        <!--<link rel="stylesheet" href="css/app.css" media="screen">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Review-Assistant.Authorization</title>
    </head>
    <body>
    <form action="/DoLoginServlet" method="POST">
        <div class="form-group">
            <label for="username">Логин</label>
            <input id="username" name="username" type="text" placeholder="Логин" autofocus required>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input id="password" name="password" type="password" placeholder="Пароль" required>
        </div>
        <div>
            <p>${message}</p>
        </div>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <button class="btn btn-primary" onclick="goStartPage()">На главную</button>
    </body>
</html>

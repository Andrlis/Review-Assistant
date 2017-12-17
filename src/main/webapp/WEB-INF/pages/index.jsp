<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/app.js"></script>
    <!--<script src="bootstrap/js/bootstrap.min.js"></script>-->
    <link rel='stylesheet' href="bootstrap/css/bootstrap.css" type='text/css' media='all'>
    <link rel="stylesheet" href="css/app.css" media="screen">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body class="body-class">

<nav class="navbar navbar-inverse menu-bar-class navbar-fixed-top">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="dropdown" style="height: 50px;">
                <a class="dropdown-toggle"  data-toggle="dropdown" href="#"><span
                        class="glyphicon glyphicon-menu-hamburger menu-bar-button-class"></span></a>
                <ul class="dropdown-menu">
                    <li><button class="btn btn-linkk ch-tab-type" value="m">Оценки</button></li>
                    <li><button class="btn btn-linkk ch-tab-type" value="p">Посещения</button></li>
                    <li><button class="btn btn-linkk ch-tab-type" value="e">Студенты</button></li>
                </ul>
            </li>

            <c:forEach items="${groups}" var="group">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" value="${group.numberOfGroup}">
                    <span class=" menu-bar-button-class"><c:out
                            value="${group.numberOfGroup}"/></span>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="subgroup" items="${group.subGroupList}" varStatus="num">
                        <li>
                            <button class="btn btn-linkk btn-block ch-tab-gr" value="${num.count}">Подгруппа
                                    ${num.count}</button></li>
                    </c:forEach>
                </ul>
            </li>
            </c:forEach>
        </ul>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <ul class="nav navbar-nav navbar-right">
                    <li id="login"><a data-toggle="modal" data-target="#popup-login"><span class="glyphicon glyphicon-log-in menu-bar-button-class"></span><span class=" menu-bar-button-class"> Войти</span></a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav navbar-nav navbar-right">
                    <li id="logout"><a href="/LogoutServlet"><span class=" menu-bar-button-class"> Выйти</span></a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<div id="table-title" class="container-ver">
    <div class="text-center h1">Группа <span id="group-number"
                                             value="${groups[0].numberOfGroup}">${groups[0].numberOfGroup}</span></div>
    <div class="text-center h2">Подгруппа
        <span id="subgroup-number" value="${groups[0].subGroupList[0].subGroupNumber}">
            ${groups[0].subGroupList[0].subGroupNumber}</span>
    </div>
    <div class="text-center h3"><span id="info-type" value="m">Оценки</span></div>
</div>

<div style="text-align: center;">
<div id="table-container" style="display: inline-table;">

</div>
</div>

<!-- popup form for adding column with lab or test
<div class="popup" id="popup-add-column">
    <button data-toggle="modal" data-target="#addLabTest" onclick="showPopupFormAddColumn()">Добавить</button>
    <form class="popup-form container-ver" id="popup-form-add-column">
        <select name="new-column-type" id="new-column-type" onchange="changeNewColumnType()">
            <option value="lab">Лабораторная работа <span id="new-lab-number"></span></option>
            <option value="test">Контрольная работа <span id="new-test-number"></span></option>
        </select>
            <select name="new-lab-date" id="new-lab-date">

            </select>
        <textarea id="comment-text-area" cols="30" rows="5"></textarea>
        <input type="button" value="Добавить" onclick="addLabOrTestButton()">
        <input type="button" value="Отменить" onclick="cancelPopupFormAddColumn()">
    </form>
</div>-->

<!-- popup form for adding column with lab or test-->
<div class="popup" id="popup-add-column">
    <!--<button data-toggle="modal" data-target="#addLabTest" onclick="showPopupFormAddColumn()">Добавить</button>-->
    <!--<button onclick="showPopupFormAddColumn()">Добавить</button>-->

    <div id="addLabTest" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Добавить лабораторную/контрольную</h4>
                </div>
                <div class="btn-group model-button" data-toggle="buttons" id="choose-column-type">
                    <label class="btn btn-success active btn-lg" id="lab-radio-button">
                        <input type="radio" name="options" id="lab-radio-button" autocomplete="off" value="lab" checked>Лабораторная работа  <span id="new-lab-number"></span>
                    </label>
                    <label class="btn btn-success btn-lg" id="test-radio-button">
                        <input type="radio" name="options" autocomplete="off" value="test"> Контрольная работа <span id="new-test-number"></span>
                    </label>
                </div>
                <div>
                    <select class="select-style" name="new-lab-date" id="new-lab-date">

                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="addLabOrTestButton()">Добавить</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- popup form for adding or edditing student
<div class="popup" id="popup-edit-student">
    <button data-toggle="modal" data-target="#addStudent" onclick="showEmptyPopupFormEditStudent()">Добавить студента</button>
    <form class="popup-form container-ver" id="popup-form-edit-student">
        <input id="student-id" hidden>
        <label for="student-surname">Фамилия</label>
        <input type="text" id="student-surname">
        <label for="student-name">Имя</label>
        <input type="text" id="student-name">
        <label for="student-eMail">Почта</label>
        <input type="text" id="student-eMail">
        <label for="student-git">Репозиторий</label>
        <input type="text" id="student-git">
        <input type="button" value="Сохранить" onclick="saveStudentButtonClick()">
        <input type="button" value="Удалить" id="delete-student-button" onclick="deleteStudentButtonClick()">
        <input type="button" value="Отменить" onclick="cancelPopupFormEditStudent()">
    </form>
</div>-->

<!-- popup form for adding or edditing student-->
<div class="popup" id="popup-edit-student">
    <!--button data-toggle="modal" data-target="#addStudent" onclick="showEmptyPopupFormEditStudent()">Добавить студента</button>-->
    <!--<button onclick="showEmptyPopupFormEditStudent()">Добавить студента</button>-->

    <div id="popup-form-edit-student" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Добавить нового студента</h4>
                    <input id="student-id" hidden>
                    <div class="form-group">
                        <label for="student-surname">Фамилия</label>
                        <input class="form-control" id="student-surname" type="text">
                    </div>
                    <div class="form-group">
                        <label for="student-name">Имя</label>
                        <input class="form-control" id="student-name" type="text">
                    </div>
                    <div class="form-group">
                        <label for="student-eMail">eMail</label>
                        <input class="form-control" id="student-eMail" type="text">
                    </div>
                    <div class="form-group">
                        <label for="student-git">git URL</label>
                        <input class="form-control" id="student-git" type="text">
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="delete-student-button" type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteStudentButtonClick()">Удалить</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="saveStudentButtonClick()">Добавить</button>
                </div>
            </div>

        </div>
    </div>
</div>



<div class="popup">
    <div id="popup-login" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Пожалуйста авторизуйтесь</h4>
                    <div class="form-group">
                        <label for="login-email">Логин</label>
                        <input class="form-control" id="login-email" type="text">
                    </div>
                    <div class="form-group">
                        <label for="login-password">Пароль</label>
                        <input class="form-control" id="login-password" type="password">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="loginButtonClick()" >Войти</button>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/app.js"></script>
    <link rel='stylesheet' href="bootstrap/css/bootstrap.css" type='text/css' media='all'>
    <link rel="stylesheet" href="css/app.css" media="screen">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
                        class="glyphicon glyphicon-menu-hamburger"></span></a>
                <ul class="dropdown-menu">
                    <li><button class="btn btn-linkk ch-tab-type" value="m">Оценки</button></li>
                    <li><button class="btn btn-linkk ch-tab-type" value="p">Посещения</button></li>
                    <li><button class="btn btn-linkk ch-tab-type" value="e">Редактирование</button></li>
                </ul>
            </li>

            <c:forEach items="${groups}" var="group">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" value="${group.numberOfGroup}"><c:out
                        value="${group.numberOfGroup}"/>
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
        <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
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

<div id="table-container">

</div>

<!-- popup form for adding column with lab or test-->
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
</div>

<!-- Modal -->
<div id="addLabTest" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content center-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Добавить лабораторную/контрольную</h4>
            </div>
            <div class="btn-group model-button" data-toggle="buttons">
                <label class="btn btn-primary active btn-lg">
                    <input type="radio" name="options" id="option1" autocomplete="off" value="lab" checked>Лабораторная работа  <span id="new-lab-number"></span>
                </label>
                <label class="btn btn-primary btn-lg">
                    <input type="radio" name="options" id="option2" autocomplete="off" value="test"> Контрольная работа <span id="new-test-number"></span>
                </label>
            </div>
            <div>
                <select name="new-lab-date" id="new-lab-date">

                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Добавить</button>
            </div>
        </div>

    </div>
</div>

<!-- popup form for adding or edditing student-->
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
</div>

<!-- Modal -->
<div id="addStudent" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content center-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Добавить нового студента</h4>
                <div class="form-group">
                    <label for="inputdefault">Фамилия</label>
                    <input class="form-control" id="inputdefault" type="text">
                </div>
                <div class="form-group">
                    <label for="inputdefault">Имя</label>
                    <input class="form-control" id="inputdefault" type="text">
                </div>
                <div class="form-group">
                    <label for="inputdefault">eMail</label>
                    <input class="form-control" id="inputdefault" type="text">
                </div>
                <div class="form-group">
                    <label for="inputdefault">git URL</label>
                    <input class="form-control" id="inputdefault" type="text">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Добавить</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>
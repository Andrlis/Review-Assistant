<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/app.js"></script>
    <!--<script src="bootstrap/js/bootstrap.min.js"></script>-->
    <link rel='stylesheet' href="bootstrap/css/bootstrap.css" type='text/css' media='all'>
    <link rel="stylesheet" href="css/app.css" media="screen">
    <link rel="stylesheet" href="external/css/font-awesome.css">

    <script src="external/js/jquery.js"></script>
    <script src="external/js/bootstrap.js"></script>

    <!-- Include Required Prerequisites -->
    <script type="text/javascript" src="external/js/moment.js"></script>

    <!-- Include Date Range Picker -->
    <script type="text/javascript" src="external/js/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="external/css/daterangepicker.css"/>

    <title>Review-Assistant</title>
    <link rel="icon" href="picture/git.png">
</head>
<body class="body-class">

<!--Navbar-->

<nav class="navbar navbar-inverse menu-bar-class navbar-fixed-top">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="dropdown" style="height: 50px;">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
                        class="glyphicon glyphicon-menu-hamburger menu-bar-button-class"></span></a>
                <ul class="dropdown-menu">
                    <li>
                        <button class="btn btn-linkk ch-tab-type" value="g" disabled>Группы</button>
                    </li>
                    <li>
                        <button class="btn btn-linkk ch-tab-type" value="l" disabled>Лабораторные</button>
                    </li>
                    <li>
                        <button class="btn btn-linkk ch-tab-type" value="a" disabled>Учётные записи</button>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li id="logout"><a href="/LogoutServlet"><span class=" menu-bar-button-class">Выйти</span></a></li>
        </ul>
    </div>
</nav>


<!--Title for page-->
<div id="page-title" class="container-ver panel-heading">
    <div class="text-center h1">Группы</div>
</div>


<div class="group-windows-container">
    <c:forEach items="${groups}" var="group">
        <div class="modal-content center-modal group-window">
            <div class="modal-header">
                <div class="btn-toolbar" role="toolbar">
                    <h4 class="pull-left">
                        <strong>
                            Группа ${group.numberOfGroup}
                        </strong>
                    </h4>
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#popup-edit-group" onclick="">
                            <span class="glyphicon glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#popup-delete" onclick="">
                            <span class="glyphicon glyphicon glyphicon-remove"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="group-window-content">
                <c:forEach var="subgroup" items="${group.subGroupList}" varStatus="num">
                    <div class="sub-group-window-content">
                        <h4 class="pull-left">
                            <strong>
                                Подгруппа ${num.count}
                            </strong>
                        </h4>
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#popup-edit-subgroup" onclick="">
                                <span class="glyphicon glyphicon glyphicon-pencil"></span>
                            </button>
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#popup-delete" onclick="">
                                <span class="glyphicon glyphicon glyphicon-remove"></span>
                            </button>
                        </div>
                    </div>
                </c:forEach>
                <c:choose>
                    <c:when test="${fn:length(group.subGroupList) < 2}">
                        <div class="sub-group-window-content">
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#popup-edit-subgroup" onclick="">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>

    <!--Add new group-->
    <div class="modal-content center-modal group-window" style="padding: 0px;">
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-default" id="add-group-button" data-toggle="modal" data-target="#popup-edit-group" onclick="">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </div>
    </div>
</div>

<!-- popup form for adding or edditing group-->
<div class="popup">
    <div id="popup-edit-group" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Редактирование группы</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group" style="width: 300px;">
                        <span class="input-group-addon">№ группы</span>
                        <input type="text" class="form-control">
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                            Сохранить
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- popup form for adding or edit subgroup-->
<div class="popup">
    <div id="popup-edit-subgroup" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Редактировать подгруппу</h4>
                </div>


                <div class="modal-body">
                    <div class="input-group" style="width: 300px;">
                        <span class="input-group-addon">Преподаватель</span>
                        <input type="text" class="form-control">
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Имя пользователя</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="modal-footer">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                            Добавить студентов
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                            Сохранить
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- popup form for delete-->
<div class="popup">
    <div id="popup-delete" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content center-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Удаление</h4>
                </div>
                <div class="modal-body">
                    <h4>Вы уверены?</h4>
                </div>
                <div class="modal-footer" style="padding: 10px;">
                    <div class="btn-group" >
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                            Удалить
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                            Отмена
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>

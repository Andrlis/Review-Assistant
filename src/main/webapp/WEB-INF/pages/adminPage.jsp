<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/app.js"></script>
    <script src="js/adminPage.js"></script>
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


    <%--Created by Adrlis. For upload file input--%>
    <!--<style>
        #uploaded-file {
            width: 0.1px;
            height: 0.1px;
            position: absolute;
            z-index: -1;
            overflow: hidden;
            opacity: 0;
        }
    </style>-->
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
                        <button type="button" class="btn btn-default" data-toggle="modal"
                                onclick="showPopupFormEditGroup('${group.numberOfGroup}')">
                            <span class="glyphicon glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" class="btn btn-default" data-toggle="modal"
                                onclick="showPopupFormRemove('${group.numberOfGroup}')">
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
                            <button type="button"
                                    class="btn btn-default"
                                    data-toggle="modal"
                                    onclick="showPopupFormEditSubGroup('${group.numberOfGroup}_${num.count}')">
                                <span class="glyphicon glyphicon glyphicon-pencil"></span>
                            </button>
                            <button type="button"
                                    class="btn btn-default"
                                    data-toggle="modal"
                                    onclick="showPopupFormRemove('${group.numberOfGroup}_${num.count}')">
                                <span class="glyphicon glyphicon glyphicon-remove"></span>
                            </button>
                        </div>
                    </div>
                </c:forEach>
                <c:choose>
                    <c:when test="${fn:length(group.subGroupList) < 2}">
                        <div class="sub-group-window-content">
                            <div class="btn-group pull-right">
                                <button type="button"
                                        class="btn btn-default"
                                        data-toggle="modal"
                                        onclick="showPopupFormEditSubGroup('${group.numberOfGroup}')">
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
            <button type="button" class="btn btn-default" id="add-group-button" data-toggle="modal"
                    onclick="showPopupFormEditGroup('')">
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
                <form action="SaveGroup" method="post" style="margin: 0px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 id="group-window-title" class="modal-title">Редактирование группы</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group input-group" style="width: 300px; margin: 0px;">
                            <span class="input-group-addon">№ группы</span>
                            <input class="form-control" type="text" name="newGroupNumber"  id="new_group_number">
                            <input name="groupNumber" id="group_number" hidden>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <div class="btn-group pull-right">
                            <input type="submit" class="btn btn-default" value="Сохранить" onclose="redirection()"/>
                        </div>
                    </div>
                </form>
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
                    <h4 id="subgroup-window-title" class="modal-title">Редактировать подгруппу</h4>
                </div>
                <form method="post" action="/UploadStudentInfoFileServlet" enctype="multipart/form-data" style="margin: 0px;">
                    <div class="modal-body">
                        <div class="form-group  input-group" style="margin: 0px; margin-left: 16px; width: 350px;">
                            <span class="input-group-addon">Преподаватель</span>
                            <select class="select-style" name="lecturer" style="height: 28px;font-size: 14px;margin: 0px;width: 100%;border-bottom-left-radius: 0px;border-top-left-radius: 0px;">
                                <c:forEach items="${lecturers}" var="lec">
                                    <option value="${lec.id}">${lec.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="id-gr-sub-lec" name="number" value=""/>
                    <div class="modal-footer">
                        <div class="btn-group btn-group-toggle">
                            <label class="btn">
                                <input class="form-control-file" id="uploaded-file" type="file" name="file" accept=".xls"  value="Добавить файл"/>
                            </label>
                            <label class="btn">
                                <input type="submit" value="Сохранить" onclick="redirection()"/>
                            </label>
                        </div>
                    </div>
                </form>
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
                <form action="DeleteGroupSubGroup" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Удаление</h4>
                    </div>
                    <div class="modal-body">
                        <h4>Удаление невоможно будет отменить.</h4>
                        <input name="number" id="number" hidden>
                    </div>
                    <div class="modal-footer" style="padding: 10px;">
                        <div class="btn-group">
                            <input type="submit" class="btn btn-default btn-danger" value="Удалить" onclick="redirection()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">
                                Отмена
                            </button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


</body>
</html>

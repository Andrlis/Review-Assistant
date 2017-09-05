<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.09.2017
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">


    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>


    <script src="../JavaScript/main.js" ></script>
    <link rel="stylesheet" href="../CSS/mainStyle2.css" media="screen">
    <%--<link rel="stylesheet" href="../CSS/mainStyle.css" media="screen">--%>

    <title>Система контроля успеваемости</title>
</head>
<body>
<div class="parent">

    <div id="frame">
    <%-- Верхние кнопки. --%>
    <div>
        <div id="choose-group">
            <strong><label for="group-combo-box">Группа: </label></strong>
            <form name="form1" action="/selectgroup" method="GET">
            <select id="group-combo-box" name="selectGroup" onchange="document.form1.submit();">
                <option value="1">550501</option>
                <option value="2">550502</option>
                <option value="3">550503</option>
                <option value="4">550504</option>
            </select>
            </form>
        </div>
        <div>
            <form action="/loginpage" method="GET">
                <button id="button-enter">Войти</button>
            </form>
        </div>
    </div>

        <%-- Таблица. --%>
    <div id="table-title" class="container">
        <h2>Группа <span id="group-number"></span></h2>
        <h2>Подгруппа <span id="subgroup-number">1</span></h2>
    </div>
    <div id="information-part">
        <div id="hor-buttonset">
            <button id="marks-button" class="button">Оценки</button>

            <button id="presence-button" class="button">Присутсвие</button>
        </div>
        <div id="subgroup-tabs">
            <ul class="ul" id="clicable-tabs">
                <li class="li"><a href="#subgroup-1">Подгруппа 1</a></li>
                <li class="li"><a href="#subgroup-2">Подгруппа 2</a></li>
            </ul>
            <div id="subgroup-1">Lol</div>
            <div id="subgroup-2">Kek</div>

        </div>
    </div>
    </div>

        <%-- Информация о студенте. --%>
    <div id="stud-inf-wind" class="modal-wind">
        <form>
            <div class="modal-wind-content">
                <strong><i><label id="mw-stud-name">ФИО студента</label></i></strong>
            </div>
            <div class="modal-wind-content">
                <strong><label for="mw-eMail-ln">eMail: </label></strong>
                <a id="mw-eMail-ln"></a>
            </div>
            <div class="modal-wind-content">
                <strong><label for="mw-gitHub-ln">GitHub: </label></strong>
                <a target="_blank" id="mw-gitHub-ln"></a>
            </div>
        </form>
    </div>

        <%-- Добавление оценки. --%>
    <div id="create-mark-field-wind" class="modal-wind">
        <form>
            <div class="modal-wind-content">
                <strong><label for="mark-type-combo-box">Тип: </label></strong>
                <select id="mark-type-combo-box">
                    <option value="lab">Лабораторная работа</option>
                    <option value="test">Контрольная работа</option>
                </select>
            </div>
            <div class="modal-wind-content">
                <strong><label for="mark-type-number">Номер: </label></strong>
                <input type="number" id="mark-type-number" placeholder="Номер эл-та данного типа." min="1" max="6">
            </div>
            <div class="modal-wind-content" id="date-of-lab-mw">
                <strong><label for="date-of-giving-lab">Дата выдачи: </label></strong>
                <input type="date" id="date-of-giving-lab" onfocus="(this.type='date')" onblur="(this.type='text')" >
            </div>
            <div class="modal-wind-content">
                <button id="give-button-mw" class="button">Добавить</button>
                <button id="cancel-button-mw" class="button">Отменить</button>
            </div>
        </form>
    </div>

</div>

</body>
</html>
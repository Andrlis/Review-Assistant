<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/app.js" ></script>
    <link rel="stylesheet" href="css/app.css" media="screen">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Title</title>
</head>
<body>

<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn">
            <img class="menu-icon" src="picture/hamburger-icon.png" alt="menu">
        </button>
        <div class="dropdown-content">
            <button class="navbar-button ch-tab-type" value="m">Оценки</button>
            <button class="navbar-button ch-tab-type" value="p">Посещения</button>
            <button class="navbar-button ch-tab-type" value="e">Редактирование</button>
        </div>
    </div>
    <c:forEach items="${groups}" var="group">
        <div class="dropdown">
            <button class="dropbtn" value="${group.numberOfGroup}">
                <c:out value="${group.numberOfGroup}"/>
            </button>
            <div class="dropdown-content">
                <c:forEach var="subgroup" items="${group.subGroupList}" varStatus="num">
                    <button class="navbar-button ch-tab-gr" value="${num.count}">Подгруппа ${num.count}</button>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    <div id="log-btn" >
       <!--<button class="navbar-button">Log in</button>-->
        <button class="navbar-button">Log out</button>
    </div>
</div>

<div id="table-title" class="container-ver">
    <div>Группа <span id="group-number" value="${groups[0].numberOfGroup}">${groups[0].numberOfGroup}</span></div>
    <div>Подгруппа
        <span id="subgroup-number" value="${groups[0].subGroupList[0].subGroupNumber}">
            ${groups[0].subGroupList[0].subGroupNumber}
        </span>
    </div>
    <span id="info-type" value="m">Оценки</span>
</div>

<div id="table-container">

</div>

<div class="popup">
    <button  onclick="showPopupForm()">Добавить</button>
    <form class="popup-form container-ver" id="popup-form">
        <select name="new-column-type" id="new-column-type">
            <option value="lab">Лабораторная работа <span id="new-lab-number"></span></option>
            <option value="test">Контрольная работа <span id="new-test-number"></span></option>
        </select>
        <select name="new-lab-date" id="new-lab-date">
            <option>Дата1</option>
            <option>Дата2</option>
        </select>
        <input type="button" value="Добавить" onclick="addLabOrTestButton()">
        <input type="button" value="Отменить" onclick="cancelPopupForm()">
    </form>
</div>


</body>
</html>

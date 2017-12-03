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
    <table class="table-ui">
        <tr class="header-cell">
            <td class="cell-ui">student</td>
            <td class="cell-ui">lab1</td>
            <td class="cell-ui">lab2</td>
            <td class="cell-ui">test1</td>
            <td class="cell-ui">test2</td>
            <td class="cell-ui">bonus</td>
        </tr>
        <tr>
            <td class="cell-ui editable" data-id="1" data-type="student">Julia Runova</td>
            <td class="cell-ui editable coef-08" data-id="1" data-type="lab"></td>
            <td class="cell-ui editable coef-06" data-id="3" data-type="lab">9</td>
            <td class="cell-ui editable coef-04" data-id="2" data-type="test">7</td>
            <td class="cell-ui editable coef-02" data-id="4" data-type="test">8</td>
            <td class="cell-ui editable coef-00" data-id="1" data-type="bonus">3</td>
        </tr>
        <tr>
            <td class="cell-ui editable" data-id="2" data-type="student">Sasha Runov</td>
            <td class="cell-ui editable coef-10" data-id="2" data-type="lab">9</td>
            <td class="cell-ui editable" data-id="4" data-type="lab"></td>
            <td class="cell-ui editable" data-id="3" data-type="test"></td>
            <td class="cell-ui editable" data-id="5" data-type="test">9</td>
            <td class="cell-ui editable" data-id="2" data-type="bonus"></td>
        </tr>
    </table>
</div>


</body>
</html>

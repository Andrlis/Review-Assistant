<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="../JavaScript/main.js" ></script>
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
            <a href="#">Оценки</a>
            <a href="#">Посещения</a>
            <a href="#">Редактирование</a>
        </div>
    </div>
    <c:forEach items="${groups}" var="group">
        <div class="dropdown">
            <button class="dropbtn">
                ${group.numberOfGroup}
            </button>
            <div class="dropdown-content">
                <c:forEach begin="1" end="${group.subGroupList.size}" var="num">
                    <a href="#">Подгруппа ${num}</a>
                </c:forEach>
            </div>
        </div>
    </c:forEach>

    <div class="dropdown">
        <button class="dropbtn">
            550502
        </button>
        <div class="dropdown-content">
            <a href="#">Подгруппа 1</a>
            <a href="#">Подгруппа 2</a>
        </div>
    </div>

    <div id="log-btn">
        <a id="log-in" href="#">Log in</a>
        <a id="log-out" href="#">Log out</a>
    </div>
</div>

<div id="table-title" class="container-ver">
    Группа <span id="group-number"></span><br>
    Подгруппа  <span id="subgroup-number">1</span><br>
    <span id="info-type">Оценки</span><br>
</div>

<div>
    <table class="table-ui" id="table-550501-1">
        <tr class="row-ui">
            <td class="cell-ui stud-name header-cell">Студент</td>
            <td class="cell-ui lab-mark header-cell">Лабораторная 1</td>
            <td class="cell-ui lab-mark header-cell">Лабораторная 2</td>
            <td class="cell-ui test-mark header-cell">Тест 1</td>
            <td class="cell-ui test-mark header-cell">Тест 2</td>
            <td class="cell-ui bonus-mark header-cell">Бонус</td>
            <td class="cell-ui button-cell-div header-cell">
                <button class="add-col-button">Добавить</button>
            </td>
            <td class="cell-ui presence-cell header-cell">27.8.2017</td>
            <td class="cell-ui presence-cell header-cell">31.8.2017</td>
        </tr>
        <tr class="row-ui">
            <td class="cell-ui stud-name data-cell">
                <div class="stud-name">Julia Runova</div>
                <div class="stud-eMail-ln">jul.runova@gmail.com</div>
                <div class="stud-gitHub-ln">repo</div>
            </td>
            <td class="cell-ui data-cell editable lab-mark"></td>
            <td class="cell-ui data-cell editable lab-mark">9</td>
            <td class="cell-ui data-cell editable test-mark">7</td>
            <td class="cell-ui data-cell editable test-mark">8</td>
            <td class="cell-ui data-cell editable bonus-mark">3</td>
            <td class="cell-ui data-cell presence-cell absent">н</td>
            <td class="cell-ui data-cell presence-cell present"></td>
        </tr>
        <tr class="row-ui">
            <td class="cell-ui stud-name data-cell">
                <div class="stud-name">Sasha Runov</div>
                <div class="stud-eMail-ln">agreenwalrus@mail.ru</div>
                <div class="stud-gitHub-ln"></div>
            </td>
            <td class="cell-ui data-cell editable lab-mark">9</td>
            <td class="cell-ui data-cell editable lab-mark"></td>
            <td class="cell-ui data-cell editable test-mark"></td>
            <td class="cell-ui data-cell editable test-mark">9</td>
            <td class="cell-ui data-cell editable bonus-mark"></td>
            <td class="cell-ui data-cell presence-cell present"></td>
            <td class="cell-ui data-cell presence-cell present"></td>
        </tr>
    </table>
</div>


</body>
</html>

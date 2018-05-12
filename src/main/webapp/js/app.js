$(document).ready(function () {
    //setEventsToTable();
    //ShowMarksTable();

    $(".ch-tab-type").click(function () {

        var val = $(this).attr("value");
        if (val === 's') {
            $("#popup-edit-student").hide();
            $("#popup-add-column").hide();
            $('#statistic-period').daterangepicker({
                locale: {
                    format: 'DD.MM.YYYY'
                }
            });
            $("#popup-statistic-file-window").show();
            $("#statistic-file-window").modal('show');
            return;
        }
        else if (val === 'm') {
            $("#popup-add-column").show();
            $("#popup-edit-student").hide();
        } else if (val === 'e') {
            $("#popup-edit-student").show();
            $("#popup-add-column").hide();
        } else {
            $("#popup-edit-student").hide();
            $("#popup-add-column").hide();
        }
        var cont = $(this).html();
        $("#info-type").html(cont);
        $("#info-type").attr("value", val);
        loadTable();
    });
    $(".ch-tab-gr").click(function () {
        var val = $(this).attr("value");
        $("#subgroup-number").html(val);
        $("#subgroup-number").attr("value", val);
        var group = $(this).parent().parent().parent().children().first().attr("value");
        $("#group-number").html(group);
        $("#group-number").attr("value", group);
        loadTable();
    });

    $("#lab-radio-button").click(function () {
        $("#new-lab-date").show();
    });

    $("#test-radio-button").click(function () {
        $("#new-lab-date").hide();
    });

    loadTable();
    /*$("#popup-add-column").show();
    $("#popup-edit-student").hide();*/
    $("#result-message").hide();

    $("#statistic-tab").click(function () {

    });

});

function isUserLoggedIn() {
    var authFlag = $("#logout").first().length;
    return authFlag == 1;
}

/*functions for forming tables*/
function getSimpleCell(data) {
    var cell = $("<td></td>");
    cell.attr("data-id", data['id']);
    cell.attr("data-type", data['type']);
    cell.attr("class", data['cell-class']);
    var link = data['link'];
    if (link == null || link == "") {
        cell.append(data['value']);
    }
    else {
        var linkTag = $('<a></a>', {href: link, target: "_blank"});
        linkTag.append(data['value']);
        cell.append(linkTag);
    }
    return cell;
}

function getCell(textData) {
    var cell = $("<td nowrap></td>");
    cell.append(textData);
    return cell;
}

function getHeaderCell(name) {
    var cell = $('<th nowrap></th>');
    cell.append(name);
    return cell;
}

function getSimpleRow(data, header, number) {
    var row = $('<tr></tr>');
    row.append(getCell(number));
    header.forEach(function (column) {
        row.append(getSimpleCell(data[column]));
    });
    return row;
}

function getHeaderRow(headerArray) {
    var headerRow = $('<tr style="font-weight: bold;"></tr>');
    headerRow.append(getCell("№"));

    headerArray.forEach(function (column) {
        var t = column.replace(/\d/g, '');
        var n = column.replace(/\D/g, '');

        switch (t){
            case 'student':
                tr = 'студент';
                break;
            case 'lab':
                tr = 'л.р. ' + n;
                break;
            case 'test':
                tr = 'тест ' + n;
                break;
            case 'bonus':
                tr = 'бонус';
                break;
            case 'itog':
                tr = 'итог';
                break;
            default:
                tr = column;
        }
        cell = getHeaderCell(tr);
        if(t == 'lab' && $("#logout").is("li"))
            $(cell).addClass("editable-lab");
        headerRow.append(cell);
    });
    return headerRow;
}



function getTableHeaderCollapse(headerArray) {
    var header = $('<thead class="table-header" style="visibility: collapse;"></thead>');
    header.append(getHeaderRow(headerArray));
    return header;
}

function getTableBodyCollapse(dataArray, headerArray) {
    var body = $('<tbody style="visibility: collapse;"></tbody>');
    dataArray.sort(function (a, b) {
        return a[headerArray[0]]['value'] > b[headerArray[0]]['value'];
    });
    var numberOfRow = 0;
    dataArray.forEach(function (data) {
        numberOfRow++;
        body.append(getSimpleRow(data, headerArray, numberOfRow));
    });
    return body;
}

function getTableHeader(headerArray) {
    var header = $('<thead class="table-header"></thead>');
    header.append(getHeaderRow(headerArray));
    return header;
}

function getTableBody(dataArray, headerArray) {
    var body = $('<tbody></tbody>');
    dataArray.sort(function (a, b) {
        return a[headerArray[0]]['value'] > b[headerArray[0]]['value'];
    });
    var numberOfRow = 0;
    dataArray.forEach(function (data) {
        numberOfRow++;
        body.append(getSimpleRow(data, headerArray, numberOfRow));
    });
    return body;
}

function getHeader(data) {
    var table;
    if (data['args'].length >= 8)
        table = $("<table style=\"margin-bottom: -1; margin-right: 14px;\"></table>");
    else
        table = $("<table style=\"margin-bottom: -1;\"></table>");

    var tableClass = data['table-class'] + " table-fixed";
    table.attr("class", tableClass);
    var header = data['header'];
    var args = data['args'];
    table.append(getTableHeader(header));

    table.append(getTableBodyCollapse(args, header));
    return table;
}

function getSimpleTable(data) {
    var table = $("<table></table>");
    var tableClass = data['table-class'] + " table-fixed";
    table.attr("class", tableClass);
    var header = data['header'];
    var args = data['args'];
    table.append(getTableHeaderCollapse(header));
    table.append(getTableBody(args, header));
    return table;
}

function formPresenceTable(data) {
    //test
    var header = getHeader(data);
    $('#table-header').html(header);

    var table = getSimpleTable(data);
    $('#table-container').html(table);
    setEventsToTable();
}

function formMarkTable(data) {
    //test
    var header = getHeader(data);
    if (isUserLoggedIn()) {
        var th = $('<th style="vertical-align: middle;"></th>');
        var button = $('<button class="btn-linkkk"><span class="glyphicon glyphicon-plus"></span></button>');
        th.append(button);
        button.attr("onclick", "showPopupFormAddColumn()");
        header.children().first().children().first().append(th);
    }
    $('#table-header').html(header);

    var table = getSimpleTable(data);
    if (isUserLoggedIn()) {
        var th = $('<th style="vertical-align: middle;"></th>');
        var button = $('<button class="btn-linkkk"><span class="glyphicon glyphicon-plus"></span></button>');
        th.append(button);
        button.attr("onclick", "showPopupFormAddColumn()");
        table.children().first().children().first().append(th);
    }

    $('#table-container').html(table);
    setEventsToTable();
}

function formEditTable(data) {
    //test
    var header = getHeader(data);
    $('#table-header').html(header);

    var table = getSimpleTable(data);

    /*Check if user logged in. And put button if he did it*/
    if (isUserLoggedIn()) {
        var row = $('<tr></tr>');
        var cell = $('<tb>  </tb>');
        var tb = $('<tb></tb>');
        var button = $('<button class="btn-linkkkk"><span class="glyphicon glyphicon-user"</button>');
        button.attr("onclick", "showEmptyPopupFormEditStudent()");
        cell.append(button);
        row.append(cell);
        row.append(tb);
        table.children().first().next().append(row);
    }

    $('#table-container').html(table);
    setEventsToTable();
}

function loadTable() {
    $("#table-header").html("");
    var div = $('<div style="width: 200px"></div>');
    var loader = $('<div></div>', {class: "loader"});
    div.append(loader)
    $("#table-container").html(div);
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var type = $("#info-type").attr("value");
    var successFunction;
    if (type === "m") {
        successFunction = formMarkTable;
    } else if (type === "e") {
        successFunction = formEditTable;
    } else {
        successFunction = formPresenceTable;
    }
    $.ajax({
        url: "GetTableServlet?group=" + group +
        "&subgroup=" + subgroup +
        "&type=" + type,
        dataType: "json",
        success: function (data) {
            successFunction(data);
        }
    });
}

//for showing window at any position
function showFormWithComment(jQueryObject) {//, xCoord, yCoord) {
    /*$("#class-comment").css("position", "absolute");
    $("#class-comment").css("left", xCoord + "px");
    $("#class-comment").css("top", yCoord + "px");*/

    $("#class-comment").html(jQueryObject);
    $("#add-comment").modal('show');
    $("#comment-text").focus();
}

function requestForSaveComment(commentMessage) {
    $.ajax({
        type: "POST",
        url: "SaveCommentServlet",
        data: "commentId=" + $("#comment-id").val() +
        "&secondCommentId=" + $("#second-comment-id").val() +
        "&type=" + $("#comment-type").val() +
        "&comment=" + commentMessage
    });
}

//save comments from classes
function deleteComment() {
    requestForSaveComment("");
    $("#add-comment").modal('hide');
}

//save comments from classes
function saveComment() {
    requestForSaveComment($("#comment-text").val());
    $("#add-comment").modal('hide');
}

function showComment(comment) {//, xCoord, yCoord) {
    var commentWindow = $("" +
        "<div class=\"popup\" id=\"popup-add-comment\">\n" +
        "    <div id=\"add-comment\" class=\"modal fade\" role=\"dialog\">\n" +
        "        <div class=\"modal-dialog\">\n" +
        "            <div class=\"modal-content center-modal\" style=\"width: 230px;\">\n" +
        "                <div class=\"modal-header\">\n" +
        "                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n" +
        "                    <h4 class=\"modal-title\" style=\"margin-left: 12px;\">" + comment['student']  + "</h4>\n" +
        "                    <h4 class=\"modal-title\">" + comment['description'] + "</h4>\n" +
        "                </div>\n" +
        "                <div class=\"modal-body\" style=\"padding: 5px;\">\n" +
        "                    <input type=\"hidden\" id=\"comment-id\" value=\"" + comment['commentId'] + "\">\n" +
        "                    <input type=\"hidden\" id=\"second-comment-id\" value=\"" + comment['secondCommentId'] + "\">\n" +
        "                    <input type=\"hidden\" id=\"comment-type\" value=\"" + comment['type'] + "\">\n" +
        "                    <form class=\"form-horizontal\" style=\"margin: 10px;\">\n" +
        "                        <textarea class=\"form-control\" id=\"comment-text\" rows=\"3\">" + comment['comment'] + "</textarea>\n" +
        "                    </form>\n" +
        "                </div>\n" +
        "                <div class=\"modal-footer \" style=\"padding-top: 5px; padding-bottom: 5px; padding-right: 15px; position: center\">\n" +
        "                    <div class=\"btn-group btn-group-toggle\">\n" +
        "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\"\n" +
        "                                onclick=\"deleteComment()\">Удалить\n" +
        "                        </button>\n" +
        "                        <button id=\"save-comment\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"\n" +
        "                                onclick=\"saveComment()\">Сохранить\n" +
        "                        </button>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>");


    showFormWithComment(commentWindow);//, xCoord, yCoord);
    $("#comment-text").focus();
}

//save issued lab edited by lecturer
function saveEditedIssuedLab(){
    var id = $("#id-of-edited-lab").val();
    var coef = $("#current-coef-of-lab").val();

    $.ajax({
        url: "/SaveEditedIssuedLab?" +
        "id=" + id +
        "&coef=" + coef
    });

    $("#edit-issued-lab").modal('hide');
}


//events for table with marks or presence
function setEventsToTable() {

    //Event for click at button
    $("button.add-col-button").click(function () {

        ShowCreateMarkFieldWind();
    });


    function requestForComment(type, commentId, secondCommentId) {
        //Запрос на сервер за старым комментарием
        $.ajax({
            url: "GetComment",
            data: "commentId=" + commentId +
            "&secondCommentId=" + secondCommentId +
            "&type=" + type,
            dataType: "json",
            success: showComment//, xCoord, yCoord)
        });

        //showCommentAtClass("Julia is cool");
    }

    //click left button at mouse
    $(".bonus-mark-cell.editable").contextmenu(function (e) {
        requestForComment("bonus", $(this).attr("data-id"), "");
        return false;
    });
    $(".bonus-mark-cell.editable").keypress(function(e){
        if(e.keyCode==13) {
            $("input:focus").blur();
            $("input[type='text']:focus").blur();
        }
    });

    //click left button at mouse
    $(".test-mark-cell.editable").contextmenu(function (e) {
        requestForComment("test", $(this).attr("data-id"), "");
        return false;
    });
    $(".test-mark-cell.editable").keypress(function(e){
        if(e.keyCode==13) {
            $("input:focus").blur();
            $("input[type='text']:focus").blur();
        }
    });

    //click left button at mouse
    $(".lab-mark-cell.editable").contextmenu(function (e) {
        requestForComment("lab", $(this).attr("data-id"), "");
        return false;
    });
    $(".lab-mark-cell.editable").keypress(function(e){
        if(e.keyCode==13) {
            $("input:focus").blur();
            $("input[type='text']:focus").blur();
        }
    });

    //click left button at mouse
    $(".presence-cell.editable").contextmenu(function (e) {
        requestForComment("class",
            $(this).parent().children().first().next().attr("data-id"),
            $(this).attr("data-id"));
        return false;
    });

    function showFormWithEditingOfIssuedLab(data) {
        $("#id-of-edited-lab").val(data['id']);
        $("#edited-lab-description").text(data['lab']);
        $("#current-coef-of-lab").val(data['coef']);
        $("#edit-issued-lab").modal('show');
    }

    function requestForCoefficient(group, subgroup, lab){
        $.ajax({
            url: "/GetCoefficientOfLab?" +
            "group=" + group +
            "&subgroup=" + subgroup +
            "&lab=" + lab,
            dataType: "json",
            success: function (data) {
                showFormWithEditingOfIssuedLab(data);
            }
        });
    }

///////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //click left button at mouse
    $(".editable-lab").click(function (e) {
        var group = $("#group-number").text().replace(/\D/g, '');
        var subgroup = $("#subgroup-number").text().replace(/\D/g, '');
        var lab = $(this).text();
        requestForCoefficient(group, subgroup, lab);
    });

    //Event for click at cell with presence
    $(".presence-cell.editable").click(function () {

        var studentId = $(this).parent().children().first().next().attr("data-id");
        var classId = $(this).attr("data-id");

        if ($(this).css("background-color") == "rgb(194, 255, 10)") {   //if student is absent
            $(this).css("background", "rgb(255, 218, 6)");
            $(this).html("н");
            $.ajax({
                url: "NoteStudentAbsence?" +
                "&classId=" + classId +
                "&studentId=" + studentId
            });
        } else {                                                         //if - precent
            $(this).css("background", "rgb(194, 255, 10)");
            $(this).html("");
            $.ajax({
                url: "NoteStudentPresence?" +
                "&classId=" + classId +
                "&studentId=" + studentId
            });
        }
    });

    //event for edit conent of cell with marks
    $(".mark-cell.editable").click(function (e) {
        //alert(this.className);

        e = e || window.event;
        var t = e.target || e.srcElement;
        var elm_name = t.tagName.toLowerCase();
        if (elm_name == 'input') {
            return false;
        }
        // alert(elm_name);

        var val = $(this).html();
        var code = '<input type="text" class="edit" value="' + val + '"/>';
        $(this).html(code);
        $(".edit").focus();

        $(".edit").blur(function () {
            var val = $(".edit").val();
            var parent = $(".edit").parent();
            var data_id = parent.attr("data-id");
            var data_type = parent.attr("data-type");
            //////Нужна другая проверка в!!!!!
            if (isNaN(val))
                val = "";
            parent.html(val);
            $.ajax({
                url: "SaveMarkServlet?id=" + data_id +
                "&type=" + data_type +
                "&value=" + val
            });
        });
    });
    ////event function for click at cell whith student name
    $("td.cell-ui.stud-name.data-cell").click(function () {
        clickAtCellWithStudName(event);
    });
    $("#stud-inf-wind").mouseleave(function () {
        HideStudentInformationWind();
    });

    ////////////Редактируй. Нерабочий кусок кода. Для склика поя чейке таблице редактирования
    $(".cell-ui.info-cell-editable").click(function (event) {
        showPopupFormEditStudent(event);
    });
}


function createStatisticFile() {
    var group = $("#statistic-group-number").val();
    var dates = $("#statistic-period").val().split(' - ');
    //var till =
    window.open('./CreateStatisticFile?' + "group=" + group +
        "&from=" + dates[0] +
        "&till=" + dates[1]);

    $("#statistic-file-window").modal('hide');
}

/*
    functions for showing popup-add-column form: after pressing button "Добавить"
 */

function formAndShowPopupFormAddColumn(data) {
    $("#new-column-type").html("");

    $("#new-lab-number").html(data['lab-number']);
    $("#new-test-number").html(data['test-number']);

    /* var labOption = $("<option></option>");
     labOption.attr("value", "lab");
     labOption.html("Лабораторная работа " + data['lab-number']);
     $("#new-column-type").append(labOption);

     var testOption = $("<option></option>");
     testOption.attr("value", "test");
     testOption.html("Контрольная работа " + data['test-number']);
     $("#new-column-type").append(testOption);*/

    $("#new-lab-date").html("");
    data['dates'].forEach(function (date) {
        var select = $("<option></option>");
        select.attr("value", date);
        select.html(date);
        $("#new-lab-date").append(select);
    });

    $("#addLabTest").modal('show');
    //disablePageEvents();
}

function showPopupFormAddColumn() {
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    $.ajax({
        url: "/GetClassesDatesLabAndTestNumber?" +
        "group=" + group +
        "&subgroup=" + subgroup,
        dataType: "json",
        success: function (data) {
            formAndShowPopupFormAddColumn(data);
        }
    });
}

function cancelPopupFormAddColumn() {
    $("#popup-form-add-column").removeClass("show");
    enablePageEvents();
}

function errorAddLabOrTestButton() {

}

function succesAddLabOrTestButton() {
    loadTable();
    setEventsToTable();
}

function addLabOrTestButton() {
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var type = $('#choose-column-type input:radio:checked').val();
    var date = $("#new-lab-date").val();
    var comment = $("#comment-text-area").val();

    $.post(
        "AddLabOrTestServlet",
        {
            group: group,
            subgroup: subgroup,
            type: type,
            date: date,
            comment: comment
        },
        succesAddLabOrTestButton
    );
    //cancelPopupFormAddColumn();

    /*$.ajax({
        url: "AddLabOrTestServlet?group=" + group +
        "&subgroup=" + subgroup +
        "&type=" + type +
        "&date=" + date,
        dataType: "json",
        success: function(data){
            succesAddLabOrTestButton();
        }
    });*/
}

/*Functions for disable and enable page*/

function disablePageEvents() {
    $("#navbar").addClass("disabled");
    $("#table-container").addClass("disabled");
    $("#table-title").addClass("disabled");
}

function enablePageEvents() {
    $("#navbar").removeClass("disabled");
    $("#table-container").removeClass("disabled");
    $("#table-title").removeClass("disabled");
}

/*
    function for showing popup-edit-student form: after pressing button "Добавить студента"
 */

function formAndShowPopupFormEditStudent(student) {
    $("#student-id").attr("value", student['id']);
    $("#student-name").val(student['name']);
    $("#student-surname").val(student['surname']);
    $("#student-eMail").val(student['eMail']);
    $("#student-git").val(student['git']);
    if(student['type'] == "add"){
        $("#popup-edit-student-title").html("Добавить студента");
        $("#student-btn-group").html("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"\n" +
            "                                    onclick=\"saveStudentButtonClick()\">Сохранить\n" +
            "                            </button>");
    } else {
        $("#popup-edit-student-title").html(student['surname'] + " " + student['name']);
        $("#student-btn-group").html("<button id=\"delete-student-button\" type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\"\n" +
            "                                    onclick=\"deleteStudentButtonClick()\">Удалить\n" +
            "                            </button>\n" +
            "                            <button id=\"save-student-button\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"\n" +
            "                                    onclick=\"saveStudentButtonClick()\">Сохранить\n" +
            "                            </button>")
    }
    $("#popup-form-edit-student").modal('show');
    $("#student-surname").focus();
    //disablePageEvents();
}

function showEmptyPopupFormEditStudent() {
    var student = {};
    student['id'] = "";
    student['name'] = "";
    student['surname'] = "";
    student['eMail'] = "";
    student['git'] = "";
    student['type'] = "add";
    $("#delete-student-button").hide();
    formAndShowPopupFormEditStudent(student);

}

function showPopupFormEditStudent(event) {
    var row = $(event.target).parent();
    var children = row.children();
    var student = {};
    var nameAndSurname = children.first().next().html().split(" ");
    student['surname'] = nameAndSurname[0];
    student['name'] = nameAndSurname[1];
    /////??????????????????????????????
    student['id'] = children.first().next().attr("data-id");
    student['eMail'] = children.first().next().next().html();
    student['git'] = children.first().next().next().next().html();
    student['type'] = "edit";
    $("#delete-student-button").show();
    formAndShowPopupFormEditStudent(student);
}


function cancelPopupFormEditStudent() {
    //$("#popup-form-edit-student").removeClass("show");

    enablePageEvents();
}

function errorDeleteStudent() {

}


function successDeleteStudent() {
    loadTable();
    setEventsToTable();
    // cancelPopupFormEditStudent();
}

function deleteStudentButtonClick() {
    var studentId = $("#student-id").attr("value");
    $.ajax({
        url: "DeleteStudent?" +
        "studentId=" + studentId,
        success: function (data) {
            successDeleteStudent();
        }
    });
    //cancelPopupFormEditStudent();
}

function successSaveStudent() {
    loadTable();
    setEventsToTable();
}

function errorSaveStudent() {

}

function saveStudentButtonClick() {
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var studentName = $("#student-name").val();
    var studentSurname = $("#student-surname").val();
    var studentId = $("#student-id").attr("value");
    var studentGit = $("#student-git").val();
    var studentEmail = $("#student-eMail").val();

    $.post(
        "SaveStudent",
        {
            group: group,
            subgroup: subgroup,
            name: studentName,
            surname: studentSurname,
            studentId: studentId,
            git: studentGit,
            email: studentEmail
        },
        successSaveStudent
    );
}

function successDoLoginServlet(data) {
    var result = JSON.parse(data);
    if (result['code'] == "0") {
        window.location = "/Welcome";
    }
    else {
        $("#result-message").val(result['message']);
        $("#result-message").html(result['message'])
        $("#result-message").show();
    }
}

function loginButtonClick() {
    var username = $("#login-email").val();
    var password = $("#login-password").val();

    $.post(
        "DoLoginServlet",
        {
            username: username,
            password: password
        },
        successDoLoginServlet
    );
}

function hideResultMessage() {
    var username = $("#login-email").val("");
    var password = $("#login-password").val("");
    $("#result-message").hide();
}

function showLoginModal() {
    $('#popup-login').modal('show');
    $('#login-email').focus();
}


$(document).ready(function(){
    $('#login-email').keypress(function(e){
        if(e.keyCode==13)
            $('#login_button').click();
    });
    $('#login-password').keypress(function(e){
        if(e.keyCode==13)
            $('#login_button').click();
    });
    $('#login-email').keypress(function(e){
        if(e.keyCode==27)
            $('#login_hibe').click();
    });
    $('#login-password').keypress(function(e){
        if(e.keyCode==27)
            $('#login_hibe').click();
    });


    $('#student-name').keypress(function(e){
        if(e.keyCode==13)
            $('#save-student-button').click();
    });
    $('#student-surname').keypress(function(e){
        if(e.keyCode==13)
            $('#save-student-button').click();
    });
    $('#student-eMail').keypress(function(e){
        if(e.keyCode==13)
            $('#save-student-button').click();
    });
    $('#student-git').keypress(function(e){
        if(e.keyCode==13)
            $('#save-student-button').click();
    });
    $('#student-name').keypress(function(e){
        if(e.keyCode==27)
            $('#save-student-hibe').click();
    });
    $('#student-surname').keypress(function(e){
        if(e.keyCode==27)
            $('#save-student-hibe').click();
    });
    $('#student-eMail').keypress(function(e){
        if(e.keyCode==27)
            $('#save-student-hibe').click();
    });
    $('#student-git').keypress(function(e){
        if(e.keyCode==27)
            $('#save-student-hibe').click();
    });
});

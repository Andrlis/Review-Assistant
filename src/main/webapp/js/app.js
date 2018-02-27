$(document).ready( function () {
    //setEventsToTable();
    //ShowMarksTable();

    $(".ch-tab-type").click(function () {
        var val = $(this).attr("value");
        var cont = $(this).html();
        $("#info-type").html(cont);
        $("#info-type").attr("value", val);
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
        else if (val === 'm')
        {
            $("#popup-add-column").show();
            $("#popup-edit-student").hide();
        } else if (val === 'e')
        {
            $("#popup-edit-student").show();
            $("#popup-add-column").hide();
        } else
        {
            $("#popup-edit-student").hide();
            $("#popup-add-column").hide();
        }
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

    $("#lab-radio-button").click(function() {
        $("#new-lab-date").show();
    });

    $("#test-radio-button").click(function() {
        $("#new-lab-date").hide();
    });

    loadTable();
    /*$("#popup-add-column").show();
    $("#popup-edit-student").hide();*/
    $("#result-message").hide();

    $("#statistic-tab").click(function() {

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
    if (link == null || link == "")
    {
        cell.append(data['value']);
    }
    else {
        var linkTag = $('<a></a>', {href : link, target : "_blank"});
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
        headerRow.append(getHeaderCell(column));
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
    dataArray.sort(function(a, b) {
        return a[headerArray[0]]['value'] > b[headerArray[0]]['value'];
    });
    var numberOfRow = 0;
    dataArray.forEach(function (data) {
        numberOfRow ++;
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
    dataArray.sort(function(a, b) {
        return a[headerArray[0]]['value'] > b[headerArray[0]]['value'];
    });
    var numberOfRow = 0;
    dataArray.forEach(function (data) {
        numberOfRow ++;
        body.append(getSimpleRow(data, headerArray, numberOfRow));
    });
    return body;
}

function getHeader(data) {
    var table ;
    if(data['args'].length >= 8)
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
        success: function(data){
            successFunction(data);
        }
    });
}



//for showing window at any position
function showObjectAtAnyPosition(jQueryObject, xCoord, yCoord) {
    jQueryObject.css("left", xCoord + "px");
    jQueryObject.css("top", yCoord + "px");
    jQueryObject.css("z-index", "100");
    alert("before");
    $("#classComment").html(jQueryObject);
    alert("after");
}

//save comments from classes

function saveCommentFromClass() {
    alert("save");
}

function showCommentAtClass(comment, xCoord, yCoord) {
    var commentWindow = $("" +
        "<form id=\"commentForm\" action=\"saveCommentFromClass\">\n" +
        "  <p><textarea name=\"comment\"></textarea></p>\n" +
        "  <p><input type=\"submit\" name='Сохранить' value='" +
        comment +
        "'></p>\n" +
        " </form>");

    showObjectAtAnyPosition(commentWindow, xCoord, yCoord);
    alert("lol");
}


//events for table with marks or presence
function setEventsToTable() {


    //Event for click at button
    $("button.add-col-button").click(function () {
        
        ShowCreateMarkFieldWind();
    });

    //click left button at mouse
    $(".presence-cell.editable").contextmenu(function (e) {

        var studentId = $(this).parent().children().first().next().attr("data-id");
        var classId = $(this).attr("data-id");
        showCommentAtClass(
            "Julia is cool",
            event.clientX,
            event.clientY);

        //Запрос на сервер за старым комментарием
        /*$.ajax({
            url: "   ?" +
            "&classId=" + classId +
            "&studentId=" + studentId,
            success: function(data){
                showCommetAtClass(comment, xCoord, yCoord)
            }
        });*/


        return false;
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
                val="";
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

/*
    functions for showing popup-add-column form: after pressing button "Добавить"
 */

function formAndShowPopupFormAddColumn(data)
{
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

function showPopupFormAddColumn()
{
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    $.ajax({
        url: "/GetClassesDatesLabAndTestNumber?" +
        "group=" + group +
        "&subgroup=" + subgroup,
        dataType: "json",
        success: function(data){
            formAndShowPopupFormAddColumn(data);
        }
    });
}

function cancelPopupFormAddColumn()
{
    $("#popup-form-add-column").removeClass("show");
    enablePageEvents();
}

function errorAddLabOrTestButton()
{

}

function succesAddLabOrTestButton()
{
    loadTable();
    setEventsToTable();
}

function addLabOrTestButton()
{
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

function disablePageEvents()
{
    $("#navbar").addClass("disabled");
    $("#table-container").addClass("disabled");
    $("#table-title").addClass("disabled");
}

function enablePageEvents()
{
    $("#navbar").removeClass("disabled");
    $("#table-container").removeClass("disabled");
    $("#table-title").removeClass("disabled");
}

/*
    function for showing popup-edit-student form: after pressing button "Добавить студента"
 */

function formAndShowPopupFormEditStudent(student)
{
    $("#student-id").attr("value", student['id']);
    $("#student-name").val(student['name']);
    $("#student-surname").val(student['surname']);
    $("#student-eMail").val(student['eMail']);
    $("#student-git").val(student['git']);
    $("#popup-form-edit-student").modal('show');
    //disablePageEvents();
}

function showEmptyPopupFormEditStudent()
{
    var student = {};
    student['id'] = "";
    student['name'] = "";
    student['surname'] = "";
    student['eMail'] = "";
    student['git'] = "";
    $("#delete-student-button").hide();
    formAndShowPopupFormEditStudent(student);

}

function showPopupFormEditStudent(event)
{
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
    $("#delete-student-button").show();
    formAndShowPopupFormEditStudent(student);
}

function cancelPopupFormEditStudent()
{
    //$("#popup-form-edit-student").removeClass("show");

    enablePageEvents();
}

function errorDeleteStudent()
{

}


function successDeleteStudent()
{
    loadTable();
    setEventsToTable();
   // cancelPopupFormEditStudent();
}

function deleteStudentButtonClick()
{
    var studentId = $("#student-id").attr("value");
    $.ajax({
        url: "DeleteStudent?" +
        "studentId=" + studentId,
        success: function(data){
            successDeleteStudent();
        }
    });
    //cancelPopupFormEditStudent();
}

function successSaveStudent()
{
    loadTable();
    setEventsToTable();
}

function errorSaveStudent()
{

}

function saveStudentButtonClick()
{
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






$(document).ready(function () {
    setEventsToTable();
    //ShowMarksTable();

    $(".ch-tab-type").click(function () {
        var val = $(this).attr("value");
        var cont = $(this).html();
        $("#info-type").html(cont);
        $("#info-type").attr("value", val);
        if (val === 'm')
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
        var group = $(this).parent().parent().children().first().attr("value");
        $("#group-number").html(group);
        $("#group-number").attr("value", group);
        loadTable();
    });

    loadTable();
    $("#popup-add-column").show();
    $("#popup-edit-student").hide();
});

//Function onchange for select new-column-type
function changeNewColumnType()
{
    var type = $("#new-column-type").val();
    if (type==="test")
        $("#new-lab-date").hide();
    else
        $("#new-lab-date").show();
}

function formTable(data) {
    var table = $("<table></table>");
    var tableClass = data['table-class'];
    table.attr("class", tableClass);
    var header = data['header'];
    var args = data['args'];
    var headerRow = $('<tr class="header"></tr>');
    header.forEach(function (column) {
        headerRow.append("<td>" + column + "</td>");
    });
    table.append(headerRow);
    args.forEach(function (data) {
        var row = $('<tr></tr>');
        header.forEach(function (column) {
            var cell = $("<td></td>");
            cell.attr("data-id", data[column]['id']);
            cell.attr("data-type", data[column]['type']);
            cell.attr("class", data[column]['cell-class']);
            cell.append(data[column]['value']);
            row.append(cell);
            //row.append("<td>" + data[column]['value'] + "</td>");
        });
        table.append(row);
    });
    $('#table-container').html(table);
    setEventsToTable();
}

function loadTable() {
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var type = $("#info-type").attr("value");
    $.ajax({
        url: "GetTableServlet?group=" + group +
        "&subgroup=" + subgroup +
        "&type=" + type,
        dataType: "json",
        success: function(data){
            formTable(data);
        }
    });
}


//events for table with marks or presence
function setEventsToTable() {


    //Event for click at button
    $("button.add-col-button").click(function () {
        ShowCreateMarkFieldWind();
    });

    //Event for click at cell with presence
    $(".presence-cell.editable").click(function () {
        var group = $("#group-number").attr("value");
        var subgroup = $("#subgroup-number").attr("value");
        var studentId = "";
        var classId = "";

        if ($(this).css("background-color") == "rgb(194, 255, 10)") {   //if student is absent
            $(this).css("background", "rgb(255, 218, 6)");
            $(this).html("н");
            $.ajax({
                url: "NoteStudentAbsence?" +
                "group=" + group +
                "&subgroup=" + subgroup +
                "&classId=" + classId +
                "&studentId=" + studentId
            });
        } else {                                                         //if - precent
            $(this).css("background", "rgb(194, 255, 10)");
            $(this).html("");
            $.ajax({
                url: "NoteStudentPresence?" +
                "group=" + group +
                "&subgroup=" + subgroup +
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

function formAndShowPopupFormAddColumn(dates)
{
    $("#new-lab-date").html("");
    dates.forEach(function (date) {
        var select = $("<option></option>");
        select.attr("value", date);
        select.html(date);
        $("#new-lab-date").append(select);
    });

    $("#popup-form-add-column").addClass("show");
    disablePageEvents();
}

function showPopupFormAddColumn()
{
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    $.ajax({
        url: "/GetClassesDate?" +
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

}

function addLabOrTestButton()
{
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var type = $("#new-column-type").val();
    var date = $("#new-lab-date").val();
    var comment = $("#comment-text-area").val();

    $.post(
        "AddLabOrTestServlet",
        {
            group:'"' + group + '"',
            subgroup:'"' + subgroup + '"',
            type:'"' + type + '"',
            date:'"' + date + '"',
            comment:'"' + comment + '"'
        },
        succesAddLabOrTestButton
    );
    enablePageEvents();
    loadTable();
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
    $("#student-name").attr("value", student['name']);
    $("#student-surname").attr("value", student['surname']);
    $("#student-eMail").attr("value", student['eMail']);
    $("#student-git").attr("value", student['git']);
    $("#popup-form-edit-student").addClass("show");
    disablePageEvents();
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
    var nameAndSurname = children.first().html().split(" ");
    student['surname'] = nameAndSurname[0];
    student['name'] = nameAndSurname[1];
    student['id'] = "";
    student['eMail'] = children.first().next().html();
    student['git'] = children.first().next().next().html();
    $("#delete-student-button").show();
    formAndShowPopupFormEditStudent(student);
}

function cancelPopupFormEditStudent()
{
    $("#popup-form-edit-student").removeClass("show");
    setEventsToTable();
    enablePageEvents();
}

function errorDeleteStudent()
{

}


function successDeleteStudent()
{

}

function deleteStudentButtonClick()
{
    var studentId = $("#student-id").html();
    $.ajax({
        url: "DeleteStudent?" +
        "studentId=" + studentId,
        success: function(data){
            successDeleteStudent(); 
        }
    });
    enablePageEvents();
    loadTable();
}

function successSaveStudent()
{

}

function errorSaveStudent()
{

}

function saveStudentButtonClick()
{
    var group = $("#group-number").attr("value");
    var subgroup = $("#subgroup-number").attr("value");
    var studentName = $("#student-name").html();
    var studentSurname = $("#student-surname").html();
    var studentId = $("#student-id").html();
    var studentGit = $("#student-git").html();
    var studentEmail = $("#student-eMail").html();

    $.post(
        "SaveStudent",
        {
            group:'"' + group + '"',
            subgroup:'"' + subgroup + '"',
            name:'"' + studentName + '"',
            surname:'"' + studentSurname + '"',
            studentId:'"' + studentId + '"',
            git:'"' + studentGit +'"',
            email:'"' + studentEmail + '"'
        },
        successSaveStudent
    );
    enablePageEvents();
    loadTable();
}


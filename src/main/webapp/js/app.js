$(document).ready(function () {
    setEventsToTable();
    //ShowMarksTable();

    $(".ch-tab-type").click(function () {
        var val = $(this).attr("value");
        var cont = $(this).html();
        $("#info-type").html(cont);
        $("#info-type").attr("value", val);
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

    ////////Верни строку!!!!!!!!!!!!
    //loadTable();
});


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
        //var ref = $('<a href="userForm?id=${user.user}">$</a>');
        // row.append("<td><a href=\"userForm?id=" + user["user"] + "\">" + user["user"] + "</a></td>");
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
    $("td").click(function (event) {
        var type = $(this).attr("data-type");
        var id = $(this).attr("data-id");
        var student_id = $(this).parent().children().first().attr("data-id");

    });
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
    $(".presence-cell.data-cell").click(function () {
        //alert(this.className);

        if ($(this).css("background-color") == "rgb(194, 255, 10)") {   //if precent
            $(this).css("background", "rgb(255, 218, 6)");
            $(this).html("н");
        } else {                                                         //if absent
            $(this).css("background", "rgb(194, 255, 10)");
            $(this).html("");
        }
    });

    //event for edit conent of cell with marks
    $(".editable").click(function (e) {
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
}

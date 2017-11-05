$(document).ready(function () {
    setEventsToTable();
    ShowMarksTable();
});

//events for table with marks or presence
function setEventsToTable() {
    ShowMarksTable();

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
            $(".edit").parent().html(val);
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

function ShowMarksTable() {
    $(".bonus-mark").show();
    $(".test-mark").show();
    $(".lab-mark").show();
    $(".button-cell-div").show();
    $(".presence-cell").hide();
}
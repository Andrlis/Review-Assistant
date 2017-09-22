
//function will be called when page is loaded
$(document).ready(function () {
    changeGroupNumberTitle();

    loadTables();

    //jQuery tabs
    $("#subgroup-tabs").tabs({
        select: function(event, ui) {
            $("#subgroup-number").html(ui.index + 1);
            //loadTable();
        }
    });

    //jQuery buttonset
    $("#hor-buttonset").buttonset();

    $("#presence-button").click(function () {
        ShowClassesTable();
    });

    $("#marks-button").click(function () {
        ShowMarksTable();
    });

    //Event for ENTER key
    $(window).keydown(function(event){
        var enterCode = 13;
        if(event.keyCode == enterCode) {
            $('.edit').blur();
        }
    });

    $("#cancel-button-mw").click(function () {
        HideCreateMarkFieldWind();
    });

    //event for combo-box at modal window, where user chooses type of new column
    $("#mark-type-combo-box").change(function () {
       CheckLabOrTestMarkEvent();
    });

    //event for combo-box at main page, whare user chooses number of group to view
    $("#group-combo-box").change(function () {
        changeGroupNumberTitle();
        loadTables();
    });
});

//reaction if document with table isn't found at table webapp/folders
function tableNotFound(xhr, divId) {
    var msg = "Sorry! There's an error: ";
    $(divId).html( msg + xhr.status + " " + xhr.statusText );
}

//it loads table to divs at tabs of subgroups
function loadTables() {
    var sel = document.getElementById("group-combo-box");
    var group = sel.options[sel.selectedIndex].text;

    $("#subgroup-1").load("../tables/marks_and_classes/table-" + group + "-1.html", function( response, status, xhr ) {
        //setEventsToTable();
        if ( status == "error" )
            tableNotFound(xhr, "#subgroup-1");
    });

    $("#subgroup-2").load("../tables/marks_and_classes/table-" + group + "-2.html", function (response, status, xhr) {
        if ( status == "error" )
            tableNotFound(xhr, "#subgroup-2");
        setEventsToTable();
    });
    ShowMarksTable();
}

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

function changeGroupNumberTitle() {
    var sel = document.getElementById("group-combo-box");
    var val = sel.options[sel.selectedIndex].text;
    $("#group-number").html(val);
}

function ShowStudentInformationWind(xCoord, yCoord, studFullName, eMailLink, gitHubRepoLink) {

    if (studFullName == undefined || studFullName == "") {
        $("#mw-stud-name").html("<i>No information</i>");
    } else {
        $("#mw-stud-name").html(studFullName);
    }

    if (eMailLink == undefined || eMailLink == "") {
        $("#mw-eMail-ln").html("<i>No information</i>")
            .removeAttr("href");
    } else {
        $("#mw-eMail-ln").html(eMailLink);
        $("#mw-eMail-ln").attr("href", "mailto:" + eMailLink);
    }

    if (gitHubRepoLink == undefined || gitHubRepoLink == "") {
        $("#mw-gitHub-ln").html("<i>No information</i>")
            .removeAttr("href");
    } else {
        $("#mw-gitHub-ln").html(gitHubRepoLink)
            .attr("href", gitHubRepoLink);
    }

    document.getElementById("stud-inf-wind").style.left = xCoord + "px";
    document.getElementById("stud-inf-wind").style.top = yCoord - 70 + "px";
    $("#stud-inf-wind").show();
}

function HideStudentInformationWind() {
    $("#stud-inf-wind").hide();
}

function ShowCreateMarkFieldWind(xCoord, yCoord) {
    document.getElementById("create-mark-field-wind").style.left = xCoord + "px";
    document.getElementById("create-mark-field-wind").style.top = yCoord + "px";
    $("#create-mark-field-wind").show();
}

function HideCreateMarkFieldWind() {
    $("#create-mark-field-wind").css("display", "none");
}

function CheckLabOrTestMarkEvent() {
    if ($("#mark-type-combo-box").val() == "lab")
        $("#date-of-lab-mw").css("display", "block");
    else $("#date-of-lab-mw").css("display", "none");
}

function ShowMarksTable() {
    $(".bonus-mark").show();
    $(".test-mark").show();
    $(".lab-mark").show();
    $(".button-cell-div").show();
    $(".presence-cell").hide();
}

function ShowClassesTable() {
    $(".presence-cell").show();
    $(".bonus-mark").hide();
    $(".test-mark").hide();
    $(".lab-mark").hide();
    $(".button-cell-div").hide();
}

//event function for click at cell whith student name
function clickAtCellWithStudName(event) {
    var elem =  jQuery((event.target || event.srcElement).parentNode);
    ShowStudentInformationWind(
        event.clientX,
        event.clientY,
        elem.children(".stud-name").html(),
        elem.children(".stud-eMail-ln").html(),
        elem.children(".stud-gitHub-ln").html()
    );
}
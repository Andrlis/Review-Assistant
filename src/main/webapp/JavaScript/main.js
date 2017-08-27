$(document).ready(function () {
    changeGroupNumberTitle();
    ShowMarksTable();


    $("#subgroup-tabs").tabs({
        select: function(event, ui) {
            $("#subgroup-number").html(ui.index + 1);
        }
    });

    $("#hor-buttonset").buttonset();
    $("#presence-button").click(function () {
        //ShowStudentInformationWind(550, 500, "Julia Runova", "vk.com", "vk.com");
        ShowClassesTable();
    });
    $("#marks-button").click(function () {
        //HideStudentInformationWind();
        ShowMarksTable();
    });

    //event for edit conent of cell with marks
    $(".editable").click(function (e) {

        var t = e.target || e.srcElement;
        var elm_name = t.tagName.toLowerCase();
        if(elm_name == 'input')	{return false;}

        var val = $(this).html();
        var code = '<input type="text" id="edit" pattern="\d{1,5}" value="' + val + '"/>';
        $(this).html(code);
        $("#edit").focus();

        $("#edit").blur(function () {
            var val = $("#edit").val();
            $("#edit").parent().html(val);
        });
    });

    //Event for ENTER key
    $(window).keydown(function(event){
        var enterCode = 13;
        if(event.keyCode == enterCode) {
            $('#edit').blur();
        }
    });

    $("#cancel-button-mw").click(function () {
        HideCreateMarkFieldWind();
    });

    $("#mark-type-combo-box").change(function () {
       CheckLabOrTestMarkEvent();
    });

    $("#group-combo-box").change(function () {
        changeGroupNumberTitle();
    });

    $("#button-enter").click(function () {
       ShowCreateMarkFieldWind(30, 30);
    });

    $("button.add-col-button").click(function () {
        ShowCreateMarkFieldWind();
    });

    $(".presence-cell.data-cell").click(function () {

        if ($(this).css("background-color") == "rgb(194, 255, 10)") {   //if precent
            $(this).css("background", "rgb(255, 218, 6)");
            $(this).html("н");
        } else {                                                         //if absent
            $(this).css("background", "rgb(194, 255, 10)");
            $(this).html("");
        }
    });
/*
    $(".present").click(function (event) {
        //event = event || window.event;
        //var element = event.target||event.srcElement;
        $(this).html("н");
        //$(this).attr("class", "absent");
        $(this).removeClass("present");
        $(this).addClass("absent");
    });

    $(".absent").click(function (event) {
        //event = event || window.event;
        //var element = event.target||event.srcElement;
        $(this).html("");
        $(this).removeClass("absent");
        $(this).addClass("present");
    });*/

   // $("#information-part").hide();
    //(".stud-name").hide();
});

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
        $("#mw-eMail-ln").html("<i>No information</i>");
    } else {
        $("#mw-eMail-ln").html(eMailLink);
        $("#mw-eMail-ln").attr("href", "mailto:" + eMailLink);
    }

    if (gitHubRepoLink == undefined || gitHubRepoLink == "") {
        $("#mw-gitHub-ln").html("<i>No information</i>");
    } else {
        $("#mw-gitHub-ln").html(gitHubRepoLink);
        $("#mw-gitHub-ln").attr("href", gitHubRepoLink);
    }

    document.getElementById("stud-inf-wind").style.left = xCoord + "px";
    document.getElementById("stud-inf-wind").style.top = yCoord + "px";
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
    $("#create-mark-field-wind").hide();
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
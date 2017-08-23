$(document).ready(function () {
    changeGroupNumberTitle();

    $("#subgroup-tabs").tabs({
        select: function(event, ui) {
            $("#subgroup-number").html(ui.index + 1);
        }
    });

    $("#hor-buttonset").buttonset();
    $("#presence-button").click(function () {
        ShowStudentInformationWind(550, 500, "Julia Runova", "vk.com", "vk.com");
    });
    $("#marks-button").click(function () {
        HideStudentInformationWind();
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

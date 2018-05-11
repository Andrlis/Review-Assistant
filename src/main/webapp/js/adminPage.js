function redirection() {
    window.location = "/Welcome"
}

function showPopupFormEditGroup(groupNumber) {
    $("#group_number").val(groupNumber);
    $("#new_group_number").val(groupNumber);
    if(groupNumber == ''){
        $("#group-window-title").html("Добавление группы");
    } else {
        $("#group-window-title").html("Группа " + groupNumber);
    }

    $("#popup-edit-group").modal('show');
}

function showPopupFormRemove(number) {
    $("#number").val(number);
    $("#popup-delete").modal('show');
}

function showPopupFormEditSubGroup(groupSubgroup) {
    $("#id-gr-sub-file").val(groupSubgroup);
    $("#id-gr-sub-lec").val(groupSubgroup);
    if(groupSubgroup.split("_").length == 2){
        $("#subgroup-window-title").html("Редактирование: группа " + groupSubgroup.split("_")[0] + " подгруппа " + groupSubgroup.split("_")[1]);
    } else {
        $("#subgroup-window-title").html("Добавление подгруппы: группа " + groupSubgroup);
    }
    $("#popup-edit-subgroup").modal('show');
}




function redirection() {
    window.location = "/Welcome"
}

function showPopupFormEditGroup(groupNumber) {
    $("#group_number").val(groupNumber);
    $("#new_group_number").val(groupNumber);
    $("#popup-edit-group").modal('show');
}

function showPopupFormRemove(number) {
    $("#number").val(number);
    $("#popup-delete").modal('show');
}

function showPopupFormEditSubGroup(groupSubgroup) {
    $("#id-gr-sub-file").val(groupSubgroup);
    $("#id-gr-sub-lec").val(groupSubgroup);
    $("#popup-edit-subgroup").modal('show');
}




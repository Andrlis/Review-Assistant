
function redirection() {
    window.location = "/Welcome"
}


function formAndShowPopupFormEditGroup(group) {
    $("#group_number").val(group['groupNumber']);
    $("#new_group_number").val(group['groupNumber']);

    $("#popup-edit-group").modal('show');
}

function showEmptyPopupFormEditGroup() {
    var group = {};
    group['groupNumber'] = "";
    group['newGroupNumber'] = "";

    formAndShowPopupFormEditGroup(group);
}

function showPopupFormEditGroup(groupNumber) {
    var group = {};
    group['groupNumber'] = groupNumber;

    formAndShowPopupFormEditGroup(group);
}

function formAndShowPopupFormRemove(number) {
    $("#number").val(number);

    $("#popup-delete").modal('show');
}





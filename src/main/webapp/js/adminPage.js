
function successSaveGroup() {
    window.location = "/Welcome"
}


function editGroupButton() {
    var group_number = $("#group_number").val();
    var new_group_number = $("#new_group_number").val();

    $.post(
        "SaveGroup",
        {
            newGroupNumber: new_group_number,
            groupNumber: group_number
        },
        successSaveGroup
    );
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



$("#tabs").tabs();
$("#new_name").on("blur", "toggleCreateButton()");


function toggleCreateButton() {
  if ($("#new_name").val().length) {
    $("#new_submit").removeAttr("disabled");
  }
  else {
    $("#new_submit").attr("disabled", "disabled");
  }
}
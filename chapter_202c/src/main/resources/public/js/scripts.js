function openaccount() {
    var nameValue = document.getElementById("accountId").value;
    var request = "{\"accountId\":\"" + nameValue + "\"}";
    $.ajax({
        type: "POST",
        url: "account",
        data: request,
        dataType: "JSON",
        contentType: "application/json",
        success: function (response) {
            if (response.result = "true") {
                $("#result").empty().append("<table><tr><td>" + response.description + "</td></tr><tr><td>Your password is: " + response.password + "</td></tr></table>");
            }
        },
        error: function () {
            $("#result").empty().append("<table><tr><td>Account with that ID already exists</td></tr></table>");
        }
    })
}
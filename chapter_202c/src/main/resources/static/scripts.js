function openaccount() {
    var acc = $("#accountId").val();
    var data = {AccountId:acc};
    $.ajax({
        type: "POST",
        url: "/account",
        dataType: "JSON",
        contentType : "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response);
            $("#result").empty().append(response.description);
            $("#password").empty().append(response.password);
        },
        error: function (response) {
            console.log(response.responseJSON.description);
            $("#result").empty().append(response.responseJSON.description);
            $("#password").empty();
        }
    })
}
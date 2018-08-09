function openaccount(account) {
    // var formData = JSON.stringify($("#usercreate").serializeArray());
    console.log(account);
    $.ajax({
        type: "POST",
        url: "account",
        data: {"accountId":account},
        success: function () {},
        dataType: JSON,
        contentType : "application/json"
    })
}
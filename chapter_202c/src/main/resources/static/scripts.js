// $(document).ready(function () {
//     fill_stat_table();
// });



// function register_url() {
//     var data = {
//         url:$("#reg_url").val(),
//         redirectType:$("#redirect_type").val()
//     };
//     $.ajax({
//         type: "POST",
//         url: "/register",
//         dataType: "JSON",
//         contentType : "application/json",
//         data: JSON.stringify(data),
//         success: function (response) {
//             console.log(response);
//             $("#shortUrl").empty().append(response.shortUrl);
//         },
//         error: function (response) {
//             console.log("error");
//             console.log(response);
//             $("#shortUrl").empty().append("URL already registered!");
//         }
//     })
// }

// function fill_stat_table() {
//     $("#table_stat").empty();
//     $.ajax({
//         type: "GET",
//         url: "/statistic",
//         dataType: "JSON",
//         success: function (response) {
//             console.log(response);
//             $.each(response, function(i, item) {
//                 var tr = $('<tr>').append(
//                     $('<td>').text(item[0]),
//                     $('<td>').text(item[1])
//                 );
//             });
//             $("#table_stat").append(tr);
//         }
//     })
// }
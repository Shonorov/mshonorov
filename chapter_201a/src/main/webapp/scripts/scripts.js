$(document).ready(function () {
    printitems();
});

function created(data) {
    var date = data.created.date.day + ":"
        + data.created.date.month + ":"
        + data.created.date.year + " "
        + data.created.time.hour + ":"
        + data.created.time.minute;
    return date;
}

function printitems() {
    $("#items").empty();
    $.ajax({
        type: "GET",
        url: "./list",
        datatype: "JSON",
        // data: {done : $("#done").is(':checked')},
        success: function (response) {
            var data = response["items"];
            for (var i in data) {
                var date = data[i].car.model.releaseDate.date.day + ":"
                    + data[i].car.model.releaseDate.date.month + ":"
                    + data[i].car.model.releaseDate.date.year;
                    // + data[i].car.model.releaseDate.time.hour + ":"
                    // + data[i].car.model.releaseDate.time.minute;
                // var doneinput = "";
                // if (data[i].done == true) {
                //     doneinput = "checked='checked'";
                // }
                $("#items").append("<div class='itemelement'>" +
                    "<table>" +
                    "<tr>" +
                    "<td><img src='./style/car.jpg' alt='Car' height='240' width='240'></td>" +
                    "<td><table style='border: 1px solid black; height: 240px; width: 750px'>" +
                    "<tr style='height: 30px'>" +
                    "<td class='header'><b>" + data[i].header + "</b></td>" +
                    "</tr><tr>" +
                    "<td class='createdate' align='right'>created " + created(data[i]) + "; author: " + data[i].author.name + "</td>" +
                    "</tr>" +
                    "<tr style='height: 30px'>" +
                    "<td class='price'>" + data[i].car.price + " руб.</td>" +
                    "<td class='status'>" +
                    "<form name='status'>" +
                    "<select id='sold'>" +
                    "<option value='" + data[i].sold + "'>" + data[i].sold + "</option>" +
                    "</select>" +
                    "</form></td>" +
                    "</tr>" +
                    "<tr style='height: 60px'><td>" + data[i].text + "</td></tr>" +
                    "<tr style='height: 20px'>" +
                    "<td>Manufacturer: " + data[i].car.manufacturer.name + "</td>" +
                    "<td>Country: " + data[i].car.manufacturer.country + "</td>" +
                    "<td>Model: " + data[i].car.model.name + "</td>" +
                    "<td>Released: " + date + "</td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td>Engine type: " + data[i].car.engine.type + "</td>" +
                    "<td>Engine volume: " + data[i].car.engine.volume + "</td>" +
                    "<td>Engine power: " + data[i].car.engine.power + "</td>" +
                    "<td>Milage: " + data[i].car.engine.mileage + "</td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td>Body type: " + data[i].car.body.type + "</td>" +
                    "<td>Color: " + data[i].car.body.color + "</td>" +
                    "<td>Wheel side: " + data[i].car.body.wheelSide + "</td>" +
                    "<td></td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td>Gearbox: " + data[i].car.gearbox.type + "</td>" +
                    "<td>Gear count: " + data[i].car.gearbox.gearCount + "</td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "</tr>" +
                    "</table></td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>");
            }
        }
    })
}
$(document).ready(function () {
    printitems();
});

function setstatus(itemid) {
    console.log(itemid);
    //TODO wrong id
    var newstatus = revertstatus($("#sold").val());
    console.log(newstatus);
    $.ajax({
        type: "POST",
        url: "./list",
        datatype: "JSON",
        data: {id : itemid, status: newstatus},
        success: function () {
            printitems();
        }
    })
}

function created(data) {
    var date = data.created.date.day + ":"
        + data.created.date.month + ":"
        + data.created.date.year + " "
        + data.created.time.hour + ":"
        + data.created.time.minute;
    return date;
}

function released(data) {
    var date = data.date.day + ":"
        + data.date.month + ":"
        + data.date.year;
    return date;
}

function color(data) {
    var tag = "";
    if (data == true) {
        tag = "style='background-color: red'";
    } else {
        tag = "style='background-color: green'";
    }
    return tag;
}

function status(data) {
    var tag = "";
    if (data == false) {
        tag = "available";
    } else {
        tag = "sold out";
    }
    return tag;
}

function revertstatus(data) {
    var tag = "";
    if (data == "available") {
        tag = false;
    } else if (data == "sold out") {
        tag = true;
    }
    return tag;
}

function printitems() {
    $("#items").empty();
    $.ajax({
        type: "GET",
        url: "./list",
        datatype: "JSON",
        success: function (response) {
            var data = response["items"];
            for (var i in data) {
                $("#items").append("<div class='itemelement'>" +
                    "<table>" +
                    "<tr>" +
                    "<td><img src='./style/car.jpg' alt='Car' height='240' width='240'></td>" +
                    "<td><table style='border: 1px solid black; height: 240px; width: 750px'>" +
                    "<tr style='height: 30px'>" +
                    "<td class='header' colspan='4'><b>" + data[i].header + "</b></td>" +
                    "</tr><tr>" +
                    "<td class='createdate' colspan='4'>created " + created(data[i]) + "; author: " + data[i].author.name + "</td>" +
                    "</tr>" +
                    "<tr style='height: 30px'>" +
                    "<td class='price'>" + data[i].car.price + " руб.</td>" +
                    "<td class='status' colspan='3' align='right'>" +
                    "<form name='status'>Status: " +
                    "<select id='sold' onchange='setstatus(" + data[i].id + ")' " + color(data[i].sold) + ">" +
                    "<option value='" + status(data[i].sold) + "'>" + status(data[i].sold) + "</option>" +
                    "<option value='" + status(!data[i].sold) + "'>" + status(!data[i].sold) + "</option>" +
                    "</select>" +
                    "</form></td>" +
                    "</tr>" +
                    "<tr style='height: 60px'><td colspan='4'>" + data[i].text + "</td></tr>" +
                    "<tr style='height: 20px'>" +
                    "<td class='param'>Manufacturer: " + data[i].car.manufacturer.name + "</td>" +
                    "<td class='param'>Country: " + data[i].car.manufacturer.country + "</td>" +
                    "<td class='param'>Model: " + data[i].car.model.name + "</td>" +
                    "<td class='param'>Released: " + released(data[i].car.model.releaseDate) + "</td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td class='param'>Engine type: " + data[i].car.engine.type + "</td>" +
                    "<td class='param'>Engine volume: " + data[i].car.engine.volume + "</td>" +
                    "<td class='param'>Engine power: " + data[i].car.engine.power + "</td>" +
                    "<td class='param'>Milage: " + data[i].car.engine.mileage + "</td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td class='param'>Body type: " + data[i].car.body.type + "</td>" +
                    "<td class='param'>Color: " + data[i].car.body.color + "</td>" +
                    "<td class='param'>Wheel side: " + data[i].car.body.wheelSide + "</td>" +
                    "<td class='param'></td>" +
                    "</tr>" +
                    "<tr style='height: 20px'>" +
                    "<td class='param'>Gearbox: " + data[i].car.gearbox.type + "</td>" +
                    "<td class='param'>Gear count: " + data[i].car.gearbox.gearCount + "</td>" +
                    "<td class='param'></td>" +
                    "<td class='param'></td>" +
                    "</tr>" +
                    "</table></td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>");
            }
        }
    })
}
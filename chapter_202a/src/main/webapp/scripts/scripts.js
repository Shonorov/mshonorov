$(document).ready(function () {
    printitems("all");
    appendfilter();
});

function appendfilter() {
    $.ajax({
        type: "POST",
        url: "./manufacturers",
        datatype: "JSON",
        success: function (response) {
            var data = response["manufacturers"];
            var unique = new Set();
            for (var i in data) {
                if (!unique.has(data[i].name)) {
                    unique.add(data[i].name);
                    $("#filter").append("<option value='" + data[i].name + "' onclick='printitems(\"manufacturer: " + data[i].name + "\");'>manufacturer: " + data[i].name + "</option>")
                }
            }
        }
    })
}

function setstatus(itemid, authorid) {
    var select = "sold" + itemid;
    var newstatus = revertstatus(document.getElementById(select).value);
    $.ajax({
        type: "POST",
        url: "./list",
        datatype: "JSON",
        data: {id : itemid, status: newstatus, author: authorid},
        success: function () {
            printitems("all");
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

function arrayBufferToBase64( buffer ) {
    var binary = '';
    var bytes = new Uint8Array( buffer );
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return window.btoa( binary );
}

function printitems(filter) {
    $("#items").empty();
    $.ajax({
        type: "GET",
        url: "./list",
        datatype: "JSON",
        data: {filter : filter},
        success: function (response) {
            var data = response["items"];
            for (var i in data) {
                $("#items").append("<div class='itemelement'>" +
                    "<table>" +
                    "<tr>" +
                    "<td><img src='data:image/jpeg;base64," + arrayBufferToBase64(data[i].car.photo) + "' alt='No image' height='240' width='240' onerror=\"this.src='./style/nophoto.jpg';\"></td>" +
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
                    "<select id='sold" + data[i].id + "' onchange='setstatus(" + data[i].id + "," + data[i].author.id + ")' " + color(data[i].sold) + ">" +
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

function getuser() {
    $("#currentuser").empty();
    $.ajax({
        type: "POST",
        url: "./signin",
        success: function (response) {
            $("#currentuser").append(response);
        }
    })
}

$("#currentuser").onload = getuser();

function validatecreate() {
    var text = "";
    var header = document.forms["createform"]["header"].value;
    if (header == "") {
        text += "Field 'header' must be filled out!\n";
    }
    var itemtext = document.forms["createform"]["text"].value;
    if (itemtext == "") {
        text += "Field 'text' must be filled out!\n";
    }
    var price = document.forms["createform"]["price"].value;
    if (price == "" && !Number.isInteger(price)) {
        text += "Field 'price' must be filled out and numeric!!\n";
    }
    var drive = document.forms["createform"]["drive"].value;
    if (drive == "") {
        text += "Field 'drive' must be filled out!\n";
    }
    var manufactured = document.forms["createform"]["manufactured"].value;
    if (manufactured == "") {
        text += "Field 'manufactured' must be filled out!\n";
    }
    var manufactured = document.forms["createform"]["manufactured"].value;
    if (manufactured == "") {
        text += "Field 'manufactured' must be filled out!\n";
    }
    var manufacturer = document.forms["createform"]["manufacturer"].value;
    if (manufacturer == "") {
        text += "Field 'manufacturer' must be filled out!\n";
    }
    var country = document.forms["createform"]["country"].value;
    if (country == "") {
        text += "Field 'country' must be filled out!\n";
    }
    var model = document.forms["createform"]["model"].value;
    if (model == "") {
        text += "Field 'model' must be filled out!\n";
    }
    var releasedate = document.forms["createform"]["releasedate"].value;
    if (releasedate == "") {
        text += "Field 'releasedate' must be filled out!\n";
    }
    var manufacturing = document.forms["createform"]["manufacturing"].value;
    if (manufacturing == "") {
        text += "Field 'manufacturing' must be filled out!\n";
    }
    var enginetype = document.forms["createform"]["enginetype"].value;
    if (enginetype == "") {
        text += "Field 'enginetype' must be filled out!\n";
    }
    var enginevolume = document.forms["createform"]["enginevolume"].value;
    if (enginevolume == "" && !Number.isInteger(enginevolume)) {
        text += "Field 'enginevolume' must be filled out and numeric!!\n";
    }
    var enginepower = document.forms["createform"]["enginepower"].value;
    if (enginepower == "" && !Number.isInteger(enginepower)) {
        text += "Field 'enginepower' must be filled out and numeric!!\n";
    }
    var enginemilage = document.forms["createform"]["enginemilage"].value;
    if (enginemilage == "" && !Number.isInteger(enginemilage)) {
        text += "Field 'enginemilage' must be filled out and numeric!!\n";
    }
    var bodytype = document.forms["createform"]["bodytype"].value;
    if (bodytype == "") {
        text += "Field 'bodytype' must be filled out!\n";
    }
    var bodycolor = document.forms["createform"]["bodycolor"].value;
    if (bodycolor == "") {
        text += "Field 'bodycolor' must be filled out!\n";
    }
    var wheelside = document.forms["createform"]["wheelside"].value;
    if (wheelside == "") {
        text += "Field 'wheelside' must be filled out!\n";
    }
    var gearboxtype = document.forms["createform"]["gearboxtype"].value;
    if (gearboxtype == "") {
        text += "Field 'gearboxtype' must be filled out!\n";
    }
    var gearcount = document.forms["createform"]["gearcount"].value;
    if (gearcount == "" && !Number.isInteger(gearcount)) {
        text += "Field 'gearcount' must be filled out and numeric!\n";
    }

    if (text != "") {
        alert(text);
        return false;
    }
}
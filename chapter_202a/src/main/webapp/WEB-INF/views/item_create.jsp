<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car Shop</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="./scripts/scripts.js"></script>
    <link rel="stylesheet" type="text/css" href="./style/style.css"><meta charset="UTF-8">
</head>
<body>
<div id="head">
    <table width="1000px">
        <tr>
            <td width="60%">
                Item create
            </td>
            <td width="20%" id="currentuser">
            <td width="10%" align="right">
                <form id="shop" action="shop"><input type="submit" value="To shop"></form>
            </td>
            <td width="10%" align="right">
                <form id="signout" action="./logout" method="post"><input type="submit" value="Sign out"></form>
            </td>
        </tr>
    </table>
</div>

<div id="itemcreate">
    <form name="createform" action="create" method="POST" enctype="multipart/form-data" onsubmit="return validatecreate()">
        <table width="1000px">
            <tr class="propline">
                <td class="proprow">Item name: </td>
                <td class="propvaluerow"><input type="text" name='header' class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Item text: </td>
                <td class="propvaluerow"><input type="text" name="text" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car price: </td>
                <td class="propvaluerow"><input type="text" name="price" class="propinput"> руб.</td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car drive type: </td>
                <td class="propvaluerow">
                    <select name="drive" class="propinput">
                        <option value="FWD">FWD</option>
                        <option value="RWD">RWD</option>
                        <option value="4WD">4WD</option>
                    </select>
                </td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car manufacture date: </td>
                <td class="propvaluerow"><input type="date" name="manufactured" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car photo: </td>
                <td class="propvaluerow"><input type="file" name="photo" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Manufacturer: </td>
                <td class="propvaluerow"><input type="text" name="manufacturer" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Manufacturer country: </td>
                <td class="propvaluerow"><input type="text" name="country" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car model name: </td>
                <td class="propvaluerow"><input type="text" name="model" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Car model release date: </td>
                <td class="propvaluerow"><input type="date" name="releasedate" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Is car model manufacturing: </td>
                <td class="propvaluerow">
                    <select name="manufacturing" class="propinput">
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </td>
            </tr>

            <tr class="propline">
                <td class="proprow">Engine type: </td>
                <td class="propvaluerow">
                    <select name="enginetype" class="propinput">
                        <option value="Petrol">Petrol</option>
                        <option value="Diesel">Diesel</option>
                        <option value="Electric">Electric</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>
                </td>
            </tr>
            <tr class="propline">
                <td class="proprow">Engine volume: </td>
                <td class="propvaluerow"><input type="text" name="enginevolume" class="propinput"> L.</td>
            </tr>
            <tr class="propline">
                <td class="proprow">Engine power: </td>
                <td class="propvaluerow"><input type="text" name="enginepower" class="propinput"> HP</td>
            </tr>
            <tr class="propline">
                <td class="proprow">Engine milage: </td>
                <td class="propvaluerow"><input type="text" name="enginemilage" class="propinput"> Km.</td>
            </tr>
            <tr class="propline">
                <td class="proprow">Body type: </td>
                <td class="propvaluerow">
                    <select name="bodytype" class="propinput">
                        <option value="Coupe">Coupe</option>
                        <option value="Sedan">Sedan</option>
                        <option value="Convertible">Convertible</option>
                        <option value="Crossover">Crossover</option>
                        <option value="VAN">VAN</option>
                        <option value="Wagon">Wagon</option>
                        <option value="Truck">Truck</option>
                    </select>
                </td>
            </tr>
            <tr class="propline">
                <td class="proprow">Body color: </td>
                <td class="propvaluerow"><input type="text" name="bodycolor" class="propinput"></td>
            </tr>
            <tr class="propline">
                <td class="proprow">Wheel side: </td>
                <td class="propvaluerow">
                    <select name="wheelside" class="propinput">
                        <option value="Left">Left</option>
                        <option value="Right">Right</option>
                    </select>
                </td>
            </tr>
            <tr class="propline">
                <td class="proprow">Gearbox type: </td>
                <td class="propvaluerow">
                    <select name="gearboxtype" class="propinput">
                        <option value="Automatic">Automatic</option>
                        <option value="Semi automatic">Semi automatic</option>
                        <option value="Manual">Manual</option>
                        <option value="CVT">CVT</option>
                        <option value="DSG">DSG</option>
                    </select>
                </td>
            </tr>
            <tr class="propline">
                <td class="proprow">Gear count: </td>
                <td class="propvaluerow"><input type="text" name="gearcount" class="propinput"/></td>
            </tr>
        </table>
        <input type="submit" value="Create item">
    </form>
</div>
</body>
</html>
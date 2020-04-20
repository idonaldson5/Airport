$(document).ready(function() {

$.getJSON("planes",
function (data) {
    var tr;
    for (var i = 0; i < data.length; i++) {
        tr = $("<tr/>");
        tr.append("<td>" + data[i].id + "</td>");
        tr.append("<td>" + data[i].name + "</td>");
        tr.append("<td>" + data[i]['airport']['airportName'] + "</td>");
        tr.append("<td>" + data[i].fuel + "</td>");
        var refuelButton = '<button onclick=' + '"' + 'refuel(' + data[i].id + ')' + '"' + '> Refuel </button>';
        tr.append(refuelButton);
        var flyButton = '<button onclick=' + '"' + 'fly(' + data[i].id + ')' + '"' + '> Fly </button>';
        tr.append(flyButton);
        var deleteButton = '<button onclick=' + '"' + 'deletePlane(' + data[i].id + ')' + '"' + '> Remove plane </button>';
        tr.append(deleteButton);
        $('#table').append(tr);
    }
});

} );
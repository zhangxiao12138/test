var $table = $('#mytable');
var $button = $('#loadbutton');

function randomData() {
    var startId = ~~(Math.random() * 100);
    var rows = [];

    for (var i = 0; i < 10; i++) {
        rows.push({
            id: startId + i,
            name: 'test' + (startId + i),
            price: '$' + (startId + i)
        });
    }



    return rows;
}

$(function() {
    $button.click(function () {
        alert("button clicked!");
        $table.bootstrapTable('load', randomData());
    });
})
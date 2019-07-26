var $table = $('#mytable');
var $button = $('#loadbutton');
var $vb = $('#videobutton');

var $up = $('#upbutton');
var $down = $('#downbutton');
var $left = $('#leftbutton');
var $right = $('#rightbutton');

var data1 = [
    {
        "id": 0,
        "name": "Item 0",
        "price": "$0",
        "amount": 3
    },
    {
        "id": 1,
        "name": "Item 1",
        "price": "$1",
        "amount": 4
    },
    {
        "id": 2,
        "name": "Item 2",
        "price": "$2",
        "amount": 8
    },
    {
        "id": 3,
        "name": "Item 3",
        "price": "$3",
        "amount": 2
    },
    {
        "id": 4,
        "name": "Item 4",
        "price": "$4",
        "amount": 90
    },
    {
        "id": 5,
        "name": "Item 5",
        "price": "$5",
        "amount": 2
    },
    {
        "id": 6,
        "name": "Item 6",
        "price": "$6",
        "amount": 3
    },
    {
        "id": 7,
        "name": "Item 7",
        "price": "$7",
        "amount": 7
    },
    {
        "id": 8,
        "name": "Item 8",
        "price": "$8",
        "amount": 39
    },
    {
        "id": 9,
        "name": "Item 9",
        "price": "$9",
        "amount": 78
    },
    {
        "id": 10,
        "name": "Item 10",
        "price": "$10",
        "amount": 30
    },
    {
        "id": 11,
        "name": "Item 11",
        "price": "$11",
        "amount": 32
    },
    {
        "id": 12,
        "name": "Item 12",
        "price": "$12",
        "amount": 12
    },
    {
        "id": 13,
        "name": "Item 13",
        "price": "$13",
        "amount": 76
    },
    {
        "id": 14,
        "name": "Item 14",
        "price": "$14",
        "amount": 10
    },
    {
        "id": 15,
        "name": "Item 15",
        "price": "$15",
        "amount": 9
    },
    {
        "id": 16,
        "name": "Item 16",
        "price": "$16",
        "amount": 8
    },
    {
        "id": 17,
        "name": "Item 17",
        "price": "$17",
        "amount": 1
    },
    {
        "id": 18,
        "name": "Item 18",
        "price": "$18",
        "amount": 99
    },
    {
        "id": 19,
        "name": "Item 19",
        "price": "$19",
        "amount": 100
    },
    {
        "id": 20,
        "name": "Item 20",
        "price": "$20",
        "amount": 109
    }
];

function clickVideoButton() {
    alert("here in clickVideoButton");
    $('#testpage').load("/demo/hkws");
}
function click3DButton() {
    alert("here in click3D button!");
    $('#testpage').load("/demo/3Ddemo");
}
function clickFrontPage(){
    $('#testpage').load("/demo/frontPage");
}

function clickEchartsButton() {
    $('#testpage').load("/demo/echartsDemo");
}

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


function findDataMethod() {
    var findData = [];
    $.ajax({
        url:'demo/data',
        type:'GET',
        success:function(data){
            findData = data;
        }
    });
    return findData;
}

$(function() {
    $button.click(function () {
        alert("video button clicked!");
        $.ajax({
            url:'demo/hkws',
            type:'GET',
            success:function(){
                alert("success!");
            }
        });
        /*$table.bootstrapTable('load', [{
            "id": 20,
            "itemName": "Item 20",
            "price": "$20",
            "amount": 109
        }]);*/
    });
    $vb.click(function () {
        alert("video button clicked!");
        $.ajax({
            url:'demo/hkws',
            type:'GET',
            success:function(){
                alert("success!");
            }
        });
    });

    $up.click(function () {
        alert("up clicked!");
        $.ajax({
            url:'demo/direction?d=u',
            type:'GET',
            success:function(){
                //alert("success!");
            }
        });
    });

        $down.click(function () {
            alert("down clicked!");
            $.ajax({
                url:'demo/direction?d=d',
                type:'GET',
                success:function(){
                    alert("success!");
                }
            });
        });

        $left.click(function () {
            alert("left clicked!");
            $.ajax({
                url:'demo/direction?d=l',
                type:'GET',
                success:function(){
                    alert("success!");
                }
            });
        });

        $right.click(function () {
            alert("right clicked!");
            $.ajax({
                url:'demo/direction?d=r',
                type:'GET',
                success:function(){
                    alert("success!");
                }
            });
        });
}
)


/*
$(function() {
        $vb.click(function () {
            alert("video button clicked!");
            $.ajax({
                url:'demo/hkws',
                type:'GET',
                success:function(){
                    alert("success!");
                }
            });
        });
    }
)*/

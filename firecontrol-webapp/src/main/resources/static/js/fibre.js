$(function(){

   alert("here init fibre");

})

var dom = document.getElementById("myChart");
var myChart = echarts.init(dom);

function fetchData(cb) {
    // 通过 setTimeout 模拟异步加载

    $.ajax({
            type: "Get",
            url: "/user/echart",
            data: {},
            success: function () {

                /*  var valueList=[{"value":[[${list.dCount}]],"name":[[${list.dCount}]]}];*/


                var obj = {value: [[${list.dCount}]], name: [[${list.dName}]]};
                var obj1 = {name: [[${list.dName}]]};
                valueList.push(obj);
                nameList.push(obj1)
                console.log(JSON.stringify(valueList));

                cb({
                    categories: nameList,
                    /*       data: [
                     {value: 10, name: 'Guilt Free Play'},
                     {value: 5, name: 'Quality Work'},
                     {value: 12, name: 'Mandatory Work'},
                     {value: 6, name: 'Rest'},
                     {value: 8, name: 'Procrastination'}]*/
                    data:valueList
                });


            }
        }


    )
}
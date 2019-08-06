
var currentChart;
var singleChart;
var locations = [];
var tempList = [];
var currentNode;

var legendArr = [];//名字
var legendNameArr = [];//展示的名字
var xArray = [];//x轴时间
var hisDataList = [];//多个单点的历史数据，可以是二维数组
var existFlag = false;
var k;

var seData =  new Object();
var seList = [];
var tList = [];


$(document).ready(function(){
    currentChart = echarts.init(document.getElementById('currentChart'));
    singleChart = echarts.init(document.getElementById('singleChart'));

    currentChart.showLoading();


    //初始化位置信息
    var si = 0;
    for(si = 0; si < 200; si++){
        locations.push((si * 41)/100);
    }
    setInterval(getCurrentChart, 5000);

    singleChart.setOption({
        color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026'],
        title: {
            text: '单点历史温度图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[]
        },
        grid: {
            left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
                boundaryGap: false,
                data: [1,2]
        },
        yAxis: {
            type: 'value'
        },
        series: [

        ]
    });

    currentChart.on('dblclick', function (params) {
        alert("dblclicked!"+params.type+","+params.value +","+params.name);
        //if (params.name === 'node') {
        // 数据项，类型string
        currentNode = params.name;
        alert("点击了第" + currentNode + "个小点点");
        reloadSingleChart(currentNode);
        //}
    });

})


function getCurrentChart(){

    for (sj = 0; sj < 200; sj++) {
        tempList[sj] = Math.floor((Math.random() * 3 + 25) * 100) / 100;
    }
    currentChart.hideLoading();    //隐藏加载动画

    currentChart.setOption({
        title: {
            text: '感温光纤折线图堆叠'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['当前时刻感温光纤温度']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: locations
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'当前时刻感温光纤温度',
                type:'line',
                data: tempList
            }
        ]
    });

}

function reloadSingleChart(name){

    //x轴展示页面 随机数 假设展示过去1小时的，每分钟一个点
    var ti = 0;
    for(ti = 0; ti < 60; ti++) {
        xArray.push(ti);
    }
    //线的名称,去重
    if(legendArr.length < 1) {
        legendArr.push(name);
        legendNameArr.push("点"+name);
    }else {
        for(ti = 0; ti < legendArr.length; ti ++){
            if(legendArr[ti] == name) {
                existFlag = true;
            }
        }
        if(!existFlag){
            legendArr.push(name);
            legendNameArr.push("点"+name);
        }
    }

    for (var i = 0; i < legendArr.length; i++) {
        seData.name = legendNameArr[i];
        seData.type = 'line';
        seData.data = new Array();
        for (var j = 0; j < xArray.length; j++) {
            seData.data[j] = Math.floor((Math.random() * 3 + 25) * 100) / 100;
        }
        seList.push(seData);
    }
    console.info("legendName: " + legendNameArr);
    console.info("seList" + JSON.stringify(seList));

    singleChart.setOption({
        title: {
            text: '单点历史温度图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:legendNameArr
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xArray
        },
        yAxis: {
            type: 'value'
        },
        series: seList
    });

}

function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

function formatTime(number,format) {

    var formateArr  = ['Y','M','D','h','m','s'];
    var returnArr   = [];

    var date = new Date(number);
    returnArr.push(date.getFullYear());
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));

    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));

    for (var i in returnArr)
    {
        format = format.replace(formateArr[i], returnArr[i]);
    }
    return format;
}
function flyToModel(){
    var device = document.getElementById("device").value;
    alert(device);
    iframe1.window.flyToModel(device);
}

function resetSrc(){
        document.getElementById("iframe1").src='http://test.tianbo.cn/bim/';
}

function showRoutesByFloor(){
    var floor = document.getElementById("floor").value;
    alert(floor);
    iframe1.window.showRoutesByFloor(floor);
}

function ResetAllActor(){
    iframe1.window.Flicker("0");
}

function flickertest() {
    iframe1.window.Flicker("0");
}

function resetActor() {
    var sample = document.getElementById("sample").value;
    iframe1.window.ResetActor(sample);
}

function resetPositiontest() {
    iframe1.window.ResetAllActor();
    console.info("iframe1.window.loadSuccess:" + iframe1.window.loadSuccess);
}
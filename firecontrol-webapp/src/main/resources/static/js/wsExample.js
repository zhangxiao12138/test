var socket;
if(typeof(WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
}else{
    console.log("您的浏览器支持WebSocket");
    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
    //等同于
    socket = new WebSocket("ws://localhost:11001/websocket/radomStr");
    //socket = new WebSocket("${basePath}websocket/${cid}".replace("http","ws"));
    //打开事件
    socket.onopen = function() {
        console.log("Socket 已打开");
        socket.send("这是来自客户端的消息" + new Date());
    };
    //获得消息事件
    socket.onmessage = function(msg) {
        console.info("接收到从服务器发来的请求");
        console.log(msg);
        //发现消息进入    开始处理前端触发逻辑
    };
    //关闭事件
    socket.onclose = function() {
        console.log("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function() {
        alert("Socket发生了错误");
        //此时可以尝试刷新页面
    }
    //离开页面时，关闭socket
    //jquery1.8中已经被废弃，3.0中已经移除
    // $(window).unload(function(){
    //     socket.close();
    //});
}


// var socket;
// if(typeof(WebSocket) == "undefined") {
//     console.log("您的浏览器不支持WebSocket");
// }else{
//     console.log("您的浏览器支持WebSocket");
    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
    //等同于
    // socket = new WebSocket("ws://localhost:11001/websocket/radomString");
    //socket = new WebSocket("${basePath}websocket/${cid}".replace("http","ws"));
    //打开事件
    // socket.onopen = function() {
    //     console.log("Socket 已打开");
    //     socket.send("这是来自客户端的消息" + location.href + new Date());
    // };
    // var wsurl = "/tainbo/ws/radomStr";
    //
    //
    // var from = $("#from").val();
    // var to = $("#to").val();
    // var socket = new SockJS(wsurl);
    // stompClient = Stomp.over(socket);
    // console.info("will connect");
    // stompClient.connect({}, function (frame) {
    //     setConnected(true);
    //     console.log('Connected: ' + frame);
    //     stompClient.subscribe('/serviceInit/socket/push/' + from + to, function (result) {
    //         console.info("subscribe received result: ");
    //         console.info(result);
    //     });
    // });
    //
    // console.info("will send");
    //
    // stompClient.send("/clientInit/socket/radonStr", {}, JSON.stringify({
    //     'message': "这是一条来自客户端的请求",
    //     'toUser': "server",
    //     'fromUser': "client"
    // }));
    // xmlhttp = new XMLHttpRequest();
    // xmlhttp.open("GET", "/socket/radomStr", false);
    // xmlhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
    //
    //
    //
    // //获得消息事件
    // socket.onmessage = function(msg) {
    //     console.log("接收到来自服务器的消息！");
    //     console.log(msg);
    //
    // };
    // //关闭事件
    // socket.onclose = function() {
    //     console.log("Socket已关闭");
    // };
    // //发生了错误事件
    // socket.onerror = function() {
    //     alert("Socket发生了错误");
    //     //此时可以尝试刷新页面
    // }
    //离开页面时，关闭socket
    //jquery1.8中已经被废弃，3.0中已经移除
    // $(window).unload(function(){
    //     socket.close();
    //});
// }
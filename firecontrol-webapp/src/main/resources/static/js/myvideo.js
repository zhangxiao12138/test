var videoSrc;
var videoButton = document.getElementById("video-button");
var video = document.getElementById("myvideo");
var div1 = document.getElementById("div1");

var newVideo;
var newSrc;
var urll="rtmp://58.200.131.2:1935/livetv/hunantv";
var $button = $('#loadbutton');
var $button1 = $('#loadbutton1');
var myPlayera;
var videoObj;

var myPlayer;



$(function() {

    $button.click(function () {
        alert("video button clicked!");

        //要实例化！重点
/*
        myPlayer = videojs('my-video');
        myPlayer.src({ type: 'rtmp/flv', src: urll });
        alert("ok!");
*/

        $.ajax({
            url:'demo/videoUrl',
            type:'GET',
            success:function(videoUrl){
                myPlayer = videojs('my-video');
                myPlayer.src({ type: 'rtmp/flv', src: videoUrl });
            },
            error:function(){
                alert("error!");
            }
        });

    })
})



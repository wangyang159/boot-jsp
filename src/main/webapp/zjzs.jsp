<%--
  Created by IntelliJ IDEA.
  User: 王洋
  Date: 2022/11/1
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>证件照生成</title>
</head>
<body>
    <div id="contentHolder">
        <video id="video" autoplay style="background-color: #000"></video>
        <canvas id="canvas"></canvas>
        <canvas id="canvas_bg" style="display: none"></canvas>
        <img id="imgXX" src=""/>
    </div>

    <button id="btn_snap" onclick="takePhoto()">拍照</button>
    <button id="btn_sc" onclick="push()">保存</button>
    <button id="btn_xk" onclick="sq()">舍弃</button>

    <input type="color" oninput="changeBgColor(this.value)" value="#0066cc" />

    <script src="/js/tf.min.js"></script>
    <script src="/js/zjz.js"></script>
    <script src="/js/jquery-3.6.0.js"></script>

    <script>
        const cvs = document.getElementById('canvas')
        const video = document.getElementById('video')
        const canvasBg = document.getElementById('canvas_bg')
        cvs.width = video.width = canvasBg.width = ${xs_x}
        cvs.height = video.height = canvasBg.height = ${xs_y}
        const ctxBg = canvasBg.getContext('2d')

        function changeBgColor(v) {
            ctxBg.clearRect(0, 0, cvs.width, cvs.height)
            ctxBg.fillStyle = v || '#06c'
            ctxBg.fillRect(0, 0, cvs.width, cvs.height)
        }
        function takePhoto() {
            document.getElementById('imgXX').src = canvas.toDataURL('image/png')
        }
        changeBgColor()
        main()

        function push(){
            var datas = document.getElementById('imgXX').getAttribute("src")
            console.log(datas)
            //ajax
            $.ajax({
                url:"/zjzFromBase64",
                data:{base:datas},
                dataType:"json",
                success:function (result){
                    alert(result["resultCode"] + result["msg"])
                },
                error:function (result){
                    alert(result["resultCode"] + result["msg"])
                }
            })
        }

        function sq() {
            document.getElementById('imgXX').src = ""
        }
    </script>
</body>
</body>
</html>

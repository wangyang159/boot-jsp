#程序使用方法介绍

本程序的目的用来生成证件照。

拿到程序jar后，打开<font color="green">/BOOT-INF/classes/application.properties</font>文件

在配置文件中按照需要修改<font color="green">文件保存路径</font>以及<font color="green">所需的像素</font>

修改完成后，请确保你的计算机中拥有JAVA-JDK，并在cmd窗口中运行<font color="red">java -jar 本程序存放路径</font>

程序运行后，不要关闭cmd窗口，使用浏览器访问<font color="blun">localhost:91</font>，本程序默认91端口

浏览器访问后，第一次运行浏览器会请求摄像头权限，请点击同意。

同意后，你会看到页面上出现了镜头框、两个按钮以及一个调色板，此时不要操作，程序需要初始化。

当镜头框的右侧出现了以调色板颜色为背景的画布时，就可以调整姿势，并点击拍照按钮，点击后会出现第三个结果图片框。

如果满意结果，请点击保存，如果不满意点击舍弃
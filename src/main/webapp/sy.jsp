<%--
  Created by IntelliJ IDEA.
  User: 王洋
  Date: 2022/12/18
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>切片上传</title>
</head>
<body>

    <form id="addFrm" enctype="multipart/form-data" method="post">
        文件上传:<input type="file" name="file" id="file"><br/>
        <input type="button" onclick="saveObject()" value="文件上传"/>
    </form>


    <script type="text/javascript" src="/sy.js"></script>
    <script type="text/javascript" src="spark-md5.min.js"></script>
    <script type="text/javascript">
        /**
         * 1、分片获取文件的md5 并 顺序保存分片fileblock
         * 给单文件标签添加一个修改事件，修改时将文件分为sliceLength个片
         * 文件总大小除以sliceLength获得每个块的大小chunkSize
         * FileReader每读取一块文件的字节数组就追加到SparkMD5中
         * onload回调函数递归闭包的读取方法直到读取结束
         * file_index是原文件的字节数组指针，fileblock_index是保存块文件的数组指针
         * 最终将结果封装在一个总的结果集readyfilemeg中
         *
         * 但是注意结果中块数往往是sliceLength+1块，这是因为除法大概率除不尽文件的总大小
         * 而递归读取的条件是file_index大于文件总字节才结束，因此往往会多一块
         */
        var readyfilemeg1 = []
        document.querySelector('#file').addEventListener('change', e => {
            const fileblock = [];
            let fileblock_index = 0;
            //document获取文件
            const file = e.target.files[0];
            const sliceLength = 9;
            const chunkSize = Math.ceil(file.size / sliceLength);
            const fileReader = new FileReader();
            const md5 = new SparkMD5();
            let file_index = 0;
            const loadFile = () => {
                const slice = file.slice(file_index, file_index + chunkSize);
                fileblock[fileblock_index]=slice;
                fileblock_index++;
                fileReader.readAsBinaryString(slice);
            }
            loadFile();
            fileReader.onload = e1 => {
                md5.appendBinary(e1.target.result);
                if (file_index < file.size) {
                    file_index += chunkSize;
                    loadFile();
                } else {
                    //封装结果:文件的md5、文件块集合、文件块总数、文件的名称
                    readyfilemeg1["filemd5"]=md5.end();
                    readyfilemeg1["fileblocks"]=fileblock;
                    readyfilemeg1["fileblocksize"]=fileblock_index;
                    readyfilemeg1["filename"]=file.name;
                    console.log(readyfilemeg1)
                }
            };
        });

        /**
         * 2、当要点击上传的时候，使用并发API-Promise将切片信息并发传递给后台
         */
        function saveObject() {
            console.log("触发方法");
            concurRequst(readyfilemeg1,2)
            // concurRequst(readyfilemeg1,2).then((resps) => {
            //     console.log("触发结果")
            //     console.log(resps)
            // })
        }

    </script>
</body>
</html>

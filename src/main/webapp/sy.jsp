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
    <script type="text/javascript" src="jquery-3.2.1.js"></script>
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
         * 注意结果中块数一定是sliceLength块，不要担心除法有可能除不尽文件的总大小
         * 递归读取的条件是file_index小于文件总字节当运行到最后一块不够的的时候任然会切片
         */
        var readyfilemeg = []
        document.querySelector('#file').addEventListener('change', e => {
            //准备需要的变量
            const fileblock = [];
            let fileblock_index = 0;
            //document获取文件
            const file = e.target.files[0];
            const sliceLength = 5;
            const chunkSize = Math.ceil(file.size / sliceLength);
            const fileReader = new FileReader();
            const md5 = new SparkMD5();
            let file_index = 0;

            //运行方法
            const loadFile = () => {
                //slice是一个左闭右开的方法
                const slice = file.slice(file_index, file_index + chunkSize);
                fileblock[fileblock_index]=slice;
                fileblock_index++;
                file_index += chunkSize;
                fileReader.readAsBinaryString(slice);
            }

            //第一次需要手动调一下，才能触发onload
            loadFile();
            fileReader.onload = e1 => {
                md5.appendBinary(e1.target.result);
                if ( file_index < file.size ) {
                    loadFile();
                } else {
                    //封装结果:文件的md5、文件块集合、文件块总数、文件的名称、文件总大小
                    readyfilemeg["filemd5"]=md5.end();
                    readyfilemeg["fileblocks"]=fileblock;
                    readyfilemeg["fileblocksize"]=fileblock_index;
                    readyfilemeg["filename"]=file.name;
                    readyfilemeg["filesize"]=file.size;
                    console.log(readyfilemeg)
                }
            };
        });

        /**
         * 2、当要点击上传的时候，使用并发API-Promise将切片信息并发传递给后台
         */
        function saveObject() {
            //秒传
            $.ajax({
                method:"post",
                dataType:"json",
                url:"/minupload",
                data:{"fileMd5":readyfilemeg["filemd5"]},
                success:function (result) {
                    if(result){
                        alert("上传成功")
                    }else {
                        concurRequst(readyfilemeg,2).then(resps=>{
                            let reduce = 0;
                            for (let i = 0; i < resps.length; i++) {
                                reduce+=parseInt(resps[i]);
                            }

                            /**
                             * 3、根据后台传递回来的信息全部为上传成功，那么就要触发后台合并
                             */
                            if(reduce != 0){
                                alert("上传失败请重新上传文件")
                            }else{
                                $.ajax({
                                    method:"post",
                                    dataType:"json",
                                    url:"/allhb",
                                    data:{"fileMd5":readyfilemeg["filemd5"],"fileSize":readyfilemeg["filesize"],"fileName":readyfilemeg["filename"]},
                                    success:function (result) {
                                        if(result){
                                            alert("上传成功")
                                        }else{
                                            alert("上传失败")
                                        }
                                    }
                                })
                            }
                        });

                    }
                }
            })

        }

    </script>
</body>
</html>

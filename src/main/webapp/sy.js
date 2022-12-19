/**
 * 用并发请求的方式发出所有文件，本质上是Promise提供的功能，运行上就是用n个并发，将请求体数组中的请求分摊开来发送
 * @param readyfilemeg 请求体数组
 * @param maxNum 最大并发数
 * @returns
 */
function concurRequst(readyfilemeg,maxNum) {
    console.log("进入方法")
    //并发发送请求,resolve是Promise用来回调的结果集
    return new Promise(resolve => {
        console.log("进入并发")
        //文件数据不能为空
        if(readyfilemeg == null){
            console.log("进入空数据检查")
            //Promise类的方法，调用时表示Resolved已完成，又称Fulfilled，参数是结束后可供回调的数据
            resolve([])
            return
        }
        console.log("经过空数据检查")

        //定义一个保存结果的临时数组、一个取文件信息时的临时下标、将文件块信息取出来
        const result = [];
        let index = 0;
        let fileblocks = readyfilemeg["fileblocks"];

        //并发发出请求的方法
        async function request() {
            //当文件块数组中的内容全部发完，结束任务，返回回调结果
            if(index === fileblocks.length){
                resolve([]);
                return;
            }

            //取出文件块，index后移，但另存一份后面要按下标保存结果
            const fileblock = fileblocks[index];
            const i = index;
            index++;

            //!!!!!!!!!!!!!!!!卡在了前后端如何通过fetch 把块数据传递到后台，后台又应该用什么接收。
            //后台还没有开始，详细的步骤卡在了块数据的前台处理，试过base64但网上找到的都转不过来

            // try {
            //     //把文件块的核心信息发到后台并保存返回结果 Blob
            //     const resp = await fetch("http://localhost:91/upload",{
            //         method:"post",
            //         headers: {
            //             'Content-Type': 'application/json'
            //         },
            //         body:{
            //             "blockdata":blockdata,
            //             "blockindex":i
            //         }
            //     })
            //
            //     //后台将结果信息保存在响应头中，拿出来按顺序放到临时的结果集中
            //     result[i]=resp.headers.get("meg")
            // }catch (err){
            //     //出现意外 也要把错误信息加入进去
            //     result[i] = err
            // }finally {
            //     //递归发送下一个请求
            //     request()
            // }
        }

        //用并发数和请求体数组元素个数取最小值控制发送数量
        const t = Math.min(maxNum,fileblocks.length)
        for (let i = 0 ; i < t ; i++){
            request()
        }
    })
}

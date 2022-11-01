package com.wy.bootjsp.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/11/1 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;Base64Controller
 */
@Controller
public class Base64Controller {

    @Value("${imgFilePath}")
    private String imgFilePath;

    @Value("${xs.x}")
    private String xs_x;
    @Value("${xs.y}")
    private String xs_y;

    @RequestMapping("/")
    public String loadMain(Model model){
        model.addAttribute("xs_x",xs_x);
        model.addAttribute("xs_y",xs_y);
        return "zjzs";
    }

    @RequestMapping("/zjzFromBase64")
    @ResponseBody
    public Map getFileFromBase64(String base) throws Exception {
        String base64 = base;
        File file = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (base64 == null || "".equals(base64)) { // 图像数据为空
            resultMap.put("resultCode", 0);
            resultMap.put("msg", "图片数据未接收到");
        } else {
            base64 = base64.substring(base64.indexOf(",")+1);
            base64 = base64.toString().replace("\r\n", "");
            //创建文件目录
            String prefix=".jpg";
            if(!imgFilePath.endsWith("\\")){
                file = new File(imgFilePath+"\\"+System.currentTimeMillis()+prefix);
            }else{
                file = new File(imgFilePath+System.currentTimeMillis()+prefix);
            }
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            try {
                byte[] bytes = Base64.decodeBase64(base64);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bytes);
            }finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            resultMap.put("resultCode", 1);
            resultMap.put("msg", "数据接收");
        }
        return resultMap;
    }


}

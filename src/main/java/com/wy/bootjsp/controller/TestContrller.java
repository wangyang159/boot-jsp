package com.wy.bootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/18 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;TestContrller
 */
@Controller
public class TestContrller {

    @RequestMapping("/upload")
    @ResponseBody
    public void test(@RequestParam("blockdata") MultipartFile blockdata,@RequestParam("blockindex") int blockindex, HttpServletResponse response){
        System.out.println(blockindex);
        response.addHeader("meg","OK"+blockindex);
    }

}

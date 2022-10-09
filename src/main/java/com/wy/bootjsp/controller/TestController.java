package com.wy.bootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @创建人 wangyang
 * @创建时间 2022/10/4
 * @描述
 */
@Controller
public class TestController {

    @RequestMapping("getTest")
    public String getTest(Model model){
        model.addAttribute("data","JSP整合");
        return "test";
    }

}

package com.youwon.kafkademo.jdbcTemplateBiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youwon.kafkademo.jdbcTemplateBiz.service.DemoService;

@Controller
public class DemoJdbcTemplateController {
    @Autowired
    private DemoService demoService;

    // public DemoJdbcTemplateController(DemoService demoService) {
    //     this.demoService = demoService;
    // }


    @GetMapping("/testAPI2")
    @ResponseBody
	public String getData(@RequestBody(required = false) String param) {
        
        String message = "";
        if(param.equals("JDBC")){
            message = "JDBC TEST SUCCESS : ";
            message += demoService.findAllDemo().toString();
        }
        else{
            message ="JDBC TEST Fail";
        }
        System.out.println(message);
        return new String(message);
    }
}

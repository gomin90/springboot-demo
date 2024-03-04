package com.youwon.kafkademo.jpabiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youwon.kafkademo.jpabiz.entity.DemoEntity;
import com.youwon.kafkademo.jpabiz.service.DemoEntityService;

@RestController
public class DemoController {

    @Autowired
    private DemoEntityService demoService;
    
    /**
     * 
     * JPA
     * 
     * @param param
     * @return
     */
	@GetMapping("/testAPI")  
    @ResponseBody  
	public String getMethodName(@RequestBody(required = false) String param) {

        String message = "";
        List<DemoEntity> entity = null;

        if(param.equals("JPA")){
            message = "JPA TEST Success : ";
            entity = demoService.findAll();

            message += entity.toString();
        }
        else if(param.equals("JPA2")){
            message = "JPA2 TEST Success : ";
            entity = demoService.findByNativeQuery();

            message += entity.toString();
        }
        else if(param.equals("jdbcTemplate")){
            message = "JPA TEST Success : ";
        }
        else{
            message = "Fail Test";
        }
        
		return new String(message);
	}

    @PostMapping("/saveData2")
    public DemoEntity saveData2(@RequestBody DemoEntity demoEntity){
        return demoService.save(demoEntity);
    }

}

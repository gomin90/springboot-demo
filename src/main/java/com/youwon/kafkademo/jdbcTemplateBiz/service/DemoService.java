package com.youwon.kafkademo.jdbcTemplateBiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youwon.kafkademo.jdbcTemplateBiz.domain.Demo;
import com.youwon.kafkademo.jdbcTemplateBiz.repository.DemoRepository;

@Service
public class DemoService {

    private DemoRepository demoRepository;
    
    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<Demo> findAllDemo(){        
        List<Demo> result = demoRepository.findAll();
        System.out.println(result.toString());
        return result;
    }

}

package com.youwon.kafkademo.jdbcTemplateBiz.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.youwon.kafkademo.jdbcTemplateBiz.domain.Demo;

@Repository
public class DemoRepository {

    private JdbcTemplate jdbcTemplate;

    public DemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Demo> findAll() {

        String queryString = "select d.id as id, d.value as value from common.test_table d order by d.id asc";
        return jdbcTemplate.query(queryString, new BeanPropertyRowMapper<>(Demo.class));
    }

}

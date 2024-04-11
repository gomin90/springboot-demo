package com.youwon.kafkademo.jdbcTemplateBiz.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public Demo save(Demo demo){
        String queryString = "insert into common.test_table (id, value) values (DEFAULT, ?) RETURNING id";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, demo.getValue());
            return ps;
        }, keyHolder);

        demo.setId(keyHolder.getKey().longValue());

        return demo;
    }

}

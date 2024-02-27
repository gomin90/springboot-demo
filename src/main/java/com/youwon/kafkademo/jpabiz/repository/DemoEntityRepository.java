package com.youwon.kafkademo.jpabiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.youwon.kafkademo.jpabiz.entity.DemoEntity;

@Repository
public interface DemoEntityRepository extends JpaRepository<DemoEntity, Long>{
    @Query("select d.id as id, d.value as vlue from DemoEntity d order by d.id asc")
    List<Object[]> findByNativeQuery();
} 

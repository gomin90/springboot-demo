package com.youwon.kafkademo.jpabiz.service;

import com.youwon.kafkademo.jpabiz.entity.DemoEntity;
import com.youwon.kafkademo.jpabiz.repository.DemoEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemoEntityService {
    @Autowired
    private final DemoEntityRepository demoEntityRepository;

    public DemoEntityService(DemoEntityRepository demoEntityRepository) {
        this.demoEntityRepository = demoEntityRepository;
    }
    
    public List<DemoEntity> findAll() {
        return demoEntityRepository.findAll();
    }

    public Optional<DemoEntity> findById(Long id) {
        return demoEntityRepository.findById(id);
    }

    public DemoEntity save(DemoEntity demoEntity) {
        return demoEntityRepository.save(demoEntity);
    }

    public void deleteById(Long id) {
        demoEntityRepository.deleteById(id);
    }

    /**
     * 직접 쿼리하는 방식으로 호출
     */
    public List<DemoEntity> findByNativeQuery() {

        List<Object[]> results = demoEntityRepository.findByNativeQuery();
        List<DemoEntity> entities = results.stream()
                .map(result -> new DemoEntity((Long) result[0], (String) result[1])) // 예상되는 엔터티 생성자에 따라 수정해야 함
                .collect(Collectors.toList());

        return entities;
    }
}

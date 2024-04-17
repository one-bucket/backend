package com.onebucket.domain.testApi.mongoDBTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoDBTestService {

    private final MongoDbTestRepository mongoRepository;

    public List<MongoDBTestDomain> findAll() {
        return mongoRepository.findAll();
    }

    public MongoDBTestDomain findById(String id) {
        return mongoRepository.findById(id).orElse(null);
    }

    public MongoDBTestDomain save(MongoDBTestDomain mongoDBTestDomain) {
        return mongoRepository.save(mongoDBTestDomain);
    }

    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }
}

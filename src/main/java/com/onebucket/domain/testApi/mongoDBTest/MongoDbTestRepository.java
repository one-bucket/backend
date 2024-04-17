package com.onebucket.domain.testApi.mongoDBTest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDbTestRepository extends MongoRepository<MongoDBTestDomain, String> {
}

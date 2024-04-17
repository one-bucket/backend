package com.onebucket.domain.testApi.mongoDBTest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/mongo")
@RequiredArgsConstructor
public class MongoDbTestController {
    private final MongoDBTestService mongoService;

    @GetMapping("/get-all")
    public List<MongoDBTestDomain> getAll(){
        return mongoService.findAll();
    }

    @GetMapping("/search/{id}")
    public MongoDBTestDomain getById(@PathVariable String id) {
        return mongoService.findById(id);
    }

    @PostMapping("/save")
    public MongoDBTestDomain create(@RequestBody MongoDBTestDomain mongoDBTestDomain) {
        return mongoService.save(mongoDBTestDomain);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        mongoService.deleteById(id);
        return ResponseEntity.ok("success");
    }
}

package com.onebucket.domain.testApi.mongoDBTest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "testDomain")
public class MongoDBTestDomain {
    @Id
    private String id;
    private String title;
    private String text;
    private int number;

}

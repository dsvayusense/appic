package com.vayusense.appic.config;

import com.vayusense.appic.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class IndexConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndex(){
        mongoTemplate.indexOps(State.class).ensureIndex(new Index().on("batchId", Sort.Direction.ASC));
        mongoTemplate.indexOps(State.class).ensureIndex(new Index().on("batchStartDate", Sort.Direction.ASC));
        mongoTemplate.indexOps(State.class).ensureIndex(new Index().on("batchSerialNumber", Sort.Direction.ASC));
        mongoTemplate.indexOps(State.class).ensureIndex(new Index().on("BatchAgeInMin", Sort.Direction.ASC));

    }
}

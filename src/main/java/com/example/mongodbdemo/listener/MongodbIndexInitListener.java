package com.example.mongodbdemo.listener;

import jakarta.annotation.Resource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
public class MongodbIndexInitListener {

    MongoTemplate mongoTemplate;

    @Resource
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initIndexAfterStartup() {
        var mappingContext = mongoTemplate.getConverter().getMappingContext();
        var resolver = new MongoPersistentEntityIndexResolver(mappingContext);
        mappingContext.getPersistentEntities()
                .stream()
                .filter(i -> i.isAnnotationPresent(Document.class))
                .forEach(i -> {
                    IndexOperations indexOps = mongoTemplate.indexOps(i.getType());
                    resolver.resolveIndexFor(i.getType()).forEach(indexOps::ensureIndex);
                });
    }

}

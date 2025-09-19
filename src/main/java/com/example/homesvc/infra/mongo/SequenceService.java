package com.example.homesvc.infra.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {
    private final MongoOperations ops;
    public SequenceService(MongoOperations ops){
        this.ops = ops;
    }
    public long next(String name){
        var q = Query.query(Criteria.where("_id").is(name));
        var u = new Update().inc("seq", 1);
        var o = FindAndModifyOptions.options().upsert(true).returnNew(true);
        var doc = ops.findAndModify(q, u, o, DbSequence.class);
        return doc.seq;
    }
    @Data
    @Document
    static class Counter{
        @Id
        String id;
        long seq;
    }
}

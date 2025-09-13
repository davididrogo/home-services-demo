package com.example.homesvc.infra.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("seq")
public class DbSequence {
    @Id
    public String id;
    public Long seq;
    public DbSequence(){}
    public DbSequence(String id, long seq){
        this.id = id;
        this.seq = seq;
    }
}

package com.example.homesvc.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

//@Document
//@Data
public class Acc {
    //@Id
    //private String id;
    public BigDecimal price = BigDecimal.ZERO;
    public BigDecimal taxes = BigDecimal.ZERO;
    public BigDecimal surCharges = BigDecimal.ZERO;
    public final StringBuilder notes = new StringBuilder();

    public void note(String n) {
        if(notes.length() > 0)
            notes.append("; ");
        notes.append(n);
    }
}

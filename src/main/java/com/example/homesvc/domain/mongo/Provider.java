package com.example.homesvc.domain.mongo;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Set;

@Document("providers")
@CompoundIndex(name = "region_skill", def = "{'region': 1, 'skills': 1}")
@AllArgsConstructor
@Getter
@Setter
public class Provider {
    @Id
    public String id;
    private String name;
    public Region region;
    public Set<ServiceType> skills = EnumSet.noneOf(ServiceType.class);
    public BigDecimal hourlyRate;
    public boolean licensed;
    public int reputation;
    public Provider(){}
}

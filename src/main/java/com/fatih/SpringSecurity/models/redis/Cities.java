package com.fatih.SpringSecurity.models.redis;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@RedisHash("cities")
public class Cities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private Integer plateCode;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(Integer plateCode) {
        this.plateCode = plateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
                ", plateCode=" + plateCode +
                ", name='" + name + '\'' +
                '}';
    }
}

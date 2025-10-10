package com.app.guimscore.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ApiKey {
    private UUID id;
    private String key;
    private String name;
    private Date createdData;

    public ApiKey(String name, String key) {
        this.id = UUID.randomUUID();
        this.key = key;
        this.name = name;
        this.createdData = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedData() {
        return createdData;
    }
    public void setCreatedData(Date createdData) {
        this.createdData = createdData;
    }
}

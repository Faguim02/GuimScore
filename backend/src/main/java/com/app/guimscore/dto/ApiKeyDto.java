package com.app.guimscore.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ApiKeyDto {
    private UUID id;
    private String key;
    private String name;
    private String createdData;

    public ApiKeyDto(UUID id, String key, String name, String createdData) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.createdData = createdData;
    }

    public ApiKeyDto(String createdData) {
        this.createdData = createdData;
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

    public String getCreatedData() {
        return createdData;
    }
}

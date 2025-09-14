package com.app.guimscore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "player")
public class Player {
    @Id
    private UUID id;
    private String name;
    private String password;
    private Date dateOfBirth;

    private UUID gameServerId;

    private List<ItemsModel> items;
    private List<DataModel> data;
}

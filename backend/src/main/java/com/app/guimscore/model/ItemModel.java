package com.app.guimscore.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "item")
public class ItemModel {
    @Id
    private UUID uuid;
    @NotNull(message = "É necessario preencher o nome do item")
    private String itemName;
    private String itemDescription;
    private ItemsModel listItem;

    public ItemModel(String itemName, String itemDescription) {
        this.uuid = UUID.randomUUID();
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public ItemModel() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ItemsModel getListItem() {
        return listItem;
    }

    public void setListItem(ItemsModel listItem) {
        this.listItem = listItem;
    }
}

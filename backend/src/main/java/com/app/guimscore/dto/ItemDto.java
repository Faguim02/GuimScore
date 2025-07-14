package com.app.guimscore.dto;

import com.app.guimscore.model.ItemsModel;

import java.util.UUID;

public class ItemDto {
    private UUID uuid;
    private String itemName;
    private String itemDescription;
    private ItemsModel listItem;

    public ItemDto() {
        this.uuid = UUID.randomUUID();
    }

    public ItemDto(String itemName, String itemDescription) {
        this.uuid = UUID.randomUUID();
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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

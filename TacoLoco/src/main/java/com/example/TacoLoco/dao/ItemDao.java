package com.example.TacoLoco.dao;

import java.util.UUID;

import com.example.TacoLoco.model.Item;

public interface ItemDao {

    int insertItem(UUID id, Item item);

    default int addItem(Item item){
        UUID id = UUID.randomUUID();
        return insertItem(id, item);
    }

    

}

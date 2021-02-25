package com.example.TacoLoco.service;

import com.example.TacoLoco.dao.ItemDao;
import com.example.TacoLoco.model.Item;

public class ItemService {

    private final ItemDao itemDao;
    
    public ItemService(ItemDao itemDao){
        this.itemDao = itemDao;
    }
    public int addItem(Item item){
        return itemDao.insertItem(item);
    }
}

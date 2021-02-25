package com.example.TacoLoco.service;

import com.example.TacoLoco.dao.ItemDao;
import com.example.TacoLoco.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemService(@Qualifier("itemDao") ItemDao itemDao){
        this.itemDao = itemDao;
    }
    public int addItem(Item item){
        return itemDao.insertItem(item);
    }
}
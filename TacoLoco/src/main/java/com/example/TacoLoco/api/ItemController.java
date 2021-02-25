package com.example.TacoLoco.api;

import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.service.ItemService;

public class ItemController {
    
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    public void addItem(Item item){
        itemService.addItem((item));
    }
}

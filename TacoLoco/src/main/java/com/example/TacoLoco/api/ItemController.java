package com.example.TacoLoco.api;

import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    
    private final ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    
    @PostMapping
    public void addItem(Item item){
        itemService.addItem((item));
    }
}

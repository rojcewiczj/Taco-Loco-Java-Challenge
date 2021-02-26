package com.example.TacoLoco.api;

import java.util.List;

import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.model.ShoppingCart;
import com.example.TacoLoco.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/item")
@RestController
public class ItemController {
    
    private final ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    
    @PostMapping
    public void addItem(@RequestBody Item item){
        itemService.addItem((item));
        
    }

    @GetMapping
    public ShoppingCart getCart(){
        return itemService.returnCart();
    }
    
}

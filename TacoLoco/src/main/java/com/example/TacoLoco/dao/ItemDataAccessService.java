package com.example.TacoLoco.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.model.ShoppingCart;

import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDataAccessService implements ItemDao {
    
    private static List<Item>  DB = new ArrayList<>();

    @Override
    public int insertItem(UUID id, Item item){
        
        DB.add(new Item(id, item.getName(), item.getPrice(),item.getQuantity()));
        return 1;
    }
    
    @Override
    public ShoppingCart returnCart(){

        int totalQuantity = 0;
        
        double totalCost = 0.00;

        for(Item item : DB){
            totalQuantity += item.getQuantity();
            totalCost += (item.getPrice() * item.getQuantity());
        }
        if(totalQuantity >= 4){
            totalCost = totalCost * .8;
        }
        
        String result = String.format("%.2f", totalCost);
        
        ShoppingCart cart = new ShoppingCart(DB, result);

        return cart;

    }

}

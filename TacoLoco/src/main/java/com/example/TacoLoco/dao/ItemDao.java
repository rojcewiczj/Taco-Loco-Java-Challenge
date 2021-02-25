package com.example.TacoLoco.dao;

import java.util.List;
import java.util.UUID;

import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.model.ShoppingCart;

public interface ItemDao {

    int insertItem(UUID id, Item item);

    default int insertItem(Item item){
        UUID id = UUID.randomUUID();
        return insertItem(id, item);
    }

    List<Item> shoppingCart();

    ShoppingCart returnTotal();

}

package com.example.TacoLoco.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.TacoLoco.model.Item;

import org.springframework.stereotype.Repository;

@Repository
public class ItemDataAccessService implements ItemDao {
    
    private static List<Item>  DB = new ArrayList<>();
    
    @Override
    public int insertItem(UUID id, Item item){
        DB.add(new Item(id, item.getName(), item.getPrice()));
        return 1;
    }
}

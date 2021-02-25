package com.example.TacoLoco.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
    
    private final UUID id;

    private final String name;

    private final BigDecimal price;

    public Item(UUID id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public BigDecimal getPrice(){
        return price;
    }
}

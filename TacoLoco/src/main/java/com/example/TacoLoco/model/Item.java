package com.example.TacoLoco.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    
    private final UUID id;

    private final String name;

    private final double price;

    private final int quantity;

    public Item(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("quantity") int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }
}

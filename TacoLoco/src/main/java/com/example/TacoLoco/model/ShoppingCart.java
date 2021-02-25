package com.example.TacoLoco.model;

import java.util.List;

public class ShoppingCart {
    
    public final List<Item> AllItems;

    public final String totalCost;

    public ShoppingCart(List<Item> AllItems, String totalCost){
        this.AllItems = AllItems;
        this.totalCost = totalCost;
    }
}

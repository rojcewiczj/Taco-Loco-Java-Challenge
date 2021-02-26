package com.example.TacoLoco.model;

import java.util.List;

public class ShoppingCart {
    
    public final List<Item> Cart;

    public final String Total;

    public ShoppingCart(List<Item> Cart, String Total){
        this.Cart = Cart;
        this.Total = Total;
    }
}

package com.example.listen_my_order.activities;

import java.io.Serializable;

public class MenuData implements Serializable {

    private String name;
    private String content;
    private float price;

    public MenuData(String name, String content, float price){
        this.name = name;
        this.content = content;
        this.price = price;
    }

    public String getName() {return this.name;}
    public void setName(final String name) {this.name = name;}

    public String getContent() {return this.content;}
    public void setContent(final String content) {this.content = content;}

    public float getPrice() {return this.price;}
    public void setPrice(final float price) {this.price = price;}
}

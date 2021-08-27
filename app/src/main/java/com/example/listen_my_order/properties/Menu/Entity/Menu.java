package com.example.listen_my_order.properties.Menu.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Menu")
public class Menu {
    @PrimaryKey(autoGenerate = true)
    private int menuId;
    @ColumnInfo(name = "menuName")
    private String menuName;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name="description")
    private String description;

    public Menu(String menuName, String price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(final String menuName) {
        this.menuName = menuName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(final int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "id : " + this.menuId + " name : " + this.menuName + " price: " + this.price + " description : " + this.description;
    }
}

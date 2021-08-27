package com.example.listen_my_order.properties.Menu.Functions;

import android.content.Context;

import com.example.listen_my_order.properties.Menu.Database.MenuDatabase;

public class MenuFunction {
    private MenuDatabase menuDatabase;

    public MenuFunction(Context context) {
        menuDatabase = MenuDatabase.getAppDatabase(context);
    }

    public String getMenus() {
        return "";
    }

    public MenuDatabase getMenuDatabase() {
        return this.menuDatabase;
    }

    public void setMenuDatabase(final MenuDatabase menuDatabase) {
        this.menuDatabase = menuDatabase;
    }
}

package com.example.listen_my_order.properties.Menu.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listen_my_order.properties.Menu.Entity.Menu;

import java.util.List;

@Dao
public interface MenuDAO {
    @Insert
    public void insertMenus(Menu... menus);

    @Insert
    public void insertMenu(Menu menu);

    @Update
    public void updateMenu(Menu... menus);

    @Delete
    public void deleteMenus(Menu menu);

    @Query("SELECT * FROM menu")
    public LiveData<List<Menu>> loadAllMenus();
}

package com.example.listen_my_order.properties.Menu.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.listen_my_order.properties.Menu.DAO.MenuDAO;
import com.example.listen_my_order.properties.Menu.Entity.Menu;

@Database(entities = {Menu.class}, version = 1)
public abstract class MenuDatabase extends RoomDatabase {
    private static MenuDatabase DBInstance;
    public abstract MenuDAO menuDAO();
    public static MenuDatabase getAppDatabase(Context context) {
        if(DBInstance == null) {
            DBInstance = Room.databaseBuilder(context, MenuDatabase.class, "menu-db").build();
        }
        return DBInstance;
    }
    public static void destroyInstance() {
        DBInstance = null;
    }

}

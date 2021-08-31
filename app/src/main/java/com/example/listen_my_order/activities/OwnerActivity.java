package com.example.listen_my_order.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.listen_my_order.R;
import com.example.listen_my_order.adapters.ExportMenuAdapter;
import com.example.listen_my_order.properties.Menu.DAO.MenuDAO;
import com.example.listen_my_order.properties.Menu.Database.MenuDatabase;
import com.example.listen_my_order.properties.Menu.Entity.Menu;

import java.util.ArrayList;
import java.util.List;

import euphony.lib.receiver.EuRxManager;
import euphony.lib.transmitter.EuTxManager;

public class OwnerActivity extends AppCompatActivity {
    // Properties
    boolean speakOn = false;

    // Components
    private ActionBar appbar;
    private Button btn_export_menu, btn_add;
    private ImageView iv_back;
    private EditText storeName;
    private OnClickListener onClickListener = new OnClickListener();
    private EuTxManager euTxManager = new EuTxManager();
    // For menuList
    private RecyclerView rv_menu;
    private LinearLayoutManager linearLayoutManager;
    private ExportMenuAdapter exportMenuAdapter;
    private ArrayList<MenuData> menuList = new ArrayList<MenuData>();
    // Dialogs
    private Dialog dialog_new_menu;

    //Room Database
    private MenuDatabase menuDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        // Set ActionBar
        appbar = getSupportActionBar();
        appbar.setTitle("Set Menu list");

        // Set components
        this.btn_export_menu = (Button)findViewById(R.id.btn_export_menu);
        this.btn_add = (Button)findViewById(R.id.btn_add);
        this.iv_back = (ImageView)findViewById(R.id.iv_back);
        this.storeName = (EditText)findViewById(R.id.et_store_name);
        this.rv_menu = (RecyclerView)findViewById(R.id.rv_menu);

        // Set recyclerView
        this.linearLayoutManager = new LinearLayoutManager(this);
        this.rv_menu.setLayoutManager(this.linearLayoutManager);

        // Set adapter
        this.exportMenuAdapter = new ExportMenuAdapter(this.menuList);
        this.rv_menu.setAdapter(this.exportMenuAdapter);

        // Set onClickListeners
        this.btn_export_menu.setOnClickListener(this.onClickListener);
        this.btn_add.setOnClickListener(this.onClickListener);
        this.iv_back.setOnClickListener(this.onClickListener);

        //Set Room Database
        menuDatabase = MenuDatabase.getAppDatabase(OwnerActivity.this);

        menuDatabase.menuDAO().loadAllMenus().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                if (menuList.size() == 0) {
                    for (int i = 0; i < menus.size(); i++) {
                        float price = Float.parseFloat(menus.get(i).getPrice());
                        MenuData menuData = new MenuData((menus.get(i).getMenuName()), (menus.get(i).getDescription()), price);
                        menuList.add(i, menuData);
                    }
                }
                else {
                    float price = Float.parseFloat(menus.get(menus.size() - 1).getPrice());
                    MenuData menuData = new MenuData((menus.get(menus.size() - 1).getMenuName()), (menus.get(menus.size() - 1).getDescription()), price);
                    menuList.add(menuData);
                }

                exportMenuAdapter.notifyDataSetChanged();

            }
        });
    }

    // OnclickListener
    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_export_menu:
                    exportMenu();
                    break;
                case R.id.btn_add:
                    showAddMenu();
                    System.out.println("showAddMenu");
                    break;
                case R.id.iv_back:
                    finish();
                    break;
            }
        }
    }

    // Private methods
    private void showAddMenu() {
        System.out.println("showAddMenu");

        this.dialog_new_menu = new Dialog(OwnerActivity.this);
        this.dialog_new_menu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog_new_menu.setContentView(R.layout.dialog_new_menu);

        // Components
        EditText et_name = (EditText) dialog_new_menu.findViewById(R.id.et_name);
        EditText et_content = (EditText) dialog_new_menu.findViewById(R.id.et_content);
        EditText et_price = (EditText) dialog_new_menu.findViewById(R.id.et_price);
        Button btn_ok = (Button) dialog_new_menu.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) dialog_new_menu.findViewById(R.id.btn_cancel);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get input
                String name = et_name.getText().toString();
                String content = et_content.getText().toString();
                float price = Float.parseFloat(et_price.getText().toString());

                // Exception
                if(name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter food name", Toast.LENGTH_SHORT).show();
                    return;
                }else if(content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter explain about food", Toast.LENGTH_SHORT).show();
                    return;
                }else if(price == 0){
                    Toast.makeText(getApplicationContext(), "Please enter food price", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add new menu in rv_menu
                MenuData menuData = new MenuData(name, content, price);
                //menuList.add(menuData);
                new InsertAsyncTask(menuDatabase.menuDAO()).execute(new Menu(menuData.getName(), Float.toString(menuData.getPrice()), menuData.getContent()));
                //exportMenuAdapter.notifyDataSetChanged();

                dialog_new_menu.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_new_menu.dismiss();
            }
        });
        dialog_new_menu.show();
    }

    private void exportMenu(){
        if(speakOn){
            euTxManager.stop();
            btn_export_menu.setText("Export\nMenu");
            speakOn = false;
        }else{
            // To generate acoustic data
            euTxManager.euInitTransmit(storeName.getText().toString());

            int index = 0;
            for(MenuData menuData : menuList){
                euTxManager.euInitTransmit("\n");
                euTxManager.euInitTransmit(Integer.toString(index));
                euTxManager.euInitTransmit(" ");
                euTxManager.euInitTransmit(menuData.getName());
                euTxManager.euInitTransmit(" ");
                euTxManager.euInitTransmit(menuData.getContent());
                euTxManager.euInitTransmit(" ");
                euTxManager.euInitTransmit(Float.toString(menuData.getPrice()));
                index++;
            }
            
//            euTxManager.process(-1);
            btn_export_menu.setText("Exporting\nMenu...");
            speakOn = true;
        }
    }

    //Function for async tasks
    public static class InsertAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDAO menuDAO;

        public InsertAsyncTask(MenuDAO m) {
            this.menuDAO = m;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDAO.insertMenu(menus[0]);
            return null;
        }
    }
}
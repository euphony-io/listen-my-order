package com.example.listen_my_order.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.listen_my_order.R;

import java.util.ArrayList;

public class OwnerActivity extends AppCompatActivity {

    // Components
    private Button btn_export_menu, btn_add;
    private ImageView iv_back;
    private OnClickListener onClickListener;
    // For menuList
    private RecyclerView rv_menu;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<MenuData> menuList;
    private MenuAdapter menuAdapter;
    // Dialogs
    private Dialog dialog_new_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        onClickListener = new OnClickListener();

        // Set components
        this.btn_export_menu = (Button)findViewById(R.id.btn_export_menu);
        this.btn_add = (Button)findViewById(R.id.btn_add);
        this.iv_back = (ImageView)findViewById(R.id.iv_back);
        this.rv_menu = (RecyclerView)findViewById(R.id.rv_menu);

        // Set recyclerView
        this.linearLayoutManager = new LinearLayoutManager(this);
        this.rv_menu.setLayoutManager(this.linearLayoutManager);
        this.menuList = new ArrayList<MenuData>();

        // Set adapter
        this.menuAdapter = new MenuAdapter(this.menuList);
        this.rv_menu.setAdapter(this.menuAdapter);

        // Set onClickListeners
        this.btn_export_menu.setOnClickListener(this.onClickListener);
        this.btn_add.setOnClickListener(this.onClickListener);
        this.iv_back.setOnClickListener(this.onClickListener);
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

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog_new_menu.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        this.dialog_new_menu.getWindow().setAttributes(lp);

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
                menuList.add(menuData);
                menuAdapter.notifyDataSetChanged();

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

    }
}
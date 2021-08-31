package com.example.listen_my_order.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listen_my_order.R;
import com.example.listen_my_order.adapters.ImportMenuAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import euphony.lib.receiver.AcousticSensor;
import euphony.lib.receiver.EuRxManager;

public class ImportMenuActivity extends AppCompatActivity {
    // Components
    private ActionBar appbar;
    private Button setImportButton;
    private TextView storeNameView;

    // For menuList
    private RecyclerView menuListView;
    private ImportMenuAdapter importMenuAdapter;
    private ArrayList<MenuData> menuList = new ArrayList<>();

    // Dialog
    private Dialog menuInfoDialog;

    // Properties
    private boolean listenOn = true;
    private EuRxManager mRxManager = new EuRxManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_menu);

        // Set ActionBar
        appbar = getSupportActionBar();
        appbar.setTitle("Menu list");
        appbar.setDisplayHomeAsUpEnabled(true);

        // Set components
        setImportButton = (Button) findViewById(R.id.btn_set_import);
        storeNameView = (TextView) findViewById(R.id.tv_store_name);
        menuListView = (RecyclerView) findViewById(R.id.rv_menu_list);
        menuListView.setLayoutManager(new LinearLayoutManager(this));

        // Set adapter to recyclerView
        importMenuAdapter = new ImportMenuAdapter(menuList);
        menuListView.setAdapter(importMenuAdapter);

        // setOnClickListener for setImportButton
        setImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenOn) {
                    mRxManager.finish();
                    setImportButton.setText(R.string.btn_start_listen);
                    listenOn = false;
                }
                else {
                    mRxManager.listen();
                    setImportButton.setText(R.string.btn_stop_listen);
                    listenOn = true;
                }
            }
        });

        // setOnClickListener for menuList itemView
        importMenuAdapter.setOnItemClickListener(new ImportMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                menuInfoDialog = new Dialog(ImportMenuActivity.this);
                menuInfoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                menuInfoDialog.setContentView(R.layout.dialog_menu_info);

                final MenuData item = menuList.get(position);

                TextView menuName = (TextView) menuInfoDialog.findViewById(R.id.tv_menu_name);
                TextView menuContent = (TextView) menuInfoDialog.findViewById(R.id.tv_menu_content);
                TextView menuPrice = (TextView) menuInfoDialog.findViewById(R.id.tv_menu_price);
                Button addButton = (Button) menuInfoDialog.findViewById(R.id.btn_add);
                Button cencelButton = (Button) menuInfoDialog.findViewById(R.id.btn_cancel);

                menuName.setText(item.getName());
                menuContent.setText(item.getContent());
                menuPrice.setText("$ " + Float.toString(item.getPrice()));

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Todo: checkbox가 check되도록
                    }
                });

                cencelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menuInfoDialog.dismiss();
                    }
                });

                menuInfoDialog.show();
            }
        });

        // setOnClickListener for menuList checkBox
        importMenuAdapter.setOnCheckBoxClickListener(new ImportMenuAdapter.OnCheckBoxClickListener() {
            @Override
            public void onItemClick(CheckBox checkBox, int position) {
                if(checkBox.isChecked()) {
                    //Todo: menu 장바구니에 넣기
                } else {
                    //Todo: menu 장바구니에서 빼기
                }
            }
        });

        mRxManager.setAcousticSensor(new AcousticSensor() {
            @Override
            public void notify(String letters) {
                ArrayList<String> datas = new ArrayList<>(Arrays.asList(letters.split("\n")));
                storeNameView.setText(datas.remove(0));

                for(String menu : datas) {
                    String[] menuInfo = menu.split(" ");
                    MenuData menuData = new MenuData(menuInfo[1], menuInfo[2], Float.parseFloat(menuInfo[3]));
                    menuList.add(menuData);
                }
                importMenuAdapter.notifyDataSetChanged();

                Toast.makeText(ImportMenuActivity.this, "Finish importing!", Toast.LENGTH_SHORT).show();

                mRxManager.finish();
                setImportButton.setText(R.string.btn_start_listen);
                listenOn = false;
            }
        });
        mRxManager.listen();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
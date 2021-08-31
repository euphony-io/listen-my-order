package com.example.listen_my_order.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private RecyclerView menuListView;
    private ImportMenuAdapter importMenuAdapter;
    private ArrayList<MenuData> menuList = new ArrayList<>();

    // Properties
    private boolean mode = true;
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
        importMenuAdapter = new ImportMenuAdapter(menuList);
        menuListView.setAdapter(importMenuAdapter);

        setImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode) {
                    mRxManager.finish();
                    setImportButton.setText(R.string.btn_start_listen);
                    mode = false;
                }
                else {
                    mRxManager.listen();
                    setImportButton.setText(R.string.btn_stop_listen);
                    mode = true;
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

                Toast.makeText(getApplicationContext(), "Finish importing!", Toast.LENGTH_SHORT).show();

                mRxManager.finish();
                setImportButton.setText(R.string.btn_start_listen);
                mode = false;
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
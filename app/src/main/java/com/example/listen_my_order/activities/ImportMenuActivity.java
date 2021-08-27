package com.example.listen_my_order.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.listen_my_order.R;

import java.util.ArrayList;

import euphony.lib.receiver.AcousticSensor;
import euphony.lib.receiver.EuRxManager;

public class ImportMenuActivity extends AppCompatActivity {

    private TextView storeNameView;
    private RecyclerView menuListView;

    private EuRxManager mRxManager = new EuRxManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_menu);

        storeNameView = (TextView) findViewById(R.id.tv_store_name);
        menuListView = (RecyclerView) findViewById(R.id.rv_menu_list);
        menuListView.setLayoutManager(new LinearLayoutManager(this));

        mRxManager.setAcousticSensor(new AcousticSensor() {
            @Override
            public void notify(String letters) {
                storeNameView.setText(letters); //Todo: letters에서 storeName만 분리해 저장

                ArrayList<String> menuList = new ArrayList<>();
                menuList.add(letters); //Todo: letters에서 menu를 각각 분리해 menuList에 저장

                ImportMenuAdapter adapter = new ImportMenuAdapter(menuList);
                menuListView.setAdapter(adapter);
            }
        });
        mRxManager.listen();
    }
}
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

import com.example.listen_my_order.R;
import com.example.listen_my_order.adapters.ImportMenuAdapter;
import java.util.ArrayList;

import euphony.lib.receiver.AcousticSensor;
import euphony.lib.receiver.EuRxManager;

public class ImportMenuActivity extends AppCompatActivity {
    // Components
    private ActionBar appbar;
    private Button setImportButton;
    private TextView storeNameView;
    private RecyclerView menuListView;

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
                storeNameView.setText(letters); //Todo: letters에서 storeName만 분리해 저장

                ArrayList<String> menuList = new ArrayList<>();
                menuList.add(letters); //Todo: letters에서 menu를 각각 분리해 menuList에 저장

                ImportMenuAdapter adapter = new ImportMenuAdapter(menuList);
                menuListView.setAdapter(adapter);
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
package com.example.listen_my_order.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listen_my_order.R;
import com.example.listen_my_order.adapters.ExportOrderAdapter;

import java.util.ArrayList;

import euphony.lib.transmitter.EuTxManager;

public class ExportOrderActivity extends AppCompatActivity {
    // Components
    private ActionBar appbar;
    private Button exportButton;
    private TextView totalPriceView;

    // For orderList
    private RecyclerView orderListView;
    private ExportOrderAdapter exportOrderAdapter;
    private ArrayList<MenuData> orderList = new ArrayList<>();

    // Properties
    private float priceSum = 0;
    private boolean speakOn = false;
    private EuTxManager euTxManager = new EuTxManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_order);

        Intent intent = getIntent();
        orderList = (ArrayList<MenuData>) intent.getSerializableExtra("OrderList");

        // Set ActionBar
        appbar = getSupportActionBar();
        appbar.setTitle("Order menu");
        appbar.setDisplayHomeAsUpEnabled(true);

        // Set components
        exportButton = (Button) findViewById(R.id.btn_export_order);
        totalPriceView = (TextView) findViewById(R.id.tv_total_price);
        orderListView = (RecyclerView) findViewById(R.id.rv_order_list);
        orderListView.setLayoutManager(new LinearLayoutManager(this));

        // Set adapter to recyclerView
        exportOrderAdapter = new ExportOrderAdapter(orderList);
        orderListView.setAdapter(exportOrderAdapter);

        // Set total price view
        for(MenuData order : orderList) {
            priceSum += order.getPrice();
        }
        totalPriceView.setText("$ " + Float.toString(priceSum));

        // setOnClickListener for exportButton
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(speakOn) {
                    euTxManager.stop();
                    exportButton.setText(R.string.btn_export_order);
                    speakOn = false;
                }
                else {
                    String message = "";
                    message += Float.toString(priceSum);

                    for(MenuData order : orderList) {
                        message += "\n" + order.getName();
                    }

                    euTxManager.euInitTransmit(message);
                    euTxManager.process(-1);

                    exportButton.setText(R.string.btn_exporting_order);
                    speakOn = true;
                }
            }
        });
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

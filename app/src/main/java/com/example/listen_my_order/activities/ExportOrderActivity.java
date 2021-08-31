package com.example.listen_my_order.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listen_my_order.R;

import java.util.ArrayList;

public class ExportOrderActivity extends AppCompatActivity {

    private ArrayList<MenuData> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_order);

        Intent intent = getIntent();
        orderList = (ArrayList<MenuData>) intent.getSerializableExtra("OrderList");
    }
}

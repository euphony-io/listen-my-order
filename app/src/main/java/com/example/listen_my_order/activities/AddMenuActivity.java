package com.example.listen_my_order.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listen_my_order.R;
import com.example.listen_my_order.properties.Menu.DAO.MenuDAO;
import com.example.listen_my_order.properties.Menu.Database.MenuDatabase;
import com.example.listen_my_order.properties.Menu.Entity.Menu;

import java.util.List;

public class AddMenuActivity extends AppCompatActivity {
    private EditText menuNameEditText;
    private EditText menuPriceEditText;
    private EditText menuDescriptionEditText;
    private Button addMenuButton;
    private MenuDatabase menuDatabase;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        menuNameEditText = findViewById(R.id.et_menu_name);
        menuPriceEditText = findViewById(R.id.et_menu_price);
        addMenuButton = findViewById(R.id.btn_add_menu);
        resultTextView = findViewById(R.id.tv_menu_result);
        menuDescriptionEditText = findViewById(R.id.et_menu_description);
        menuDatabase = MenuDatabase.getAppDatabase(AddMenuActivity.this);

        menuDatabase.menuDAO().loadAllMenus().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                resultTextView.setText(menus.toString());
            }
        });

        resultTextView.setText(menuDatabase.menuDAO().loadAllMenus().toString());

        addMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuNameEditText.getText().toString().trim().length() <= 0 || menuPriceEditText.getText().toString().trim().length() <= 0) {
                    Toast.makeText(AddMenuActivity.this, "입력 오류", Toast.LENGTH_SHORT).show();
                }
                else {
                    new InsertAsyncTask(menuDatabase.menuDAO()).execute(new Menu(menuNameEditText.getText().toString(), menuPriceEditText.getText().toString(), menuDescriptionEditText.getText().toString()));
                    menuPriceEditText.setText("");
                    menuNameEditText.setText("");
                    menuDescriptionEditText.setText("");
                }
            }
        });
    }
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
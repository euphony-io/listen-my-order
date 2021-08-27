package com.example.listen_my_order.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import com.example.listen_my_order.R;
import com.example.listen_my_order.properties.Menu.DAO.MenuDAO;
import com.example.listen_my_order.properties.Menu.Database.MenuDatabase;
import com.example.listen_my_order.properties.Menu.Entity.Menu;

import java.util.List;

public class AddMenuActivity extends AppCompatActivity {
    private EditText menuNameEditText;
    private EditText menuPriceEditText;
    private Button addMenuButton;
    private MenuDatabase menuDatabase;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        menuNameEditText = findViewById(R.id.menu_name_edit_text);
        menuPriceEditText = findViewById(R.id.menu_price_edit_text);
        addMenuButton = findViewById(R.id.add_menu_button);
        resultTextView = findViewById(R.id.menu_result_text_view);
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
                    new InsertAsyncTask(menuDatabase.menuDAO()).execute(new Menu(menuNameEditText.getText().toString(), menuPriceEditText.getText().toString()));
                    menuPriceEditText.setText("");
                    menuNameEditText.setText("");
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
package com.example.bgsamz.igidgets;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class UpdateList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView shoppingListView = (ListView) findViewById(R.id.updateItemListView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        shoppingListView.setAdapter(adapter);

        onClick(adapter);
    }

    public void onClick(final ArrayAdapter<String> adapter) {
        Button addItemButton = (Button) findViewById(R.id.addItemButton);
        Button updateListButton = (Button) findViewById(R.id.updateListButton);
        final EditText updateListAddItem = (EditText) findViewById(R.id.updateListAddItem);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                adapter.add(updateListAddItem.getText().toString().trim());
                updateListAddItem.setText("");
            }
        });

        updateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String table_name = getIntent().getStringExtra("table_name");
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String[] items = new String[adapter.getCount()];
                for (int i = 0; i < adapter.getCount(); i++) {
                    items[i] = adapter.getItem(i);
                }

                dbHelper.insertItems(db, table_name, items);

                Intent intent = new Intent(getApplicationContext(), ShoppingList.class);
                intent.putExtra("title", getIntent().getStringExtra("title"));
                intent.putExtra("table_name", getIntent().getStringExtra("table_name"));

                startActivity(intent);
            }
        });
    }
}

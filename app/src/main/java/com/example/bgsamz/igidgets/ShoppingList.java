package com.example.bgsamz.igidgets;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

public class ShoppingList extends AppCompatActivity {
    String[] test = {"Apples, last bought by Brandon 10/20/16",
                     "Bananas, last bought by Brandon 10/21/16",
                     "Pears, last bought by Brandon 10/19/16"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateListView();

        onClick();
    }

    public void onClick() {
        Button listUpdate = (Button) findViewById(R.id.button);

        listUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateList.class);
                intent.putExtra("title", getIntent().getStringExtra("title"));
                intent.putExtra("table_name", getIntent().getStringExtra("table_name"));

                startActivity(intent);
            }
        });
    }

    private void populateListView() {
        String table_name = getIntent().getStringExtra("table_name");

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] lists = dbHelper.getItems(db, table_name);
        db.close();
        dbHelper.close();

        final ListView shoppingListView = (ListView) findViewById(R.id.shoppingListView);
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        shoppingListView.setAdapter(adapter);
    }
}

package com.example.bgsamz.igidgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        final ListView shoppingListView = (ListView) findViewById(R.id.shoppingListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.shopping_list_item, test);
        shoppingListView.setAdapter(adapter);

        onClick();
    }

    public void onClick() {
        Button listUpdate = (Button) findViewById(R.id.button);

        listUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(getApplicationContext(), UpdateList.class));
            }
        });
    }
}

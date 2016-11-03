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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

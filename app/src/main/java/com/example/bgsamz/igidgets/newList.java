package com.example.bgsamz.igidgets;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onClick();
    }

    public void onClick() {
        Button createListNextButton = (Button) findViewById(R.id.createListNextButton);

        createListNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String listTitle = ((EditText) findViewById(R.id.enterListName)).getText().toString().trim();
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.createNewList(db, listTitle);
                db.close();
                dbHelper.close();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}

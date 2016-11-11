package com.example.bgsamz.igidgets;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bgsamz on 11/8/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "list_database.db";

    public static final String CREATE_TABLES =
            "CREATE TABLE shopping_lists(" +
                    "_id   INT PRIMARY KEY NOT NULL, " +
                    "title TEXT            NOT NULL )";
    public static final String DROP_TABLES =
            "DROP TABLE IF EXISTS shopping_lists";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLES);
        onCreate(db);
    }

    public boolean createNewList(SQLiteDatabase db, String tableName) {
        String createNewTable =
                "CREATE TABLE " + tableName + "(" +
                        "_id      INT PRIMARY KEY NOT NULL, " +
                        "item     TEXT            NOT NULL, " +
                        "quantity INT             NOT NULL, " +
                        "priority INT, " +
                        "cost     NUMERIC)";

        Cursor result = db.query("shopping_lists", null, "title = ?", new String[] {tableName}, null, null, null);

        if (result.getCount() == 0) {
            result.close();
            return false;
        } else {
            result.close();
            db.execSQL(createNewTable);
            return true;
        }
    }
}

package com.example.bgsamz.igidgets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bgsamz on 11/8/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "list_database.db";

    private static final String CREATE_TABLES =
            "CREATE TABLE shopping_lists(" +
                    "_id   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "title TEXT NOT NULL," +
                    "table_name TEXT NOT NULL )";
    private static final String DROP_TABLES =
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

    public String[] getLists(SQLiteDatabase db) {
        Cursor result = db.query("shopping_lists", new String[] {"table_name"}, null, null, null, null, null);

        String[] lists = new String[result.getCount()];
        int i = 0;

        if (result.moveToFirst()){
            while(!result.isAfterLast()){
                String data = result.getString(result.getColumnIndex("table_name"));
                lists[i++] = data;
                result.moveToNext();
            }
        }
        result.close();

        return lists;
    }

    public String[] getItems(SQLiteDatabase db, String table) {
        Cursor result = db.query(table, new String[] {"item"}, null, null, null, null, null);

        String[] items = new String[result.getCount()];
        int i = 0;

        if (result.moveToFirst()){
            while(!result.isAfterLast()){
                String data = result.getString(result.getColumnIndex("item"));
                items[i++] = data;
                result.moveToNext();
            }
        }
        result.close();

        return items;
    }

    public void insertItems (SQLiteDatabase db, String tableName, String[] items) {
        ContentValues row = new ContentValues(1);
        for (String item : items) {
            row.put("item", item);
            db.insert(tableName, null, row);
            row.clear();
        }
    }

    public boolean createNewList(SQLiteDatabase db, String tableName) {
        String fixedTableName = tableName.replace(' ', '_');
        String createNewTable =
                "CREATE TABLE " + fixedTableName + "(" +
                        "_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "item     TEXT NOT NULL, " +
                        "quantity INT, " +
                        "priority INT, " +
                        "cost     NUMERIC)";

        Cursor result = db.query("shopping_lists", null, "title = ?", new String[] {tableName}, null, null, null);

        if (result.getCount() != 0) {
            result.close();
            return false;
        } else {
            ContentValues row = new ContentValues(2);
            row.put("title", fixedTableName);
            row.put("table_name", tableName);

            result.close();
            db.insert("shopping_lists", null, row);
            db.execSQL(createNewTable);

            return true;
        }
    }
}

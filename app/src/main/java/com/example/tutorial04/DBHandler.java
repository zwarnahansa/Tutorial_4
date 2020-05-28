package com.example.tutorial04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String Database_Name = "UserInfo.bd";
    public static final String Table_Name = "users";

    public static final String Col1 = "ID";
    public static final String Col2 = "Name";
    public static final String Col3 = "Password";


    public DBHandler(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT , Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean addInfo(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Col2, userName);
        values.put(Col3, password);
        long newRowId = db.insert(Table_Name, null, values);
        if (newRowId == -1) {
            return false;
        } else
            return true;
    }

    public Cursor readAllInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM " + Table_Name, null);
        return cursor;
    }

    public void deleteInfo(String userName){
        SQLiteDatabase db =getReadableDatabase();
        String selection = Col2 + "LIKE ? " ;
        String[] selectionArgs = {userName};
        db.delete(Table_Name, selection,selectionArgs);
    }

    public void updateInfo(String userName, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Col3, password);
        String selection = Col2 + "LIKE ? " ;
        String[] selectionArgs = {userName};

        int count = db.update(Table_Name, values,selection, selectionArgs);
    }
}

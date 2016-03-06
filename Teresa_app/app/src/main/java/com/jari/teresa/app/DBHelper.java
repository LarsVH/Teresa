package com.jari.teresa.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Method;

/**
 * @author Jari Van Melckebeke
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "TeresaDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table questions(" +
                        "id int primary key," +
                        "question varchar," +
                        "action varchar," +
                        "accessClass varchar" +
                        ");"
        );
    }

    public boolean insertContact(String question, String action, String accessClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question);
        contentValues.put("action", action);
        contentValues.put("accessClass", accessClass);
        db.insert("questions", null, contentValues);
        return true;
    }

    public boolean updateQuestion(Integer id, String question, String action, String accessClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question);
        contentValues.put("action", action);
        contentValues.put("accessClass", accessClass);
        db.update("questions", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Class getActionClass(String text) throws ClassNotFoundException {
        SQLiteDatabase database = this.getReadableDatabase();
        database.beginTransaction();
        Cursor result = database.rawQuery("select accessClass from * where question equals " + text.toUpperCase() + ";", null);
        result.moveToFirst();
        database.endTransaction();
        return Class.forName("actions." + result.getString(0));
    }

    public Method getMethod(String text) throws ClassNotFoundException, NoSuchMethodException {
        SQLiteDatabase database = this.getReadableDatabase();
        database.beginTransaction();
        Cursor result = database.rawQuery("select action from * where question equals " + text.toUpperCase() + ";", null);
        result.moveToFirst();
        database.endTransaction();
        return getActionClass(text).getMethod(result.getString(0), String.class);
    }
}

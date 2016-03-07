package com.jari.teresa.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Jari Van Melckebeke
 */
public class DatabaseSetup {
    private static SQLiteDatabase database;

    public static void startSetup(SQLiteDatabase database) {
        DatabaseSetup.database = database;
        database.beginTransaction();
        if (database.getPath().contains("TeresaDB")) {
            database.execSQL("create table if not exists questions(id INT, question VARCHAR , action VARCHAR , accessClass VARCHAR);");
            database.execSQL("insert into questions('1','HOW LATE IS IT','getCurrentTime','actions.TimeUtils');");
        }
        database.endTransaction();
    }

    public static Class getActionClass(ArrayList<String> text) throws ClassNotFoundException {
        database.beginTransaction();
        String query = "";
        for (String aText : text) {
            query += aText + ",";
        }
        Cursor result = database.rawQuery("select accessClass from TeresaDB where question equals " + query.toUpperCase() + ";", null);
        result.moveToFirst();
        database.endTransaction();
        result.close();
        return Class.forName("actions." + result.getString(0));
    }

    public static Method getMethod(ArrayList<String> text) throws NoSuchMethodException, ClassNotFoundException {
        String query = "";
        for (String aText : text) {
            query += aText + ",";
        }
        database.beginTransaction();
        Cursor result = database.rawQuery("select action from TeresaDB where question equals " + query.toUpperCase() + ";", null);
        result.moveToFirst();
        database.endTransaction();
        result.close();
        return getActionClass(text).getMethod(result.getString(0), String.class, MainActivity.class);
    }
}

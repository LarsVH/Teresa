package com.jari.teresa.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Jari Van Melckebeke
 */
public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private final String CREATE_DATABASE =
            "CREATE TABLE questions(" +
                    "questionKey INT AUTO_INCREMENT PRIMARY KEY, " +
                    "questionSentence TEXT, " +
                    "questionMethod TEXT, " +
                    "questionClass TEXT);";

    public Database(Context context) {
        super(context, "teresaDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
        db.beginTransaction();
        db.execSQL("USE teresaDB.questions");
        insertValues(db);
    }

    private void insertValues(SQLiteDatabase db) {
        db.execSQL(getSQLCommand("HOW LATE IS IT", "getCurrentTime", "TimeUtils"));
        db.execSQL(getSQLCommand("START COUNTDOWN AT", "startTimer", "TimeUtils"));
        db.execSQL(getSQLCommand("HOW MUCH TIME IS LEFT ", "getTimeLeft", "TimeUtils"));
        db.execSQL(getSQLCommand("STOP COUNTDOWN", "stopTimer", "TimeUtils"));
    }

    private String getSQLCommand(String question, String method, String cls) {
        return "INSERT INTO questions(questionSentence ,questionMethod ,questionClass) VALUES(" + question + " ," + method + " ," + cls + ");";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Method getMethod(ArrayList<String> text) throws ClassNotFoundException, NoSuchMethodException {
        SQLiteDatabase database = getReadableDatabase();
        for (String poss : text) {
            String[] arg = {poss};
            Cursor query = database.rawQuery("SELECT questionMethod, questionClass FROM questions WHERE questionSentence LIKE ?", arg);
            query.moveToFirst();
            if (query.getCount() == 1) {
                return Class.forName(query.getString(2)).getMethod(query.getString(1), String.class, MainActivity.class);
            }
            query.close();
        }
        return null;
    }
}

package io.github.blist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* Modified at
    Nov 29, 2019 */
public class BListDB {

    public static final String DB_NAME = "b_list_db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public BListDB(Context context) {

        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWritableDB();
        closeDB();

    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE blist (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "title VARCHAR NOT NULL, date DATE, budget DECIMAL, completed BOOLEAN)");
            db.execSQL("INSERT INTO blist (title, date, budget, completed) " +
                    "VALUES ('Traveling to Jeju Island', '2020-05-26', '2000.00', 'false')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS \"blist\"");
            this.onCreate(db);
        }

    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    public void createBList(String title, String date, String budget) throws Exception {

        openWritableDB();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("date", date);
        cv.put("budget", budget);
        cv.put("completed", false);

        long nResult = db.insert("blist", null, cv);

        if (nResult == -1) {
            throw new Exception("No Data");
        }

        closeDB();

    }

    public ArrayList<HashMap<String, String>> readBList() {

        openReadableDB();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, title, date, budget, completed FROM blist " +
                "ORDER BY date ASC", null );

        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", cursor.getString(0));
            map.put("title", cursor.getString(1));
            map.put("date", cursor.getString(2));
            map.put("budget", cursor.getString(3));
            map.put("completed", cursor.getString(4));
            data.add(map);
        }
        if (cursor != null) {
            cursor.close();
            closeDB();
        }

        return data;
    }

    public void deleteAllBList() throws Exception {

        openWritableDB();
        long nResult = db.delete("blist", null, null);

        if (nResult == -1) {
            throw new Exception();
        }

        closeDB();

    }

}

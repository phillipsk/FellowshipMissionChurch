package io.techministry.android.fellowshipmissionchurch.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import io.techministry.android.fellowshipmissionchurch.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Read from Bible_ALL.sql database
 */

public class BibleDbHelpher extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bibleapp";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private final Context context;

    private static BibleDbHelpher dbHelper = null;
    /*private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";*/

   /* private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;*/


    public BibleDbHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        create(db);
    }

    private void create(SQLiteDatabase db) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.create);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String sql = "";
        try {
            while (reader.ready()) {
                 sql += reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static BibleDbHelpher create(Context context) {
        if (dbHelper == null) {
            dbHelper = new BibleDbHelpher(context);
            dbHelper.create(dbHelper.getWritableDatabase());
        }
        return dbHelper;
    }



}

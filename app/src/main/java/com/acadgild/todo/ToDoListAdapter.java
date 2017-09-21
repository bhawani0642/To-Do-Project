package com.acadgild.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pri on 9/15/2017.
 */
public class ToDoListAdapter {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    TodoListHelper helper;

    public ToDoListAdapter(Context c) {
        context = c;
        helper = new TodoListHelper(c);
    }

    public ToDoListAdapter open() throws SQLException {
        helper = new TodoListHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }


    public long insertData(String title, String description, String date, Integer status) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListHelper.COL_TITLE, title);
        contentValues.put(TodoListHelper.COL_DESCRIPTION, description);
        contentValues.put(TodoListHelper.COL_DATE, date);
        contentValues.put(TodoListHelper.COL_STATUS, status);
        long id = db.insert(TodoListHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor fetch(Integer status) {
        String[] columns = new String[]{TodoListHelper.COL_ID, TodoListHelper.COL_TITLE, TodoListHelper.COL_DESCRIPTION, TodoListHelper.COL_DATE, TodoListHelper.COL_STATUS};
        Cursor cursor = sqLiteDatabase.query(TodoListHelper.TABLE_NAME, columns, TodoListHelper.COL_STATUS + "=" + status, null, null, null, TodoListHelper.COL_DATE + " ASC");
        if (cursor != null) {
        }
        return cursor;
    }

    public Cursor fetch() {
        String[] columns = new String[]{TodoListHelper.COL_ID, TodoListHelper.COL_TITLE, TodoListHelper.COL_DESCRIPTION, TodoListHelper.COL_DATE, TodoListHelper.COL_STATUS};
        Cursor cursor = sqLiteDatabase.query(TodoListHelper.TABLE_NAME, columns, null, null, null, null, TodoListHelper.COL_DATE + " ASC");
        if (cursor != null) {
        }
        return cursor;
    }

    public int update(Integer _id, String title, String description, String date, Integer status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListHelper.COL_TITLE, title);
        contentValues.put(TodoListHelper.COL_DESCRIPTION, description);
        contentValues.put(TodoListHelper.COL_DATE, date);
        contentValues.put(TodoListHelper.COL_STATUS, status);

        int i = sqLiteDatabase.update(TodoListHelper.TABLE_NAME, contentValues, TodoListHelper.COL_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        sqLiteDatabase.delete(TodoListHelper.TABLE_NAME, TodoListHelper.COL_ID + " = " + _id, null);
    }

    class TodoListHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "tododatabase";
        public static final String TABLE_NAME = "todotable";
        public static final String COL_ID = "keyid";
        public static final String COL_TITLE = "keytitle";
        public static final String COL_DESCRIPTION = "keydescription";
        public static final String COL_DATE = "keydate";
        public static final String COL_STATUS = "keystatus";
        public static final int DATABASE_VERSION = 3;
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT, " + COL_DESCRIPTION + " TEXT, " + COL_DATE + " TEXT, " + COL_STATUS + " INTEGER DEFAULT '0' " + " ) ";
        public static final String DROP_TABLE = "DROP TABLE IF EXITS" + TABLE_NAME;

        public TodoListHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {


                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}


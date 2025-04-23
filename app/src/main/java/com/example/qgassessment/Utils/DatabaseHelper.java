package com.example.qgassessment.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "account";
    private static final String KEY_USER_PASSWORD = "password";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_USERNAME + " TEXT,"
            + KEY_USER_PASSWORD + " TEXT" + ")";


    private Context mContext;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(String account, String password) {      //插入用户数据（注册）
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_USERNAME, account);
        values.put(KEY_USER_PASSWORD, password);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public boolean checkUser(String account, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String USERS_SELECT_QUERY = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_USERNAME + " = ? AND " + KEY_USER_PASSWORD + " = ?";

        try (Cursor cursor = db.query(TABLE_USERS, null, KEY_USER_USERNAME + "=? AND " + KEY_USER_PASSWORD + "=?",
                new String[]{account, password}, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("db",e.getMessage(),e);
            return false;
        }
    }
}

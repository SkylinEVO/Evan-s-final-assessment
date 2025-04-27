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
    private static final String TABLE_FINANCE = "finance";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "account";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_TYPE = "type";
    private static final String KEY_USER_MONEY = "money";
    private static final String KEY_USER_TIME = "time";
    private static final String KEY_USER_CONNECT_ID = "user_id";
    private static final String KEY_USER_YEAR = "year";
    private static final String KEY_USER_MONTH = "month";
    private static final String KEY_USER_DAY = "day";

    //用户登陆注册表
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_USERNAME + " TEXT,"
            + KEY_USER_PASSWORD + " TEXT" + ")";
    //记账表
    private static final String CREATE_USERS_FINANCE_TABLE = "CREATE TABLE" + TABLE_FINANCE + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_CONNECT_ID + " INTEGER NOT NULL,"
            + KEY_USER_TYPE + " TEXT,"
            + KEY_USER_MONEY + " REAL NOT NULL,"
            + KEY_USER_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + KEY_USER_YEAR + "INTEGER,"
            + KEY_USER_MONTH + "INTEGER,"
            + KEY_USER_DAY + "INTEGER,"
            + "FOREIGN KEY (" + KEY_USER_CONNECT_ID + ") REFERENCES " + TABLE_USERS + " (" + KEY_USER_ID + ")" + ")";

    private Context mContext;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_USERS_FINANCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINANCE);
        onCreate(db);
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

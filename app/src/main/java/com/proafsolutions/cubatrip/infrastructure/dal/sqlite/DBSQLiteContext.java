package com.proafsolutions.cubatrip.infrastructure.dal.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alex on 4/15/2016.
 */
public class DBSQLiteContext extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "cubatrip";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_PERSON = "person";
    private static final String TABLE_ADDRESS = "address";

    // Post Table Columns
    private static final String KEY_PERSON_ID = "personId";
    private static final String KEY_PERSON_ADDRESS_FK = "addressId";

    // User Table Columns
    private static final String KEY_ADDRESS_ID = "addressId";


    public DBSQLiteContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
            onCreate(db);
        }
    }
}

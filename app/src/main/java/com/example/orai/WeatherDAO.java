package com.example.orai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WeatherDAO extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION = 1;
    private static final String DATA_BASE_NAME = "weather_data";
    private static final String TABLE_NAME = "weathers";

    private static final String KEY_ID = "id";
    public static final String COUNTRY_NAME = "country_name";
    public static final String DEGREES = "degrees";

    public WeatherDAO(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEATHER_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + COUNTRY_NAME + " TEXT, "
                + DEGREES + " REAL " + ")";
        db.execSQL(CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void create(Weather weather) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COUNTRY_NAME, weather.getCountryName());
        cv.put(DEGREES, weather.getDegrees());

        db.insert(TABLE_NAME, null, cv);

        db.close();

    }

    public List<Weather> getAll() {
        List<Weather> weathers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Weather weather = new Weather();

                weather.setId(Integer.parseInt(cursor.getString(0)));
                weather.setCountryName(cursor.getString(1));
                weather.setDegrees(cursor.getDouble(2));

                weathers.add(weather);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return weathers;
    }

    public int update(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COUNTRY_NAME, weather.getCountryName());
        cv.put(DEGREES, weather.getDegrees());

        int message = db.update(TABLE_NAME, cv, KEY_ID + "=?",
                new String[]{String.valueOf(weather.getId())});
        db.close();
        return message;


    }

    public void delete(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[]{String.valueOf(weather.getId())});

        db.close();
    }


}




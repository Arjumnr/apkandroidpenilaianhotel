package com.example.apk_android_penilaian_hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.apk_android_penilaian_hotel.Models.ModelData;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB_HOTEL";
    public static final String DB_TABLE_NAME = "DATA_RESPONDEN";
    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_USER = "user_id";
    public static final String DB_COLUMN_PILKEP = "kepentingan";
    public static final String DB_COLUMN_PILKIN = "kinerja";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final  String SQL_CREATE_TABLE = "CREATE TABLE DATA_RESPONDEN (id INTEGER PRIMARY KEY autoincrement, user_id TEXT, pertanyaan_id TEXT NOT NULL, kepentingan TEXT NOT NULL, kinerja TEXT NOT NULL )";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + DB_TABLE_NAME + "'");
        onCreate(sqLiteDatabase);

    }


    public void insert(int user_id,int pertanyaan_id, int kepentingan, int kinerja){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("pertanyaan_id", pertanyaan_id);
        contentValues.put("kepentingan", kepentingan);
        contentValues.put("kinerja", kinerja);
        Log.e("data", contentValues.toString());
        try{
            this.getWritableDatabase().insertOrThrow("DATA_RESPONDEN","", contentValues);
        }catch (Exception e){
            Log.e("erorsi", e.toString());
        }
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> dataArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM DATA_RESPONDEN";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();
                map.put("_id", cursor.getString(0));
                map.put("user_id", cursor.getString(1));
                map.put("pertanyaan_id", cursor.getString(2));
                map.put("kepentingan", cursor.getString(3));
                map.put("kinerja", cursor.getString(4));
                dataArrayList.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dataArrayList;

    }

    //delete all
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE_NAME, null, null);
        db.close();
    }

    //cek db
    public boolean cekDb(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DATA_RESPONDEN", null);
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }






}

package com.negaveez.crudoperationinandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBController extends SQLiteOpenHelper
{
    // All Static variables
    public Context context;


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
   private static final String DATABASE_NAME = "AndroidJSonDataBase.db";

    private static final String TABLE_NAME = "table_name";
    private static final String NAME_ID = "name_id";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";


    private static final String TABLE_SECOND = "table_second";
    private static final String SECOND_ID = "second_id";
    private static final String FIRST = "first";
    private static final String SECOND = "second";



    public DBController(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create Table of Name from SensorModel Fragment
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SECOND + "(" + SECOND_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST + " TEXT ," + SECOND + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);


       /*  String create_table = "create table table_demo(my_i INTEGER NOT NULL,name TEXT NOT NULL)";
         db.execSQL(create_table);*/

       /* String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + NAME_ID +
                " INTEGER NOT NULL," + NAME + " TEXT ," + PASSWORD + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);*/


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECOND);
        // Create tables again
        onCreate(db);
    }

    public void insertData(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }


    public void insertDataSubmit(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRST, name);
        values.put(SECOND, password);
        db.insert(TABLE_SECOND, null, values);
        db.close(); // Closing database connection
    }


    //---deletes a particular title---
    public boolean deleteTitle(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, NAME + "=" + name, null) > 0;
    }

    //delete the note
    public void deleteDeviceList(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
      //  db.delete(TABLE_NAME, NAME + "=" +'"+selectRoom+"', null);
        db.execSQL("delete from table_name where name='"+name+"'");
        db.close();
        //db.execSQL("delete from table_name where NAME='"+name+"'");
       /* db.execSQL("delete from table_name where rowid=2");
        System.out.println("delete id :::::");
        db.close();*/
      getVaccum();
    }


    //***************To Get Mac Address ***********************VACUUM
    public void getVaccum()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // String selectQuery = "SELECT * FROM " + TABLE_MACADDRESS;
        String selectQuery = "VACUUM";
        db.execSQL(selectQuery);
        db.close();

        System.out.println("selectQuery:::: "+selectQuery);
       // SQLiteDatabase db  = this.getReadableDatabase();
       //  db.rawQuery(selectQuery, null);
        getDataFromDatabase();
    }


    //***************To Get Mac Address ***********************
    public String getDataFromDatabase()
    {
        // String selectQuery = "SELECT * FROM " + TABLE_MACADDRESS;
        String selectQuery = "select rowid,* from table_name";
        //String selectQuery = "select * from table_name";

        System.out.println("selectQuery::::125 "+selectQuery);
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String selectDeviceList="",url;
        String mac_address = null;
        String clientData="", clientDb, selectDeviceColumn;
        int cnt=0;

        if (cursor.moveToFirst())
        {
            do
            {
                url = (cursor.getString(0));
                clientDb = (cursor.getString(1));
                selectDeviceColumn = (cursor.getString(2));
                //   getsaveData += "\n"+ onTime + ">" + offTime  ;
                clientData += "\n"+url+">"+clientDb +">"+ selectDeviceColumn;
                cnt++;

            }
            while (cursor.moveToNext());
        }
        selectDeviceList = clientData +"#:#:#" + cnt ;

        System.out.println("selectDeviceList:::: "+selectDeviceList);
        cursor.close();
        return selectDeviceList;
    }


    //***************table second ***********************
    public String getDataSecond()
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String selectQuery = "select * from table_second";
        System.out.println("selectQuery::::185 "+selectQuery);
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String selectDeviceList="",url;
        String mac_address = null;
        String clientData="", clientDb, selectDeviceColumn;
        int cnt=0;

        if (cursor.moveToFirst())
        {
            do
            {
                url = (cursor.getString(0));
                clientDb = (cursor.getString(1));
                selectDeviceColumn = (cursor.getString(2));
                //   getsaveData += "\n"+ onTime + ">" + offTime  ;
                clientData += "\n"+url+">"+clientDb +">"+ selectDeviceColumn;
                cnt++;

            }
            while (cursor.moveToNext());
        }
        selectDeviceList = clientData +"#:#:#" + cnt ;

        System.out.println("selectDeviceList:::: "+selectDeviceList);
        cursor.close();
        return selectDeviceList;
    }





}





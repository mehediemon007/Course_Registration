package com.example.dcl.course_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase1 extends SQLiteOpenHelper {

    public static final String Database_Name = "User.db";
    public static final String Table_Name = "User_Details";
    public static final String Col1 = "Name";
    public static final String Col2 = "Email";
    public static final String Col3 = "UserName";
    public static final String Col4 = "Password";

    public Context context;
    public DataBase1(Context context) {
        super(context, Database_Name, null, 10);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL("create table User_Details(Name VARCHAR(255),Email TEXT PRIMARY KEY,UserName TEXT,Password TEXT)");

        }catch (Exception e){
            Toast.makeText(context, "DataBase Not made", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists User_Details");
        onCreate(sqLiteDatabase);


    }


    public long insertData(String name, String email, String userName, String password) {

        long row ;

        if(name.equals("") || email.equals("") || userName.equals("") || password.equals("")){

            row = -1;

        }
        else {

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Col1, name);
            contentValues.put(Col2, email);
            contentValues.put(Col3, userName);
            contentValues.put(Col4, password);
             row = sqLiteDatabase.insert(Table_Name, null, contentValues);

        }

        return row;
    }

    public boolean getValue(String userName, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User_Details",null);
        boolean value = false ;

        if(cursor.getCount()==0){

            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext()){
               String  userName2 = cursor.getString(2);
               String password2 = cursor.getString(3);

               if(userName2.equals(userName) && password2.equals(password)){
                   value=true;
                   break;
               }
            }

        }


        return value;
    }
}


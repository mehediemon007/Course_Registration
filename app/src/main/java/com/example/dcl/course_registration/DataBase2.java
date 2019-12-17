package com.example.dcl.course_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase2 extends SQLiteOpenHelper {

    public  static final String DataBase2_Name ="Courses.db";
    public  static  final String Table2    = "Courses_Details";
    public  static  final String UserName2  = "UserName2";
    public  static  final String Courses2  = "Courses";

    Context context;

    public DataBase2( Context context) {
        super(context, DataBase2_Name, null, 10);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE Courses_Details(UserName2 TEXT,Courses VARCHAR(255))");
       // Toast.makeText(context, "DataBase2 made", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Courses_Details");
        onCreate(sqLiteDatabase);

    }
    public long insert2(String UserName,String Courses){
        long row;
        if(UserName.isEmpty() && Courses.isEmpty()){
            row=-1;
        }else{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(UserName2,UserName);
            contentValues.put(Courses2,Courses);

             row = sqLiteDatabase.insert(Table2,null,contentValues);
        }
        return row;
    }

    public Cursor returnData(String UserName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM Courses_Details",null);
        return cursor;

    }
    public int deleteCourse(String deleteCourse){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int val = sqLiteDatabase.delete(Table2,"Courses=?",new String[]{deleteCourse});
        return val;
    }
}

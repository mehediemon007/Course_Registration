package com.example.dcl.course_registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectedCourse extends AppCompatActivity {

    ListView list2LV;
    TextView text4TV;
    DataBase2 dataBase2;
    Button  button4BTN;
    String deleteCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);




        list2LV = findViewById(R.id.list2LV);
        button4BTN = findViewById(R.id.button4BTN);


        dataBase2 = new DataBase2(this);
        SQLiteDatabase sqLiteDatabase = dataBase2.getWritableDatabase();

        loadData();



    }
    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
        String UserName = sharedPreferences.getString("userName","");

        Cursor cursor = dataBase2.returnData(UserName);
        if(cursor.getCount()==0){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listData.add(cursor.getString(1));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample3_view,R.id.text3TV,listData);
        list2LV.setAdapter(adapter);


        list2LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 deleteCourse = adapterView.getItemAtPosition(i).toString();

            }
        });


    }


    public void deleteCourse(View view) {

        int value = dataBase2.deleteCourse(deleteCourse);
        if(value>0){
            Toast.makeText(this, "Course Deleted", Toast.LENGTH_SHORT).show();
            loadData();
        }

    }
}

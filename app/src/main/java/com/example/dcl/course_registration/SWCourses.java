package com.example.dcl.course_registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SWCourses extends AppCompatActivity {

    String[] SWCourse;
    ListView  gridGV3;
    ArrayAdapter<String> adapter;
    SearchView searchView;

    DataBase2 dataBase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swcourses);


        SWCourse = getResources().getStringArray(R.array.SWCourses);

        gridGV3 = findViewById(R.id.gridGV3);

        dataBase2 = new DataBase2(this);
        SQLiteDatabase sqLiteDatabase = dataBase2.getWritableDatabase();

        adapter = new ArrayAdapter<String>(this,R.layout.sample2,R.id.text2TV,SWCourse);
        gridGV3.setAdapter(adapter);
        gridGV3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
                String UserName = sharedPreferences.getString("userName","");
                String Courses = SWCourse[i];

                long row = dataBase2.insert2(UserName,Courses);

                if(row>0){
                    Toast.makeText(SWCourses.this, "Course Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.SearchMN);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });


        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.SearchMN){

        }
        return super.onOptionsItemSelected(item);
    }



}

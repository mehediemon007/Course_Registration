package com.example.dcl.course_registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

public class ComputerScienceCourses extends AppCompatActivity {

    private GridView gridView;
    int[] flag = {R.drawable.c,R.drawable.cpp,R.drawable.cshrap,R.drawable.java,R.drawable.javascript,
            R.drawable.python,R.drawable.ruby,R.drawable.php};
    String[] languageName;
    SearchView searchView;

    DataBase2 dataBase2 ;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_courses);

        getSupportActionBar().setTitle("Computer Science");

        languageName = getResources().getStringArray(R.array.programmingName);

        gridView = findViewById(R.id.gridGV);

        dataBase2 = new DataBase2(this);
        SQLiteDatabase sqLiteDatabase=dataBase2.getWritableDatabase();

        adapter = new CustomAdapter(this,flag,languageName);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
                String UserName = sharedPreferences.getString("userName","");
                String Courses = languageName[i];

                long row = dataBase2.insert2(UserName,Courses);
                if(row>0){
                    Toast.makeText(ComputerScienceCourses.this, "Course Registered", Toast.LENGTH_SHORT).show();
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

                return false;
            }
        });
            return true;
       // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.SearchMN){

        }
        return super.onOptionsItemSelected(item);
    }
}

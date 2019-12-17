package com.example.dcl.course_registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Timer;

public class Course_Details extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__details);

        drawerLayout = findViewById(R.id.drawerLY);
        toolbar = findViewById(R.id.toolTB);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Courses");
//        toolbar.setSubtitle("Listed");


        NavigationView navigationView = findViewById(R.id.navigationNV);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.CSE){
                    Intent intent = new Intent(Course_Details.this,ComputerScienceCourses.class);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.EEE){

                    Intent intent = new Intent(Course_Details.this,EEECourses.class);
                    startActivity(intent);

                }else if(menuItem.getItemId()==R.id.SW){
                    Intent intent = new Intent(Course_Details.this,SWCourses.class);
                    startActivity(intent);
                }
                return true;
            }
        });



        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Nav_open,R.string.Nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }else if(item.getItemId()==R.id.homeMN){
            Intent intent = new Intent(this,Course_Details.class);
            Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else if(item.getItemId()==R.id.LogoutMN){
            SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName","");
            editor.putString("password","");
            editor.commit();

            Intent intent = new Intent(Course_Details.this,Sing_In.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.CoursesMN){
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Course_Details.this,SelectedCourse.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.ProfileMN){
            Intent intent = new Intent(Course_Details.this,Profile.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Course_Details.this);

        builder.setTitle("LearningBD");
        builder.setMessage("Want to Exit?");
        builder.setCancelable(true);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                //moveTaskToBack(true);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.cancel();
            }
        });

       AlertDialog alertDialog = builder.create();
       // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.custom));
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.custom);
        alertDialog.show();

    }
}

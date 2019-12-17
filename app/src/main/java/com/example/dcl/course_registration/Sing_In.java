package com.example.dcl.course_registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sing_In extends AppCompatActivity implements View.OnClickListener {

    private Button buttonBTN,button2BTN;
    private EditText editET,edit2ET;
    DataBase1 dataBase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__in);

        buttonBTN = findViewById(R.id.buttonBTN);
        button2BTN = findViewById(R.id.button2BTN);
        editET = findViewById(R.id.editET);
        edit2ET = findViewById(R.id.edit2ET);

        dataBase1 = new DataBase1(this);
        SQLiteDatabase sqLiteDatabase = dataBase1.getWritableDatabase();

        if(loginCheck()!=""){
            Intent intent =new Intent(Sing_In.this,Course_Details.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }


        buttonBTN.setOnClickListener(this);
        button2BTN.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.buttonBTN){

            String userName = editET.getText().toString();
            editET.setText("");
            String password = edit2ET.getText().toString();
            edit2ET.setText("");

            boolean value = dataBase1.getValue(userName,password);

            if(value==true){

                Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName",userName);
                editor.putString("password",password);
                editor.commit();

                Intent intent = new Intent(Sing_In.this,Course_Details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }else {
                Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show();
            }


        }else if(view.getId()==R.id.button2BTN){
            Intent intent = new Intent(Sing_In.this,Sign_Up.class);
            startActivity(intent);
        }


    }

    public String loginCheck(){

        SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("userName","");
        String value1 = sharedPreferences.getString("password","");

        return value+value1;

    }

    @Override
    public void onBackPressed() {

            final AlertDialog.Builder builder = new AlertDialog.Builder(Sing_In.this);

            builder.setTitle("LearningBD");
            builder.setMessage("Want to Exit?");
            builder.setCancelable(true);

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            alertDialog.show();

        }
    }


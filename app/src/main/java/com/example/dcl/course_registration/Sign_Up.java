package com.example.dcl.course_registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity  implements View.OnClickListener{

    DataBase1 dataBase1;
    private EditText NameET,UserNameET,PasswordET,EmailET;
    private Button buttonBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);


        NameET = findViewById(R.id.NameET);
        UserNameET = findViewById(R.id.UsernameET);
        EmailET  = findViewById(R.id.EmailET);
        PasswordET = findViewById(R.id.PasswordET);
        buttonBTN  =  findViewById(R.id.buttonBTN);

        dataBase1 = new DataBase1(this);
        SQLiteDatabase sqLiteDatabase  =  dataBase1.getWritableDatabase();

        buttonBTN.setOnClickListener(this);






    }

    @Override
    public void onClick(View view) {

        String Name = NameET.getText().toString();
        NameET.setText("");
        String Email = EmailET.getText().toString();
        EmailET.setText("");
        String UserName = UserNameET.getText().toString();
        UserNameET.setText("");
        String Password = PasswordET.getText().toString();
        PasswordET.setText("");

        long row = dataBase1.insertData(Name,Email,UserName,Password);



        if(row==-1){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this, "Sing Upped", Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("LogInDetails",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName",UserName);
            editor.putString("password",Password);
            editor.commit();


            Intent intent = new Intent(Sign_Up.this,Course_Details.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }


    }
}

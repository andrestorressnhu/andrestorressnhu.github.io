package com.example.torres_andres_weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.torres_andres_weighttracker.main_screen;

public class MainActivity extends AppCompatActivity {

    private void loginSuccess(View view){
        Intent intent = new Intent(this, main_screen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username =(EditText) findViewById(R.id.textUsername);
        EditText password =(EditText) findViewById(R.id.textPassword);

        Button loginbtn = (Button) findViewById(R.id.buttonLogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("CS360") && password.getText().toString().equals("CS360")){
                    loginSuccess(v);
                }else
                {
                    Toast.makeText(MainActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
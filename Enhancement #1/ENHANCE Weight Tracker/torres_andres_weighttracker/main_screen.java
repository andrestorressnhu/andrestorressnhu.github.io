package com.example.torres_andres_weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//Create class named main_screen
public class main_screen extends AppCompatActivity {

    //Create method named onCreate to startup screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Button editBtn = findViewById(R.id.buttonEditWeight);

    }
    //Create method named onCreateOptionsMenu for menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Create method named onOptionsItemSelected for selecting options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        openSettings();
        return false;
    }

    public void openSettings(){
        Intent intent = new Intent(this, UserSettings.class);
        startActivity(intent);
    }

    public void openEdit(View view){
        Intent intent = new Intent(this, EditView.class);
        startActivity(intent);
    }

    public void openWeightForm(View view){
        Intent intent = new Intent(this, weight_entry.class);
        startActivity(intent);
    }
}
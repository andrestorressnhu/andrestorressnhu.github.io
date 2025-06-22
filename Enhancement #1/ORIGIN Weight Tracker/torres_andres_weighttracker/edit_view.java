package com.example.torres_andres_weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class edit_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
    }
    public void openMainForm(View view) {
        startActivity(new Intent(this, main_screen.class));
    }

    public void openWeightForm(View view) {
    }
}
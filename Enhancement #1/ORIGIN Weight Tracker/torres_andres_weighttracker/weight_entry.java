package com.example.torres_andres_weighttracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class weight_entry extends AppCompatActivity {

    protected EditText dateEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_entry);

        dateEntry = findViewById(R.id.editWeightDate);

        dateEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        weight_entry.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateEntry.setText((monthOfYear + 1) + "-" + dayOfMonth  + "-" + year);

                            }
                        },

                        year, month, day);

                datePickerDialog.show();
            }

        });
    }

    public void openMainForm(View view){
        Intent intent = new Intent(this, main_screen.class);
        startActivity(intent);
    }
}
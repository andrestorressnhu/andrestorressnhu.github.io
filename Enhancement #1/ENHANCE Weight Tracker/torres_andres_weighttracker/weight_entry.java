package com.example.torres_andres_weighttracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import com.example.torres_andres_weighttracker.AppDatabase;
import com.example.torres_andres_weighttracker.WeightEntry;
import com.example.torres_andres_weighttracker.WeightDao;

//Define an activity named weight_entry
public class weight_entry extends AppCompatActivity {

    //Declare an entry for a dateEntry
    protected EditText dateEntry;

    private EditText weightInput;
    private ListView listView;
    private WeightDao weightDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_entry);
//Creates an editWeightDate function
        dateEntry = findViewById(R.id.editWeightDate);
//Creates a calendar popup to input date
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

        weightInput = findViewById(R.id.editWeight);
        listView = findViewById(R.id.weightListView);
        Button saveButton = findViewById(R.id.saveWeightButton);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        weightDao = db.weightDao();

        saveButton.setOnClickListener(v -> {
            String date = dateEntry.getText().toString().trim();
            String weightText = weightInput.getText().toString().trim();

            if (!date.isEmpty() && !weightText.isEmpty()) {
                float weight = Float.parseFloat(weightText);
                new Thread(() -> {
                    weightDao.insert(new WeightEntry(date, weight));
                    List<WeightEntry> entries = weightDao.getAll();

                    List<String> displayList = new ArrayList<>();
                    for (WeightEntry entry : entries) {
                        displayList.add(entry.date + " - " + entry.weight + " lbs");
                    }

                    runOnUiThread(() -> {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1, displayList);
                        listView.setAdapter(adapter);
                    });
                }).start();
            }
        });
    }

    public void openMainForm(View view){
        Intent intent = new Intent(this, main_screen.class);
        startActivity(intent);
    }
}
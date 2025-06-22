package com.example.torres_andres_weighttracker;

// Import required classes
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Activity to manage editing and deleting weight entries
public class EditView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity
        setContentView(R.layout.activity_edit_view);

        // Find the Edit button by ID and set up click handler
        Button editButton = findViewById(R.id.buttonEdit);
        if (editButton != null) {
            editButton.setOnClickListener(v ->
                Toast.makeText(this, "Edit button clicked", Toast.LENGTH_SHORT).show()
            );
        } else {
            // Show error if button not found
            Toast.makeText(this, "Error: Edit button not found", Toast.LENGTH_LONG).show();
        }

        // Find the Delete button by ID and set up click handler
        Button deleteButton = findViewById(R.id.buttonDelete);
        if (deleteButton != null) {
            deleteButton.setOnClickListener(v ->
                Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show()
            );
        } else {
            // Show error if button not found
            Toast.makeText(this, "Error: Delete button not found", Toast.LENGTH_LONG).show();
        }
    }

    // Navigate to the main form screen
    public void openMainForm(View view) {
        startActivity(new Intent(this, main_screen.class));
    }

    // Placeholder for navigating to the weight entry screen
    public void openWeightForm(View view) {
        // TODO: Implement navigation to weight entry screen
    }
}
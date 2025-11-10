package com.example.mediaplayer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {
    EditText etFirstName, etLastName, etBirthdate;
    Spinner spinnerCountry, spinnerLanguage;

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthdate = findViewById(R.id.etBirthdate);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        btnNext = findViewById(R.id.btnNext);

        // Setup country spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.country_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        // Setup language spinner
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(
                this, R.array.language_list, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(langAdapter);

        // Birthdate picker
        etBirthdate.setOnClickListener(v -> showDatePicker());

        // Button click
        btnNext.setOnClickListener(v -> validateAndProceed());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etBirthdate.setText(date);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void validateAndProceed() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();
        String country = spinnerCountry.getSelectedItem().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || birthdate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Move to MainActivity (or next step)
        startActivity(new Intent(RegistrationActivity.this, ChoiseSinger.class));
        finish();
    }
}

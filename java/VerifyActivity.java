package com.example.mediaplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VerifyActivity extends AppCompatActivity {

    Spinner spinnerType;
    EditText inputField, otpField;
    Button btnSendOtp, btnSubmitOtp;
    String selectedType = "";
    String generatedOtp = "";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Skip verification if already verified
        if (prefs.getBoolean("isVerified", false)) {
            startActivity(new Intent(this, RegistrationActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_verify);
        spinnerType = findViewById(R.id.spinnerType);
        inputField = findViewById(R.id.inputField);
        otpField = findViewById(R.id.otpField);
        btnSendOtp = findViewById(R.id.btnSendOtp);
        btnSubmitOtp = findViewById(R.id.btnSubmitOtp);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                selectedType = parent.getItemAtPosition(position).toString();
                inputField.setHint(selectedType.equals("Mobile") ? "Enter 10-digit mobile number" : "Enter email address");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnSendOtp.setOnClickListener(v -> sendOtp());
        btnSubmitOtp.setOnClickListener(v -> verifyOtp());

    }

    private void sendOtp() {
        String input = inputField.getText().toString().trim();

        if (selectedType.equals("Mobile")) {
            if (!isValidMobile(input)) {
                Toast.makeText(this, "Enter valid 10-digit mobile", Toast.LENGTH_SHORT).show();
                return;
            }
            generatedOtp = generateOtp();
            Toast.makeText(this, "OTP sent to mobile: " + generatedOtp, Toast.LENGTH_LONG).show();

        } else {
            if (!isValidEmail(input)) {
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show();
                return;
            }
            generatedOtp = generateOtp();
            Toast.makeText(this, "OTP sent to email: " + generatedOtp, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidMobile(String mobile) {
        return mobile.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void verifyOtp() {
        String enteredOtp = otpField.getText().toString().trim();
        if (enteredOtp.equals(generatedOtp)) {
            Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show();
            prefs.edit().putBoolean("isVerified", true).apply();
            startActivity(new Intent(this, RegistrationActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateOtp() {
        Random random = new Random();                 // Create random number generator
        int otp = 100000 + random.nextInt(900000);    // Range: 100000–999999 (6 digits)
        return String.valueOf(otp);                   // Convert int → String
    }

}

package com.accountabilitychat.accountabilitychat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, username, password;
    String fNameStr, lNameStr, usernameStr, passStr;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize EditText variables
        firstName = (EditText) findViewById(R.id.firstNameRegInput);
        lastName = (EditText) findViewById(R.id.lastNameRegInput);
        username = (EditText) findViewById(R.id.usernameRegInput);
        password = (EditText) findViewById(R.id.passwordRegInput);

    }

    public void onReg(View view) {

        alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Registration Error");
        fNameStr = firstName.getText().toString();
        lNameStr = lastName.getText().toString();
        usernameStr = username.getText().toString();
        passStr = password.getText().toString();
        String type = "register";


        if (fNameStr.isEmpty() || lNameStr.isEmpty() || usernameStr.isEmpty() || passStr.isEmpty()) {
            alertDialog.setMessage("Please fill all fields.");
            alertDialog.show();
        }
        else if (usernameStr.contains(" ")) {
            alertDialog.setMessage("Username cannot contain spaces.");
            alertDialog.show();
        }
        else if ((!passStr.matches(".*\\d.*") || !passStr.matches(".*[a-zA-Z].*")) && (passStr.length() < 8)) {
            alertDialog.setMessage("Password must include numerical and alphabetical values and must be at least 8 characters long.");
            alertDialog.show();
        }
        else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, fNameStr, lNameStr, usernameStr, passStr);
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finishAffinity();
        }

    }

}

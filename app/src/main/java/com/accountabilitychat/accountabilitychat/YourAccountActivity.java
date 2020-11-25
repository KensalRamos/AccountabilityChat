package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
 *
 * File name: YourAccountActivity.java
 *
 * Contributor(s): Kensal Ramos
 *
 * Description: In this activity, the user may update their information. When the user hits
 * the update button, their username is used to retrieve their row and then their information is
 * updated.
 *
 *
 */

public class YourAccountActivity extends AppCompatActivity {

    static String searchResult;
    String[] searchStr;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_account);
        setStrings();
    }

    private void setStrings() {

        // Declare TextView variables
        TextView usernameText =(TextView) findViewById(R.id.usernameTitle);
        TextView fNameText =(TextView) findViewById(R.id.firstNameTitle);
        TextView lNameText =(TextView) findViewById(R.id.lastNameTitle);
        TextView passwordText =(TextView) findViewById(R.id.passwordTitle);

        EditText fNameEdit = (EditText) findViewById(R.id.editFNameInput);
        EditText lNameEdit = (EditText) findViewById(R.id.editLNameInput);
        EditText passwordEdit = (EditText) findViewById(R.id.editPasswordInput);

        searchStr = searchResult.split(" ");

        // Alter TextView strings accordingly
        fNameText.setText(getString(R.string.first_name_title, searchStr[1]));
        lNameText.setText(getString(R.string.last_name_title, searchStr[2]));
        usernameText.setText(getString(R.string.username_title, searchStr[3]));
        passwordText.setText(getString(R.string.password_title, searchStr[4]));

        fNameEdit.setText(searchStr[1]);
        lNameEdit.setText(searchStr[2]);
        passwordEdit.setText(searchStr[4]);

    }

    public void fNameEditOnClick(View view) {

        // Initialize
        EditText fNameEdit = (EditText) findViewById(R.id.editFNameInput);
        Button fNameEditBtn = (Button) findViewById(R.id.firstNameEditBtn);

        // Edit visibilities
        fNameEdit.setVisibility(View.VISIBLE);
        fNameEditBtn.setVisibility(View.GONE);

    }


    public void lNameEditOnClick(View view) {

        // Initialize
        EditText lNameEdit = (EditText) findViewById(R.id.editLNameInput);
        Button lNameEditBtn = (Button) findViewById(R.id.lastNameEditBtn);

        // Edit visibilities
        lNameEdit.setVisibility(View.VISIBLE);
        lNameEditBtn.setVisibility(View.GONE);

    }

    public void passwordEditOnClick(View view) {

        // Initialize
        EditText passwordEdit = (EditText) findViewById(R.id.editPasswordInput);
        Button passwordEditBtn = (Button) findViewById(R.id.passwordEditBtn);

        // Edit visibilities
        passwordEdit.setVisibility(View.VISIBLE);
        passwordEditBtn.setVisibility(View.GONE);

    }

    public void updateBtnOnClick(View view) {

        // Init
        alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Update Error");
        EditText fNameEdit = (EditText) findViewById(R.id.editFNameInput);
        EditText lNameEdit = (EditText) findViewById(R.id.editLNameInput);
        EditText passwordEdit = (EditText) findViewById(R.id.editPasswordInput);
        String type = "update";
        String fName = fNameEdit.getText().toString();
        String lName = lNameEdit.getText().toString();
        String username = searchStr[3];
        String password = passwordEdit.getText().toString();

        // Check if changes are valid
        if (fName.isEmpty() || lName.isEmpty() || password.isEmpty()) {
            alertDialog.setMessage("Please fill all fields.");
            alertDialog.show();
        }
        else if ((!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) && password.length() < 8) {
            alertDialog.setMessage("Password must include numerical and alphabetical values and must be at least 8 characters long.");
            alertDialog.show();
        }
        else {
            // Check if changes have been made.
            if (!fNameEdit.getText().toString().equals(searchStr[1]) || !lNameEdit.getText().toString().equals(searchStr[2]) || !passwordEdit.getText().toString().equals(searchStr[4])) {

                // Update database information
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, fName, lName, username, password);
            }

            // Go Home
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

}

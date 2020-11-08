package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class YourAccountActivity extends AppCompatActivity {

    static String searchResult;
    String type = "edit";
    BackgroundWorker backgroundWorker;
    String[] searchStr;

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

        searchStr = searchResult.split(" ");

        // Alter TextView strings accordingly
        fNameText.setText(getString(R.string.first_name_title, searchStr[1]));
        lNameText.setText(getString(R.string.last_name_title, searchStr[2]));
        usernameText.setText(getString(R.string.username_title, searchStr[3]));
        passwordText.setText(getString(R.string.password_title, searchStr[4]));

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


        // Go Home
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}

package com.accountabilitychat.accountabilitychat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class YourAccountActivity extends AppCompatActivity {

    static String searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_account);
        setStrings();
    }

    public void toHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void setStrings() {

        // Declare TextView variables
        TextView usernameText =(TextView) findViewById(R.id.usernameTitle);
        TextView fNameText =(TextView) findViewById(R.id.firstNameTitle);
        TextView lNameText =(TextView) findViewById(R.id.lastNameTitle);
        TextView passwordText =(TextView) findViewById(R.id.passwordTitle);

        String[] searchStr = searchResult.split(" ");

        // Alter TextView strings accordingly
        fNameText.setText(getString(R.string.first_name_title, searchStr[1]));
        lNameText.setText(getString(R.string.last_name_title, searchStr[2]));
        usernameText.setText(getString(R.string.username_title, searchStr[3]));
        passwordText.setText(getString(R.string.password_title, searchStr[4]));
        //System.out.println("searchResult IS: _________________________" + searchResult);

    }

}

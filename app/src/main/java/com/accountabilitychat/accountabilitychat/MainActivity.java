package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Take the user to the "Your Account" activity. In this activity they may view their info.
     */
    public void ToYourAccountActivity(View view) {

        Intent intent = new Intent(this, YourAccountActivity.class);
        startActivity(intent);

    }

    /*
     * Take the user to the "Add User" activity. In this activity, they may input a username,
     * if the username exists within the database, that username will be added to their contacts.
     * They may message usernames in their contacts.
     */
    public void ToAddUserActivity(View view) {

        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);

    }

}

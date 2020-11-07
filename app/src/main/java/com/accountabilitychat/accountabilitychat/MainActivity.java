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
        searchDatabase();
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

    private void searchDatabase() {

        String type = "search";
        String username = BackgroundWorker.loggedUser;

        System.out.println("LOGGED USERR IS: _________________________" + username);
        //System.out.println("searchResult IS: _________________________" + BackgroundWorker.searchResult);

        // Search for database information
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        //System.out.println("searchResult IS: _________________________2" + BackgroundWorker.searchResult);
        backgroundWorker.execute(type, username);

    }

}

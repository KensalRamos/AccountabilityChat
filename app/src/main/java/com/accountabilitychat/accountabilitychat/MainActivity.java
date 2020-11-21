package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout scrollLinearLayout;
    ScrollView scrollLayout;
    TextView usernameAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchDatabase();
        displayContacts();
        //findUser();
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

        // Search for database information
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, username);
    }

    private void displayContacts() {

        scrollLinearLayout = (LinearLayout) findViewById(R.id.scollLinearLayout);
        scrollLayout = (ScrollView) findViewById(R.id.mainScrollView);
        AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add User Error");

        /*
        if (BackgroundWorker.contacts == null)
            BackgroundWorker.contacts = YourAccountActivity.searchResult.split(" ")[5].split(" ");
        */
        if (BackgroundWorker.contacts != null) {


            for (int i = 0; i < BackgroundWorker.contacts.length; i++) {
                System.out.println("Contacts at index " + i + " is " + BackgroundWorker.contacts[i]);
                usernameAdded = new TextView(this);
                usernameAdded.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                usernameAdded.setText(BackgroundWorker.contacts[i]);
                scrollLinearLayout.addView(usernameAdded);
                scrollLayout.invalidate();
                scrollLayout.requestLayout();
            }

        }
        else {
            // Do nothing.
        }

    }

/*
    private void findUser() {

        scrollLinearLayout = (LinearLayout) findViewById(R.id.scollLinearLayout);
        scrollLayout = (ScrollView) findViewById(R.id.mainScrollView);
        AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add User Error");


        if (addUserSearchResult == null || addUserSearchResult.isEmpty()) {

        }
        else {
            System.out.println("MAIN - addUserSearchResult = " + addUserSearchResult);
            if (addUserSearchResult.split(" ")[0].equals("search")) {

                System.out.println("POST IF");

                    We need to create an array of "contacts" for each row in user_data. Then, every
                    time MainActivity.java is opened, we read this list and add values to our
                    scrollLinearLayout in scrollLayout

                    Instead of an array, we will store usernames separated by a space.
                    Ex: kj11 guest mbarnett



                usernameAdded = new TextView(this);
                usernameAdded.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                usernameAdded.setText(addUserSearchResult.split(" ")[3]);
                scrollLinearLayout.addView(usernameAdded);
                scrollLayout.invalidate();
                scrollLayout.requestLayout();
                System.out.println("POST ADD!!!");

            }
            else {
                alertDialog.setMessage("Username not found! Please try another username.");
                alertDialog.show();
            }
            addUserSearchResult = "";
        }

    }*/

}

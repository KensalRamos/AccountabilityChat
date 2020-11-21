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

    private void displayContacts() {

        scrollLinearLayout = (LinearLayout) findViewById(R.id.scollLinearLayout);
        scrollLayout = (ScrollView) findViewById(R.id.mainScrollView);
        AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add User Error");


        // search guest kensal ramos 12345678 kj11 mbarnett
        if (BackgroundWorker.contacts == null) {
            String[] temp = new String[YourAccountActivity.searchResult.split(" ").length - 5];
            String[] searchResultArr = YourAccountActivity.searchResult.split(" ");
            System.out.println("temp length is " + temp.length);
            //BackgroundWorker.contacts = new String[YourAccountActivity.searchResult.split(" ").length - 4];
            for (int i = 0; i < temp.length; i++)
                temp[i] = searchResultArr[5 + i];
            BackgroundWorker.contacts = temp;
        }

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

}

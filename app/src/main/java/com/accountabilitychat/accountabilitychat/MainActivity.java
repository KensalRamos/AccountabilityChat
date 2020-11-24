package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
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
        fetchUserInfo();
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

    private void fetchUserInfo() {

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
            usernameAdded.setTextColor(Color.BLACK);
            usernameAdded.setTextSize(22);
            // Change font family - TO:DO
            // Alter bottom margins - TO:DO
            usernameAdded.setClickable(true);
            usernameAdded.setTag(Integer.toString(i));
            // On click action
            usernameAdded.setOnClickListener(contactOnClick());
            scrollLinearLayout.addView(usernameAdded);
            scrollLayout.invalidate();
            scrollLayout.requestLayout();
        }

    }
    private View.OnClickListener contactOnClick()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get contact
                Integer position = Integer.parseInt((v.getTag().toString()));

                ChatActivity.contact = BackgroundWorker.contacts[position];

                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                startActivity(intent);
            }
        };
    }
}

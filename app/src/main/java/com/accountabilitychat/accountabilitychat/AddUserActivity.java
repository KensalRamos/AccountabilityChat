package com.accountabilitychat.accountabilitychat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }


    public void AddUserOnClick(View view) {

        // Init
        EditText usernameEdit = (EditText) findViewById(R.id.getUsernameInput);
        alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add User Error");
        Boolean dupContactFlag = false; // If true contactToAdd already exists

        // BackgroundWorker variables
        String type = "update contacts";
        String username = YourAccountActivity.searchResult.split(" ")[3];
        String contactToAdd = usernameEdit.getText().toString();

        if (contactToAdd.isEmpty()) {
            alertDialog.setMessage("Please enter a username.");
            alertDialog.show();
        }
        else if (contactToAdd.contains(" ")) {
            alertDialog.setMessage("Username cannot contain whitespace.");
            alertDialog.show();
        }
        else if (contactToAdd.equals(YourAccountActivity.searchResult.split(" ")[3])) {
            alertDialog.setMessage("You, unfortunately, cannot talk to yourself.");
            alertDialog.show();
        }
        // BackgroundWorker takes over
        else {

            // Check for duplicates
            if (BackgroundWorker.contacts != null) {

                for (int i = 0; i < BackgroundWorker.contacts.length; i++) {

                    if (BackgroundWorker.contacts[i].equals(contactToAdd)) {
                        dupContactFlag = true;
                        break;
                    }

                }
            }

            if (!dupContactFlag) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, username, contactToAdd);
            }
            // If duplicates exist, just go to MainActivity.
            else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }

        }

    }
}

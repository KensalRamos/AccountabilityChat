package com.accountabilitychat.accountabilitychat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
        EditText msgEdit = (EditText) findViewById(R.id.firstMessageInput);
        alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add User Error");

        // BackgroundWorker variables
        String type = "update contacts";
        String username = YourAccountActivity.searchResult.split(" ")[3];
        String contactToAdd = usernameEdit.getText().toString();
        String message = msgEdit.getText().toString();

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
        else if (message.isEmpty()) {
            alertDialog.setMessage("Please enter a message. Perhaps try introducing yourself?");
            alertDialog.show();
        }
        // BackgroundWorker takes over
        else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            //backgroundWorker.execute(type, username);
            backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, username, contactToAdd);

            // If successful

        }

    }
}

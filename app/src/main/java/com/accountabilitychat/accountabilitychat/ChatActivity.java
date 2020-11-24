package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ChatActivity  extends AppCompatActivity {

    static String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        updateText();
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void updateText() {
        TextView senderText =(TextView) findViewById(R.id.recepTitleUsername);
        TextView usernameText =(TextView) findViewById(R.id.chattingWithTV);

        senderText.setText(getString(R.string.sender_username, YourAccountActivity.searchResult.split(" ")[3]));
        usernameText.setText(getString(R.string.chatting_with_string, contact));

    }

    // TO:DO
    public void sendMessage(View view) {

        // Init
        String type = "send chat";
        EditText msgEditTxt = (EditText) findViewById(R.id.messageInput);
        String message = msgEditTxt.getText().toString();
        String sender = YourAccountActivity.searchResult.split(" ")[3];
        String receiver = contact;
        msgEditTxt.setText("");

        // Database connection
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, message, sender, receiver);
    }
}

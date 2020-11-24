package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        System.out.println("Sending message...");
    }
}

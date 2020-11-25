package com.accountabilitychat.accountabilitychat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/*
 *
 * File name: ChatActivity.java
 *
 * Contributor(s): Kensal Ramos
 *
 * Description: In this activity, the user may chat with a desired contact. The chat log is auto
 * updated every second or every message send.
 *
 *
 */

public class ChatActivity  extends AppCompatActivity {

    static String contact;
    String[] chatLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        MainActivity.mainFlag = false;
        updateText();
        try {
            updateChat();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Handler handler = new Handler();
        final int delay = 1000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    updateChat();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!MainActivity.mainFlag)
                    handler.postDelayed(this, delay);
            }
        }, delay);

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
    public void sendMessage(View view) throws ExecutionException, InterruptedException {

        // Init
        String type = "send chat";
        AlertDialog alertDialog = alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Message Error");
        EditText msgEditTxt = (EditText) findViewById(R.id.messageInput);
        String message = msgEditTxt.getText().toString();
        if (message.isEmpty()) {
            alertDialog.setMessage("Please enter a message");
            alertDialog.show();
        }
        else {
            String sender = YourAccountActivity.searchResult.split(" ")[3];
            String receiver = contact;
            msgEditTxt.setText("");

            // Database connection
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, message, sender, receiver);
            backgroundWorker.get();
            updateChat();
        }
    }

    private void updateChat() throws ExecutionException, InterruptedException {

        // Init
        String type = "update chat";
        TextView senderTV = (TextView) findViewById(R.id.recepTitleUsername);
        String sender = senderTV.getText().toString();
        String receiver = contact;
        ArrayList<String> srArr = new ArrayList<String>();
        ArrayList<String> rsArr = new ArrayList<String>();
        ScrollView scrollLayout = (ScrollView) findViewById(R.id.chatScrollView);
        LinearLayout scrollLinearLayout = (LinearLayout) findViewById(R.id.chatLinearLayout);
        scrollLinearLayout.removeAllViews();

        // Database connection
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, sender, receiver);
        String result = backgroundWorker.get();
        if (result.isEmpty())
            return;
        chatLog = result.split("-");

        for (int i = 0; i < chatLog.length; i++) {
            System.out.println(chatLog[i]);
        }

        // Generate srArr and rsArr
        for (int i = 0; i < chatLog.length; i++) {

            if (chatLog[i].split("_")[1].split(" ")[0].equals(sender)) {
                srArr.add(chatLog[i]);
            }
            else {
                rsArr.add(chatLog[i]);
            }

        }

        // Init pointers
        int srPtr = 0;
        int rsPtr = 0;

        for (int i = 0; i < chatLog.length; i++) {

            String message;
            String params;

            // Determine which to use
            if (srPtr >= srArr.size() && rsPtr >= rsArr.size())
                break;
            else if (srArr.isEmpty()) {

                if (rsPtr >= rsArr.size())
                    break;

                message = rsArr.get(rsPtr).split("_")[0];
                params = rsArr.get(rsPtr).split("_")[1];
                rsPtr++;
            }
            else if (rsArr.isEmpty()) {

                if (srPtr >= srArr.size())
                    break;

                message = srArr.get(srPtr).split("_")[0];
                params = srArr.get(srPtr).split("_")[1];
                srPtr++;
            }
            else if (srPtr >= srArr.size()) {
                message = rsArr.get(rsPtr).split("_")[0];
                params = rsArr.get(rsPtr).split("_")[1];
                rsPtr++;
            }
            else if (rsPtr >= rsArr.size()) {
                message = srArr.get(srPtr).split("_")[0];
                params = srArr.get(srPtr).split("_")[1];
                srPtr++;
            }
            else if (Integer.parseInt(srArr.get(srPtr).split("_")[1].split(" ")[2]) < Integer.parseInt(rsArr.get(rsPtr).split("_")[1].split(" ")[2])) {

                message = srArr.get(srPtr).split("_")[0];
                params = srArr.get(srPtr).split("_")[1];
                srPtr++;

            }
            else {
                message = rsArr.get(rsPtr).split("_")[0];
                params = rsArr.get(rsPtr).split("_")[1];
                rsPtr++;
            }

            TextView chatLine = new TextView(this);
            chatLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            String lineText = "";
            if (params.split(" ")[0].equals(sender)) {
                lineText = "You: " + message;
            }
            else {
                lineText = params.split(" ")[0] + ": " + message;
            }
            chatLine.setText(lineText);
            // Change font family - TO:DO
            // Alter bottom margins - TO:DO
            // On click action
            scrollLinearLayout.addView(chatLine);
            scrollLayout.invalidate();
            scrollLayout.requestLayout();
        }

    }

}

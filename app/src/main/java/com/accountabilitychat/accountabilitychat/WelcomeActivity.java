package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

/*
 *
 * File name: WelcomeActivity.java
 *
 * Contributor(s): Kensal Ramos
 *
 * Description: This is a simple welcome screen displayed after a user logs in. This instructs the
 * user on how to use the application.
 *
 *
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        fetchUserInfo();
    }

    private void fetchUserInfo() {

        String type = "search";
        String username = BackgroundWorker.loggedUser;

        // Search for database information
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type, username);
    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}

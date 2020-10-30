package com.accountabilitychat.accountabilitychat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    EditText usernameEt, passwordEt;
    String currentUser, currentfName, currentlName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = (EditText) findViewById(R.id.usernameInput);
        passwordEt = (EditText) findViewById(R.id.passwordInput);

    }

    public void OnLogin(View view) {

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);

    }

    public void ToRegActivity(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void ToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
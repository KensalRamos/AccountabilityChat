package com.accountabilitychat.accountabilitychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class YourAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_account);
    }

    public void toHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}

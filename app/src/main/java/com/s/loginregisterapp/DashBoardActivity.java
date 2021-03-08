package com.s.loginregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity {

    private Button LogOut;
    private TextView EmailShow;
    private String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LogOut = (Button) findViewById(R.id.button);
        EmailShow = (TextView) findViewById(R.id.EmailShow);

        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(UserLoginActivity.UserEmail);
        EmailShow.setText(EmailHolder);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(DashBoardActivity.this, UserLoginActivity.class);
                startActivity(intent);
                Toast.makeText(DashBoardActivity.this,"Log Out Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
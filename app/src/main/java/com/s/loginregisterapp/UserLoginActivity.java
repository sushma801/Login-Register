package com.s.loginregisterapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class UserLoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private String Email_enter, Password_enter, finalresult;
    private String htturl = "https://letscreateworld.000webhostapp.com/LoginRegister/UserLogin.php";
    private Boolean checkEditText;
    private ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                if (checkEditText) {
                    UserLoginFunction(Email_enter, Password_enter);
                } else {
                    Toast.makeText(UserLoginActivity.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UserLoginFunction(final String email_enter, final String password_enter) {
        class UserLoginClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UserLoginActivity.this, "Loding Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                if (s.equalsIgnoreCase("Data Matched")) {
                    finish();
                    Intent intent = new Intent(UserLoginActivity.this, DashBoardActivity.class);
                    intent.putExtra(UserEmail, String.valueOf(email));
                    startActivity(intent);
                } else {
                    Toast.makeText(UserLoginActivity.this, "", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email", params[0]);
                hashMap.put("password", params[1]);
                finalresult = httpParse.postRequest(hashMap, htturl);
                return finalresult;
            }
        }
        UserLoginClass userLoginClass = new UserLoginClass();
        userLoginClass.execute(Email_enter, Password_enter);
    }

    private void CheckEditTextIsEmptyOrNot() {
        Email_enter = email.getText().toString();
        Password_enter = password.getText().toString();
        if (Email_enter.length() == 0 || Password_enter.length() == 0)
            checkEditText = false;
        else
            checkEditText = true;
    }


}

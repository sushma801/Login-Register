package com.s.loginregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button register, log_in;
    private EditText fullname, username, email, password;
    private String Full_Name, User_Name, Email, Password;
    private String finalResult;
    private String httpurl = "https://letscreateworld.000webhostapp.com/LoginRegister/UserRegistration.php";
    private Boolean CheckEditText;
    private ProgressDialog progressDialog;
    private HashMap<String, String> hashMap = new HashMap<>();
    private HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign Id's
        fullname = (EditText) findViewById(R.id.full_name);
        username = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        log_in = (Button) findViewById(R.id.login);

        //Adding Click Listener on button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check wether edit text is empty or not
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText) {
                    //If Edit Text is Not Empty
                    UserRegisterFunction(Full_Name, User_Name,  Password, Email);
                } else {
                    //If edit text is Empty
                    Toast.makeText(MainActivity.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void CheckEditTextIsEmptyOrNot() {
        Full_Name = fullname.getText().toString();
        User_Name = username.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        if (Full_Name.length() == 0 || User_Name.length() == 0 || Email.length() == 0 || Password.length() == 0) {
            CheckEditText = false;
        } else {
            CheckEditText = true;
        }
    }

    private void UserRegisterFunction(final String Full_name, final String User_Name,  final String Password, final String Email) {
        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, "register succefully", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("fullname", params[0]);
                hashMap.put("username", params[1]);
                hashMap.put("email", params[3]);
                hashMap.put("password", params[2]);
                finalResult = httpParse.postRequest(hashMap, httpurl);
                return finalResult;
            }
        }
        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(Full_name, User_Name, Password, Email);
    }
}
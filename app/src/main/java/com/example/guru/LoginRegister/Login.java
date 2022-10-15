package com.example.guru.LoginRegister;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.guru.ApplicationClass;
import com.example.guru.Main.MainActivity;
import com.example.guru.R;

import static com.example.guru.ApplicationClass.USER_EMAIL_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_NAME_SHAREDPREFS;

public class Login extends AppCompatActivity {

    private Button login, register;
    private EditText etPassword, etEmail;
    private TextView forgotPass, or;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        or = findViewById(R.id.or);

        login = findViewById(R.id.register_login);
        final Login thisActivity = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataIsntEmpty()) {
                    checkEmailPasswordMatch();

                }


            }
        });

        register = findViewById(R.id.login_register_page);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, Register.class);
                startActivity(intent);
                finish();
            }
        });
        etPassword = findViewById(R.id.password);
        etEmail = findViewById(R.id.email);

        forgotPass = findViewById(R.id.forgot_password);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().trim().isEmpty()) {
                    Toast.makeText(thisActivity, "Enter your email in the email field", Toast.LENGTH_LONG).show();
                } else {
                    String email = etEmail.getText().toString().trim();
                    showProgress(true);
                    Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void response) {
                            String passwordSent = getResources().getString(R.string.password_sent);
                            Toast.makeText(thisActivity, passwordSent, Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            LoginUtils.handleFault(fault, Login.this);

                        }
                    });
                }
            }
        });
    }


    private boolean checkDataIsntEmpty() {
        if (etPassword.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()){
            String plsEnterAllFields = getResources().getString(R.string.enter_all_fields);
            Toast.makeText(this, plsEnterAllFields, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkEmailPasswordMatch() {
        final String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        showProgress(true);
        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                ApplicationClass.user = response;
                String name = (String) Backendless.UserService.CurrentUser().getProperty("name");
                String hello = getResources().getString(R.string.hello);
                Toast.makeText(Login.this, hello + " " + name, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE).edit();
                editor.putString(USER_EMAIL_SHAREDPREFS, email);
                editor.putString(USER_NAME_SHAREDPREFS, name);
                editor.commit();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                LoginUtils.handleFault(fault, Login.this);
            }
        }, true);

    }

    public void showProgress(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            etPassword.setVisibility(View.GONE);
            etEmail.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            forgotPass.setVisibility(View.GONE);
            or.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            etPassword.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            forgotPass.setVisibility(View.VISIBLE);
            or.setVisibility(View.VISIBLE);
        }

    }
}

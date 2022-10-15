package com.example.guru.LoginRegister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
import com.backendless.persistence.local.UserIdStorageFactory;
import com.example.guru.ApplicationClass;
import com.example.guru.Main.MainActivity;
import com.example.guru.R;

import static com.example.guru.ApplicationClass.GURU_USER_LOGGED_OUT_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_NAME_SHAREDPREFS;

public class Register extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etPassReEnter;
    private ProgressBar progressBar;
    private Button register, login;
    private TextView alreadyHaveAccount;
    private TextView emailconfirmation_tv, emailconfirmation_tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Register thisActivity = this;

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etPassReEnter = findViewById(R.id.password_reenter);
        progressBar = findViewById(R.id.progressBar);
        register = findViewById(R.id.register_login);
        alreadyHaveAccount = findViewById(R.id.or);
        login = findViewById(R.id.login_register_page);
        emailconfirmation_tv = findViewById(R.id.confirmation_email_tv);
        emailconfirmation_tv2 = findViewById(R.id.confirmation_email_tv2);
        SharedPreferences prefs = getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE);
        final String name = prefs.getString(USER_NAME_SHAREDPREFS, "");

        showProgress(true);
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                if (response) {
                    final String userObjectId = UserIdStorageFactory.instance().getStorage().get();
                    if( userObjectId != null && !userObjectId.equals( "" )) {

                        Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Backendless.UserService.setCurrentUser( response );
                                ApplicationClass.user = response;
                                String name = (String) response.getProperty("name");
                                String hello = getResources().getString(R.string.hello);
                                Toast.makeText(Register.this, hello + " " + name, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(thisActivity, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                if (name != null && !name.isEmpty() && !name.equals(GURU_USER_LOGGED_OUT_SHAREDPREFS)) {
                                    String hello = getResources().getString(R.string.hello);
                                    Toast.makeText(Register.this, hello + " " + name + " (offline)", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(thisActivity, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    LoginUtils.handleFault(fault, Register.this);
                                }

                            }
                        });
                    } else {
                        showProgress(false);
                    }
                } else {
                    showProgress(false);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if (name != null && !name.isEmpty()) {
                    String hello = getResources().getString(R.string.hello);
                    Toast.makeText(Register.this, hello + " " + name + " (offline)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(thisActivity, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LoginUtils.handleFault(fault, Register.this);
                }
            }
        });


        Button login = findViewById(R.id.login_register_page);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, Login.class);
                startActivity(intent);
                finish();
            }
        });

        Button register = findViewById(R.id.register_login);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataIsntEmpty()) {
                    if (checkPasswordsAreSame()) {
                        if (checkEmailIsNotYetUsed()) {
                            final String name = etName.getText().toString().trim();
                            String email = etEmail.getText().toString().trim();
                            String password = etPassword.getText().toString().trim();

                            BackendlessUser user = new BackendlessUser();
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setProperty("name", name);
                            showProgress(true);
                            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {


                                @Override
                                public void handleResponse(BackendlessUser response) {
                                    setConfirmationVisibility();



                                }


                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    LoginUtils.handleFault(fault, Register.this);

                                }
                            });


                        }

                    }
                }

            }
        });

    }



    private boolean checkDataIsntEmpty() {
        if (etName.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() ||
        etPassword.getText().toString().isEmpty() || etPassReEnter.getText().toString().isEmpty()) {
            String plsEnterAllFields = getResources().getString(R.string.enter_all_fields);
            Toast.makeText(this, plsEnterAllFields, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPasswordsAreSame() {
        if (!etPassword.getText().toString().trim().equals(etPassReEnter.getText().toString().trim())) {
            Toast.makeText(this, "Make sure passwords are the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkEmailIsNotYetUsed() {
        return true;
    }

    public void showProgress(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            etName.setVisibility(View.GONE);
            etEmail.setVisibility(View.GONE);
            etPassword.setVisibility(View.GONE);
            etPassReEnter.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            alreadyHaveAccount.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            etName.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.VISIBLE);
            etPassword.setVisibility(View.VISIBLE);
            etPassReEnter.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            alreadyHaveAccount.setVisibility(View.VISIBLE);
        }

    }

    private void setConfirmationVisibility() {
        progressBar.setVisibility(View.GONE);
        emailconfirmation_tv.setVisibility(View.VISIBLE);
        emailconfirmation_tv2.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        alreadyHaveAccount.setVisibility(View.VISIBLE);
        alreadyHaveAccount.setText(R.string.link_already_clicked);
        View blankspace = findViewById(R.id.blank_space);
        blankspace.setVisibility(View.VISIBLE);

    }




}

package com.example.guru;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class ApplicationClass extends Application {

    public static final String APPLICATION_ID = "F0F1122D-1E00-668C-FF2A-E378B3E8CA00";
    public static final String API_KEY = "BF43AF9E-F08C-49EE-BBFB-EEFED5AE85BD";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static final String GURU_USER_LOGGED_OUT_SHAREDPREFS = "com.example.guru.user_logged_out";
    public static final String USER_FILENAME_SHAREDPREFS = "com.example.guru.saved_user_preferences";
    public static final String USER_EMAIL_SHAREDPREFS = "com.example.guru_saved_user_email";
    public static final String USER_NAME_SHAREDPREFS = "com.example.guru_saved_user_name";

    public static BackendlessUser user;



    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );



    }


}

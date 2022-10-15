package com.example.guru.LoginRegister;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessFault;
import com.example.guru.Main.MainActivity;
import com.example.guru.R;

public class LoginUtils {
    public static void handleFault(BackendlessFault fault, Context context) {
        String error = context.getResources().getString(R.string.error);
        Toast.makeText(context, error + ": " + fault.getMessage(),
                Toast.LENGTH_LONG).show();
        if (context instanceof Register) {
            ((Register) context).showProgress(false);
        } else if (context instanceof Login) {
            ((Login) context).showProgress(false);
        }
    }

//    public static void handleResponse(BackendlessUser response) {
//
//    }
}

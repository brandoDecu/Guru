package com.example.guru.Main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.guru.LoginRegister.Login;
import com.example.guru.LoginRegister.LoginUtils;
import com.example.guru.R;

import static android.content.Context.MODE_PRIVATE;
import static com.example.guru.ApplicationClass.GURU_USER_LOGGED_OUT_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_EMAIL_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_NAME_SHAREDPREFS;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private View view;
    private int[] themes;
    private FrameLayout progressView;
    private ScrollView scrollView;
    private static final String TAG = "SettingsFragment";

    private ImageView dropdown2, dropdown3;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView username_tv = view.findViewById(R.id.username);
        SharedPreferences prefs = getActivity().getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE);
        final String username = prefs.getString(USER_NAME_SHAREDPREFS, "");
        username_tv.setText(username);



        /*
         LOGOUT
         */
        Button logout = view.findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        String logged_out = getResources().getString(R.string.logged_out);
                        Toast.makeText(getActivity(), username + " " + logged_out, Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE).edit();
                        editor.putString(USER_NAME_SHAREDPREFS, GURU_USER_LOGGED_OUT_SHAREDPREFS);
                        editor.putString(USER_EMAIL_SHAREDPREFS, GURU_USER_LOGGED_OUT_SHAREDPREFS);
                        editor.commit();
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        LoginUtils.handleFault(fault, getActivity());
                        showProgress(false);

                    }
                });




            }
        });

        Switch airplanemode_switch = view.findViewById(R.id.airplanemode_switch);
        airplanemode_switch.setChecked(prefs.getBoolean("airplanemode", false));
        airplanemode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE).edit();
                if (isChecked) {
                    editor.putBoolean("airplanemode", true);
                } else {
                    editor.putBoolean("airplanemode", false);
                }
                editor.commit();
            }
        });

        dropdown2 = view.findViewById(R.id.drop_down2);
        dropdown3 = view.findViewById(R.id.drop_down3);

        LinearLayout profile_layout = view.findViewById(R.id.profile_layout);
        LinearLayout airplanemode_layout = view.findViewById(R.id.airplanemode_layout);

        OnArrowClickListener listener = new OnArrowClickListener();
        profile_layout.setOnClickListener(listener);
        airplanemode_layout.setOnClickListener(listener);




        progressView = view.findViewById(R.id.progressview);
        scrollView = view.findViewById(R.id.scrollview);

        return view;
    }

    class OnArrowClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LinearLayout subLayout = view.findViewById(R.id.profile_sublayout);
            ImageView dropdownImageView = dropdown2;

            switch (v.getId()) {
                case R.id.profile_layout:
                    subLayout = view.findViewById(R.id.profile_sublayout);
                    dropdownImageView =dropdown2;
                    break;
                case R.id.airplanemode_layout:
                    subLayout = view.findViewById(R.id.airplanemode_sublayout);
                    dropdownImageView = dropdown3;
                    break;
            }

            if (subLayout.getVisibility() == View.VISIBLE) {
                subLayout.setVisibility(View.GONE);
                dropdownImageView.setImageResource(R.drawable.arrow_down_32dp);
            } else {
                subLayout.setVisibility(View.VISIBLE);
                dropdownImageView.setImageResource(R.drawable.arrow_up_32dp);
            }
        }
    }




    public void showProgress(boolean b) {
        if (b) {
            scrollView.setVisibility(View.GONE);
            progressView.setVisibility(View.VISIBLE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);

        }

    }




}

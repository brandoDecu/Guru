package com.example.guru.Main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guru.R;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextView self_observation;
    private TextView internal_dialogue;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        self_observation = view.findViewById(R.id.self_observation);
        internal_dialogue = view.findViewById(R.id.internal_dialogue);
        LinearLayout self_obs_layout = view.findViewById(R.id.self_obs_layout);
        LinearLayout int_dial_layout =  view.findViewById(R.id.int_dial_layout);
        self_observation.setVisibility(View.GONE);
        internal_dialogue.setVisibility(View.GONE);
        ImageView self_obs_image = view.findViewById(R.id.self_obs_img);
        self_obs_image.setImageResource(R.drawable.self_obs_logo);
        ImageView int_dial_img = view.findViewById(R.id.int_dial_img);
        int_dial_img.setImageResource(R.drawable.internal_dial_logo);
        setSelfObs(view);
        setInternalDial(view);

        class OnSelfObsClicked implements View.OnClickListener {
            public void onClick(View v) {
                if (self_observation.getVisibility() == View.GONE) {
                    self_observation.setVisibility(View.VISIBLE);
                    internal_dialogue.setVisibility(View.GONE);
                } else {
                    self_observation.setVisibility(View.GONE);
                }
            }

        }

        class OnIntDialClicked implements View.OnClickListener {
            public void onClick(View v) {
                if (internal_dialogue.getVisibility() == View.GONE) {
                    internal_dialogue.setVisibility(View.VISIBLE);
                    self_observation.setVisibility(View.GONE);
                } else {
                    internal_dialogue.setVisibility(View.GONE);
                }
            }

        }

        OnSelfObsClicked selfObsListener = new OnSelfObsClicked();
        OnIntDialClicked indDialListener = new OnIntDialClicked();

        self_obs_layout.setOnClickListener(selfObsListener);
        int_dial_layout.setOnClickListener(indDialListener);



        return view;
    }


    public void setSelfObs(View view) {
        Date today_date = new Date(System.currentTimeMillis());
        int day = today_date.getDate();

        String self_observation_practice = "";
        switch (day) {
            case 1:
                self_observation_practice = getString(R.string.self_observation1);
                break;
            case 2:
                self_observation_practice = getString(R.string.self_observation2);
                break;
            case 3:
                self_observation_practice = getString(R.string.self_observation3);
                break;
            case 4:
                self_observation_practice = getString(R.string.self_observation4);
                break;
            case 5:
                self_observation_practice = getString(R.string.self_observation5);
                break;
            case 6:
                self_observation_practice = getString(R.string.self_observation6);
                break;
            case 7:
                self_observation_practice = getString(R.string.self_observation7);
                break;
            case 8:
                self_observation_practice = getString(R.string.self_observation8);
                break;
            case 9:
                self_observation_practice = getString(R.string.self_observation9);
                break;
            case 10:
                self_observation_practice = getString(R.string.self_observation10);
                break;
            case 11:
                self_observation_practice = getString(R.string.self_observation11);
                break;
            case 12:
                self_observation_practice = getString(R.string.self_observation12);
                break;
            case 13:
                self_observation_practice = getString(R.string.self_observation13);
                break;
            case 14:
                self_observation_practice = getString(R.string.self_observation14);
                break;
            case 15:
                self_observation_practice = getString(R.string.self_observation15);
                break;
            case 16:
                self_observation_practice = getString(R.string.self_observation16);
                break;
            case 17:
                self_observation_practice = getString(R.string.self_observation17);
                break;
            case 18:
                self_observation_practice = getString(R.string.self_observation18);
                break;
            case 19:
                self_observation_practice = getString(R.string.self_observation19);
                break;
            case 20:
                self_observation_practice = getString(R.string.self_observation20);
                break;
            case 21:
                self_observation_practice = getString(R.string.self_observation21);
                break;
            case 22:
                self_observation_practice = getString(R.string.self_observation22);
                break;
            case 23:
                self_observation_practice = getString(R.string.self_observation23);
                break;
            case 24:
                self_observation_practice = getString(R.string.self_observation24);
                break;
            case 25:
                self_observation_practice = getString(R.string.self_observation25);
                break;
            case 26:
                self_observation_practice = getString(R.string.self_observation26);
                break;
            case 27:
                self_observation_practice = getString(R.string.self_observation27);
                break;
            case 28:
                self_observation_practice = getString(R.string.self_observation28);
                break;
            case 29:
                self_observation_practice = getString(R.string.self_observation29);
                break;
            case 30:
                self_observation_practice = getString(R.string.self_observation30);
                break;
            case 31:
                self_observation_practice = getString(R.string.self_observation31);
                break;
        }
        self_observation.setText(self_observation_practice);
    }

    public void setInternalDial(View view) {

        Date today_date = new Date(System.currentTimeMillis());
        int day = today_date.getDate();
        String internal_dialogue_practice = "";
        switch (day) {
            case 1:
                internal_dialogue_practice = getString(R.string.internal_dialogue1);
                break;
            case 2:
                internal_dialogue_practice = getString(R.string.internal_dialogue2);
                break;
            case 3:
                internal_dialogue_practice = getString(R.string.internal_dialogue3);
                break;
            case 4:
                internal_dialogue_practice = getString(R.string.internal_dialogue4);
                break;
            case 5:
                internal_dialogue_practice = getString(R.string.internal_dialogue5);
                break;
            case 6:
                internal_dialogue_practice = getString(R.string.internal_dialogue6);
                break;
            case 7:
                internal_dialogue_practice = getString(R.string.internal_dialogue7);
                break;
            case 8:
                internal_dialogue_practice = getString(R.string.internal_dialogue8);
                break;
            case 9:
                internal_dialogue_practice = getString(R.string.internal_dialogue9);
                break;
            case 10:
                internal_dialogue_practice = getString(R.string.internal_dialogue10);
                break;
            case 11:
                internal_dialogue_practice = getString(R.string.internal_dialogue11);
                break;
            case 12:
                internal_dialogue_practice = getString(R.string.internal_dialogue12);
                break;
            case 13:
                internal_dialogue_practice = getString(R.string.internal_dialogue13);
                break;
            case 14:
                internal_dialogue_practice = getString(R.string.internal_dialogue14);
                break;
            case 15:
                internal_dialogue_practice = getString(R.string.internal_dialogue15);
                break;
            case 16:
                internal_dialogue_practice = getString(R.string.internal_dialogue16);
                break;
            case 17:
                internal_dialogue_practice = getString(R.string.internal_dialogue17);
                break;
            case 18:
                internal_dialogue_practice = getString(R.string.internal_dialogue18);
                break;
            case 19:
                internal_dialogue_practice = getString(R.string.internal_dialogue19);
                break;
            case 20:
                internal_dialogue_practice = getString(R.string.internal_dialogue20);
                break;
            case 21:
                internal_dialogue_practice = getString(R.string.internal_dialogue21);
                break;
            case 22:
                internal_dialogue_practice = getString(R.string.internal_dialogue22);
                break;
            case 23:
                internal_dialogue_practice = getString(R.string.internal_dialogue23);
                break;
            case 24:
                internal_dialogue_practice = getString(R.string.internal_dialogue24);
                break;
            case 25:
                internal_dialogue_practice = getString(R.string.internal_dialogue25);
                break;
            case 26:
                internal_dialogue_practice = getString(R.string.internal_dialogue26);
                break;
            case 27:
                internal_dialogue_practice = getString(R.string.internal_dialogue27);
                break;
            case 28:
                internal_dialogue_practice = getString(R.string.internal_dialogue28);
                break;
            case 29:
                internal_dialogue_practice = getString(R.string.internal_dialogue29);
                break;
            case 30:
                internal_dialogue_practice = getString(R.string.internal_dialogue30);
                break;
            case 31:
                internal_dialogue_practice = getString(R.string.internal_dialogue31);
                break;
        }
        internal_dialogue.setText(internal_dialogue_practice);

    }
}

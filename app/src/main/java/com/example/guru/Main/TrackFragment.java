package com.example.guru.Main;


import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guru.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackFragment extends Fragment {

    private static final String TAG = "TrackFragment";
    private View view;


    public TrackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.tracks_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new TrackAdapter(getActivity()));

    }
}

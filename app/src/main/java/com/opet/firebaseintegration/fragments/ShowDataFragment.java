package com.opet.firebaseintegration.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

import com.opet.firebaseintegration.R;
import com.opet.firebaseintegration.ShowDataActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowDataFragment extends Fragment{

    private TextView showData;

    public ShowDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_data, container, false);

        showData = view.findViewById(R.id.showData);

        showData.setText(ShowDataActivity.resultado);

        return view;
    }
}

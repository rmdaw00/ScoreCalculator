package com.rassam.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rassam.data.Data;

public class History extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        Data data = new Data(getContext());
        data.loadSample(getContext());

        TextView viewHistory = view.findViewById(R.id.viewHistory);
        viewHistory.setText(data.sampleOutput);

        return view;
    }
}

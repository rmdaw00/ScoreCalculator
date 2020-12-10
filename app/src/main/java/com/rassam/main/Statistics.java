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

public class Statistics extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Data data = new Data(getContext());
        data.loadSample(getContext());

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        TextView textSearchResult = view.findViewById(R.id.textSearchResult);
        textSearchResult.setText("");

        return view;
    }


}

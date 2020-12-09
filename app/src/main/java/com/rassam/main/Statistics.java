package com.rassam.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rassam.data.DummyData;

import org.w3c.dom.Text;

public class Statistics extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DummyData data = new DummyData(getContext());
        data.loadSample(getContext());

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        TextView textSearchResult = view.findViewById(R.id.textSearchResult);
        textSearchResult.setText("");

        return view;
    }


}

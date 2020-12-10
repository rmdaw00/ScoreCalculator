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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rassam.data.DummyData;

import org.w3c.dom.Text;

public class History extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        DummyData data = new DummyData(getContext());
        data.loadSample(getContext());

        TextView viewHistory = view.findViewById(R.id.viewHistory);
        viewHistory.setText(data.sampleOutput);

        return view;
    }
}

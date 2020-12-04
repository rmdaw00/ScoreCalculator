package com.rassam.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;

public class GameSnooker extends AppCompatActivity {
    TextView txtPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_snooker);
        Ball ball = new Ball(1, BallColor.WHITE);
        txtPoints = findViewById(R.id.txtPoints);
        txtPoints.setText(ball.getPoints()+ "");
    }
}
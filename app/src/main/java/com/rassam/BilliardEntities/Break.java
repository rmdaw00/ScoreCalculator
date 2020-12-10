package com.rassam.BilliardEntities;

import com.rassam.ESnooker.Ball;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Break implements Serializable {
    List<Ball> balls = new ArrayList<Ball>();
    int id;
    Date date;
    Ball lastBall;



    public Break() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,01,01);
        date = new Date();
    }

    public int getPoints(){
        int points = 0;
        for (Ball ball: balls) {
            points+=ball.getPoints();
        }
        return points;
    }

    public int getBallCount() {
        return balls.size();
    }
    public void addBall(Ball ball) {
        lastBall = ball;
        balls.add(ball);
        ball.setOrderInBreak(getBallCount());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Ball getLastBall() {
        return lastBall;
    }

    public void setLastBall(Ball lastBall) {
        this.lastBall = lastBall;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

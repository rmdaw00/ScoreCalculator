package com.rassam.ESnooker;

import java.io.Serializable;

public class Ball implements Serializable {
    private int orderInBreak;
    private BallColor ballColor;
    private int Points;

    public Ball(int orderInBreak, BallColor ballColor) {
        this.orderInBreak = orderInBreak;
        this.ballColor = ballColor;
        switch (ballColor)
        {
            case WHITE:
                setPoints(-4);
                break;
            default:
                setPoints(ballColor.ordinal());
        }
    }

    public Ball(BallColor ballColor) {
        this.ballColor = ballColor;
        switch (ballColor)
        {
            case WHITE:
                setPoints(-4);
                break;
            default:
                setPoints(ballColor.ordinal());
        }
    }

    public int getOrderInBreak() {
        return orderInBreak;
    }

    public void setOrderInBreak(int orderInBreak) {
        this.orderInBreak = orderInBreak;
    }

    public BallColor getBallColor() {
        return ballColor;
    }

    public void setBallColor(BallColor ballColor) {
        this.ballColor = ballColor;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}






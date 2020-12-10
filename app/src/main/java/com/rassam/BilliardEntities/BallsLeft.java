package com.rassam.BilliardEntities;

import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BallsLeft  implements Serializable  {
    private List<Ball> balls;

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    public BallsLeft(GameType gametype) {
        balls = new ArrayList<Ball>();
        switch (gametype) {
            case snooker:

                //Adding All Colors except White and 15Reds
                for (BallColor ballColor: BallColor.values()) {
                    if (ballColor != BallColor.WHITE) {
                        balls.add(new Ball(0,ballColor));
                    } else if (ballColor != BallColor.RED) {
                        for (int i=1;i<16; i++){
                            //Using OrderInBreak to Identify Reds by ID to reflect Reds in activity
                            balls.add(new Ball(i, BallColor.RED));
                        }
                    }
                }
                break;

            case Ball8:
                break;
        }
    }

    public int getRedCount() {
        int count = 0;
        for (Ball ball:balls) {
            if (ball.getBallColor() == BallColor.RED) count++;
        }
        return count;
    }

    public void removeColoredBallMAYBE(BallColor ballColor, boolean exception) {
        if (exception) return;

        if (getRedCount()==0) {
            for (Ball ball : balls) {
                if (ball.getBallColor() == ballColor) {
                    balls.remove(ball);
                    break;
                }
            }
        }
    }

    public void removeRed() {
        for (Ball ball: balls) {
            if (ball.getBallColor() == BallColor.RED) {
                    balls.remove(ball);
                    break;
            }
        }
    }

    //this is supposed to be counted at teh start of a players turn not inside
    public int getMaxPointsLeft() {
        int max = 0;
        for (Ball ball:balls) {
            if (ball.getBallColor() == BallColor.RED) {
                max += 8;
            } else {
                max += ball.getBallColor().ordinal();
            }
        }
        return max;
    }
}

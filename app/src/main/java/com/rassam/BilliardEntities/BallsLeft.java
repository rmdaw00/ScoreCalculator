package com.rassam.BilliardEntities;

import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;

import java.util.ArrayList;
import java.util.List;

public class BallsLeft {
    private List<Ball> balls;


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


    public void removeRed(int id) {
        for (Ball ball: balls) {
            if (ball.getBallColor() == BallColor.RED) {
                if (ball.getOrderInBreak() == id) {
                    balls.remove(ball);
                    break;
                }
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

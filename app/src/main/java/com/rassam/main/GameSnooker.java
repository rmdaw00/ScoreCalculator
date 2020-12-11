package com.rassam.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.rassam.BilliardEntities.Game;
import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;
import com.rassam.ESnooker.PlayerInGame;
import com.rassam.data.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSnooker extends AppCompatActivity {

    TextView txtGameID,txtGamePoints,txtPlayerName,txtPlayerScore,txtPlayerPots,txtPlayerFouls;
    TextView txtPlayerBreakAvg,txtPlayerBreakMax,txtCongrats;
    ImageView bRed1, bRed2, bRed3, bRed4, bRed5, bRed6, bRed7, bRed8;
    ImageView bRed9, bRed10, bRed11, bRed12, bRed13, bRed14, bRed15;
    ImageView bGreen, bBlue, bYellow, bBrown, bPink, bBlack;
    ImageView imgCurrentBall;
    LinearLayout lyt_Break;
    ChipGroup chipGroup;
    ConstraintLayout cly_break,cly_Options,cly_Balls,cly_Congrats;
    List<ImageView> imgRedBalls = new ArrayList<ImageView>();
    List<ImageView> imgColoredBalls = new ArrayList<ImageView>();
    List<Chip> chips = new ArrayList<Chip>();
    Game game;
    PlayerInGame currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_snooker);

        findIDs();

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if(intent.hasExtra("game")) {
            game = (Game) extras.getSerializable("game");
        }
        txtGameID.setText(String.valueOf(game.getGameID()));
        currentPlayer = game.getPlayers().get(0);
        updatePlayerUI();

        //Creating new TextView
        chipGroup= findViewById(R.id.chpGroup_Players);
        chipGroup.removeAllViews();
        chipGroup.setSingleSelection(true);



        for (PlayerInGame player: game.getPlayers()) {
            Chip newChip = new Chip(this);
            newChip.setId(player.getId());
            newChip.setOnClickListener(this::chipClicked);
            newChip.setText(player.getName());
            newChip.setCheckable(true);
            newChip.setHeight(170);
            if (player.getId() == 1) newChip.setChecked(true);
            chips.add(newChip);
            chipGroup.addView(newChip);
        }

    }

    private void findIDs() {
        txtGameID = findViewById(R.id.txtGameID);
        txtGamePoints = findViewById(R.id.txtGamePoints);
        txtPlayerName = findViewById(R.id.txtPlayerName);
        txtPlayerScore = findViewById(R.id.txtPlayerPoints);
        txtPlayerPots = findViewById(R.id.txtPlayerPots);
        txtPlayerFouls= findViewById(R.id.txtPlayerFouls);;
        txtPlayerBreakAvg= findViewById(R.id.txtPlayerAvgBreak);;
        txtPlayerBreakMax= findViewById(R.id.txtPlayerMaxBreak);;
        txtCongrats = findViewById(R.id.txtCongrats);

        lyt_Break = findViewById(R.id.lyt_Break);
        cly_break = findViewById(R.id.cly_break);
        cly_Options = findViewById(R.id.cly_Options);
        cly_Balls = findViewById(R.id.cly_Balls);
        cly_Congrats = findViewById(R.id.cly_Congrats);

        imgCurrentBall = findViewById(R.id.imgCurrentBall);

        bYellow = findViewById(R.id.bYellow);
        bBrown = findViewById(R.id.bBrown);
        bGreen = findViewById(R.id.bGreen);
        bBlue = findViewById(R.id.bBlue);
        bPink = findViewById(R.id.bPink);
        bBlack = findViewById(R.id.bBlack);
        bRed1 = findViewById(R.id.bRed1);
        bRed2 = findViewById(R.id.bRed2);
        bRed3 = findViewById(R.id.bRed3);
        bRed4 = findViewById(R.id.bRed4);
        bRed5 = findViewById(R.id.bRed5);
        bRed6 = findViewById(R.id.bRed6);
        bRed7 = findViewById(R.id.bRed7);
        bRed8 = findViewById(R.id.bRed8);
        bRed9 = findViewById(R.id.bRed9);
        bRed10 = findViewById(R.id.bRed10);
        bRed11 = findViewById(R.id.bRed11);
        bRed12 = findViewById(R.id.bRed12);
        bRed13 = findViewById(R.id.bRed13);
        bRed14 = findViewById(R.id.bRed14);
        bRed15 = findViewById(R.id.bRed15);
        Collections.addAll(imgRedBalls,bRed1,bRed2,bRed3,bRed4,bRed5,bRed5,bRed6,bRed7,bRed8
                ,bRed9,bRed10,bRed11,bRed12,bRed13,bRed14,bRed15);
        Collections.addAll(imgColoredBalls,bGreen, bBlue, bYellow, bBrown, bPink, bBlack);
    }

    @Override
    public void onBackPressed() {
        if (game.isFinished()) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            super.onBackPressed();
        } else
        {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            super.onBackPressed();
        }
    }



    public void chipClicked(View view) {
        Chip chip = (Chip) view;
        currentPlayer.nextTurn(false);
        chip.setChecked(true);
        for (PlayerInGame player: game.getPlayers()){
            if (chip.getId() == player.getId())
            {
                currentPlayer = player;
                break;
            }

        }
        updatePlayerUI();
    }

    public void nextChip() {
        View nextchip = chips.get(((int)chipGroup.getCheckedChipId()) % (chipGroup.getChildCount()));
        chipClicked(nextchip);

    }

    public void updatePlayerUI() {
        txtGamePoints.setText(String.valueOf(game.getBallsLeft().getMaxPointsLeft()));
        String extrascore = "";
        txtPlayerName.setText(currentPlayer.getName());
        if (currentPlayer.getCurrentBreak().getPoints()>0){
            extrascore+="(+"+ currentPlayer.getCurrentBreak().getPoints()+")";
        }
        txtPlayerScore.setText(String.valueOf(currentPlayer.getPoints())+extrascore);
        txtPlayerPots.setText(currentPlayer.getPots() + "  (Accuracy " + currentPlayer.getAccuracy() + ")" );
        txtPlayerFouls.setText(currentPlayer.getFouls() + "  (Percentage " + currentPlayer.getFoulPercentage() + ")");
        txtPlayerBreakAvg.setText(currentPlayer.getBreakAvgPoints() + " Points\n" + currentPlayer.getBreakAvgBalls() + " Balls");
        txtPlayerBreakMax.setText(currentPlayer.getHighestBreak().getPoints() + " Points\n" + currentPlayer.getHighestBreak().getBallCount() + " Balls");


        boolean noMoreRed = game.getBallsLeft().getRedCount() == 0;
        boolean isFirstBall = currentPlayer.getCurrentBreak().getLastBall()==null;
        if (!isFirstBall) {
            boolean lastBallIsRed = currentPlayer.getCurrentBreak().getLastBall().getBallColor()==BallColor.RED;
            if (noMoreRed && lastBallIsRed) {
                uiEnableColored(true);
            }  else if (noMoreRed) {
                //will enable the user to select colored balls if last ball potted is red
                uiEnableOneColored();
            } else {
                uiEnableColored(lastBallIsRed);
            }
        } else{
            imgCurrentBall.setImageDrawable(null);
            if (!noMoreRed) {
                uiEnableColored(false);
            } else {
                uiEnableOneColored();
            }
        }
        //setting the color of current ball in the last ball box is in drawBreak
        drawBreak();

        if (game.isFinished()){
            finishGame();
        }
    }



    public void uiEnableColored(boolean enabled) {
        /*
        * This Method will do 2 things:
        * - this is meant for the start of the game, to disable and dim red ball that are gone
        * - disable and change reduce opacity of colored balls when not in turn (VARIABLE NAME: alpha)
        * */
        int alpha = 0, redBallCount = 0;

        for (ImageView imgBall: imgRedBalls) {
            imgBall.setImageAlpha(50);
            imgBall.setEnabled(false);
        }

        if (enabled) alpha = 255; else alpha = 155;
        for (ImageView imgBall: imgColoredBalls) {
            imgBall.setImageAlpha(alpha);
            imgBall.setEnabled(enabled);
        }

        for (Ball ball:game.getBallsLeft().getBalls()) {
            if (ball.getBallColor() == BallColor.RED) {
                imgRedBalls.get(redBallCount).setImageAlpha(255);
                imgRedBalls.get(redBallCount++).setEnabled(true);
            }
        }
    }

    public void uiEnableOneColored() {
        /*
         * This Method will:
         * do the last game events, allowing shooting only the lowest colored ball
         * */
        Ball lowestValueBall = new Ball(BallColor.BLACK);

        for (ImageView imgBall: imgColoredBalls) {
            imgBall.setImageAlpha(50);
            imgBall.setEnabled(false);
        }

        for (Ball ball:game.getBallsLeft().getBalls()) {
            if(ball.getPoints()<lowestValueBall.getPoints()) lowestValueBall = ball;

        }

        for (ImageView imgBall: imgColoredBalls) {
            if (lowestValueBall.getPoints()<Integer.parseInt(imgBall.getTag().toString())) {
                //Ball is still available yet not the one to shoot
                imgBall.setImageAlpha(155);
            } else if (lowestValueBall.getPoints()==Integer.parseInt(imgBall.getTag().toString())){
                imgBall.setImageAlpha(255);
                imgBall.setEnabled(true);
            }
        }
    }


    public void missed(View view) {
        currentPlayer.miss();
        nextChip();
        updatePlayerUI();
    }

    public void madeFoul(View view) {
        currentPlayer.foul(4);
        nextChip();
        updatePlayerUI();
    }

    public void removeRed(View view) {
       game.getBallsLeft().removeRed();

        updatePlayerUI();
    }

    public void doFinish(View view) {
        game.forceFinish();
        updatePlayerUI();
    }

    public void sBall(View view) {
        //color value is stored in tag of colored ball images (to use 1 method)
        int color = Integer.parseInt(view.getTag().toString());

        boolean lastBallIsRed = true; //had to do it
        if (currentPlayer.getCurrentBreak().getLastBall() != null)
            lastBallIsRed = currentPlayer.getCurrentBreak().getLastBall().getBallColor()==BallColor.RED;

        boolean noMoreRed = game.getBallsLeft().getRedCount() == 0;

        BallColor ballcolor = BallColor.values()[color];
        currentPlayer.pot(new Ball(ballcolor));


        //the end condition parameter if true it will remove the colored balls
        // it is true if no more reds and the last ball potted wasnt red
        if (noMoreRed && lastBallIsRed){
            game.getBallsLeft().removeColoredBallMAYBE(ballcolor, true);
        } else {
            game.getBallsLeft().removeColoredBallMAYBE(ballcolor, false);
        }




        updatePlayerUI();
    }

    public void sRedBall(View view) {
        currentPlayer.pot(new Ball(BallColor.RED));
        game.getBallsLeft().removeRed();

        updatePlayerUI();
    }

    public void drawBreak() {
        lyt_Break.removeAllViews();
        int lastBallID = currentPlayer.getCurrentBreak().getBallCount();
        for (Ball ball:currentPlayer.getCurrentBreak().getBalls()) {
            ImageView imgball = new ImageView(this);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            imgball.setAdjustViewBounds(true);
            imgball.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgball.setPadding(10,10,10,10);

            imgball.setLayoutParams(params);
            boolean isLastBall = ball.getOrderInBreak() == lastBallID;
            switch(ball.getBallColor()){
                case RED:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bred);
                    imgball.setImageResource(R.drawable.bred);
                    break;

                case BLUE:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bblue);
                    imgball.setImageResource(R.drawable.bblue);
                    break;

                case PINK:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bpink);
                    imgball.setImageResource(R.drawable.bpink);
                    break;

                case BLACK:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bblack);
                    imgball.setImageResource(R.drawable.bblack);
                    break;

                case BROWN:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bbrown);
                    imgball.setImageResource(R.drawable.bbrown);
                    break;

                case GREEN:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.bgreen);
                    imgball.setImageResource(R.drawable.bgreen);
                    break;

                case YELLOW:
                    if (isLastBall) imgCurrentBall.setImageResource(R.drawable.byellow);
                    imgball.setImageResource(R.drawable.byellow);
                    break;
            }

            lyt_Break.addView(imgball,0);
        }
    }

    private void finishGame() {
        cly_Balls.removeAllViews();
        cly_Options.removeAllViews();
        cly_break.removeAllViews();
        cly_Congrats.setVisibility(View.VISIBLE);
        if (game.getWinner()!=null)  {
            txtCongrats.setText("The Winner is " + game.getWinner().getName());
            txtCongrats.setVisibility(View.VISIBLE);
        }
        for (PlayerInGame player: game.getPlayers()) {
            Data.updatePlayerTotals(player.getPlayerAssociated());
        }
        Data.updateGame(game);
        Data.saveData(this);
    }

}
package com.rassam.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rassam.BilliardEntities.Break;
import com.rassam.BilliardEntities.Game;
import com.rassam.BilliardEntities.GameType;
import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;
import com.rassam.ESnooker.PlayerInGame;
import com.rassam.ESnooker.PlayerTotal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class testFile extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView txtSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtSample = findViewById(R.id.txtSample);

    }
    public void loadSample(View view) {
        preferences = getSharedPreferences(String.valueOf(R.string.DatabaseLists),MODE_PRIVATE);
        //editor = preferences.edit();
        ArrayList<Game> allGames;
        ArrayList<PlayerTotal> allPlayers;
        Gson gson = new Gson();

        /*
         *
         * */
        String gameListStr = preferences.getString("ListOfGames", "");
        if(gameListStr.length()>0) {
            Type gameType = new TypeToken<ArrayList<Game>>() {}.getType();
            allGames = gson.fromJson(gameListStr,gameType);
        } else {
            allGames = new ArrayList<Game>();
        }

        String playerListStr = preferences.getString("ListOfPlayers", "");
        if(playerListStr.length()>0) {
            Type playerType = new TypeToken<ArrayList<PlayerTotal>>() {}.getType();
            allPlayers = gson.fromJson(playerListStr,playerType);
        } else {
            allPlayers = new ArrayList<PlayerTotal>();
        }

        String sampleOutput = "";
        for (Game game:allGames) {
            sampleOutput += "Game " + game.getGameID() +"\n";
            sampleOutput += "Game Type: " + game.getGametype().toString() +"\n";
            sampleOutput += "Players: ";
            for (PlayerInGame player: game.getPlayers()) {
                sampleOutput += player.getName() + ",";
            }
            sampleOutput += "\n";

            sampleOutput += "Winner: " + game.getWinner().getName() +"\n";
            sampleOutput += "StartTime: " + game.getStartTime().toString() +"\n";
            sampleOutput += "EndTime: " + game.getEndTime().toString() +"\n\n";


            for (PlayerInGame player: game.getPlayers()) {
                sampleOutput += player.getName() + "\n";
                sampleOutput += "Score: " + player.getPoints() + "\n";
                sampleOutput += player.getPots() + " balls potted with accuracy of "+ player.getAccuracy()+"\n";
                sampleOutput += player.getFouls() + " Fouls committed, " + player.getFoulPercentage() +"\n";
                sampleOutput += "Average Balls per Break: " + player.getBreakAvgBalls() + "\n";
                sampleOutput += "Average Points per Break: " + player.getBreakAvgPoints() + "\n\n";
            }

        }




        txtSample.setText(sampleOutput);


    }

    public void createSample(View view) {


        PlayerTotal noorTotal = new PlayerTotal(1,"Noor", GameType.snooker);

        Break highestBreak = new Break();
        highestBreak.addBall(new Ball(1, BallColor.RED));
        highestBreak.addBall(new Ball(2, BallColor.BLACK));
        highestBreak.addBall(new Ball(3, BallColor.RED));
        highestBreak.addBall(new Ball(4, BallColor.PINK));
        Calendar cld = Calendar.getInstance();
        cld.set(2020,9,5);
        highestBreak.setDate(cld.getTime());
        noorTotal.setHighestBreak(highestBreak);

        noorTotal.setPoints(800);
        noorTotal.setShots(1000);
        noorTotal.setFouls(50);
        noorTotal.setPots(300);
        noorTotal.setGamesPlayed(40);
        noorTotal.setWins(20);

        cld = Calendar.getInstance();
        cld.set(2020,10,3);
        noorTotal.setHighestGamePointsDate(cld.getTime());
        noorTotal.setHighestGamePoints(80);
        noorTotal.setSumBreaksPlayed(100);


        PlayerInGame noor = new PlayerInGame(1,"Noor", noorTotal);
        noor.setPoints(40);
        noor.setShots(60);
        noor.setFouls(2);
        noor.setPots(30);
        ArrayList<Break> breaks = new ArrayList<Break>();
        Break break1 = new Break();
        break1.addBall(new Ball(1, BallColor.RED));
        breaks.add(break1);

        Break break2 = new Break();
        break2.addBall(new Ball(1, BallColor.RED));
        break2.addBall(new Ball(2, BallColor.BLACK));
        break2.addBall(new Ball(3, BallColor.RED));
        breaks.add(break2);

        Break break3 = new Break();
        break3.addBall(new Ball(1, BallColor.RED));
        breaks.add(break3);

        noor.setBreaks(breaks);
        noor.setHighestBreak(break2);


        PlayerTotal rashedTotal = new PlayerTotal(2,"Rashed", GameType.snooker);

        Break RhighestBreak = new Break();
        RhighestBreak.addBall(new Ball(1, BallColor.RED));
        RhighestBreak.addBall(new Ball(2, BallColor.BLUE));
        RhighestBreak.addBall(new Ball(3, BallColor.RED));
        RhighestBreak.addBall(new Ball(4, BallColor.PINK));
        RhighestBreak.addBall(new Ball(5, BallColor.RED));
        Calendar rcld = Calendar.getInstance();
        rcld.set(2020,5,2);
        highestBreak.setDate(rcld.getTime());
        rashedTotal.setHighestBreak(RhighestBreak);

        rashedTotal.setPoints(700);
        rashedTotal.setShots(1200);
        rashedTotal.setFouls(70);
        rashedTotal.setPots(330);
        rashedTotal.setGamesPlayed(50);
        rashedTotal.setWins(24);

        rcld = Calendar.getInstance();
        rcld.set(2020,7,20);
        rashedTotal.setHighestGamePointsDate(rcld.getTime());
        rashedTotal.setHighestGamePoints(80);
        rashedTotal.setSumBreaksPlayed(100);


        PlayerInGame rashed = new PlayerInGame(4,"Rashed", rashedTotal);
        rashed.setPoints(20);
        rashed.setShots(60);
        rashed.setFouls(7);
        rashed.setPots(20);
        ArrayList<Break> rbreaks = new ArrayList<Break>();
        Break rbreak1 = new Break();
        rbreak1.addBall(new Ball(1, BallColor.RED));
        rbreaks.add(rbreak1);

        Break rbreak2 = new Break();
        rbreak2.addBall(new Ball(1, BallColor.RED));
        rbreak2.addBall(new Ball(2, BallColor.BROWN));
        rbreak2.addBall(new Ball(3, BallColor.RED));
        rbreaks.add(rbreak2);

        Break rbreak3 = new Break();
        rbreak3.addBall(new Ball(1, BallColor.RED));
        rbreaks.add(rbreak3);

        Break rbreak4 = new Break();
        rbreak4.addBall(new Ball(1, BallColor.RED));
        rbreaks.add(rbreak4);

        rashed.setBreaks(breaks);
        rashed.setHighestBreak(break2);


        //Creating a game object
        ArrayList<PlayerInGame> players = new ArrayList<PlayerInGame>();
        players.add(noor);
        players.add(rashed);
        Game game = new Game(1,GameType.snooker,players);

        Calendar cldstart = Calendar.getInstance();
        cldstart.set(2020,5,2,22,10);
        Calendar cldend = Calendar.getInstance();
        cldend.set(2020,5,2,23,00);
        game.setEndTime(cldend.getTime());
        game.setWinner(noor);


        ArrayList<Game> games = new ArrayList<Game>();
        games.add(game);

        ArrayList<PlayerTotal> allPlayers = new ArrayList<PlayerTotal>();
        allPlayers.add(noorTotal);
        allPlayers.add(rashedTotal);

        preferences = getSharedPreferences(String.valueOf(R.string.DatabaseLists),MODE_PRIVATE);
        editor = preferences.edit();

        Gson gson = new Gson();
        String gameListStr = gson.toJson(games);
        String playerListStr = gson.toJson(allPlayers);
        editor.putString("ListOfGames", gameListStr);
        editor.putString("ListOfPlayers", playerListStr);
        editor.apply();
    }
}
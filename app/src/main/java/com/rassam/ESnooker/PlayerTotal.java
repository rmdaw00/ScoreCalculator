package com.rassam.ESnooker;

import com.rassam.BilliardEntities.Break;
import com.rassam.BilliardEntities.GameType;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PlayerTotal extends Player {
    //private static int idCount = 0;
    private GameType gameType;
    private int gamesPlayed, highestGamePoints, sumBreaksPlayed, wins;
    private Date highestGamePointsDate;

    /* NOTE //////////////////////
    *   highestBreak and date saved inside parent (Player)
    *   Same for shots, misses, fouls, pots, points,
    *   breaks Arraylist isn't used here
    * *///////////////////////////


    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    //NOT used directly use constructor below
    private PlayerTotal(int id, String name) {
        super(name);
        super.setId(id);
        gamesPlayed = highestGamePoints = sumBreaksPlayed = wins = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,01,01);
        highestGamePointsDate = new Date();
    }

    public PlayerTotal(int id, String name, GameType gameType) {
        this(id, name);
        setGameType(gameType);
    }

    //this method and above called when the game is finished
    @Override
    public void finish() {
        gamesPlayed++;
    }

    //this method and above called when the game is finished
    public void update(int gamePoints, int breaksPlayed, int ballsPotted, Break highestBreak){
        sumBreaksPlayed += breaksPlayed;
        setPots(getPots()+ ballsPotted);
        if (gamePoints > highestGamePoints) {
            highestGamePoints = gamePoints;
            highestGamePointsDate = new Date();
        }

        setPots(getPots()+ballsPotted);

        setSumBreaksPlayed(getSumBreaksPlayed()+breaksPlayed);

        if(highestBreak.getPoints() > this.getHighestBreak().getPoints()) {
            this.setHighestBreak(highestBreak);
        }
    }

    public String getWinRate() {
        double winRate = (double)wins/gamesPlayed*100;
        return String.format(Locale.CANADA,"%.2f",winRate)+"%";
    }

    @Override
    public String getBreakAvgBalls() {
        double breakAvg = (double)getPots()/sumBreaksPlayed;
        return String.format(Locale.CANADA,"%.1f",breakAvg);
    }

    @Override
    public String getBreakAvgPoints() {
            double breakPointAvg = (double)getPoints()/sumBreaksPlayed;
            return String.format(Locale.CANADA,"%.1f",breakPointAvg);
    }

    //SETTERS AND GETTERS
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getHighestGamePoints() {
        return highestGamePoints;
    }

    public void setHighestGamePoints(int highestGamePoints) {
        this.highestGamePoints = highestGamePoints;
    }

    public Date getHighestGamePointsDate() {
        return highestGamePointsDate;
    }

    public void setHighestGamePointsDate(Date highestGamePointsDate) {
        this.highestGamePointsDate = highestGamePointsDate;
    }


    public int getSumBreaksPlayed() {
        return sumBreaksPlayed;
    }

    public void setSumBreaksPlayed(int sumBreaksPlayed) {
        this.sumBreaksPlayed = sumBreaksPlayed;
    }

    public GameType getGameType() {
        return gameType;
    }




}

package com.rassam.ESnooker;

import com.rassam.BilliardEntities.Break;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Player {

    private String name;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private int shots, fouls, pots, points;
    private Break highestBreak;
    private List<Break> breaks;

    public Player(String name) {
        this.name = name;
        shots = fouls = pots = points = 0;
        breaks =  new ArrayList<Break>();
    }


    public String getAccuracy() {
        double accuracy = (double)(pots)/shots*100;
        return String.format(Locale.CANADA,"%.2f",accuracy) + "%";
    }

    public String getFoulPercentage() {
        double foulPer = (double)(fouls)/shots*100;
        return String.format(Locale.CANADA,"%.2f",foulPer) + "%";
    }

    public String getBreakAvgPoints() {
        int sum = 0;
        for (Break theBreak: breaks) {
            sum+=theBreak.getPoints();
        }
        double BreakAvg = (double)sum/breaks.size();
        return String.format(Locale.CANADA,"%.1f",BreakAvg);
    }

    public String getBreakAvgBalls() {
        int sum = 0;
        for (Break theBreak: breaks) {
            sum+=theBreak.getBallCount();
        }
        double BreakAvg = (double)sum/breaks.size();
        return String.format(Locale.CANADA,"%.1f",BreakAvg);
    }

    public abstract void finish();


    //Getters and setters


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Break> getBreaks() {

        return breaks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreaks(List<Break> breaks) {
        this.breaks = breaks;
    }


    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getPots() {
        return pots;
    }

    public void setPots(int pots) {
        this.pots = pots;
    }

    public Break getHighestBreak() {
        return highestBreak;
    }

    public void setHighestBreak(Break highestBreak) {
        this.highestBreak = highestBreak;
    }


}

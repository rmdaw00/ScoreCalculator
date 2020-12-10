package com.rassam.BilliardEntities;

import com.rassam.ESnooker.Player;
import com.rassam.ESnooker.PlayerInGame;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Game implements Serializable {
    //private static int countID = 0;
    private GameType gametype;
    private Date startTime, endTime;
    private List<PlayerInGame> players;
    private BallsLeft ballsLeft;
    private int gameID;
    private int pointDifference;

    public GameType getGametype() {
        return gametype;
    }

    public void setGametype(GameType gametype) {
        this.gametype = gametype;
    }

    public List<PlayerInGame> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInGame> players) {
        this.players = players;
    }

    private PlayerInGame winner;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BallsLeft getBallsLeft() {
        return ballsLeft;
    }

    public void setBallsLeft(BallsLeft ballsLeft) {
        this.ballsLeft = ballsLeft;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public PlayerInGame getWinner() {
        return winner;
    }

    public void setWinner(PlayerInGame winner) {
        this.winner = winner;
    }

    public Game(int id, GameType gametype, List<PlayerInGame> players) {
        gameID = id;
        this.gametype = gametype;
        this.players = players;
        startTime = Calendar.getInstance().getTime();
        ballsLeft = new BallsLeft(gametype);

    }

    public void sortPlayers() {
        Collections.sort(players, new Comparator<PlayerInGame>() {
            public int compare(PlayerInGame p1, PlayerInGame p2) {
                return p1.getTotalPoints()-p2.getTotalPoints();
            }}
        );
    }

    //containts finish sequence
    public boolean isFinished() {
        PlayerInGame highest, secondHighest;
        if (winner != null) return true;

        sortPlayers();

        if (getPlayers().size() > 1) {
            highest = getPlayers().get(getPlayers().size()-1);
            secondHighest = getPlayers().get(getPlayers().size()-2);
            pointDifference = highest.getTotalPoints()-secondHighest.getTotalPoints();
            if (ballsLeft.getMaxPointsLeft() < pointDifference)
            {   winner = highest;
                finishGame();
                return true;}


        }
        return false;
    }

    public void forceFinish() {
        sortPlayers();
        if (pointDifference != 0) {
            winner =  getPlayers().get(getPlayers().size()-1);
        }
        finishGame();
    }

    private void finishGame() {
        for (PlayerInGame player:players) {
            player.finish();
            endTime = Calendar.getInstance().getTime();
            winner.getPlayerAssociated().setWins(winner.getPlayerAssociated().getWins()+1);
        }
    }
}

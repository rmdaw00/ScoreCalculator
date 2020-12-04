package com.rassam.BilliardEntities;

import com.rassam.ESnooker.Player;
import com.rassam.ESnooker.PlayerInGame;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Game {
    //private static int countID = 0;
    private GameType gametype;
    private Date startTime, endTime;
    private List<PlayerInGame> players;
    private BallsLeft ballsLeft;
    private int gameID;

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

    //containts finish sequence
    public boolean isFinished() {
        PlayerInGame highest;

        Collections.sort(players, new Comparator<PlayerInGame>() {
            public int compare(PlayerInGame p1, PlayerInGame p2) {
                    return p1.getPoints()-p2.getPoints();
                }}
            );

        return true;
    }
}

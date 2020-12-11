package com.rassam.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rassam.BilliardEntities.Break;
import com.rassam.BilliardEntities.Game;
import com.rassam.BilliardEntities.GameType;
import com.rassam.ESnooker.Ball;
import com.rassam.ESnooker.BallColor;
import com.rassam.ESnooker.PlayerInGame;
import com.rassam.ESnooker.PlayerTotal;
import com.rassam.main.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * statistics data
 */
public class Data {

    //total game
    private int totalGames;

    //total wins
    private int totalWins;

    //total losses
    private int totalLosses;

    private static SharedPreferences stt_preferences;
    private static SharedPreferences.Editor stt_editor;
    private static ArrayList<PlayerTotal> stt_allPlayers;
    private static ArrayList<Game> stt_allGames;


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public String sampleOutput = "";

    private ArrayList<Game> allGames;
    private ArrayList<PlayerTotal> allPlayers;

    //constructor
    public Data(Context context){
        totalGames = 0;
        totalWins = 0;
        totalLosses = 0;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public void loadSample(Context context) {

        preferences = context.getSharedPreferences(String.valueOf(R.string.DatabaseLists), MODE_PRIVATE);

        Gson gson = new Gson();

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

        totalGames = allGames.size();

        for (Game game:allGames) {
            if (game.getWinner() == null) continue; //TEMP bugfix

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
    }

    public String getHistory(String playerName) {
        boolean found = false;

        String result = "";
        result += "Player Name: " + playerName +"\n\n";

            for (PlayerTotal player: allPlayers) {
                if (player.getName().equalsIgnoreCase(playerName)){
                    result += "Wins: " + player.getWins() + "\n";
                    result += "Games Played: " + player.getGamesPlayed() + "\n";
                    result += "Win Rate: " + player.getWinRate() + "\n\n";

                    result += "Total Points: " + player.getPoints() + "\n";
                    result += player.getPots() + " Total balls potted with accuracy of "+ player.getAccuracy()+"\n";
                    result += player.getFouls() + " Total Fouls committed, " + player.getFoulPercentage() +"\n\n";

                    result += "Average Balls per Break: " + player.getBreakAvgBalls() + "\n";
                    result += "Average Points per Break: " + player.getBreakAvgPoints() + "\n\n";

                    result += "Highest Break Points: " + player.getHighestBreak().getPoints() + "\n";
                    result += "Highest Break Ball Count: " + player.getHighestBreak().getBallCount() + "\n";
                    result += "Highest Break Date: " + player.getHighestBreak().getDate().toString() + "\n\n";

                    result += "Highest Game Points: " + player.getHighestGamePoints() + "\n";
                    result += "Highest Game Date: " + player.getHighestGamePointsDate().toString() + "\n\n";

                    found = true;
                    break;
                }
            }

        if (!found){
            result = "Player not found!";
        }
        return result;
    }

    public static void saveData(Context context) {
        stt_preferences = context.getSharedPreferences(String.valueOf(R.string.DatabaseLists),MODE_PRIVATE);
        stt_editor = stt_preferences.edit();

        Gson gson = new Gson();
        String gameListStr = gson.toJson(stt_allGames);
        String playerListStr = gson.toJson(stt_allPlayers);
        stt_editor.putString("ListOfGames", gameListStr);
        stt_editor.putString("ListOfPlayers", playerListStr);
        stt_editor.apply();
    }

    public static void loadData(Context context) {
        stt_preferences = context.getSharedPreferences(String.valueOf(R.string.DatabaseLists),MODE_PRIVATE);
        Gson gson = new Gson();


        String playerListStr = stt_preferences.getString("ListOfPlayers", "");
        if(playerListStr.length()>0) {
            Type playerType = new TypeToken<ArrayList<PlayerTotal>>() {}.getType();
            stt_allPlayers = gson.fromJson(playerListStr,playerType);
        } else {
            stt_allPlayers = new ArrayList<PlayerTotal>();
        }

        String gameListStr = stt_preferences.getString("ListOfGames", "");
        if(gameListStr.length()>0) {
            Type gameType = new TypeToken<ArrayList<Game>>() {}.getType();
            stt_allGames = gson.fromJson(gameListStr,gameType);
        } else {
            stt_allGames = new ArrayList<Game>();
        }


    }
    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static PlayerTotal findPlayerByName(String name, GameType gametype) {
        for (PlayerTotal player:stt_allPlayers) {
            if (player.getName().equalsIgnoreCase(name) && player.getGameType() == gametype){
                return player;
            }
        }
        return null;
    }

    public static void updatePlayerTotals(PlayerTotal newPlayerTotal) {
        int index = stt_allPlayers.indexOf(findPlayerByID(newPlayerTotal.getId()));
        stt_allPlayers.set(index,newPlayerTotal);
    }


    public static void updateGame(Game game) {
        int index = stt_allGames.indexOf(findGameByID(game.getGameID()));
        stt_allGames.set(index,game);
    }

    public static void deleteGame(Game game) {
        int index = stt_allGames.indexOf(findGameByID(game.getGameID()));
        stt_allGames.remove(index);
    }


    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static PlayerTotal findPlayerByID(int id) {
        for (PlayerTotal player:stt_allPlayers) {
            if (player.getId() == id){
                return player;
            }
        }
        return null;
    }


    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static Game findGameByID(int id) {
        for (Game game:stt_allGames) {
            if (game.getGameID() == id){
                return game;
            }
        }
        return null;
    }

    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static int getPlayersCount() {
        if (stt_allPlayers == null)
                return 0;
        else
                return stt_allPlayers.size();
    }

    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static PlayerTotal addNewPlayerTotal(Context context, String name, GameType gametype) {
        if (stt_allPlayers == null) loadData(context);

        PlayerTotal newPlayer = new PlayerTotal(stt_allPlayers.size()+1,name, gametype);
        stt_allPlayers.add(newPlayer);
        saveData(context);
        return newPlayer;
    }

    /*
     * VERY Important note:
     * before working on any Data.Player functions you have to loadData
     *
     * */
    public static Game addNewGame(Context context, GameType gametype) {
        if (stt_allGames == null) loadData(context);

        List<PlayerInGame> players = new ArrayList<PlayerInGame>();
        Game newGame = new Game(stt_allGames.size()+1,gametype,players);
        stt_allGames.add(newGame);
        saveData(context);
        return newGame;
    }

}

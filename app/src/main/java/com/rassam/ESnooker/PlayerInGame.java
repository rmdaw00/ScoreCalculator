package com.rassam.ESnooker;

import com.rassam.BilliardEntities.Break;

import java.io.Serializable;

public class PlayerInGame extends Player {
    public Break getCurrentBreak() {
        return currentBreak;
    }

    public void setCurrentBreak(Break currentBreak) {
        this.currentBreak = currentBreak;
    }

    //private static int idCount = 0;
    private Break currentBreak;

    private PlayerInGame(String name) {
        super(name);
    }

    // Note:
    // this is the players profile that will be updated at the end
    // all time stats can be accessed from player.PlayerAssociated
    // e.g. player.PlayerAssociated.getAvgBreakPoints()
    private PlayerTotal playerAssociated;



    public PlayerInGame(int id, String name, PlayerTotal playerAssociated) {
        super(name);
        super.setId(id);
        this.playerAssociated = playerAssociated;
        currentBreak = new Break();
        currentBreak.setId(1);
        setHighestBreak(new Break());
    }

    public void pot(Ball ball) {
        currentBreak.addBall(ball);

        setPots(getPots()+1);
        setShots(getShots()+1);
    }

    public void foul(int penalty) {
        setFouls(getFouls()+1);
        setShots(getShots()+1);

        // Apply Foul Point Penalty
        setPoints(getPoints()-penalty);
        nextTurn(true);
    }

    public void miss() {
        setShots(getShots()+1);
        nextTurn(true);
    }

    public int getTotalPoints() {
        return getPoints()+currentBreak.getPoints();
    }

    public void nextTurn(Boolean IncrementBreaks) {
        // Update highest break
        if (currentBreak.getPoints() > getHighestBreak().getPoints()) {
            setHighestBreak(currentBreak);
        }

        // Add Break Points
        this.setPoints(this.getPoints()+currentBreak.getPoints());

        // Move to next Break
        this.getBreaks().add(currentBreak);
        if (currentBreak.getBallCount() !=0 || IncrementBreaks) currentBreak = new Break();
        currentBreak.setId(getBreaks().size()+1);
    }

    //Game is finished, this method is called by Game Class once Score Difference is higher than game.ballsLeft.getMaxPointsLeft();
    @Override
    public void finish() {
        //Winner Game Attribute to be updated & reflect on Associated Player
        nextTurn(true);
        if (currentBreak.getPoints() > getHighestBreak().getPoints()) {
            setHighestBreak(currentBreak);
        }

        playerAssociated.update(this.getPoints(), this.getBreaks().size(), this.getPots(), this.getHighestBreak());
        playerAssociated.finish();
    }


    public PlayerTotal getPlayerAssociated() {
        return playerAssociated;
    }

    public void setPlayerAssociated(PlayerTotal playerAssociated) {
        this.playerAssociated = playerAssociated;
    }
}

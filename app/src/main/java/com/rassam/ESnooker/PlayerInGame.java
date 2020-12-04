package com.rassam.ESnooker;

import com.rassam.BilliardEntities.Break;

public class PlayerInGame extends Player {
    //private static int idCount = 0;
    private Break currentBreak;

    public PlayerInGame(String name) {
        super(name);
    }

    // Note:
    // this is the players profile that will be updated at the end
    // all time stats can be accessed from player.PlayerAssociated
    // e.g. player.PlayerAssociated.getAvgBreakPoints()
    public PlayerTotal PlayerAssociated;

    public PlayerInGame(int id, String name, PlayerTotal playerAssociated) {
        super(name);
        super.setId(id);
        PlayerAssociated = playerAssociated;
        currentBreak = new Break();
    }

    public void pot(Ball ball) {
        currentBreak.addBall(ball);
        setPots(getPots()+1);
        setShots(getShots()+1);
        nextTurn();
    }

    public void foul(int penalty) {
        setFouls(getFouls()+1);
        setShots(getShots()+1);

        // Apply Foul Point Penalty
        setPoints(getPoints()-penalty);
        nextTurn();
    }

    public void miss() {
        setShots(getShots()+1);
        nextTurn();
    }

    private void nextTurn() {
        // Update highest break
        if (currentBreak.getPoints() > getHighestBreak().getPoints()) {
            setHighestBreak(currentBreak);
        }

        // Add Break Points
        this.setPoints(this.getPoints()+currentBreak.getPoints());

        // Move to next Break
        this.getBreaks().add(currentBreak);
        currentBreak = new Break();
    }

    //Game is finished, this method is called by Game Class once Score Difference is higher than game.ballsLeft.getMaxPointsLeft();
    @Override
    public void finish() {
        //Winner Game Attribute to be updated & reflect on Associated Player

        if (currentBreak.getPoints() > getHighestBreak().getPoints()) {
            setHighestBreak(currentBreak);
        }

        PlayerAssociated.update(this.getPoints(), this.getBreaks().size(), this.getPots(), this.getHighestBreak());
        PlayerAssociated.finish();
    }

}

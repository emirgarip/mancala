package com.bol.mancala.business.observers;

import com.bol.mancala.business.strategies.OperationGetStones;
import com.jcabi.aspects.Loggable;

import java.util.HashMap;

/**
 * This class is using for observation the game finish.
 * @author emir
 */
public class TheGameFinishObserver extends Observer{

    public TheGameFinishObserver(OperationGetStones subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    /**
     * This is a check method, from observer pattern.
     * Check the game finish after each move.
     * @param stonesOnBoard
     * @param turn
     * @param lastPitKey
     * @return if return 0, game is finish, else which player has to move.
     */
    @Override
    @Loggable
    public Integer check(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long lastPitKey) {
        boolean isP1Finish = true;
        boolean isP2Finish = true;
        for (Long i = 10L ; i < 16L ; i++) {
            if (stonesOnBoard.get(i) > 0) {
                isP1Finish = false;
            }
        }
        for (Long i=20L ; i < 26L ; i++) {
            if (stonesOnBoard.get(i) > 0) {
                isP2Finish = false;
            }
        }

        return isP1Finish || isP2Finish ? 0 : turn;
    }
}

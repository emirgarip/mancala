package com.bol.mancala.business.observers;

import com.bol.mancala.business.strategies.OperationGetStones;
import com.jcabi.aspects.Loggable;

import java.util.HashMap;

/**
 * This class is using for observation the last move.
 * @author emir
 */
public class TheLastPitObserver extends Observer{

    public TheLastPitObserver(OperationGetStones subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    /**
     * This is a check method, from observer pattern.
     * Check the last move and it decides the next move.
     * In else if, the last pit was empty and your last stone is put over there,
     * calculate together with opposite pit, and these sum of stones is put to the big pit.
     * @param stonesOnBoard
     * @param turn
     * @param lastPitKey
     * @return if the last pit is big pit, last player will play again.
     * Else next player.
     */
    @Override
    @Loggable
    public Integer check(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long lastPitKey) {
        if (lastPitKey == 16L || lastPitKey == 26L) {
            return turn;
        } else if (stonesOnBoard.get(lastPitKey) == 1) {
            Long oppositePitKey = 35L - lastPitKey;
            Integer totalStonesFromOppositePits = 1 + stonesOnBoard.get(oppositePitKey);
            stonesOnBoard.put(turn == 1 ? 16L : 26L, stonesOnBoard.get(turn == 1 ? 16L : 26L) + totalStonesFromOppositePits);
            stonesOnBoard.put(lastPitKey, 0);
            stonesOnBoard.put(oppositePitKey, 0);
        }
        return turn == 1 ? 2 : 1;
    }
}

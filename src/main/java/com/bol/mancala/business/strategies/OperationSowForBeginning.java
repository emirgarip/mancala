package com.bol.mancala.business.strategies;

import com.jcabi.aspects.Loggable;

import java.util.HashMap;

/**
 * This class is using for initialization of board.
 * This is an operation class from Strategy.
 * @author emir
 */
public class OperationSowForBeginning implements Strategy{

    /**
     * This method is helping for initialization stones of board.
     * @param stonesOnBoard
     * @param turn
     * @param key
     * @return turn, who is selected randomly.
     */
    @Override
    @Loggable
    public Integer doOperation(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key) {
        for (Long i = 10L ; i < 16L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        for (Long i = 20L ; i < 26L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        stonesOnBoard.put(16L, 0);
        stonesOnBoard.put(26L, 0);

        turn = (Math.random() <= 0.5) ? 1 : 2;
        return turn;
    }
}

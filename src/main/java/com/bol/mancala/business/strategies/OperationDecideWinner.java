package com.bol.mancala.business.strategies;

import com.jcabi.aspects.Loggable;

import java.util.HashMap;

/**
 * This class is using for deciding the winner.
 * This is an operation class from Strategy.
 * @author emir
 */
public class OperationDecideWinner implements Strategy{

    /**
     * This method is using for deciding the winner.
     * @param stonesOnBoard
     * @param turn
     * @param key
     * @return
     */
    @Override
    @Loggable
    public Integer doOperation(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key) {
        stonesOnBoard.put(16L, stonesOnBoard.get(16L) + sumOfPlayerStones(stonesOnBoard, 1));
        stonesOnBoard.put(26L, stonesOnBoard.get(26L) + sumOfPlayerStones(stonesOnBoard, 2));
        return stonesOnBoard.get(16L).compareTo(stonesOnBoard.get(26L));
    }

    /**
     * This method is using for sum of stones for each player.
     * @param stonesOnBoard
     * @param player
     * @return
     */
    @Loggable
    private Integer sumOfPlayerStones(HashMap<Long, Integer> stonesOnBoard, Integer player) {
        Integer sum = 0;
        if (player == 1) {
            for (Long i = 10L ; i < 16L ; i++) {
                sum += stonesOnBoard.get(i);
            }
        } else {
            for (Long i = 20L ; i < 26L ; i++) {
                sum += stonesOnBoard.get(i);
            }
        }
        return sum;
    }


}

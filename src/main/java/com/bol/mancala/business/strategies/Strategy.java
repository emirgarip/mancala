package com.bol.mancala.business.strategies;

import java.util.HashMap;

/**
 * This is main interface for Strategy pattern.
 * @author emir
 */
public interface Strategy {
    public Integer doOperation(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key);
}

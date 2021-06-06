package com.bol.mancala.business.observers;

import com.bol.mancala.business.strategies.OperationGetStones;

import java.util.HashMap;

/**
 * This is an abstract class for using Observer pattern.
 * @author emir
 */
public abstract class Observer {
    protected OperationGetStones subject;
    public abstract Integer check(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long lastPitKey);
}

package com.bol.mancala.business.strategies;

import com.bol.mancala.business.observers.Observer;
import com.jcabi.aspects.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is using for each selection pit.
 * This is an operation class from Strategy.
 * This is the subject class for observer pattern.
 * @author emir
 */
public class OperationGetStones implements Strategy{

    private List<Observer> observers = new ArrayList<Observer>();

    private static final Logger logger = LoggerFactory.getLogger(OperationGetStones.class);

    /**
     * Attach new observer from this method.
     * It is called from each observer.
     * @param observer
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * This is the main operation for strategy.
     * Observers can observe the last position and the game finish from this method.
     * @param stonesOnBoard
     * @param turn
     * @param key
     * @return which player is next?
     */
    @Override
    @Loggable
    public Integer doOperation(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key) {
        Long lastPit = 0L;
        lastPit = sowStones(stonesOnBoard, turn, key);
        if (observers.size() > 0) {
            for (Observer observer : observers) {
                turn = observer.check(stonesOnBoard, turn, lastPit);
            }
        } else {
            throw new RuntimeException();
        }
        return turn;
    }

    /**
     * Sowing stones from the selected pit.
     * @param stonesOnBoard
     * @param turn
     * @param key
     * @return the last position after sowing.
     */
    @Loggable
    private Long sowStones(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key) {
        List<Long> pitsList = getPitsList(stonesOnBoard.get(key), key, turn);
        for (Long pit : pitsList) {
            stonesOnBoard.put(pit, stonesOnBoard.get(pit)+1);
        }
        stonesOnBoard.put(key, 0);
        return pitsList.get(pitsList.size()-1);
    }

    /**
     * Data structure is hashmap, so we have to get pit list after each move.
     * @param stones
     * @param key
     * @param turn
     * @return pit list
     */
    @Loggable
    private List<Long> getPitsList(Integer stones, Long key, Integer turn) {
        List<Long> pitsList = new ArrayList<>();
        Long pitKey = key + 1;
        for (int i = 0; i < stones; i++) {
            if (turn == 1 && pitKey == 26L) {
                pitKey = 10L;
            } else if (turn == 2 && pitKey == 16L) {
                pitKey = 20L;
            }
            pitsList.add(pitKey);
            if (pitKey == 16L) {
                pitKey = 20L;
            } else if (pitKey == 26L) {
                pitKey = 10L;
            } else {
                pitKey++;
            }
        }
        return pitsList;
    }
}

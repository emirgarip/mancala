package com.bol.mancala.business.context;

import com.bol.mancala.business.strategies.Strategy;
import com.bol.mancala.enums.ContextType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * This is the Context class for using Strategy pattern.
 * @author emir
 */
public class Context implements Cloneable{
    private Strategy strategy;
    @Getter @Setter
    private ContextType contextType;

    /**
     * We have to decide which strategy using before call constructor of Context.
     * @param strategy
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * This is execution strategy method.
     * Each strategy get same parameters and after all operation we have to know which player is next.
     * @param stonesOnBoard
     * @param turn
     * @param key
     * @return which player is next
     */
    public Integer executeStrategy(HashMap<Long, Integer> stonesOnBoard, Integer turn, Long key) {
        return strategy.doOperation(stonesOnBoard, turn, key);
    }

    /**
     * This method is using for prototype pattern.
     * We don't want create a Context object for each strategic operation.
     * So, this method is called from ContextCache class.
     * @return
     */
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}

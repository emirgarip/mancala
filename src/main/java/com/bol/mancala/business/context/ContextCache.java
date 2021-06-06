package com.bol.mancala.business.context;

import com.bol.mancala.business.observers.TheGameFinishObserver;
import com.bol.mancala.business.observers.TheLastPitObserver;
import com.bol.mancala.business.strategies.OperationDecideWinner;
import com.bol.mancala.business.strategies.OperationGetStones;
import com.bol.mancala.business.strategies.OperationSowForBeginning;
import com.bol.mancala.enums.ContextType;

import java.util.Hashtable;

/**
 * This class for using prototype pattern.
 * @author emir
 */
public class ContextCache {

    private static Hashtable<ContextType, Context> contextMap  = new Hashtable<ContextType, Context>();

    /**
     * get Context from cache map.
     * @param contextType
     * @return
     */
    public static Context getContext(ContextType contextType) {
        Context cachedContext = contextMap.get(contextType);
        return (Context) cachedContext.clone();
    }


    /**
     * Loading all context, which are necessary for application.
     * It is called one time and cached to the map.
     */
    public static void loadCache() {
        Context initialContext = new Context(new OperationSowForBeginning());
        initialContext.setContextType(ContextType.initial);
        contextMap.put(initialContext.getContextType(), initialContext);

        OperationGetStones operationGetStones = new OperationGetStones();
        new TheLastPitObserver(operationGetStones);
        new TheGameFinishObserver(operationGetStones);
        Context gettingStonesContext = new Context(operationGetStones);
        gettingStonesContext.setContextType(ContextType.gettingStones);
        contextMap.put(gettingStonesContext.getContextType(), gettingStonesContext);

        Context decidingWinnerContext = new Context(new OperationDecideWinner());
        decidingWinnerContext.setContextType(ContextType.decidingWinner);
        contextMap.put(decidingWinnerContext.getContextType(), decidingWinnerContext);
    }

}

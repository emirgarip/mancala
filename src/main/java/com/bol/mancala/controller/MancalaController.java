package com.bol.mancala.controller;

import com.bol.mancala.business.context.Context;
import com.bol.mancala.business.context.ContextCache;
import com.bol.mancala.enums.ContextType;
import com.jcabi.aspects.Loggable;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;

/**
 * This is the controller for the view of mancala.xhtml.
 * @author emir
 */
@Controller
public class MancalaController implements Serializable {

    @Getter @Setter
    private HashMap<Long, Integer> stonesOnBoard = new HashMap<>();

    @Getter @Setter
    private Integer turn;

    @Getter @Setter
    private Integer result;

    @Getter
    private String errMsg;

    private static final Logger logger = LoggerFactory.getLogger(MancalaController.class);

    /**
     * This is the first initialize method for mancala board.
     */
    @PostConstruct
    @Loggable
    public void init() {
        ContextCache.loadCache();
        Context context = ContextCache.getContext(ContextType.initial);
        turn = context.executeStrategy(stonesOnBoard, turn, null);
    }

    /**
     *  Every pit selection call this method.
     *  A lot of validation is done in this method.
     * @param key :This is the map key, which shows us which pit is picked.
     */
    @Loggable
    public void getStonesFromThePit(Long key) {
        Context context;
        if (checkTheMove(key)) {
            context = ContextCache.getContext(ContextType.gettingStones);
            turn = context.executeStrategy(stonesOnBoard, turn, key);
            PrimeFaces.current().ajax().update("form:boardPanel");
        } else {
            errMsg = "Wrong move!";
            logErrorAndUpdateWarnDialog();
        }
        if(turn == 0) {
            context = ContextCache.getContext(ContextType.decidingWinner);
            result = context.executeStrategy(stonesOnBoard, turn, key);
            if(result != null) {
                PrimeFaces.current().ajax().update("dialogFrm:winnerDlg");
                PrimeFaces.current().executeScript("PF('winnerDlg').show();");
            } else {
                errMsg = "An error occured while deciding the winner!";
                logErrorAndUpdateWarnDialog();
            }
        }
    }

    /**
     * It is the validation method.
     * Besides validating the move, it also checks the status of the board.
     * @param key :This is the map key, which shows us which pit is picked.
     * @return validation result.
     */
    @Loggable
    private boolean checkTheMove(Long key) {
        if (turn != null && stonesOnBoard != null && stonesOnBoard.size() == 14) {
            if (checkStatusForTurnAndStonesOnBoard(turn, stonesOnBoard, key)) {
                return true;
            }
        } else {
            errMsg = "Stones couldn't be initialize correctly!";
            logErrorAndUpdateWarnDialog();
        }
        return false;
    }

    /**
     * Check if the turn and board is valid.
     * @param turn
     * @param stonesOnBoard :This map keep all stones on board.
     * @param key :This is the map key, which shows us which pit is picked.
     * @return validation result.
     */
    @Loggable
    private boolean checkStatusForTurnAndStonesOnBoard (Integer turn, HashMap<Long, Integer> stonesOnBoard, Long key) {
        if (((turn == 1 && key < 20) || (turn == 2 && key >= 20)) &&
                (stonesOnBoard.get(key) != null && stonesOnBoard.get(key) != 0)) {
            return true;
        }
        return false;
    }

    /**
     * After the game finish, players can play again with this method.
     * All board is getting initialize again.
     */
    @Loggable
    public void playAgain () {
        init();
        PrimeFaces.current().ajax().update("form:boardPanel");
    }

    /**
     * Written to avoid code duplication.
     * If validation result is false, it triggers to dialog in view and give us an error message.
     */
    @Loggable
    private void logErrorAndUpdateWarnDialog () {
        logger.error(errMsg);
        PrimeFaces.current().ajax().update("dialogFrm:warnDlg");
        PrimeFaces.current().executeScript("PF('warnDlg').show();");
    }
}

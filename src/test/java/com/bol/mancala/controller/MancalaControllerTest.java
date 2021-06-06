package com.bol.mancala.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * MancalaController test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class MancalaControllerTest {

    @InjectMocks
    MancalaController mancalaController;

    /**
     * just call initialize method from controller.
     * No chance of error.
     */
    @Test
    public void init_ShouldNotThrowException () {
        Throwable thrown = catchThrowable(() -> {
            mancalaController.init();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    /**
     * Call getStonesFromThePit method from controller.
     * It has to return exception in logErrorAndUpdateWarnDialog method.
     * That method is only called while there is an validation error.
     * And in that method, Primefaces object is null because JSF lifecycle is not started yet,
     * so throw an exception and we catch it.
     */
    @Test
    public void getStonesFromThePit_ShouldThrowException_WhenTurnIsNull () {
        Throwable thrown = catchThrowable(() -> {
            mancalaController.getStonesFromThePit(15L);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    /**
     * Call getStonesFromThePit method from controller.
     * It has to return exception in logErrorAndUpdateWarnDialog method.
     * That method is only called while there is an validation error.
     * And in that method, Primefaces object is null because JSF lifecycle is not started yet,
     * so throw an exception and we catch it.
     */
    @Test
    public void getStonesFromThePit_ShouldThrowException_WhenStonesOnBoardIsNull () {
        mancalaController.setTurn(1);
        Throwable thrown = catchThrowable(() -> {
            mancalaController.getStonesFromThePit(15L);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    /**
     * Call getStonesFromThePit method from controller.
     * It has to return exception in logErrorAndUpdateWarnDialog method.
     * That method is only called while there is an validation error.
     * And in that method, Primefaces object is null because JSF lifecycle is not started yet,
     * so throw an exception and we catch it.
     */
    @Test
    public void getStonesFromThePit_ShouldThrowException_WhenMoveIsWrong () {
        mancalaController.setTurn(1);
        mancalaController.setStonesOnBoard(setStonesOnBoard());
        Throwable thrown = catchThrowable(() -> {
            mancalaController.getStonesFromThePit(21L);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    /**
     * Setting board for test cases.
     * @return
     */
    public HashMap<Long, Integer> setStonesOnBoard() {
        HashMap<Long, Integer> stonesOnBoard = new HashMap<>();
        for (Long i = 10L ; i < 16L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        for (Long i = 20L ; i < 26L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        stonesOnBoard.put(16L, 0);
        stonesOnBoard.put(26L, 0);

        return stonesOnBoard;
    }
}

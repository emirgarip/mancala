package com.bol.mancala.business.strategies;

import com.bol.mancala.business.observers.TheGameFinishObserver;
import com.bol.mancala.business.observers.TheLastPitObserver;
import com.bol.mancala.business.strategies.OperationGetStones;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * OperationGetStones test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationGetStonesTest {

    @InjectMocks
    OperationGetStones operationGetStones;

    @Test
    public void doOperation_ShouldThrowException_WhenObserversIsNull () {
        Throwable thrown = catchThrowable(() -> {
            operationGetStones.doOperation(setStonesOnBoard(), 1, 10L);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void doOperation_ShouldNotThrowException () {
        operationGetStones.attach(new TheLastPitObserver(new OperationGetStones()));
        operationGetStones.attach(new TheGameFinishObserver(new OperationGetStones()));
        Throwable thrown = catchThrowable(() -> {
            operationGetStones.doOperation(setStonesOnBoard(), 1, 10L);
        });
        assertThat(thrown).doesNotThrowAnyException();
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

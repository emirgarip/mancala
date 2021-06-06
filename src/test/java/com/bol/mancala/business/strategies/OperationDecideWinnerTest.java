package com.bol.mancala.business.strategies;

import com.bol.mancala.business.strategies.OperationDecideWinner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OperationDecideWinner test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationDecideWinnerTest {

    @InjectMocks
    OperationDecideWinner operationDecideWinner;

    @Test
    public void doOperation_ResultIsDeuce () {
        Integer result = 0;
        result = operationDecideWinner.doOperation(setStonesOnBoard(result), 1, 10L);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void doOperation_ResultIsP1 () {
        Integer result = 1;
        result = operationDecideWinner.doOperation(setStonesOnBoard(result), 1, 10L);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void doOperation_ResultIsP2 () {
        Integer result = 2;
        result = operationDecideWinner.doOperation(setStonesOnBoard(result), 1, 10L);
        assertThat(result).isEqualTo(-1);
    }

    /**
     * Setting board for test cases.
     * @return
     */
    public HashMap<Long, Integer> setStonesOnBoard(Integer result) {
        HashMap<Long, Integer> stonesOnBoard = new HashMap<>();
        if (result == 0) {
            stonesOnBoard.put(16L, 36);
            stonesOnBoard.put(26L, 36);
        } else if (result == 1) {
            stonesOnBoard.put(16L, 37);
            stonesOnBoard.put(26L, 35);
        } else {
            stonesOnBoard.put(16L, 35);
            stonesOnBoard.put(26L, 37);
        }
        for (Long i = 10L ; i < 16L ; i++) {
            stonesOnBoard.put(i, 0);
        }
        for (Long i = 20L ; i < 26L ; i++) {
            stonesOnBoard.put(i, 0);
        }
        return stonesOnBoard;
    }
}

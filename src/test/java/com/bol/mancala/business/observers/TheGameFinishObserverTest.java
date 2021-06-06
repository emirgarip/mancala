package com.bol.mancala.business.observers;

import com.bol.mancala.business.observers.TheGameFinishObserver;
import com.bol.mancala.business.strategies.OperationGetStones;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TheGameFinishObserver test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class TheGameFinishObserverTest {

    @InjectMocks
    TheGameFinishObserver theGameFinishObserver = new TheGameFinishObserver(new OperationGetStones());

    @Test
    public void check_TheGameNotFinish () {
        Integer turn = theGameFinishObserver.check(setStonesOnBoard(false), 1, null);
        assertThat(turn).isEqualTo(1);
    }

    @Test
    public void check_TheGameFinish () {
        Integer turn = theGameFinishObserver.check(setStonesOnBoard(true), 1, null);
        assertThat(turn).isEqualTo(0);
    }

    /**
     * Setting board for test cases.
     * @return
     */
    public HashMap<Long, Integer> setStonesOnBoard(boolean isFinish) {
        HashMap<Long, Integer> stonesOnBoard = new HashMap<>();
        if (isFinish) {
            for (Long i = 10L ; i < 16L ; i++) {
                stonesOnBoard.put(i, 0);
            }
        } else {
            for (Long i = 10L ; i < 16L ; i++) {
                stonesOnBoard.put(i, 6);
            }
        }
        for (Long i = 20L ; i < 26L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        return stonesOnBoard;
    }
}

package com.bol.mancala.business.observers;

import com.bol.mancala.business.observers.TheLastPitObserver;
import com.bol.mancala.business.strategies.OperationGetStones;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TheLastPitObserver test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class TheLastPitObserverTest {

    @InjectMocks
    TheLastPitObserver theLastPitObserver = new TheLastPitObserver(new OperationGetStones());

    private HashMap<Long, Integer> stonesOnBoard = new HashMap<>();

    @Before
    public void init() {
        stonesOnBoard = setStonesOnBoard();
    }

    /**
     * Testing for big pit cases
     */
    @Test
    public void check_Turn1Again () {
        Integer turn = theLastPitObserver.check(stonesOnBoard, 1, 16L);
        assertThat(turn).isEqualTo(1);
    }

    /**
     * Testing for big pit cases
     */
    @Test
    public void check_Turn2Again () {
        Integer turn = theLastPitObserver.check(stonesOnBoard, 2, 26L);
        assertThat(turn).isEqualTo(2);
    }

    /**
     * Testing for one stone in last pit case.
     * Check two different situation; is turn changed and sum of stones correction.
     */
    @Test
    public void check_stoneOfLastPitIsOne () {
        Integer turn = theLastPitObserver.check(stonesOnBoard, 1, 10L);
        assertThat(turn).isEqualTo(2);
        assertThat(stonesOnBoard.get(16L)).isEqualTo(8);
    }

    /**
     * Setting board for test cases.
     * @return
     */
    public HashMap<Long, Integer> setStonesOnBoard() {
        for (Long i = 10L ; i < 16L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        for (Long i = 20L ; i < 26L ; i++) {
            stonesOnBoard.put(i, 6);
        }
        stonesOnBoard.put(16L, 1);
        stonesOnBoard.put(26L, 0);
        stonesOnBoard.put(10L, 1);

        return stonesOnBoard;
    }
}

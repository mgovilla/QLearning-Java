package edu.cs3431.a4;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testBasic() {
    }

    @Test
    public void testGetNextState() {
        int[][] board = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Board b = new Board(board, 3, 3);
        GridWorldAgent agent = new GridWorldAgent(new Position(0, 0), 1.0);
        System.out.println(b.getNextState(GridWorldAction.LEFT, agent).getState());
    }

    @Test
    public void testQInputHashAndEq() {
        QLearning.QInput q = new QLearning.QInput(new Position(5, 3), GridWorldAction.LEFT);
        QLearning.QInput q2 = new QLearning.QInput(new Position(5, 3), GridWorldAction.LEFT);
        Assert.assertEquals(q.hashCode(), q2.hashCode());
        Assert.assertEquals(q, q2);
    }

    @Test
    public void testQInputMap() {
        Map<QLearning.QInput, Double> QTable = new HashMap<>();
        QLearning.QInput q = new QLearning.QInput(new Position(5, 3), GridWorldAction.LEFT);
        QLearning.QInput q2 = new QLearning.QInput(new Position(5, 3), GridWorldAction.LEFT);
        QTable.put(q, 0.05);
        Assert.assertTrue(QTable.containsKey(q2));
    }

    @Test
    public void testRun() {
        App.main(new String[]{"sample.txt", "10", "0.9", "-0.05"});
    }

    @Test
    public void deflectTo() {
        System.out.println(-1 % 4);
    }

    @Test
    public void getValue() {
        int[][] board = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Board b = new Board(board, 3, 3);
        Assert.assertEquals(6, b.getValue(new Position(2, 1)));
    }
}

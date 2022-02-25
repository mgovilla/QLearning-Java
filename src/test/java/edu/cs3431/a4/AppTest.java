package edu.cs3431.a4;


import org.junit.Test;

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
    public void testQInput() {

    }
}

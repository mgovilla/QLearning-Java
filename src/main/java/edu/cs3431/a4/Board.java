package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;

import java.util.Random;

/**
 * Grid World representation
 */
public class Board {
	int[][] board;
	int width, height;
	Random rand;

	public Board(int[][] values, int width, int height) {
		this.board = values;
		this.width = width;
		this.height = height;
		rand = new Random();
	}

	// for testing
	public Board(int[][] values, int seed) {
		this.board = values;
		rand = new Random(seed);
	}

	/**
	 * @return value of the board at given pos
	 */
	public int getValue(Position pos) { return 0; }

	/**
	 * generate random starting location in the board
	 * @return Position
	 */
	public Position getRandomPosition() {
		return new Position(rand.nextInt(width), rand.nextInt(height));
	}

	/**
	 * Get the next position
	 * @param desiredAction direction we intend to move
	 * @return new Agent after moving
	 */
	public GridWorldAgent getNextState(GridWorldAction desiredAction, GridWorldAgent currentAgent) {
		Action action = currentAgent.getActualAction(desiredAction);
		int dx = action.equals(GridWorldAction.LEFT) ? -1 : action.equals(GridWorldAction.RIGHT) ? 1 : 0;
		int dy = action.equals(GridWorldAction.DOWN) ? -1 : action.equals(GridWorldAction.UP) ? 1 : 0;

		Position newPos =  new Position(Math.max(0, Math.min(width, currentAgent.getState().x + dx)), Math.max(0, Math.min(width, currentAgent.getState().y + dy)));
		return new GridWorldAgent(newPos, currentAgent.chanceOfCorrectMove);
	}
}

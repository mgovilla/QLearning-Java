package edu.cs3431.a4;

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
	 * Get the value of the board position
	 * @param pos The position to get the value of
	 * @return Value of the board at given pos
	 */
	public int getValue(Position pos) {
		return board[pos.y][pos.x];
	}

	/**
	 * Generate random starting location in the board that is not a terminal
	 * @return Position
	 */
	public Position getRandomPosition() {
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		while (board[y][x] != 0) {
			x = rand.nextInt(width);
			y = rand.nextInt(height);
		}
		return new Position(x, y);
	}

	/**
	 * Get the next position
	 * @param desiredAction Direction we intend to move
	 * @param currentAgent The current agent
	 * @return New Agent after moving
	 */
	public GridWorldAgent getNextState(GridWorldAction desiredAction, GridWorldAgent currentAgent) {
		GridWorldAction action = currentAgent.getActualAction(desiredAction);
		int dx = action.equals(GridWorldAction.LEFT) ? -1 : action.equals(GridWorldAction.RIGHT) ? 1 : 0;
		int dy = action.equals(GridWorldAction.DOWN) ? -1 : action.equals(GridWorldAction.UP) ? 1 : 0;

		Position newPos =  new Position(Math.max(0, Math.min(width-1, currentAgent.getState().x + dx)), Math.max(0, Math.min(height-1, currentAgent.getState().y + dy)));
		return new GridWorldAgent(newPos, currentAgent.chanceOfCorrectMove);
	}
}

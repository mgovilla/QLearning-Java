package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Agent representation
 */
public class GridWorldAgent {
	Position state;
	double chanceOfCorrectMove;
	Random rand;
	private static final List<Action> ACTION_LIST = Arrays.asList(GridWorldAction.UP, GridWorldAction.RIGHT, GridWorldAction.DOWN, GridWorldAction.LEFT);

	public GridWorldAgent(Position state, double chanceOfCorrectMove) {
		this.state = state;
		this.chanceOfCorrectMove = chanceOfCorrectMove;
		rand = new Random();
	}

	public Position getState() {
		return state;
	}

	/**
	 * Get the action 90 degrees to the left or right
	 * @param a The current action
	 * @param where The direction to move in, either 1 or -1
	 * @return The new action to move
	 */
	private Action deflectTo(Action a, int where) {
		return ACTION_LIST.get((ACTION_LIST.indexOf(a) + where + 4) % ACTION_LIST.size());
	}

	/**
	 * Get the actual action that the agent ends up taking
	 * @param a The desired action
	 * @return The action to take
	 */
	public Action getActualAction(Action a) {
		double roll = rand.nextDouble();
		if (roll <= chanceOfCorrectMove) return a;
		if (roll <= (1-chanceOfCorrectMove) / 2 + chanceOfCorrectMove) return deflectTo(a, 1);

		return deflectTo(a, -1);
	}
}

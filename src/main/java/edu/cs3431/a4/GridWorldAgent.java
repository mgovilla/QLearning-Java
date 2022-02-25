package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;
import edu.cs3431.a4.generics.Agent;
import edu.cs3431.a4.generics.State;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GridWorldAgent implements Agent<Position> {
	Position state;
	double chanceOfCorrectMove;
	Random rand;
	private static final List<Action> ACTION_LIST = Arrays.asList(GridWorldAction.UP, GridWorldAction.RIGHT, GridWorldAction.DOWN, GridWorldAction.LEFT);

	public GridWorldAgent(Position state, double chanceOfCorrectMove) {
		this.state = state;
		this.chanceOfCorrectMove = chanceOfCorrectMove;
		rand = new Random();
	}

	@Override
	public Position getCurrentState() {
		return state;
	}


	private Action deflectTo(Action a, int where) {
		return ACTION_LIST.get((ACTION_LIST.indexOf(a) + where) % ACTION_LIST.size());
	}

	@Override
	public Action getActualAction(Action a) {
		double roll = rand.nextDouble();
		if (roll <= chanceOfCorrectMove) return a;
		if (roll <= (1-chanceOfCorrectMove) / 2 + chanceOfCorrectMove) return deflectTo(a, 1);

		return deflectTo(a, -1);
	}
}

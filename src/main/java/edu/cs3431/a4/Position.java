package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;
import edu.cs3431.a4.generics.State;

public class Position implements State {
	int x, y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Action[] getPossibleActions() {
		return new Action[]{GridWorldAction.UP, GridWorldAction.DOWN, GridWorldAction.LEFT, GridWorldAction.RIGHT};
	}

	@Override
	public String toString() {
		return "Position{x: " + x + ", y: " + y + "}";
	}
}

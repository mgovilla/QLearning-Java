package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;
import edu.cs3431.a4.generics.State;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {
	int x, y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public List<GridWorldAction> getPossibleActions() {
		return Arrays.asList(GridWorldAction.UP, GridWorldAction.DOWN, GridWorldAction.LEFT, GridWorldAction.RIGHT);
	}

	@Override
	public String toString() {
		return "Position{x: " + x + ", y: " + y + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position) {
			Position other = (Position) obj;
			return other.x == x && other.y == y;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

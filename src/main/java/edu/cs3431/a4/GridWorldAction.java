package edu.cs3431.a4;

import edu.cs3431.a4.generics.Action;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GridWorldAction implements Action {
	private enum Direction {
		UP, RIGHT, DOWN, LEFT
	}

	public static GridWorldAction UP = new GridWorldAction(Direction.UP);
	public static GridWorldAction RIGHT = new GridWorldAction(Direction.RIGHT);
	public static GridWorldAction DOWN = new GridWorldAction(Direction.DOWN);
	public static GridWorldAction LEFT = new GridWorldAction(Direction.LEFT);

	Direction dir;
	private GridWorldAction(Direction dir) {this.dir = dir;}

	@Override
	public int hashCode() {
		return Objects.hash(dir);
	}
}

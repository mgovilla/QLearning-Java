package edu.cs3431.a4;
import java.util.Objects;

/**
 * Action representation
 */
public class GridWorldAction {
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
	public boolean equals(Object obj) {
		if(obj instanceof GridWorldAction) {
			GridWorldAction other = (GridWorldAction) obj;
			return other.dir == dir;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dir);
	}
}

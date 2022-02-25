package edu.cs3431.a4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class QLearning {
	static class QInput {
		Position pos;
		GridWorldAction action;

		public QInput(Position pos, GridWorldAction action) {
			this.pos = pos;
			this.action = action;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof QInput) {
				QInput other = (QInput) obj;
				return other.pos.equals(pos) && other.action.equals(action);
			}

			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(pos, action); // relies on action
		}
	}

	Map<QInput, Double> QTable;
	Board board;
	double time; // in s
	double rewardPerStep;
	Random rand;
	double epsilon;

	public QLearning(Board board, double time, double rewardPerStep, double epsilon) {
		this.board = board;
		this.time = time;
		this.rewardPerStep = rewardPerStep;
		this.epsilon = epsilon;
		this.QTable = new HashMap<>();
		this.rand = new Random();
	}

	public GridWorldAction chooseNextMove(Position s, double eps) {
		GridWorldAction next = s.getPossibleActions().stream().reduce((a, b) -> QTable.getOrDefault(new QInput(s, a), 0.0) > QTable.getOrDefault(new QInput(s, b), 0.0) ? a : b).orElseThrow();
		return rand.nextDouble() >= eps ? next : s.getPossibleActions().get(rand.nextInt(s.getPossibleActions().size()));
	}

	public void train(double chanceOfCorrectMove, double alpha, double discount) {
		double start = System.currentTimeMillis() / 1000.0;
		while(System.currentTimeMillis() / 1000.0 < time + start) {
			// create a new agent
			GridWorldAgent a = new GridWorldAgent(board.getRandomPosition(), chanceOfCorrectMove);
			while (true) {
				GridWorldAction take = chooseNextMove(a.getState(), this.epsilon);
				GridWorldAgent s_prime = board.getNextState(take, a);
				int value = board.getValue(s_prime.getState());
				double r = value == 0 ? rewardPerStep : value;
				// update Q table here
				double oldValue = QTable.getOrDefault(new QInput(a.getState(), take), 0.0);
				GridWorldAction bestNewMove = chooseNextMove(s_prime.getState(), 0.0);
				// double check this equation
				QTable.put(new QInput(a.getState(), take), oldValue + alpha*(r + discount*QTable.getOrDefault(new QInput(s_prime.getState(), bestNewMove), 0.0) - oldValue));
				if (value != 0)
					break;
				a = s_prime;
			}
		}

		printPolicy();
	}

	public void printPolicy() {
		for (int i = 0; i < board.height; i++) {
			String thisLine = "";
			for (int j = 0; j < board.width; j++) {
				Position pos = new Position(j, i);
				String printOut = "-";
				if (board.getValue(pos) == 0.0){
					List<GridWorldAction> moves = pos.getPossibleActions();
					double max = Double.NEGATIVE_INFINITY;
					for (GridWorldAction move : moves) {
						if (QTable.getOrDefault(new QInput(pos, move), Double.NEGATIVE_INFINITY) > max) {
							max = QTable.getOrDefault(new QInput(pos, move), Double.NEGATIVE_INFINITY);
							if (move.equals(GridWorldAction.UP)) printOut = "v";
							else if (move.equals(GridWorldAction.RIGHT)) printOut = ">";
							else if (move.equals(GridWorldAction.LEFT)) printOut = "<";
							else printOut = "^";
						}
					}
				}
				else { printOut = Integer.toString(board.getValue(pos)); }
				thisLine += printOut + "\t";
			}
			System.out.println(thisLine);
		}
	}
}

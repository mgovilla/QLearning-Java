package edu.cs3431.a4.generics;

import java.util.List;

public interface State {
	List<Action> getPossibleActions();
}

package edu.cs3431.a4.generics;

public interface Agent<S extends State> {
	S getCurrentState();
	Action getActualAction(Action desiredAction);
}

package edu.cs3431.a4.generics;

public interface Environment<S extends State> {
	State getNextState(State s, Action a);
}

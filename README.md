# cs4341-a4
Mayank Govilla, Mago Sheehy, Molly Sunray

Group 30

To run the program, the first argument is the file name, the second argument is the time in seconds to learn, the third argument is the probability of moving in the desired direction, and the fourth argument is the reward for each action.

The file representing the map should be in the "boards" directory.

<br />

Pseudocode:

class Position {x: int , y: int}
Enum ACTION: {UP, DOWN, LEFT, RIGHT}

Main: 
- read input parameters
- 

GridWorld
- World : int[][] // mostly 0s with some non-zero values that represent the terminal states
- startingLocation:
- getValue(Position): int
- getRandomPos(): Position
- getNextState(action, previousAgent) : Agent

Agent
- Position : position
- chanceOf!Deflection: double // probability of deflection
- getActualDirection (based on probability)


Q Learning Algo
static class QInput: (Position, action)
- QTable: Map<QInput, double>
- chooseNextMove(): ACTION
- train(Board: GridWorld, time: int, rewardFunction: (s)->int)
- returnPolicy()

chooseNextMove(Position):
    - get the 4 values from the Q table that correspond to 
    - [Q(s, UP), Q(s, RIGHT), Q(s, DOWN), Q(s, LEFT)]
    - return best action

train:
    - loop while time
        - a = new Agent(randomPos)
        - while not terminal
            - move = chooseNextMove()
            - s_prime = a.getNextState(move)
            - val = getValue(s_prime)
            - r(s) = rewardFunction(val)
            - alpha = 0.1 or something to do with time
            - QTable[{a.position, move}] = QTable[{a.position, move}] + alpha(r(s) + discount*max(QTable[{s_prime.position, m}] for m in moves) - QTable[{a.position, move}])
            - if val != 0: break
            - a = s_prime

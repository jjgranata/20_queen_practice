package pack1;

import java.util.*;

public class anneal {
    private final static int N = 20;
    int nodesGenerated;
    private queenObj[] startState;
    private pos start;

    public anneal(queenObj[] s) {
        nodesGenerated = 0;
        start = new pos();
        startState = new queenObj[N];

        for (int i = 0; i < N; i++) {
            startState[i] = new queenObj(s[i].getBoardRows(), s[i].getBoardColumns());
        }
        start.setState(startState);
        start.Heuristic();
    }

    public void startState() {
        start = new pos();
        startState = new queenObj[N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            startState[i] = new queenObj(rand.nextInt(N), i);
        }
        start.setState(startState);
        start.Heuristic();
    }

    public pos Annealing(double tempInit, double steps) {
        pos currentPos = start;
        double temperature = tempInit;
        double val = steps;
        double prob;
        int delt;
        double det;

        pos nextPos = new pos();

        while (currentPos.getHeuristic() != 0 && temperature > 0) {
            nextPos = currentPos.getRandAdjacent(currentPos);
            nodesGenerated++;

            if (nextPos.getHeuristic() == 0)
                return nextPos;

            delt = currentPos.getHeuristic() - nextPos.getHeuristic();

            if (delt > 0) {
                currentPos = nextPos;
            } else {
                prob = Math.exp(delt / temperature);
                det = Math.random();

                if (det <= prob) {
                    currentPos = nextPos;
                }
            }
            temperature = temperature - val;
        }

        return currentPos;
    }

    public int getNodesGenerated() {
        return nodesGenerated;
    }

    public pos getStartNode() {
        return start;
    }
}
package pack1;

import java.util.*;

public class hill {
    private final static int N = 20;
    private queenObj[] startup;
    private pos start;
    private int nodesProduced;

    public hill() {
        start = new pos();
        startup = new queenObj[N];
        startState();
        nodesProduced = 0;
    }

    public hill(queenObj[] s) {
        start = new pos();
        startup = new queenObj[N];
        for (int i = 0; i < s.length; i++) {
            startup[i] = new queenObj(s[i].getBoardRows(), s[i].getBoardColumns());
        }
        start.setState(startup);
        start.Heuristic();

        nodesProduced = 0;
    }

    public void startState() {
        Random gen = new Random();
        for (int i = 0; i < N; i++) {
            startup[i] = new queenObj(gen.nextInt(N), i);
        }
        start.setState(startup);
        start.Heuristic();
    }

    public pos hillClimb() {
        pos current = start;

        while (true) {
            ArrayList<pos> successors = current.genAdjacent(current);
            nodesProduced += successors.size();

            pos next = null;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(current) < 0) {
                    next = successors.get(i);
                }
            }

            if (next == null)
                return current;

            current = next;
        }
    }

    public pos getStartNode() {
        return start;
    }

    public int getNodesProduced() {
        return nodesProduced;
    }
}
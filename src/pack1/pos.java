package pack1;

import java.util.*;

public class pos implements Comparable<pos> {
    private static final int N = 20;
    public queenObj[] state;
    private ArrayList<pos> adjacentPiece;
    private int heuristicScore;

    public pos() {
        state = new queenObj[N];
        adjacentPiece = new ArrayList<>();
    }

    public pos(pos n) {
        state = new queenObj[N];
        adjacentPiece = new ArrayList<>();
        for (int i = 0; i < N; i++)
            state[i] = new queenObj(n.state[i].getBoardRows(), n.state[i].getBoardColumns());
        heuristicScore = 0;
    }

    public ArrayList<pos> genAdjacent(pos startState) {
        int count = 0;

        if (startState == null)
            System.out.println("start empty");

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                adjacentPiece.add(count, new pos(startState));
                adjacentPiece.get(count).state[i].move(j);
                adjacentPiece.get(count).Heuristic();

                count++;
            }
        }

        return adjacentPiece;
    }

    public pos getRandAdjacent(pos startState) {
        Random gen = new Random();

        int col = gen.nextInt(N);
        int d = gen.nextInt(N - 1) + 1;

        pos neighbour = new pos(startState);
        neighbour.state[col].move(d);
        neighbour.Heuristic();

        return neighbour;
    }

    public int Heuristic() {

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i].Attack(state[j])) {
                    heuristicScore++;
                }
            }
        }

        return heuristicScore;
    }

    public int getHeuristic() {
        return heuristicScore;
    }

    public int compareTo(pos n) {
        if (this.heuristicScore < n.getHeuristic())
            return -1;
        else if (this.heuristicScore > n.getHeuristic())
            return 1;
        else
            return 0;
    }

    public void setState(queenObj[] s) {
        for (int i = 0; i < N; i++) {
            state[i] = new queenObj(s[i].getBoardRows(), s[i].getBoardColumns());
        }
    }

    public queenObj[] getState() {
        return state;
    }

    public String toString() {
        String result = "";
        String[][] board = new String[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = "X ";

        for (int i = 0; i < N; i++) {
            board[state[i].getBoardRows()][state[i].getBoardColumns()] = "Q ";
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += board[i][j];
            }
            result += "\n";
        }

        return result;
    }
}
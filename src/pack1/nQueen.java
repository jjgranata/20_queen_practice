package pack1;

import java.util.*;
import java.text.NumberFormat;

public class nQueen {

    public nQueen() {
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        nQueen board = new nQueen();
        int amountRuns = 250;
        int hillNode = 0;
        int annealNode = 0;
        int hillSuccess = 0;
        int annealSuccess = 0;

        for (int i = 0; i < amountRuns; i++) {
            queenObj[] startBoard = board.makeBoard();

            hill hillInst = new hill(startBoard);
            anneal annealInst = new anneal(startBoard);

            pos solveHill = hillInst.hillClimb();
            pos solveAnneal = annealInst.Annealing(28, 0.0001);

            if (solveHill.getHeuristic() == 0) {
                hillSuccess++;
            }
            if (solveAnneal.getHeuristic() == 0) {
                annealSuccess++;
            }

            hillNode += hillInst.getNodesProduced();
            annealNode += annealInst.getNodesGenerated();
        }

        System.out.println("Number of Runs: " + amountRuns);
        System.out.println("Hill climb solved: " + hillSuccess);
        System.out.println("Annealing solved: " + annealSuccess);
        System.out.println();

        double hillPerc = (double) hillSuccess / (double) amountRuns;
        double annealPerc = (double) annealSuccess / (double) amountRuns;
        NumberFormat format = NumberFormat.getPercentInstance();

        System.out.println("Hill:\nNodes: " + hillNode);
        System.out.println("Percent: " + format.format(hillPerc));
        System.out.println();
        System.out.println("Annealing:\nNodes: " + annealNode);
        System.out.println("Percent: " + format.format(annealPerc));
        System.out.println();

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }

    public queenObj[] makeBoard() {
        queenObj[] start = new queenObj[20];
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            start[i] = new queenObj(rand.nextInt(20), i);
        }
        return start;
    }
}
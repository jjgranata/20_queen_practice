package pack1;

public class queenObj {
    private int boardRows;
    private int boardColumns;

    public queenObj(int r, int c) {
        boardRows = r;
        boardColumns = c;
    }

    public boolean Attack(queenObj q) {
        boolean attack = false;

        if (boardRows == q.getBoardRows() || boardColumns == q.getBoardColumns())
            attack = true;

        else if (Math.abs(boardColumns - q.getBoardColumns()) == Math.abs(boardRows - q.getBoardRows()))
            attack = true;

        return attack;
    }

    public void move(int space) {
        boardRows += space;

        if (boardRows > 19 && boardRows % 19 != 0) {
            boardRows = (boardRows % 19) - 1;
        } else if (boardRows > 19 && boardRows % 19 == 0) {
            boardRows = 19;
        }
    }

    public void setBoardRows(int r) {
        boardRows = r;
    }

    public int getBoardRows() {
        return boardRows;
    }

    public void setBoardColumns(int c) {
        boardColumns = c;
    }

    public int getBoardColumns() {
        return boardColumns;
    }

    public String toString() {
        return "(" + boardRows + ", " + boardColumns + ")";
    }
}
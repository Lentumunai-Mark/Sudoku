
public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[][]{
            {0, 0, 0, 1, 8, 3, 7, 0, 2},
            {0, 0, 8, 0, 0, 0, 9, 0, 0},
            {7, 6, 0, 0, 0, 4, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 0, 7, 5},
            {0, 5, 0, 0, 0, 0, 0, 8, 0},
            {6, 2, 0, 0, 0, 0, 4, 0, 0},
            {0, 0, 0, 4, 0, 0, 0, 2, 8},
            {0, 0, 1, 0, 0, 0, 6, 0, 0},
            {4, 0, 3, 2, 6, 1, 0, 0, 0}
    };

    if (solve(board)) {
        display(board);
    } else {
        System.out.println("Cannot solve");
    }
    }//is safe

    static boolean solve(int[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;

        boolean emptyLeft = true;

        // this is how we are replacing the r,c from arguments
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            // if you found some empty element in row, then break
            if (emptyLeft == false) {
                break;
            }
        }

        if (emptyLeft == true) {
            return true;
            // soduko is solved
        }

        // backtrack
        for (int number = 1; number <= 9; number++) {
            if (isSafe(board, row, col, number)) {
                board[row][col] = number;
                if (solve(board)) {
                    // found the answer
                    return true;
                } else {
                    // backtrack
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static void display(int[][] board) {
        for(int[] row : board) {
            for(int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    static boolean isSafe(int[][] board, int row, int col, int num){
        //for loop to iterate
        //test every possible combination
        for(int i = 0; i<board.length; i++){
            //check if in row or not
            if(board[row][i] == num){
                return false;
            }
        }
        for(int[] n: board){
            if(n[col] == num){
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int colStart = col - col%sqrt;
        int rowStart = row - row%sqrt;
        for(row = rowStart; row<rowStart+sqrt; row++){
            for(col = colStart; col<colStart+sqrt; col++){
                if(board[row][col]==num){
                    return false;
                }
            }
        }
        return true;
    }
}

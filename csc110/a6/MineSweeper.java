/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: November 20th 2016
 * Filename: MineSweeper.java
 * Details: CSC110 Assignment 6
*/

import java.util.*;

/* 
 * The class MineSweeper uses
 * an 8 by 8 grid in the form
 * of a two-dimensional array to
 * recreate the computer game Minesweeper.
*/
public class MineSweeper {
    public static void main(String[] args) {
        int rows = 8;
        int columns = 8;
        String[][] mineGrid = new String[rows][columns];
        mineGrid = initializeFullGrid(mineGrid);
        String[][] gameGrid = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameGrid[i][j] = ".";
            }
        }
        play(gameGrid, mineGrid, rows, columns);
        drawFullGrid(gameGrid, rows, columns);
    }
    
    /*
     * This method accepts the number of 
     * rows and columns to be used for the
     * game grid, and returns an array that
     * initializes the positions of the bombs
     * and how many bombs are adjacent to each cell.
     * Cells along edges as well as corner cells must
     * be dealt with differently than inner cells since
     * they have a different number of adjacent cells.
     * This array is used in conjunction with the
     * array that is visible to the player.
    */
    public static String[][] initializeFullGrid(String[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        Random bomb = new Random();
        int bombCount = 0;
        nested_loop:
        while (bombCount <= 10) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] != "B" && bomb.nextInt(11) == 5) {
                        grid[i][j] = "B";
                        bombCount++;
                    }
                    else if (grid[i][j] != "B") {
                        grid[i][j] = "" + 0;
                    }
                    if (bombCount == 10) {
                        break nested_loop;
                    }
                }    
            }
        }
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (grid[i][j] != "B") {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (grid[k][l] == "B") {
                                count++;
                            }
                        }
                    }
                    grid[i][j] = "" + count;
                }
                count = 0;
            }
        }
        for (int j = 1; j < columns - 1; j++) {
            if (grid[0][j] != "B") {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (grid[0][l] == "B") {
                        count++;
                    }
                    if (grid[1][l] == "B") {
                        count++;
                    }
                }
                grid[0][j] = "" + count;
            }
            count = 0;
        }
        for (int j = 1; j < columns - 1; j++) {
            if (grid[rows - 1][j] != "B") {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (grid[rows - 1][l] == "B") {
                        count++;
                    }
                    if (grid[rows - 2][l] == "B") {
                        count++;
                    }
                }
                grid[rows - 1][j] = "" + count;
            }
            count = 0;
        }
        for (int i = 1; i < rows - 1; i++) {
            if (grid[i][0] != "B") {
                for (int k = i - 1; k <= i + 1; k++) {
                    if (grid[k][0] == "B") {
                        count++;
                    }
                    if (grid[k][1] == "B") {
                        count++;
                    }
                }
                grid[i][0] = "" + count;
            }
            count = 0;
        }
        for (int i = 1; i < rows - 1; i++) {
            if (grid[i][columns - 1] != "B") {
                for (int k = i - 1; k <= i + 1; k++) {
                    if (grid[k][columns - 1] == "B") {
                        count++;
                    }
                    if (grid [k][columns - 2] == "B") {
                        count++;
                    }
                }
                grid[i][columns - 1] = "" + count;
            }
            count = 0; 
        }
        if (grid[0][0] != "B") {
            if (grid[0][1] == "B") {
                count++;
            }
            if (grid[1][0] == "B") {
                count++;
            }
            if (grid[1][1] == "B") {
                count++;
            }
            grid[0][0] = "" + count;
        }
        count = 0;
        if (grid[0][columns - 1] != "B") {
            if (grid[0][columns - 2] == "B") {
                count++;
            }
            if (grid[1][columns - 2] == "B") {
                count++;
            }
            if (grid[1][columns - 1] == "B") {
                count++;
            }
            grid[0][columns - 1] = "" + count;
        }
        count = 0;
        if (grid[rows - 1][0] != "B") {
            if (grid[rows - 1][1] == "B") {
                count++;
            }
            if (grid[rows - 2][0] == "B") {
                count++;
            }
            if (grid[rows - 2][1] == "B") {
                count++;
            }
            grid[rows - 1][0] = "" + count;
        }
        count = 0;
        if (grid[rows - 1][columns - 1] != "B") {
            if (grid[rows - 1][columns - 2] == "B") {
                count++;
            }
            if (grid[rows - 2][columns - 2] == "B") {
                count++;
            }
            if (grid[rows - 2][columns - 1] == "B") {
                count++;
            }
            grid[rows - 1][columns - 1] = "" + count;
        }
        return grid;
    }
    
    /*
     * This method is used as the basis for the game, and
     * accepts both arrays as well as the number of rows and
     * columns as parameters. A counter keeps track of the 
     * number of uncovered cells, and the player wins if 
     * all cells except bombs are uncovered. If the player 
     * loses, the bomb cell they uncovered is marked with an
     * 'X' and the other bombs are revealed with 'B'.
    */
    public static void play(String[][] playGrid, String[][] mineGrid, int rows, int columns) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        drawFullGrid(playGrid, rows, columns);
        System.out.println();
        while (score < 54) {
            System.out.print("Select a cell. Row value (a digit between 0 and 7): ");
            int rowChoice = getInt(input);
            System.out.print("Select a cell. Column value (a digit between 0 and 7): ");
            int colChoice = getInt(input);
            System.out.println();
            if (mineGrid[rowChoice][colChoice] != "B") {
                int revealedCells = revealGridCell(rowChoice, colChoice, playGrid, mineGrid);
                score += revealedCells;
                if (score == 54) {
                    System.out.println("Congratulations! You won!");
                    break;
                }
                drawFullGrid(playGrid, rows, columns);
                System.out.println();
                if (revealedCells == 0) {
                    System.out.println("You already selected that cell.");
                }
            } else {
                playGrid[rowChoice][colChoice] = "X";
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (mineGrid[i][j].equals("B") && playGrid[i][j] != "X") {
                            playGrid[i][j] = "B";
                        }
                    }
                }
                System.out.println("Kaboom! Game over!");
                return;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mineGrid[i][j].equals("B")) {
                    playGrid[i][j] = "B";
                }
            }
        }
    }
    
    /*
     * This method returns the integer the user
     * types to be used as the row/column number.
     * They will continuously be re-prompted for 
     * a different integer if what they type is
     * outside the array bounds.
    */
    public static int getInt(Scanner console) {
        int value = console.nextInt();
        while (value < 0 || value > 7) {
            System.out.print("Invalid input. Try again: ");
            value = console.nextInt();
        }
        return value;
    }
    
    /*
     * This method is called at the start of the game
     * and after each turn. It accepts the playing array as
     * a parameter and outputs the visual representation
     * of the game grid for the player. The grid itself 
     * is modified depending on what cells are uncovered.
    */
    public static void drawFullGrid(String[][] grid, int rows, int columns) {
        System.out.print("  |");
        for (int j = 0; j < columns; j++) {
            System.out.print(" " + j);
        }
        System.out.println();
        for (int j = 0; j < 2 * columns + 3; j++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < columns; j++) {
                System.out.print(" " + grid[i][j]);
            }
            System.out.println();
        }
    }
    
    /* 
     * This method is only called if the 
     * player does not uncover a bomb. It accepts
     * both arrays as well as the row number and
     * column number the player inputted. It 
     * reveals the content of the game grid cell
     * (i.e the number of bombs around it) by 
     * changing that cell to that of the pre-initialized
     * mine grid. If there are no bombs around said cell,
     * then the contents of the adjacent cells are also
     * revealed. Once again, cells along edges and corners
     * must be handled differently. 
    */
    public static int revealGridCell(int row, int col, String[][] playGrid, String[][] mineGrid) {
        int count = 1;
        int rows = playGrid.length;
        int columns = playGrid[0].length;
        if (playGrid[row][col] != ".") {
            return 0;
        }
        playGrid[row][col] = mineGrid[row][col];
        if (mineGrid[row][col].equals("0")) {
            playGrid[row][col] = " ";
            if (row != 0 && row != rows - 1 && col != 0 && col != columns - 1) {  
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == 0 && col != 0 && col != columns - 1) {
                for (int i = row; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == rows - 1 && col!= 0 && col!= columns - 1) {
                for (int i = row - 1; i <= row; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row != 0 && row != rows - 1 && col == 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col; j <= col + 1; j++) {
                       if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row!= 0 && row != rows - 1 && col == columns - 1) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == 0 && col == 0) {
                for (int i = row; i <= row + 1; i++) {
                    for (int j = col; j <= col + 1; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == rows - 1 && col == 0) {
                for (int i = row - 1; i <= row; i++) {
                    for (int j = col; j <= col + 1; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == 0 && col == columns - 1) {
                for (int i = row; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
            if (row == rows - 1 && col == columns - 1) {
                for (int i = row - 1; i <= row; i++) {
                    for (int j = col - 1; j <= col; j++) {
                        if (playGrid[i][j].equals(".")) {
                            count++;
                        }
                        playGrid[i][j] = mineGrid[i][j];
                        if (mineGrid[i][j].equals("0")) {
                            playGrid[i][j] = " ";
                        }
                    }
                }
            }
        }
        return count;
    }
}    
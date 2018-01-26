/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: November 20th 2016
 * Filename: MineSweeperExtra.java
 * Details: CSC110 Assignment 6
*/

import java.util.*;

/* 
 * The class MineSweeperExtra begins
 * by having the user choose a difficulty
 * level, then lets them play Minesweeper
 * with the grid and number of bombs 
 * adjusted according to what they choose.
 * At the end of the game, its duration
 * in minutes and seconds will be outputed,
 * and the user will have the option to replay.
 * Since at least one game must be played, a do
 * while loop is used to fit this purpose. If the 
 * user does not input a valid difficulty, the main
 * method is called repeatedly until the input is valid.
*/
public class MineSweeperExtra {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        do {
            System.out.print("Choose a difficulty (easy, medium, or hard): ");
            String difficulty = choice.next();
            if (difficulty.equalsIgnoreCase("easy")) {
                game(4, 3);
            } else if (difficulty.equalsIgnoreCase("medium")) {
                game(8, 10);
            } else if (difficulty.equalsIgnoreCase("hard")) {
                game(12, 20);
            } else {
                System.out.println("Not a valid difficulty.");
                main(args);
            }
            System.out.print("Play again? Type 'yes' to play again, or anything else to exit. ");
        } while (choice.next().equalsIgnoreCase("yes"));
    }
    
    /*
     * Once the player decides their difficulty,
     * this method is called to set up the game.
     * This essentially takes the place of the main
     * method of the original MineSweeper. At the end
     * of the game, the time elapsed is set by taking
     * the difference of System.currentTimeMillis() between
     * the end and start of the game.
    */
    public static void game(int dimensions, int numBombs) {
        double startTime = System.currentTimeMillis();
        int rows = dimensions;
        int columns = dimensions;
        String[][] mineGrid = new String[rows][columns];
        mineGrid = initializeFullGrid(mineGrid, numBombs);
        String[][] gameGrid = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameGrid[i][j] = ".";
            }
        }
        play(gameGrid, mineGrid, rows, columns, numBombs);
        drawFullGrid(gameGrid, rows, columns);
        double endTime = (System.currentTimeMillis() - startTime) / 1000;
        int minutes = (int) endTime / 60;
        int seconds = (int) endTime % 60;
        System.out.println("You finished in " + minutes + " minutes and " + seconds + " seconds.");
    }
    
    /*
     * This method largely works the same way
     * as in the original MineSweeper, but the 
     * number of bombs is also a parameter to 
     * indicate how many bombs must be initialized
     * depending on the difficulty.
    */ 
    public static String[][] initializeFullGrid(String[][] grid, int bombs) {
        int rows = grid.length;
        int columns = grid[0].length;
        Random bomb = new Random();
        int bombCount = 0;
        nested_loop:
        while (bombCount <= bombs) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] != "B" && bomb.nextInt(11) == 5) {
                        grid[i][j] = "B";
                        bombCount++;
                    }
                    else if (grid[i][j] != "B") {
                        grid[i][j] = "" + 0;
                    }
                    if (bombCount == bombs) {
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
     * This method largely works the same way
     * as in the original MineSweeper, but the 
     * number of bombs is also a parameter in order
     * to modify the score, and the prompt message
     * varies slightly depending on the grid's dimensions.
    */ 
    public static void play(String[][] playGrid, String[][] mineGrid, int rows, int columns, int bombs) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        drawFullGrid(playGrid, rows, columns);
        System.out.println();
        while (score < rows * columns - bombs) {
            System.out.print("Select a cell. Row value (a digit between 0 and " + (rows - 1) + "): ");
            int rowChoice = getInt(input, rows);
            System.out.print("Select a cell. Column value (a digit between 0 and " + (columns - 1) + "): ");
            int colChoice = getInt(input, columns);
            System.out.println();
            if (mineGrid[rowChoice][colChoice] != "B") {
                int revealedCells = revealGridCell(rowChoice, colChoice, playGrid, mineGrid);
                score += revealedCells;
                if (score == rows * columns - bombs) {
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
    
    public static int getInt(Scanner console, int dimensions) {
        int value = console.nextInt();
        while (value < 0 || value > dimensions - 1) {
            System.out.print("Invalid input. Try again: ");
            value = console.nextInt();
        }
        return value;
    }
    
    /*
     * This method largely works the same way
     * as in MineSweeper, but the 12 by 12 grid
     * must be programmed slightly differently
     * to format it properly.
    */ 
    public static void drawFullGrid(String[][] grid, int rows, int columns) {
        if (rows != 12) {
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
        } else {
            System.out.print("   |");
            for (int j = 0; j < columns; j++) {
                System.out.print(" " + j);
            }
            System.out.println();
            for (int j = 0; j < 2 * columns + 5; j++) {
                System.out.print("-");
            }
            System.out.println();
            for (int i = 0; i < rows; i++) {
                if (i < 10) {
                    System.out.print(i + "  |");
                } else {
                    System.out.print(i + " |");
                }
                for (int j = 0; j < columns; j++) {
                    System.out.print(" " + grid[i][j]);
                }
                System.out.println();
            }
        }
    }
    
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
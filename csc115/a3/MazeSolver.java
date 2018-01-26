/* 
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: February 22nd 2017
 * Filename: MazeSolver.java
 * Details: CSC 115 Assignment 3
*/

/*
 * The class MazeSolver contains a method for
 * finding a path of coordinates from the entrance
 * of a maze to its exit.
*/
public class MazeSolver {

    /*
     * The method utilizes a reference-based
     * stack to store the coordinates of each
     * location of the path. Once the stack is complete,
     * the top corresponds to the exit coordinates and the
     * bottom corresponds to the entry coordinates.
     * input: Maze to be solved.
     * returns: String representation of path from start to finish.
    */ 
    public static String findPath(Maze maze) {
        StackRefBased<MazeLocation> locationStack = new StackRefBased<MazeLocation>();
        try { // In case something goes wrong with the stack's "pop()" method. 
            boolean[][] visited = new boolean[maze.getNumRows()][maze.getNumCols()];
            locationStack.push(maze.getEntry());
            while (!locationStack.isEmpty() && !locationStack.top.data.equals(maze.getExit())) {
                MazeLocation curr = locationStack.top.data;
                visited[curr.getRow()][curr.getCol()] = true;
                /* 
                 * The following conditional statements correspond to the four possible 
                 * subsequent locations for the path. If one of the statements is satisfied,
                 * the location is pushed onto the stack as a possible path entry and becomes
                 * the new current location. Else, the top of the stack is popped and the new
                 * current location is the one previously visited.
                */
                if (!maze.isWall(curr.getRow() - 1, curr.getCol()) && !visited[curr.getRow() - 1][curr.getCol()]) {
                    curr = new MazeLocation(curr.getRow() - 1, curr.getCol());
                    locationStack.push(curr);
                } else if (!maze.isWall(curr.getRow() + 1, curr.getCol()) && !visited[curr.getRow() + 1][curr.getCol()]) {
                    curr = new MazeLocation(curr.getRow() + 1, curr.getCol());
                    locationStack.push(curr);
                } else if (!maze.isWall(curr.getRow(), curr.getCol() - 1) && !visited[curr.getRow()][curr.getCol() - 1]) {
                    curr = new MazeLocation(curr.getRow(), curr.getCol() - 1);
                    locationStack.push(curr);
                } else if (!maze.isWall(curr.getRow(), curr.getCol() + 1) && !visited[curr.getRow()][curr.getCol() + 1]) {
                    curr = new MazeLocation(curr.getRow(), curr.getCol() + 1);
                    locationStack.push(curr);
                } else {
                    locationStack.pop();
                }
            }
            if (locationStack.isEmpty()) { // This means the maze is unsolvable.
                return "";
            } else {
                String path = locationStack.pop().toString();
                while (!locationStack.isEmpty()) {
                    /*
                     * The top of the stack is popped and placed in front of the string
                     * so that once the stack is empty, the string corresponds to the path
                     * from start to finish.
                    */
                    path = locationStack.pop() + " " + path; 
                }
                return path;
            }
        } catch (StackEmptyException see) {
            System.out.println("The stack is empty.");
            return "";
        }
    }
}

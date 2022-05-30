import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int width; // Maze grid column
    private int height; // Maze grid row
    private boolean debug; // Debug mode control
    private char[][] myMaze; // Maze grid; height x width (row x column)
    private boolean[][] validPoint; // Use to check the valid point
    private ArrayList<Point> visited; // List of visited point
    private ArrayList<Point> mazePoint; // List of all point
    private Random rand = new Random(); // Use to random choose the point

    public Maze(int width, int height, boolean debug) {
        this.height = 2 * height + 1;
        this.width = 2 * width + 1;
        this.debug = debug;
        myMaze = new char[height][width];
        validPoint = new boolean[height][width];
        visited = new ArrayList<>();
        mazePoint = new ArrayList<>();
        buildMaze();
    }

    private void buildGraph() {
        for (int n = 0; n < height; n++) {
            for (int m = 0; m < width; m++) {
                if (n % 2 == 0 || m % 2 == 0) {
                    myMaze[n][m] = 'X';
                    validPoint[n][m] = false;
                } else {
                    myMaze[n][m] = ' ';
                    validPoint[n][m] = true;
                    mazePoint.add(new Point(n, m));
                }
            }
        }
        myMaze[0][1] = ' ';
        myMaze[height - 1][width - 2] = ' ';
    }

    private void buildMaze() {
        buildGraph();
        Point current = new Point(1, 1);
        markVisited(current);
        Point end = new Point(height - 2, width - 2);
        ArrayList<Point> validMoves;
        ArrayList<Point> path = new ArrayList<>();

        while (!visited.isEmpty()) {
            validMoves = checkValidPoint(current);
            int numberOfPoint = validMoves.size();
            if (numberOfPoint != 0) {
                if (debug) {
                    display();
                }
                int move = rand.nextInt(numberOfPoint);
                Point next = validMoves.get(move);
                int x = (int) ((current.getX() + next.getX()) / 2);
                int y = (int) ((current.getY() + next.getY()) / 2);
                myMaze[x][y] = ' ';
                markVisited(next);
                current = next;
                if (current.equals(end)) {
                    path = new ArrayList<>(visited);
                }
            } else {
                visited.remove(visited.size() - 1);
                if (!visited.isEmpty()) {
                    current = visited.get(visited.size() - 1);
                }
            }
        }
        visited = new ArrayList<>(path);
        if (debug) {
            display();
            for (Point point : mazePoint) {
                int x = (int) point.getX();
                int y = (int) point.getY();
                myMaze[x][y] = ' ';
            }
        }
    }

    private ArrayList<Point> checkValidPoint(Point point) {
        ArrayList<Point> unVisited = new ArrayList<>();
        int x = (int) point.getX();
        int y = (int) point.getY();
        if (x - 2 >= 1) {
            if (validPoint[x - 2][y]) {
                unVisited.add(new Point(x - 2, y));
            }
        }
        if (x + 2 < height) {
            if (validPoint[x + 2][y]) {
                unVisited.add(new Point(x + 2, y));
            }
        }
        if (y - 2 >= 1) {
            if (validPoint[x][y - 2]) {
                unVisited.add(new Point(x, y - 2));
            }
        }
        if (y + 2 < width) {
            if (validPoint[x][y + 2]) {
                unVisited.add(new Point(x, y + 2));
            }
        }
        return unVisited;
    }

    private void markVisited(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        if (debug) {
            myMaze[x][y] = 'V';
        }
        validPoint[x][y] = false;
        visited.add(point);
        mazePoint.add(point);
    }

    public void display() {
        for (int n = 0; n < height; n++) {
            for (int m = 0; m < width; m++) {
                System.out.print(myMaze[n][m] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public String toString() {
        while (!visited.isEmpty()) {
            int x = (int) visited.get(visited.size() - 1).getX();
            int y = (int) visited.get(visited.size() - 1).getY();
            myMaze[x][y] = '+';
            visited.remove(visited.size() - 1);
        }
        display();
        return "";
    }
}

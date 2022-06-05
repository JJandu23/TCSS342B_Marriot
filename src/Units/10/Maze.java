import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int width; // width of the maze
    private int height; // height of the maze
    private boolean debug; // debug mode
    private char[][] myMaze;
    private boolean[][] validPoint; // validPoint[i][j] is true if (i,j) is a valid point
    private ArrayList<Point> visited; // visited[i] is true if (i,j) is visited
    private ArrayList<Point> mazePoint; // mazePoint[i] is true if (i,j) is a maze point
    private Random rand = new Random(); // random number generator

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        myMaze = new char[height][width];
        validPoint = new boolean[height][width];
        visited = new ArrayList<>();
        mazePoint = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                myMaze[i][j] = ' ';
                validPoint[i][j] = true;
            }
        }
        // initialize the maze
        buildMaze();
    }

//     Constructor
    public Maze(int n, int m, boolean debug) {
        this.height = 2 * m + 1;
        this.width = 2 * n + 1;
        this.debug = debug;
        myMaze = new char[height][width];
        validPoint = new boolean[height][width];
        visited = new ArrayList<>();
        mazePoint = new ArrayList<>();
        buildMaze();
    }

    // build the graph
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
        myMaze[0][1] = ' '; // top left corner entrance
        myMaze[height - 1][width - 2] = ' '; // bottom right corner exit
    }

    // build the maze
    private void buildMaze() {
        buildGraph();
        Point curr = new Point(1, 1);
        markVisited(curr);
        Point end = new Point(height - 2, width - 2);
        ArrayList<Point> validMoves;
        ArrayList<Point> path = new ArrayList<>();
        while (!visited.isEmpty()) {
            validMoves = solveMaze(curr);
            int numberOfPoint = validMoves.size();
            if (numberOfPoint != 0) {
                if (debug) {
                    display();
                }
                int move = rand.nextInt(numberOfPoint);
                Point next = validMoves.get(move);
                int x = (int) ((curr.getX() + next.getX()) / 2);
                int y = (int) ((curr.getY() + next.getY()) / 2);
                myMaze[x][y] = ' ';
                markVisited(next);
                curr = next;
                if (curr.equals(end)) {
                    path = new ArrayList<>(visited);
                }
            } else {
                visited.remove(visited.size() - 1);
                if (!visited.isEmpty()) {
                    curr = visited.get(visited.size() - 1);
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

    // solveMaze returns a list of valid moves from current point
    private ArrayList<Point> solveMaze(Point point) {
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

    // markVisited marks the point as visited
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

    // display displays the maze
    public void display() {
        for (int n = 0; n < height; n++) {
            for (int m = 0; m < width; m++) {
                System.out.print(myMaze[n][m] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    // getMaze returns the maze
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

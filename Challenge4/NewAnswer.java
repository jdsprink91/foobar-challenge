package Challenge4;
import java.util.*;

// http://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
// http://algorithms.tutorialhorizon.com/backtracking-rat-in-a-maze-puzzle/
public class NewAnswer {
  public static int answer(int[][] maze) {
    // create a visited matrix and initialze it
    boolean[][] visited = new boolean[maze.length][];
    for(int a = 0; a < maze.length; a++) {
      visited[a] = new boolean[maze[a].length];
      for(int b = 0; b < maze[a].length; b++) {
        visited[a][b] = false;
      }
    }

    // get the shortest path without touching the ones, then refresh the visited matrix
    int result = getShortestPathDist(maze, visited, new Point(0, 0), new Point(maze[maze.length - 1].length - 1, maze.length - 1));
    refreshVisitedMatrix(visited);

    if(result < 0) {
      result = Integer.MAX_VALUE;
    }

    // now we need to check all the paths that have a 1 in it, if they have a shorter path,
    // then replace the number
    for(int y = 0; y < maze.length; y++) {
      for(int x = 0; x < maze[y].length; x++) {
        if(maze[y][x] == 1) {
          // change the value to a 0
          maze[y][x] = 0;

          int pathToOne = getShortestPathDist(maze, visited, new Point(0, 0), new Point(x, y));
          int pathToDest = getShortestPathDist(maze, visited, new Point(x, y), new Point(maze[maze.length - 1].length - 1, maze.length - 1));
          refreshVisitedMatrix(visited);

          // if there is a path to and from the 1, then check to see if it's better
          // subtract 1 for the double tapped point
          if(pathToOne != -1 && pathToDest != -1) {
            result = Math.min(result, pathToOne + pathToDest - 1);
          }

          // change the value back to a 1 so the maze returns to state
          maze[y][x] = 1;
        }
      }
    }

    return result;
  }

  public static void printVisited(boolean[][] visited) {
    for(int a = 0; a < visited.length; a++) {
      System.out.print("[");
      for(int b = 0; b < visited[a].length; b++) {
        System.out.print(visited[a][b] + ", ");
      }
      System.out.print("]");
      System.out.println("");
    }
  }

  public static void refreshVisitedMatrix(boolean[][] visited) {
    for(int a = 0; a < visited.length; a++) {
      for(int b = 0; b < visited[a].length; b++) {
        visited[a][b] = false;
      }
    }
  }

  public static int getShortestPathDist(int[][] maze, boolean[][] visited, Point origin, Point dest) {
      // first thing we should do is set the origin point as visited
      visited[origin.getY()][origin.getX()] = true;

      // create a queue and add the origin node to it
      Queue<MazeNode> bfsQ = new LinkedList<MazeNode>();
      bfsQ.add(new MazeNode(1, origin));

      // have a direction to make it cleaner to loop through new points
      int[][] directionPairs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

      // peek returns null if the queue is empty
      while(bfsQ.peek() != null) {
        // remove the first element from the list
        MazeNode node = bfsQ.remove();
        Point nodePoint = node.getPoint();

        // if the current point is the end return the distance
        if(nodePoint.getX() == dest.getX() && nodePoint.getY() == dest.getY()) {
          return node.getDist();
        }

        // check all directions
        for(int i = 0; i < directionPairs.length; i++) {
          int moveX = directionPairs[i][0] + nodePoint.getX();
          int moveY = directionPairs[i][1] + nodePoint.getY();

          // if this is a valid direction, has a 0 and hasn't been visited, add to the list
          if(isValid(maze, moveX, moveY) && !visited[moveY][moveX]) {
            visited[moveY][moveX] = true;
            MazeNode nextDir = new MazeNode(node.getDist() + 1, new Point(moveX, moveY));
            bfsQ.add(nextDir);
          }
        }
      }

      // return -1 if this isn't a valid direction
      return -1;

  }

  public static boolean isValid(int[][] maze, int x, int y) {
    return y >= 0 && y < maze.length && x >= 0 && x < maze[y].length && maze[y][x] == 0;
  }
}

class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }
  public int getY() {
    return this.y;
  }
}

class MazeNode {
  private int dist;
  private Point point;

  public MazeNode(int dist, Point point) {
    this.dist = dist;
    this.point = point;
  }

  public int getDist() {
    return this.dist;
  }

  public Point getPoint() {
    return this.point;
  }

  public String toString() {
    return "x: " + this.point.getX() + " y: " + this.point.getY() + " dist : " + this.dist;
  }
}

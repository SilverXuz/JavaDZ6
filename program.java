
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class program {
  public static void main(String[] args) {

    var mg = new MapGenerator();
    System.out.println(
        new MapPrinter().mapColor(
            mg.getMap())

    );

    var lee = new WaveAlgorithm(mg.getMap());
    lee.Colorize(new Point2D(3, 3));

    System.out.println(
        new MapPrinter().mapColor(
            mg.getMap())

    );

    var end = new WaveAlgorithm(mg.getMap());
    end.getRoad(new Point2D(13, 13));

    // System.out.println(
    //     new MapPrinter().mapColor(
    //         mg.getMap())

    // );
  }
}

class Point2D {
  int x, y;

  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return String.format("x: %d  y: %d", x, y);
  }
}

class MapGenerator {
  int[][] map;

  public MapGenerator() {
    int[][] map = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, -1, -1, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, -1, -1, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    this.map = map;
  }

  public int[][] getMap() {
    return map;
  }

  public void setCat(Point2D pos) {
    map[pos.x][pos.y] = -2;
  }

  public void setExit(Point2D pos) {
    map[pos.x][pos.y] = -3;
  }
}

class MapPrinter {

  public MapPrinter() {
  }

  public String rawData(int[][] map) {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[row].length; col++) {
        sb.append(String.format("%5d", map[row][col]));
      }
      sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
      sb.append("\n");
    }

    return sb.toString();
  }

  public String mapColor(int[][] map) {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[row].length; col++) {
        switch (map[row][col]) {
          // case 0:
          // sb.append("░░░");
          // break;
          case -1:
            sb.append("▒▒▒");
            break;
          case 1:
            sb.append(" ㋛");
            break;
          // case 2:
          // sb.append("E");
          // break;
          default:
            sb.append(String.format("%3d", map[row][col]));
            break;
        }
      }
      sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
      sb.append("\n");
    }
    return sb.toString();
  }
}

class WaveAlgorithm {
  int[][] map;

  public WaveAlgorithm(int[][] map) {
    this.map = map;
  }

  public void Colorize(Point2D startPoint) {
    Queue<Point2D> queue = new LinkedList<Point2D>();
    queue.add(startPoint);
    map[startPoint.x][startPoint.y] = 1;

    while (queue.size() != 0) {
      Point2D p = queue.remove();

      if (map[p.x - 1][p.y] == 0) {
        queue.add(new Point2D(p.x - 1, p.y));
        map[p.x - 1][p.y] = map[p.x][p.y] + 1;
      }
      if (map[p.x][p.y - 1] == 0) {
        queue.add(new Point2D(p.x, p.y - 1));
        map[p.x][p.y - 1] = map[p.x][p.y] + 1;
      }
      if (map[p.x + 1][p.y] == 0) {
        queue.add(new Point2D(p.x + 1, p.y));
        map[p.x + 1][p.y] = map[p.x][p.y] + 1;
      }
      if (map[p.x][p.y + 1] == 0) {
        queue.add(new Point2D(p.x, p.y + 1));
        map[p.x][p.y + 1] = map[p.x][p.y] + 1;
      }
    }
  }

  public ArrayList<Point2D> getRoad(Point2D exit) {
    ArrayList<Point2D> road = new ArrayList<Point2D>();
    // StringBuilder road = new StringBuilder();
    road.add(exit);
    System.out.println("exit " + exit);

    // road.add(map[exit.x][exit.y]);
    System.out.println("map " + map[exit.x][exit.y]);
    System.out.println("road " + road);

    while (map[exit.x][exit.y] != 1) {
      Point2D p = exit;

      if (map[p.x - 1][p.y] == map[p.x][p.y] - 1) {
        road.add(new Point2D(p.x - 1, p.y));
        map[p.x - 1][p.y] = map[p.x][p.y] + 1;
      } else if (map[p.x][p.y - 1] == map[p.x][p.y] - 1) {
        road.add(new Point2D(p.x, p.y - 1));
        map[p.x][p.y - 1] = map[p.x][p.y] + 1;
      } else if (map[p.x + 1][p.y] == map[p.x][p.y] - 1) {
        road.add(new Point2D(p.x + 1, p.y));
        map[p.x + 1][p.y] = map[p.x][p.y] + 1;
      } else if (map[p.x][p.y + 1] == map[p.x][p.y] - 1) {
        road.add(new Point2D(p.x, p.y + 1));
        map[p.x][p.y + 1] = map[p.x][p.y] + 1;
      }

      map[p.x][p.y] -= 1;
      System.out.println(map[exit.x][exit.y]);
    }
    System.out.println("road end " + road);
    return road;
  }
}
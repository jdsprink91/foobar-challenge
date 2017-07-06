package Challenge6;
import java.util.*;

public class Answer {
  public static int answer(String n) {
    Double start = Double.parseDouble(n);

    // create a queue and get going
    Queue<Num> q = new LinkedList<Num>();
    q.add(new Num(start, 0));

    // have a set of previously visited nodes
    Set<Double> visited = new HashSet<Double>();

    while(q.peek() != null) {
      // remove the first element from the list
      Num node = q.remove();
      Double val = node.getValue();
      int distPlusOne = node.getDist() + 1;

      if(val == 1) {
        return node.getDist();
      }

      if(val % 2 == 0 && !visited.contains(val / 2)) {
        visited.add(val / 2);
        q.add(new Num(val / 2, distPlusOne));
      }

      if(!visited.contains(val + 1)) {
        visited.add(val + 1);
        q.add(new Num(val + 1, distPlusOne));
      }

      if(!visited.contains(val - 1)) {
        visited.add(val - 1);
        q.add(new Num(val - 1, distPlusOne));
      }
    }

    return 0;
  }
}

class Num {
  private Double value;
  private int dist;

  public Num(Double value, int dist) {
    this.value = value;
    this.dist = dist;
  }

  public Double getValue() {
    return this.value;
  }

  public int getDist() {
    return this.dist;
  }
}

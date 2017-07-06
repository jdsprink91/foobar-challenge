package Challenge5;
import java.util.*;

public class Answer {
  public static int answer(int n) {
    // generate a list from 1 to n - 1 and possible sums
    List<Integer> allNumsToN = new ArrayList<Integer>();
    List<Integer> numOfSums = new ArrayList<Integer>();

    // loop through and initialize the arrays
    numOfSums.add(1);
    for(int i = 1; i < n; i++) {
      allNumsToN.add(i);
      numOfSums.add(0);
    }
    numOfSums.add(0);

    // loop through the all numbers to n List
    for(int a = 0; a < allNumsToN.size(); a++) {
      // let's get the sublist of the set that we can look at
      List<Integer> possSums = generateListToN(n - allNumsToN.get(a));

      for(int b = possSums.size() - 1; b >= 0; b--) {
        int index = allNumsToN.get(a) + possSums.get(b);
        numOfSums.set(index, numOfSums.get(index) + numOfSums.get(b));
      }
    }
    return numOfSums.get(n);
  }

  public static List<Integer> generateListToN(int n) {
    List<Integer> result = new ArrayList<Integer>();
    for(int i = 0; i <= n; i++) {
      result.add(i);
    }
    return result;
  }
}

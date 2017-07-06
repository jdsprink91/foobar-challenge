package Challenge3;
import java.util.*;

public class Answer {

  public static int answer(String n, int b) {
    // do some basic set up
    int numOfCycles = 0;
    int k = n.length();
    Map<String, Integer> prevAnswers = new HashMap<String, Integer>();
    String nPrime = n;

    // start cycles
    do {
      n = nPrime;
      prevAnswers.put(n, numOfCycles);
      // set up new number strings
      String x = sortDescNumString(n);
      String y = sortAscNumString(n);

      // parse the integer strings and subtract them
      Integer nPrimeInt = Integer.parseInt(x, b) - Integer.parseInt(y, b);

      // convert the nPrime back to the appropriate base
      nPrime = Integer.toString(nPrimeInt, b);

      // check to see if I need to pad zeros
      if(nPrime.length() < k) {
        nPrime = appendZeros(nPrime, k - nPrime.length());
      }

      numOfCycles += 1;
    } while (!prevAnswers.containsKey(nPrime));

    return numOfCycles - prevAnswers.get(nPrime);
  }

  public static String appendZeros(String n, int numOfZeros) {
    String result = n;
    while(numOfZeros > 0) {
      result = "0" + result;
      numOfZeros -= 1;
    }
    return result;
  }

  public static String sortAscNumString(String n) {
    String copy = n;
    String result = "";
    // break it up first
    List<String> broken = Arrays.asList(n.split(""));
    // sort it
    Collections.sort(broken);
    // join them
    for(String letter : broken) result += letter;
    return result;
  }

  public static String sortDescNumString(String n) {
    String copy = n;
    String result = "";
    // break it up first
    List<String> broken = Arrays.asList(copy.split(""));
    // sort it
    Collections.sort(broken, Collections.reverseOrder());
    // join them
    for(String letter : broken) result += letter;
    return result;
  }

}

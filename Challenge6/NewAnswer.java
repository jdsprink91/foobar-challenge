package Challenge6;
import java.util.*;
import java.math.*;

public class NewAnswer {
  public static BigInteger biThree = new BigInteger("3");
  public static BigInteger biTwo = new BigInteger("2");
  public static BigInteger biOne = new BigInteger("1");
  public static BigInteger biZero = new BigInteger("0");
  public static BigInteger biNegOne = new BigInteger("-1");

  public static int answer(String n) {
    BigInteger pellets = new BigInteger(n);
    int result = 0;
    while(pellets.compareTo(biOne) == 1) {
      if(pellets.remainder(biTwo).equals(biZero)) {
        pellets = pellets.divide(biTwo);
      } else if(!pellets.equals(biThree) && pellets.add(biNegOne).divide(biTwo).remainder(biTwo).equals(biOne)) {
        pellets = pellets.add(biOne);
      } else {
        pellets = pellets.add(biNegOne);
      }
      result += 1;
    }
    return result;
  }
}

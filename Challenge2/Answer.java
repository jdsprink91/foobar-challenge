package Challenge2;
import java.util.*;

public class Answer {
    public static int answer(int[] l) {
        // the max value
        Integer max = new Integer(0);
        // convert the array to an array list
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < l.length; i++) {
            list.add(new Integer(l[i]));
        }

        Collections.sort(list, Collections.reverseOrder());

        // generate the powerset of all subsets
        Set<List<Integer>> result = generatePowerSet(list);
        for(List<Integer> subSet : result) {
            if((sumIntegerList(subSet) % 3) == 0) {
                Integer temp = concatIntegerList(subSet);
                if(temp > max) max = temp;
            }
        }

        return max;
    }

    // using lists because duplicate values are allowed
    private static Set<List<Integer>> generatePowerSet(List<Integer> input) {
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        // if the input is 0, return empty list
        if(input.size() == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        Integer head = input.get(0);
        List<Integer> rest = input.subList(1, input.size()); // can do this because end is exclusive
        Set<List<Integer>> allPossSubSets = generatePowerSet(rest);
        for(List<Integer> subSet : allPossSubSets) {
            List<Integer> subSetAppend = new ArrayList<Integer>();
            subSetAppend.add(head);
            subSetAppend.addAll(subSet);

            // add both of these things
            result.add(subSet);
            result.add(subSetAppend);
        }

        // always add the original list as a subSet
        result.add(input);

        return result;
    }

    private static Integer sumIntegerList(List<Integer> input) {
        Integer result = new Integer(0);
        for(Integer i : input) {
            result += i;
        }
        return result;
    }

    private static Integer concatIntegerList(List<Integer> input) {
        Integer result = 0;
        for(Integer i : input) {
            result = result * 10 + i;
        }
        return result;
    }
}

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int i:L){
            sum += i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenlist = new ArrayList<>();
        for(int i:L){
            if (i%2==0){
                evenlist.add(i);
            }
        }
        return evenlist;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonlst = new ArrayList<>();
        List<Integer> smaller = L1;
        List<Integer> bigger = L2;
        if (L1.size() > L2.size()){
            smaller = L2;
            bigger = L1;
        }
        for (int i:smaller){
            if (bigger.contains(i)){
                commonlst.add(i);
            }
        }
        return commonlst;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int nums = 0;
        for(String i:words){
            for (char j:i.toCharArray()){
                if (j==c){
                    nums++;
                }
            }
        }
        return nums;
    }
}

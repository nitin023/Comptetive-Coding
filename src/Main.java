import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.add(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        a.add(new ArrayList<>(Arrays.asList(7,8,9,10,11,12)));
        a.add(new ArrayList<>(Arrays.asList(13,14,15,16,17,18)));
        a.add(new ArrayList<>(Arrays.asList(20,21,22,23,24,25)));
        a.add(new ArrayList<>(Arrays.asList(26,27,28,29,30,31)));
        a.add(new ArrayList<>(Arrays.asList(32,33,34,35,36,37)));
        a.add(new ArrayList<>(Arrays.asList(38,39,40,41,42,43)));

        System.out.println(Searching.searchMatrix(a,56));
    }
}

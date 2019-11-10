import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(10,20,30,40,50,60,70,80,90));
        System.out.println(Searching.customizedBinarySearch(a,0,a.size()-1,45));
    }
}

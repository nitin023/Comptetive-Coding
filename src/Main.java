import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList resp  = Searching.searchRange(new ArrayList<>(Arrays.asList(1,1,2,2,3)),1);
        System.out.println(resp.get(0) + "-----------" + resp.get(1));
    }
}

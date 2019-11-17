import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<Integer> A  = new ArrayList<>(Arrays.asList( -10, -10, -10));
        int B = -5;
        ArrayList<Integer> response = Hashing.twoSum(A,B);
        //System.out.println(response.get(0) + " : " + response.get(1));
    }
}

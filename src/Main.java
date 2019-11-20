import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> A  = new ArrayList<>(Arrays.asList(9, -8, -10, -7, 7, -8, 2, -7, 4, 7, 0, -3, -4, -5, -1, -4, 5, 8, 1, 9, -2, 5, 10, -5, -7, -1, -6, 4, 1, -5, 3, 8, -4, -10, -9, -3, 10, 0, 7, 9, -8, 10, -9, 7, 8, 0, 6, -6, -7, 6, -4, 2, 0, 10, 1, -2, 5, -2));
        int B = 0;
        ArrayList<ArrayList<Integer>> resp = Hashing.fourSum(A,B);
        for(ArrayList<Integer> k : resp)
        {
            for( int p : k)
            {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }
}

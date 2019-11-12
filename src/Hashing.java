import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hashing {

    /**
     * Given an array A of integers and another non negative integer k,
     * find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j
     * @param A
     * @param B
     * @return
     */
    public static int diffPossible(final List<Integer> A, int B) {
        Map<Integer , Integer> map = new HashMap();
        int i = 0;
        for(int a : A)
        {
            map.put(a,i);
            i++;
        }


        for(i = 0 ; i<A.size()  ; i++)
        {
            if(map.containsKey(A.get(i) + B))
            {
                if(i!=map.get(A.get(i) + B))
                    return 1;
            }
        }
        return 0;
    }
}

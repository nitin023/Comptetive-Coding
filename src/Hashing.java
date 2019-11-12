import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.*;

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

    /**
     * For Given Number N find if its COLORFUL number or not
     * A number can be broken into different contiguous sub-subsequence parts.
     * Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
     * And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different
     * @param A
     * @return
     */
    public  static int colorful(int A) {
        StringBuilder sb = new StringBuilder();
        while (A!=0)
        {
            sb.append(A%10);
            A/=10;
        }

        Set<Integer> prodSet = new HashSet<>();
        String testSub = sb.toString();
        int maxDigitSeq = testSub.length();
        char[] arr = testSub.toCharArray();

        for(int i = 1 ; i<=maxDigitSeq ; i++)
        {
            for(int j = 0 ; j<arr.length ; j++)
            {
                if(j+i >arr.length)
                {
                    break;
                }
                char[] test = testSub.substring(j,j+i).toCharArray();
                int prod = 1;
                for(int k = 0 ; k<test.length ; k++)
                {
                    prod*=(test[k] - '0');
                }
                if(prodSet.contains(prod))
                {
                   return 0;
                }
                prodSet.add(prod);
            }
        }
        return 1;
    }
}

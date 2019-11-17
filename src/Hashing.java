import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.annotation.processing.SupportedSourceVersion;
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

    /**
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     *
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based.
     * Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.
     *
     * If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.
     * @param A
     * @param B
     * @return
     */
    public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {

        if(A ==null || A.isEmpty())
        {
            return new ArrayList<>();
        }

        Map<Integer,List<Integer>> numIndexMap = new LinkedHashMap<>();
        List<Integer> preExistIndex ;
        for(int i = 0 ; i <A.size() ; i++)
        {
            int num = A.get(i);
            if(!numIndexMap.containsKey(num))
            {
                preExistIndex = new ArrayList<>();
                preExistIndex.add(i);
                numIndexMap.put(num, preExistIndex);
            }
            else
            {
                preExistIndex = numIndexMap.get(num);
                preExistIndex.add(i);
                numIndexMap.put(num, preExistIndex);
            }
        }

      int minInd2 = Integer.MAX_VALUE;
      int minInd1 = Integer.MAX_VALUE;

        for(int i = 0 ; i < A.size() ; i++)
        {
            int currentNum = A.get(i);
            int numFind = B - currentNum;
            if(numIndexMap.containsKey(numFind))
            {
                List<Integer> existIndList = numIndexMap.get(numFind);
                int index = -1;
                for(int k : existIndList)
                {
                    if(i < k)
                    {
                        index = k;
                        break;
                    }
                }
                if(index >-1 && minInd2 > index)
                {
                    minInd2 = index;
                    minInd1 = i;
                }
            }
        }
        ArrayList<Integer> responseList = new ArrayList<>();
        responseList.add(minInd1+1);
        responseList.add(minInd2+1);
        return responseList;
    }

    public static List<List<String>> getGroupAnagaram(String [] strs)
    {
        Map<String, List<String>> grpMap = new HashMap<>();
        for(String s : strs)
        {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String sortedStr = String.valueOf(arr);
            List<String>tList = new ArrayList<>();
            if(grpMap.containsKey(sortedStr))
            {
                tList = grpMap.get(sortedStr);
            }
            tList.add(s);
            grpMap.put(sortedStr,tList);
        }
        List<List<String>> responseList = new ArrayList<>();
        for(Map.Entry<String, List<String>> m : grpMap.entrySet())
        {
            responseList.add(m.getValue());
        }
        return responseList;
    }
}

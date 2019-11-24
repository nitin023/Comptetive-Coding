import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;

public class Hashing {

    /**
     * Given an array A of integers and another non negative integer k,
     * find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j
     *
     * @param A
     * @param B
     * @return
     */
    public static int diffPossible(final List<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap();
        int i = 0;
        for (int a : A) {
            map.put(a, i);
            i++;
        }


        for (i = 0; i < A.size(); i++) {
            if (map.containsKey(A.get(i) + B)) {
                if (i != map.get(A.get(i) + B))
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
     *
     * @param A
     * @return
     */
    public static int colorful(int A) {
        StringBuilder sb = new StringBuilder();
        while (A != 0) {
            sb.append(A % 10);
            A /= 10;
        }

        Set<Integer> prodSet = new HashSet<>();
        String testSub = sb.toString();
        int maxDigitSeq = testSub.length();
        char[] arr = testSub.toCharArray();

        for (int i = 1; i <= maxDigitSeq; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j + i > arr.length) {
                    break;
                }
                char[] test = testSub.substring(j, j + i).toCharArray();
                int prod = 1;
                for (int k = 0; k < test.length; k++) {
                    prod *= (test[k] - '0');
                }
                if (prodSet.contains(prod)) {
                    return 0;
                }
                prodSet.add(prod);
            }
        }
        return 1;
    }

    /**
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     * <p>
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based.
     * Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.
     * <p>
     * If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.
     *
     * @param A
     * @param B
     * @return
     */
    public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {

        if (A == null || A.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, List<Integer>> numIndexMap = new LinkedHashMap<>();
        List<Integer> preExistIndex;
        for (int i = 0; i < A.size(); i++) {
            int num = A.get(i);
            if (!numIndexMap.containsKey(num)) {
                preExistIndex = new ArrayList<>();
                preExistIndex.add(i);
                numIndexMap.put(num, preExistIndex);
            } else {
                preExistIndex = numIndexMap.get(num);
                preExistIndex.add(i);
                numIndexMap.put(num, preExistIndex);
            }
        }

        int minInd2 = Integer.MAX_VALUE;
        int minInd1 = Integer.MAX_VALUE;

        for (int i = 0; i < A.size(); i++) {
            int currentNum = A.get(i);
            int numFind = B - currentNum;
            if (numIndexMap.containsKey(numFind)) {
                List<Integer> existIndList = numIndexMap.get(numFind);
                int index = -1;
                for (int k : existIndList) {
                    if (i < k) {
                        index = k;
                        break;
                    }
                }
                if (index > -1 && minInd2 > index) {
                    minInd2 = index;
                    minInd1 = i;
                }
            }
        }
        ArrayList<Integer> responseList = new ArrayList<>();
        responseList.add(minInd1 + 1);
        responseList.add(minInd2 + 1);
        return responseList;
    }

    /**
     * Given an array of strings, return all groups of strings that are anagrams.
     * Represent a group by a list of integers representing the index in the original list. Look at the sample case for clarification.
     *
     * Note: All inputs will be in lower-case
     *
     * Input : cat dog god tca
     * Output : [[1, 4], [2, 3]]
     * @param strs
     * @return
     */
    public static ArrayList<ArrayList<Integer>>getGroupAnagaram(String[] strs) {
        Map<String, List<Integer>> grpMap = new HashMap<>();
        for (int i = 0 ; i< strs.length ; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String sortedStr = String.valueOf(arr);
            List<Integer> tList = new ArrayList<>();
            if (grpMap.containsKey(sortedStr)) {
                tList = grpMap.get(sortedStr);
            }
            tList.add(i+1);
            grpMap.put(sortedStr, tList);
        }
        ArrayList<ArrayList<Integer>> responseList = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> m : grpMap.entrySet()) {
            ArrayList<Integer> array = new ArrayList<>(m.getValue());
            responseList.add(array);
        }
        return responseList;
    }

    /**
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
     * Find all unique quadruplets in the array which gives the sum of target.
     *
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     *
     * Example :
     * Given array S = {1 0 -1 0 -2 2}, and target = 0
     * A solution set is:
     * (-2, -1, 1, 2)
     * (-2,  0, 0, 2)
     * (-1,  0, 0, 1)
     *
     * Also make sure that the solution set is lexicographically sorted.
     * Solution[i] < Solution[j] iff Solution[i][0] < Solution[j][0] OR (Solution[i][0] == Solution[j][0] AND ... Solution[i][k] < Solution[j][k])
     *
     * Approach to solve problem
     * choose (a , b) pair by brute - force approach of selection
     * choose (c,d) using two pointer manipulation technique
     * after a,b selection try c value as next available value
     * now try to find c and d in remaining range consider a = i , b = i + 1 , c = i+2 , d = i+3 .....n
     * for finding c and d compare the target sum with sum obtained  and move c and d selection indexes
     * @param A
     * @param B
     * @return
     */
    public static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> A, int B) {

        if (A == null || A.isEmpty()) {
            return new ArrayList<ArrayList<Integer>>();
        }
        Collections.sort(A);

        Integer[] num = new Integer[A.size()];
        num = A.toArray(num);

        HashSet<ArrayList<Integer>>hashSet = new HashSet<>();
        ArrayList<ArrayList<Integer>> responseList = new ArrayList<>();

        int i , j , low , high , sum;
        for(i = 0 ; i<num.length ; i++)
        {
            for(j = i+1 ; j<num.length ; j++)
            {
                low = j+1;
                high = num.length - 1;

                while (low < high)
                {
                    sum = num[i] + num[j] + num[low] + num[high];
                    if(sum == B)
                    {
                        ArrayList<Integer> tempList = new ArrayList<>(Arrays.asList(num[i] , num[j] , num[low] , num[high]));
                        if(!hashSet.contains(tempList))
                        {
                            hashSet.add(tempList);
                            responseList.add(tempList);
                        }
                        low++;high--;
                    }
                    else if(sum < B)
                    {
                        low++;
                    }
                    else
                    {
                        high--;
                    }
                }
            }
        }
        return responseList;
    }



    /**
     * Longest Substring Without Repeat
     * Asked in:
     * Amazon
     *
     * Given a string,
     * find the length of the longest substring without repeating characters.
     *
     * Example:
     *
     * The longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
     *
     * For "bbbbb" the longest substring is "b", with the length of 1.
     *
     * @param A
     * @return
     */
    public static int lengthOfLongestSubstring(String A){
        if(A==null || A.length()==0)
        {
            return 0;
        }

        int maxCnt = 1;
        for(int i = 0 ; i<A.length() ; i++)
        {
            Set<Character> freqSet = new HashSet<>();
            freqSet.add(A.charAt(i));
            for(int j = i+1 ; j<A.length() ; j++)
            {
                if(!freqSet.contains(A.charAt(j)))
                {
                    freqSet.add(A.charAt(j));
                }
                else
                {
                    break;
                }
            }
            if(maxCnt < freqSet.size())
            {
                maxCnt = freqSet.size();
            }
        }
        return maxCnt;
    }
}

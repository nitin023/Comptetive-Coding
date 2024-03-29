
import org.omg.PortableInterceptor.INACTIVE;

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

    /**
     * Largest Continuous Sequence Zero Sum
     * Asked in:
     * Microsoft
     *
     * Find the largest continuous sequence in a array which sums to zero.
     *
     * Example:
     *
     *
     * Input:  {1 ,2 ,-2 ,4 ,-4}
     * Output: {2 ,-2 ,4 ,-4}
     *
     * NOTE : If there are multiple correct answers, return the sequence which occurs first in the array
     * @param A
     * @return
     */
    public static ArrayList<Integer> lszero(ArrayList<Integer> A) {

        if(A==null || A.size() ==0)
        {
            return new ArrayList<>();
        }

        int i = 0,j = 0,sum=0;

        ArrayList<Integer>sumArray = new ArrayList<>();
        for(i = 0 ; i<A.size() ; i++)
        {
            sum+=A.get(i);
            sumArray.add(sum);
        }

        Map<Integer,List<Integer>> sumIndMap = new LinkedHashMap<>();
        int x = 0 , y = 0;
        for(i = 0 ; i< sumArray.size() ; i++)
        {
            List<Integer>tempList = new ArrayList<>();
            if(!sumIndMap.containsKey(sumArray.get(i)))
            {
                tempList.add(i);
            }
            else
            {
                tempList = sumIndMap.get(sumArray.get(i));
                tempList.add(i);
            }
            sumIndMap.put(sumArray.get(i),tempList);
        }
        ArrayList<Integer>responses = new ArrayList<>();

        for(Map.Entry<Integer,List<Integer>> k : sumIndMap.entrySet())
        {
            if(k.getValue().size()>1)
            {
                x = k.getValue().get(0);
                y = k.getValue().get(k.getValue().size()-1);
            }
        }


        for(i = x; i < y ; i++ )
        {
            responses.add(A.get(i));
        }
        return responses;
    }

    public static int lengthOfLastWord(final String A) {

        if(A.isEmpty())
        {
            return 0;
        }

        int len = 0;
        int ind = 0 , i;

        String [] m = A.split(" ");
        if(m.length ==1)
        {
            len = m[0].length();
        }
        else
        {
            len = m[m.length-1].length();
        }
        return len;
    }

    public static String longestCommonPrefix(ArrayList<String> A) {

        StringBuilder prefixBuilder = new StringBuilder();
        for(int i = 0  ; i <A.size()  ; i++)
        {
            String first = A.get(i);
            for(int j = i+1 ; j < A.size() ; j++)
            {
                String second = A.get(j);

                for(int k = 0;k<first.length() && k<second.length() ; k++)
                {
                    if(first.charAt(k)==second.charAt(k))
                    {
                        prefixBuilder.append(first.charAt(k));
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return prefixBuilder.toString();
    }


    /**
     * Substring Concatenation
     * You are given a string, S, and a list of words, L, that are all of the same length
     *
     * Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
     *
     * Example :
     *
     * S: "barfoothefoobarman"
     * L: ["foo", "bar"]
     * You should return the indices: [0,9].
     * (order does not matter).
     *
     * @param A
     * @param B
     * @return
     */
    public static ArrayList<Integer> findSubstring(String A, final List<String> B) {
        if(A.isEmpty() || A==null || B == null || B.isEmpty())
        {
            return new ArrayList<>();
        }

        //sort list
        Collections.sort(B);
        StringBuilder sb = new StringBuilder();
        for(String k : B)
        {
            sb.append(k);
        }
        int strLen = B.get(0).length();

        int totalLen = strLen * B.size();

        ArrayList<Integer>response = new ArrayList<>();
        List<String>C ;
        for(int i = 0 ; i<A.length() ; i++)
        {
            if(i+totalLen > A.length())
            {
                break;
            }

            String mon = A.substring(i , i+totalLen);
            C = new ArrayList<>();
            for(int j = 0 ; j<mon.length() ; )
            {
                C.add(mon.substring(j,j+strLen));
                j+=strLen;
            }
            Collections.sort(C);
            StringBuilder sk = new StringBuilder();
            for(String n : C)
            {
                sk.append(n);
            }
            if(sk.toString().equals(sb.toString()))
            {
                response.add(i);
            }
        }
        return response;
    }


    public static int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        int maxPoints = 0;
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        if(a.size() != b.size() || a.size() == 0 || a == null || b.size() == 0 || b == null)
            return maxPoints;
        if(a.size() == 1 )
            return 1;
        for(int i = 0; i < a.size(); i++){
            int duplicate = 1;
            int vertical = 0;
            int xi = a.get(i);
            int yi = b.get(i);
            for(int j = i+1; j < a.size(); j++){
                int xj = a.get(j);
                int yj = b.get(j);
                if(xi == xj){
                    if(yi == yj){
                        duplicate++;
                    }else{
                        vertical++;
                    }
                }
                else{
                    double slope = 0.0;
                    if(yj - yi == 0)
                        slope = 0.0;
                    else if(xj - xi == 0)
                        slope = Double.MAX_VALUE;
                    else
                        slope = (double)(yj - yi) / (double)(xj - xi);

                    if(map.containsKey(slope))
                        map.put(slope, map.get(slope) + 1);
                    else
                        map.put(slope, 1);
                }
            }

            for(int sl : map.values())
                if(maxPoints < sl + duplicate)
                    maxPoints = sl + duplicate;

            maxPoints = Math.max(vertical + duplicate, maxPoints);
            map.clear();
        }


        return maxPoints;
    }

    public static String fractionToDecimal(int A, int B) {
        long x = A;
        long y = B;
        double possibleRem = Math.abs(x%y);
        if(x%y==0)
        {
            return Long.toString(x/y);
        }
        else
        {
            double division = (double) x/(double) y;
            String response = division+"";
            int indDec = response.indexOf(".");
            StringBuilder sb = new StringBuilder();
            if(indDec>-1)
            {
                for(int i = indDec+1;i<response.length();)
                {
                    char m = response.charAt(i);
                    int cnt = 0 ;
                    for(int j = i+1 ; j < response.length() ; j++)
                    {
                        char n = response.charAt(j);
                        if(n==m)
                        {
                            cnt++;
                        }
                        else
                        {
                            break;
                        }
                    }
                    if(cnt>0) {
                        sb.append("(").append(m).append(")");
                        i+=cnt;
                    }
                    else {
                        sb.append(m);
                        i++;
                    }
                }
            }
            StringBuilder t = new StringBuilder(response.substring(0,indDec+1));
            t.append(sb);
            return t.toString();
        }
    }


    public static ArrayList<Integer> equal(ArrayList<Integer> A) {
        if(A.isEmpty())
        {
            return new ArrayList<>();
        }

        ArrayList<Integer>responseList = new ArrayList<>();
        Map<Integer,List<String>>sumIndexMap = new LinkedHashMap();
        int i , j ;
        int sum;
        List<String>indexList ;
        for(i = 0 ; i < A.size() ; i++)
        {
            for(j = i+1 ; j < A.size() ; j++)
            {
                sum = A.get(i) + A.get(j);
                if(!sumIndexMap.containsKey(sum))
                {
                    indexList = new ArrayList<>();
                }
                else
                {
                    indexList = new ArrayList<>(sumIndexMap.get(sum));
                }
                indexList.add(i+"_"+j);
                sumIndexMap.put(sum,indexList);
            }
        }

        int a1 , b1 , c1 , d1;
        boolean flag;

       for(Map.Entry<Integer,List<String>> k : sumIndexMap.entrySet()){
           if(k.getValue().size() > 1)
           {
               indexList = k.getValue();
               String ab [] = indexList.get(0).split("_");
               a1 = Integer.parseInt(ab[0]);
               b1 = Integer.parseInt(ab[1]);
               flag = false;
               for(i = 1 ; i<indexList.size() ; i++)
               {
                   ab = indexList.get(i).split("_");
                   c1 = Integer.parseInt(ab[0]);
                   d1 = Integer.parseInt(ab[1]);

                   if(a1 < b1 && c1 <  d1 && a1<c1 && b1!=d1 && b1!=c1)
                   {
                       responseList.addAll(Arrays.asList(a1,b1,c1,d1));
                       flag = true;
                       break;
                   }
               }
               if(flag)
               {
                   break;
               }
           }
       }
        return responseList;

    }

    /**
     * Determine if a Sudoku is valid, according to: http://sudoku.com.au/TheRules.aspx
     *
     * The Sudoku board could be partially filled, where empty cells are filled with the character ‘.’.
     * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
     * @param A
     * @return
     */
    public static int isValidSudoku(final List<String> A) {

        Map<Integer,Set> colSetMap = new HashMap<>();
        Set<Integer>colSetN = new HashSet<>();
        for(String row : A){
            Set<Integer>numbers = new HashSet<>();
            for(int i = 0 ; i < row.length() ; i++)
            {
                char test = row.charAt(i);
                if(test!='.')
                {
                    int value = (test) - '0';
                    if (value >=1 && value<=9)
                    {
                        if(!colSetMap.containsKey(i))
                        {
                            colSetN = new HashSet<>();
                            colSetN.add(value);
                            colSetMap.put(i,colSetN);
                        }
                        else
                        {
                            colSetN = colSetMap.get(i);
                            if(colSetN.contains(value))
                            {
                                return 0;
                            }
                            else
                            {
                                colSetN.add(value);
                            }
                            colSetMap.put(i,colSetN);
                        }
                        if(!numbers.contains(value)){
                            numbers.add(value);
                        }
                        else
                        {
                            return 0;
                        }
                    }
                    else
                    {
                        return 0;
                    }
                }
            }
        }

        for(int k = 0,z=0 ; k<9; z+=3)
        {
            if(z==9)
            {
                z=0;
            }
            String row1 = A.get(k).substring(z,z+3);

            Set<Character>testu = new HashSet<>();
            if(updateSet(row1,testu))
            {
                return 0;
            }


            row1 = A.get(k+1).substring(z,z+3);

            if(updateSet(row1,testu))
            {
                return 0;
            }


            row1 = A.get(k+2).substring(z,z+3);
            if(updateSet(row1,testu))
            {
                return 0;
            }

            if(z==6)
            {
                k+=3;
            }
        }
        return 1;
    }

    private static boolean updateSet(String row , Set<Character>givenSet)
    {
        for(int l = 0 ; l <row.length() ; l++)
        {
            if(row.charAt(l)!='.')
            {
                if(!givenSet.contains(row.charAt(l)))
                {
                    givenSet.add(row.charAt(l));
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }
}

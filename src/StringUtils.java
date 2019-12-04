import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringUtils {

    /**
     * Longest Common Prefix
     *
     * Given the array of strings A,
     * you need to find the longest string S which is the prefix of ALL the strings in the array.
     *
     * Longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix of both S1
     * and S2.
     *
     * For Example, longest common prefix of "abcdefgh" and "abcefgh" is "abc".
     *
     * Input Format
     *
     * The only argument given is an array of strings A.
     * Output Format
     *
     * Return longest common prefix of all strings in A.
     *
     * @param A
     * @return
     */
    public static String longestCommonPrefix(ArrayList<String> A) {
        if(A==null || A.isEmpty())
        {
            return "";
        }

        String prefix = A.get(0);
        for(int i = 1 ; i<A.size() ; i++)
        {
            String test = A.get(i);
            StringBuilder updatedPrefix = new StringBuilder();
            for(int j = 0 ; j < prefix.length() ; j++)
            {
                if(j<test.length()) {
                    if (prefix.charAt(j) == test.charAt(j)) {
                        updatedPrefix.append(prefix.charAt(j));
                    }
                }
                else
                {
                    break;
                }
            }
            prefix = updatedPrefix.toString();
        }
        return prefix;
    }

    /**
     * Amazing Subarrays
     *
     * You are given a string S, and you have to find all the amazing substrings of S.
     *
     * Amazing Substring is one that starts with a vowel (a, e, i, o, u, A, E, I, O, U).
     *
     * Input
     *
     * Only argument given is string S.
     *
     * Return a single integer X mod 10003, here X is number of Amazing Substrings in given string.
     *
     *
     * Input
     *     ABEC
     *
     * Output
     *     6
     *
     * Explanation
     * 	Amazing substrings of given string are :
     * 	1. A
     * 	2. AB
     * 	3. ABE
     * 	4. ABEC
     * 	5. E
     * 	6. EC
     * 	here number of substrings are 6 and 6 % 10003 = 6.
     * @param A
     * @return
     */
    public static int solve(String A) {
        if(A.length()==0)
        {
            return 0;
        }

        //vowel index
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a','e','i','o','u' , 'A' , 'E' , 'I' , 'O' , 'U'));
        char currentChar ;
        int response = 0;
        for(int i = 0 ; i<A.length() ; i++)
        {
            currentChar = A.charAt(i);

            //vowel test
            if(vowelSet.contains(currentChar))
            {
                int currentIndexToEndLength = A.length() - i;
                response=  (response +  currentIndexToEndLength) % 10003;
            }
        }
        return response;
    }
}

import java.util.ArrayList;

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
}

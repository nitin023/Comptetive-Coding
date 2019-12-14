import java.util.*;

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

    /**
     * Palindrome String
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Example
     * A man, a plan, a canal: Panama" is a palindrome.
     *
     * "race a car" is not a palindrome.
     *
     * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
     * @param A
     * @return
     */

    public static int isPalindrome(String A) {
        if(A == null || A.isEmpty())
        {
            return 0;
        }

        A = A.toLowerCase();
        //65 --122
        char item;
        int ascii;
        StringBuilder sb = new StringBuilder();
        Map<Character,Integer> charFreqMap = new HashMap<>();
        for(int i = 0 ; i <A.length() ; i++)
        {
            item = A.charAt(i);
            ascii = (int)item;

            //valid alphabet
            if(ascii>=97 && ascii<=122)
            {
                sb.append(item);
                if(charFreqMap.containsKey(item))
                {
                    charFreqMap.put(item,charFreqMap.get(item)+1);
                }
                else
                {
                    charFreqMap.put(item,1);
                }
            }
            else if(Character.isDigit(item))
            {
                sb.append(item);
                if(charFreqMap.containsKey(item))
                {
                    charFreqMap.put(item,charFreqMap.get(item)+1);
                }
                else
                {
                    charFreqMap.put(item,1);
                }
            }
        }

        int strLen = sb.toString().length();
        int oddFreCnt ;
        if(strLen%2==0)
        {
            for(Map.Entry<Character,Integer> m : charFreqMap.entrySet())
            {
                int freq = m.getValue();
                if(!(freq%2==0))
                {
                   return 0;
                }
            }
            return 1;
        }
        else
        {
            oddFreCnt = 0;
            for(Map.Entry<Character,Integer> m : charFreqMap.entrySet())
            {
                int freq = m.getValue();
                if(!(freq%2==0))
                {
                    oddFreCnt++;
                }
            }
            if(oddFreCnt==1) {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    /**
     * Given a string A representing a roman numeral.
     * Convert A into integer.
     *
     * A is guaranteed to be within the range from 1 to 3999.
     *
     * Return an integer which is the integer verison of roman numeral string.
     *
     * For Example
     *
     * Input 1:
     *     A = "XIV"
     * Output 1:
     *     14
     *
     * Input 2:
     *     A = "XX"
     * Output 2:
     *     20
     *
     *  Algorithms says -
     *  iterate given string char by char and if found current char greater than or equal to next char
     *  then directly add the current char int value to the response
     *
     *  example c to x (decreasing order)
     *
     *  else
     *  add only the diff of chars
     *  example going from
     * @param A
     * @return
     */
    public static int romanToInt(String A) {

        if(A==null || A.isEmpty())
        {
            return 0;
        }

        int i;
        int response = 0;
        char currentChar , nextChar;
        int currVal , nextVal;
        Map<Character,Integer> charToDecimalMap = new HashMap<>();
        charToDecimalMap.put('I',1);
        charToDecimalMap.put('V',5);
        charToDecimalMap.put('X',10);
        charToDecimalMap.put('L',50);
        charToDecimalMap.put('C',100);
        charToDecimalMap.put('D',500);
        charToDecimalMap.put('M',1000);

        for(i = 0 ; i<A.length()  ; i++)
        {
            currentChar = Character.toUpperCase(A.charAt(i));
            currVal = charToDecimalMap.get(currentChar);
            if(i+1 < A.length()) {
                nextChar = Character.toUpperCase(A.charAt(i + 1));
                nextVal = charToDecimalMap.get(nextChar);

                if (currVal >= nextVal) {
                    response += currVal;
                } else {
                    response += (nextVal - currVal);
                    i++;
                }
            }
            else {
                response+=currVal;
            }
        }


        return response;
    }

    /**
     * Integer To Roman
     * Given an integer A, convert it to a roman numeral, and return a string corresponding to its roman numeral version
     * The only argument given is integer A.Return a string denoting roman numeral version of A.
     *
     * 1 <= A <= 3999
     *
     * Input 1:
     *     A = 5
     * Output 1:
     *     "V"
     *
     * Input 2:
     *     A = 14
     * Output 2:
     *     "XIV"
     *
     * @param A
     * @return
     */
    public static String intToRoman(int A)
    {
        Map<Integer,String> charToDecimalMap = new HashMap<>();
        charToDecimalMap.put(1,"I");
        charToDecimalMap.put(2,"II");
        charToDecimalMap.put(3,"III");
        charToDecimalMap.put(4,"IV");
        charToDecimalMap.put(5,"V");
        charToDecimalMap.put(6,"VI");
        charToDecimalMap.put(7,"VII");
        charToDecimalMap.put(8,"VIII");

        charToDecimalMap.put(10,"X");
        charToDecimalMap.put(50,"L");
        charToDecimalMap.put(100,"C");
        charToDecimalMap.put(500,"D");
        charToDecimalMap.put(1000,"M");
         StringBuilder romanStrBuilder = new StringBuilder();
         int dividingFactor = getDividingFactor(A);
         if(dividingFactor==0)
         {
             return "M";
         }

         int quotient;
         while (A>0)
         {
             quotient = A/dividingFactor;
             int val = (quotient * dividingFactor);
             String test = Integer.toString(A);
             if(!test.startsWith("9")) {
                 if(quotient==0 && A<9)
                 {
                     romanStrBuilder.append(charToDecimalMap.get(A));
                     A = 0;
                 }
                 while (quotient != 0) {
                     if(val >= 50 && val<90)
                     {
                         romanStrBuilder.append("L");
                         A-=50;
                         break;
                     }
                     else if(val >= 500 && val<900)
                     {
                         romanStrBuilder.append("D");
                         A-=500;
                         break;
                     }
                     else if(val == 40)
                     {
                         romanStrBuilder.append("XL");
                         A-=val;
                         break;
                     }
                     else if(val == 400)
                     {
                         romanStrBuilder.append("CD");
                         A-=val;
                         break;
                     }
                     romanStrBuilder.append(charToDecimalMap.get(dividingFactor));
                     quotient--;
                     A -= dividingFactor;
                 }
             }
             else
             {
                 if(val == 9 || A==9)
                 {
                     if(A==9)
                     {
                         A = 0;
                     }
                     romanStrBuilder.append("IX");
                 }
                 else if(val==90)
                 {
                     romanStrBuilder.append("XC");
                 }
                 else if(val == 900)
                 {
                     romanStrBuilder.append("CM");
                 }
                 A-=val;
             }
             dividingFactor = getDividingFactor(A);
         }
         return romanStrBuilder.toString();
    }

    private static int getDividingFactor(int A)
    {
        int dividingFactor = 0;
        if(A>0 && A<100)
        {
            dividingFactor = 10;
        }

        else if(A>99 && A<1000)
        {
            dividingFactor = 100;
        }

        else if(A>1000)
        {
            dividingFactor = 1000;
        }
        return dividingFactor;
    }
}

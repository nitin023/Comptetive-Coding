import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] strs = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};

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
    }
}

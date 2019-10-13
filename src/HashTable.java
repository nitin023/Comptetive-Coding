import java.util.*;

public  class HashTable {

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

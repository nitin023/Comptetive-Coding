import java.util.ArrayList;
import java.util.List;

public class Searching {
    private static int first(List<Integer> arr, int low, int high, int val)
    {
        if(low <= high) {
            int mid = (low + high) / 2;
            if ( (mid == 0  || arr.get(mid - 1) < val) &&  val == arr.get(mid)) {
                return mid;
            } else if (arr.get(mid) < val) {
                return first(arr, mid + 1, high, val);
            } else {
                return first(arr, low, mid - 1, val);
            }
        }
        else
        {
            return -1;
        }
    }

    private static int last(List<Integer> arr, int low, int high, int val)
    {
        if(low <= high) {
            int mid = (low + high) / 2;
            if ( (mid ==arr.size()-1  ||  arr.get(mid +1) > val ) &&  val == arr.get(mid)) {
                return mid;
            } else if (arr.get(mid) > val) {
                return last(arr, low, mid - 1, val);
            } else {
                return last(arr,mid+1,high, val);
            }
        }
        else
        {
            return -1;
        }
    }

    public static ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        ArrayList<Integer> resp = new ArrayList();
        resp.add(first(a,0,a.size()-1,b));
        resp.add(last(a,0,a.size()-1,b));
        return resp;
    }

    public static int searchMatrix(ArrayList<ArrayList<Integer>> a, int b)
    {
        int low = 0 , high = a.size() -1;
        while (low <= high)
        {
            int mid = (low+high)/2;
            ArrayList<Integer>rangeList = a.get(mid);
            int rLeft =  rangeList.get(0);
            int rRight =  rangeList.get(rangeList.size() - 1);
            if(rLeft <=b && b<=rRight)
            {
                int index = binarySearch(rangeList,0,rangeList.size()-1,b);
                if(index>=0)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                //mid largest value is < b --- mid+1
                if(b>rRight)
                {
                    low = mid +1;
                }
                else
                {
                    high = mid - 1;
                }
            }
        }
        return 0;
    }

    public static int binarySearch(ArrayList<Integer>array , int low , int high , int key)
    {
        if(low<=high)
        {
            int mid = (low+high)/2;
            if(array.get(mid) == key)
            {
                return mid;
            }
            else if(key <array.get(mid))
            {
                return binarySearch(array , low,mid -1 , key);
            }
            else
            {
                return binarySearch(array ,mid +1 ,high , key);
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * This will return the index where element should be inserted
     * if the element not is found
     * @param array
     * @param low
     * @param high
     * @param key
     * @return
     */
    public static int customizedBinarySearch(ArrayList<Integer>array , int low , int high , int key)
    {
        if(low<=high)
        {
            int mid = (low+high)/2;
            if(array.get(mid) == key)
            {
                return mid;
            }
            else if(key <array.get(mid))
            {
                return customizedBinarySearch(array , low,mid -1 , key);
            }
            else
            {
                return customizedBinarySearch(array ,mid +1 ,high , key);
            }
        }
        else
        {
            if(low>=0) {
                return low;
            }
             if(high >=0)
            {
                return high;
            }
             else
             {
                 return -1;
             }
        }
    }
}

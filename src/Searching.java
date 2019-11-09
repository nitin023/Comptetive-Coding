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
}

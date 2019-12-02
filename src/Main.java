import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String prefix = StringUtils.longestCommonPrefix(new ArrayList<>(Arrays.asList("abab", "ab", "v")));
        System.out.println(prefix);
    }
}


public class Main {
    public static void main(String[] args) {

        int nums [] = {1, 2, 3, 4, 5, 6, 7};
        TreeNode node = TraversalUtil.convertSortedArrayToBST(nums);
        TraversalUtil.getPreOrderTraversal(node);
    }
}

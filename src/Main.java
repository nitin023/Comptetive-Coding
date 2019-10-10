
public class Main {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        System.out.println(TraversalUtil.isValidBST(root));
    }
}

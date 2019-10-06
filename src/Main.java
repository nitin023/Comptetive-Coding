
public class Main {
    public static void main(String[] args) {

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(0);
        node.right = new TreeNode(0);
        node.left.left = new TreeNode(1);
        node.left.left.right = new TreeNode(0);
        node.left.left.right.left = new TreeNode(1);
        node.left.left.right.right = new TreeNode(1);
        node.left.right = new TreeNode(1);
        node.left.right.left = new TreeNode(1);
        node.left.right.right = new TreeNode(1);

        node.right = new TreeNode(0);
        node.right.right = new TreeNode(1);
        node.right.right.left = new TreeNode(1);
        node.right.right.right = new TreeNode(0);
        node.right.right.left.left = new TreeNode(1);
        node.right.right.left.right = new TreeNode(1);

        System.out.println(TraversalUtil.getSumBinaryRootToLeafPath(node));
    }
}


public class Main {
    public static void main(String[] args) {

        TreeNode node = new TreeNode(543);
        node.left = new TreeNode(384);
        node.right = new TreeNode(652);
        node.left.right = new TreeNode(445);
        node.right.right = new TreeNode(699);
        System.out.println(TraversalUtil.getMinimumDifference(node));
    }
}

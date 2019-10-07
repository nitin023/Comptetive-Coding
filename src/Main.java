
public class Main {
    public static void main(String[] args) {

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);

        TraversalUtil.getInvertedTree(node);
        System.out.println(node);
    }
}

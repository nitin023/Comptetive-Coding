import sun.reflect.generics.tree.Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);

        treeNode.left.right.right = new TreeNode(6);

        treeNode.right.right = new TreeNode(7);
        treeNode.right.right.right = new TreeNode(8);
        TraversalUtil.getBoundaryTraversal(treeNode);
    }
}

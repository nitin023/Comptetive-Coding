

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = (new TreeNode(2));
        root.right =  (new TreeNode(3));

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TraversalUtil.getPreOrderTraversal(root);
        System.out.println();

        TreeNode root1 = new TreeNode(1);
        root1.left = (new TreeNode(2));
        root1.right =  (new TreeNode(5));

        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        TraversalUtil.getInOrderTraversal(root1);
        System.out.println();
        TraversalUtil.getPostOrderTraversal(root1);
        System.out.println();

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(8);
        root2.right.right.right = new TreeNode(9);
        root2.left.left.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(7);

        TraversalUtil.levelOrderTraversal(root2);
    }
}

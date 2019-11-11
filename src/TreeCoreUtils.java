public class TreeCoreUtils {

    /**
     * This code calculates height of a binary tree recursively using the condition
     * that height of a tree = max(leftTreeHeight , rightTreeHeight ) + 1
     * space and time complexity is O(n)
     * @param treeNode
     * @return
     */
    public static int getHeightBinaryTree(TreeNode treeNode)
    {
        if(treeNode == null)
        {
            return 0;
        }

        else
        {
            int leftTreeHeight = getHeightBinaryTree(treeNode.getLeft());
            int rightTreeHeight = getHeightBinaryTree(treeNode.getRight());
            return Math.max(leftTreeHeight,rightTreeHeight) + 1;
        }
    }
}

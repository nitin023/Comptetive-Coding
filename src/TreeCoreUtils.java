import java.util.ArrayList;
import java.util.List;

public class TreeCoreUtils {

    private static int cntNodes = 0;
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

    /**
     * Using level order traversal finding count of all nodes using queue iteratively
     * @param treeNode
     * @return
     */
    public static int getNodesCntIteratively(TreeNode treeNode)
    {
        if(treeNode == null)
        {
            return 0;
        }

        //only root node exists with no childs
        else if(treeNode.getLeft() == null && treeNode.getRight() == null)
        {
            return 1;
        }

        List<TreeNode> levelOrderQueue = new ArrayList<>();
        levelOrderQueue.add(treeNode);
        int cntNodes = 0;
        while (!levelOrderQueue.isEmpty())
        {
            TreeNode currentNode = levelOrderQueue.remove(0);
            cntNodes++;
            if(currentNode.getLeft()!=null)
            {
                levelOrderQueue.add(currentNode.getLeft());
            }
            if(currentNode.getRight()!=null)
            {
                levelOrderQueue.add(currentNode.getRight());
            }
        }
        return cntNodes;
    }

    /**
     * code calculates num of nodes in a binary tree using pre-order traversal technique
     * recursively
     * @param treeNode
     * @return
     */
    public static int getNodesCntRecursively(TreeNode treeNode)
    {
        cntNodes = 0;
        return getCntUsingPreOrder(treeNode);
    }

    private static int getCntUsingPreOrder(TreeNode treeNode)
    {
        if(treeNode == null)
        {
            return 0;
        }
        cntNodes++;
        getCntUsingPreOrder(treeNode.getLeft());
        getCntUsingPreOrder(treeNode.getRight());
       return cntNodes;
    }

    /**
     * calculates leaf nodes cnt iteratively
     * @param root
     * @return
     */
    public static int getLeafNodesCntIteratively(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }
        int cntLeafNodes = 0;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove(0);
            if(node.getLeft() == null && node.getRight() == null)
            {
                cntLeafNodes++;
            }

            if(node.getLeft() != null)
            {
                queue.add(node.getLeft());
            }
            if(node.getRight() != null)
            {
                queue.add(node.getRight());
            }
        }
        return cntLeafNodes;
    }

    /**
     * code calculates num of leaf nodes in a binary tree using pre-order traversal technique
     * recursively
     * @param treeNode
     * @return
     */
    public static int getLeafNodesCntRecursively(TreeNode treeNode)
    {
        cntNodes = 0;
        return getLeafCntUsingPreOrder(treeNode);
    }

    private static int getLeafCntUsingPreOrder(TreeNode treeNode)
    {
        if(treeNode == null)
        {
            return 0;
        }
        if(treeNode.getLeft() == null && treeNode.getRight() == null) {
            cntNodes++;
        }
        getLeafCntUsingPreOrder(treeNode.getLeft());
        getLeafCntUsingPreOrder(treeNode.getRight());
        return cntNodes;
    }

    public static TreeNode getLowestCommonAncestorInBinaryTree(TreeNode root , TreeNode node1 , TreeNode node2)
    {
        if(root == null || node1 == null || node2 == null)
        {
            return null;
        }

        if(root == node1 || root == node2){
            return root;
        }

        TreeNode nodeLeft = getLowestCommonAncestorInBinaryTree(root.getLeft() , node1 , node2);
        TreeNode nodeRight = getLowestCommonAncestorInBinaryTree(root.getRight() , node1 , node2);
        if(nodeLeft!=null && nodeRight !=null)
        {
            return root;
        }
        if(nodeLeft == null && nodeRight == null)
        {
            return null;
        }
        return nodeLeft !=null  ? nodeLeft : nodeRight;
    }
}

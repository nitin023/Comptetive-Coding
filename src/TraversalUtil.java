import java.util.ArrayList;
import java.util.List;

public class  TraversalUtil {

    public static void getPreOrderTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        //Visit node
        System.out.print(root.getData() + " ");
        getPreOrderTraversal(root.getLeft());
        getPreOrderTraversal(root.getRight());
    }

    public static void  getInOrderTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        getInOrderTraversal(root.getLeft());
        //Visit node
        System.out.print(root.getData() + " ");
        getInOrderTraversal(root.getRight());
    }

    public static void getPostOrderTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        getPostOrderTraversal(root.getLeft());
        getPostOrderTraversal(root.getRight());
        //Visit node
        System.out.print(root.getData() + " ");
    }

    public static void levelOrderTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(root);

        while (!treeNodeList.isEmpty()){
            System.out.print(treeNodeList.get(0).getData() + " ");

            TreeNode treeNode = treeNodeList.get(0);
            treeNodeList.remove(0);

            if(treeNode.getLeft()!=null)
            {
                treeNodeList.add(treeNode.getLeft());
            }

            if(treeNode.getRight()!=null)
            {
                treeNodeList.add(treeNode.getRight());
            }
        }
    }
}

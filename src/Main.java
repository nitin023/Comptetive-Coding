import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Main {
    public static void main(String[] args) {

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        TreeNode findNode = TraversalUtil.searchBST(node,1);
        if(findNode!=null)
        {
            System.out.println(findNode.getData());
        }
        else
        {
            System.out.println("not found");
        }
    }
}

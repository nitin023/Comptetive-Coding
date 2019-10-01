
public class Main {
    public static void main(String[] args) {

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        if(TraversalUtil.doLeafSequenceExistsInTrees(node,node))
        {
            System.out.println("found");
        }
        else
        {
            System.out.println("not found");
        }
    }
}

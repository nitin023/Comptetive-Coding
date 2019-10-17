
public class Main {
    public static void main(String[] args) {
        int preOrder [] = {8,5,1,7,10,12};
        TreeNode node = TraversalUtil.bstFromPreOrder(preOrder);
        TraversalUtil.getInOrderTraversal(node);
    }
}

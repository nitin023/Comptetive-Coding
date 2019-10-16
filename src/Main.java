import java.util.*;

public class Main {
    public static void main(String[] args) {
        int preOrder [] = {1 ,2,4,5,3,6,8,7};
        int inOrder [] = {4,2,5,1,6,8,3,7};

       TraversalUtil.getInOrderTraversal(TraversalUtil.getBinaryTreeFromInorderAndPreOrderTraversals(preOrder,inOrder));
    }
}

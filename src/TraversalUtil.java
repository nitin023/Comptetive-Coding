import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static void getInorderIterativeTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!=null || !stack.isEmpty())
        {
            if(node!=null)
            {
                stack.push(node);
                node = node.left;
            }
            else
            {
               node = stack.pop();
               System.out.print(node.getData() + " ");
               node = node.right;
            }
        }
    }

    public static void getSumInRange(TreeNode root , int L, int R, AtomicInteger sum)
    {
        if(root == null)
        {
            return ;
        }
        if(L<=root.data && root.data<=R){
            sum.set(sum.intValue() + root.data);
        }

        getSumInRange(root.left,L,R,sum);
        getSumInRange(root.right,L,R,sum);
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        List<Integer> list1 =  getLevelTraversal(t1);
        List<Integer> list2 =  getLevelTraversal(t2);

        int i;
        if(list1.isEmpty() && list2.isEmpty())
        {
            return null;
        }

        if(list1.isEmpty())
        {

            return t2;
        }

        if(list2.isEmpty())
        {

            return t1;
        }

        List<Integer>finalList = new ArrayList();
        if(list1.size()>=list2.size()) {
            for (i = 0; i < list1.size(); i++) {
                if (list1.get(i) != null &&  i < list2.size() && list2.get(i) != null) {
                    finalList.add(list1.get(i) + list2.get(i));
                } else if (list1.get(i) == null || (i < list2.size() &&  list2.get(i) != null)) {
                    if(i < list2.size() &&  list2.get(i) != null) {
                        finalList.add(list2.get(i));
                    }
                    else
                    {
                        finalList.add(null);
                    }
                } else if ((i < list2.size()  && list2.get(i) == null) || list1.get(i) != null) {
                    if(list1.get(i) != null) {
                        finalList.add(list1.get(i));
                    }
                    else {
                        finalList.add(null);
                    }
                } else {
                    finalList.add(null);
                }
            }
        }
        else
        {
            for (i = 0; i < list2.size(); i++) {
                if (i< list1.size() && list1.get(i) != null && list2.get(i) != null) {
                    finalList.add(list1.get(i) + list2.get(i));
                } else if (i< list1.size() && list1.get(i) == null || list2.get(i) != null) {
                    if( list2.get(i) != null) {
                        finalList.add(list2.get(i));
                    }
                    else
                    {
                        finalList.add(null);
                    }
                } else if (list2.get(i) == null || (i< list1.size() && list1.get(i) != null)) {
                    if(i< list1.size() && list1.get(i) != null) {
                        finalList.add(list1.get(i));
                    }
                    else
                    {
                        finalList.add(null);
                    }
                } else {
                    finalList.add(null);
                }
            }
        }

        TreeNode node = insertLevelOrder(finalList,null,0);

        return node;
    }

    public static List<Integer> getLevelTraversal(TreeNode root) {
        List<Integer>levelList = new ArrayList();
        if (root == null) {
            return levelList;
        } else {
            int height = maxDepth(root);
            int maxChild = new Double(Math.pow(2,height)).intValue() - 1;
            List <TreeNode> queue = new ArrayList();
            TreeNode t1 = root;
            queue.add(root);

            while (!queue.isEmpty() && maxChild > 0) {
                t1 = queue.get(0);
                maxChild--;
                levelList.add(t1==null ? null : t1.data);
                // System.out.print(t1==null ? null :t1.val + " ");
                queue.remove(0);
                if(t1==null)
                {
                    continue;
                }
                if (t1.left != null) {
                    queue.add(t1.left);
                }
                else
                {
                    queue.add(null);
                }

                if (t1.right != null) {
                    queue.add(t1.right);
                }
                else
                {
                    queue.add(null);
                }
            }
        }
        return levelList;
    }

    public static int  maxDepth(TreeNode node)
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return(lDepth + 1);
            else return(rDepth + 1);
        }
    }

    public static TreeNode insertLevelOrder(List<Integer>arr, TreeNode root,
                                            int i)
    {
        if (i < arr.size()) {
            TreeNode temp = arr.get(i)!=null ?  new TreeNode(arr.get(i)) : null;
            root = temp;
            if(temp == null)
                return null;
            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    public static TreeNode searchBST(TreeNode root, int searchKey) {
        if(root == null)
        {
            return null;
        }

        TreeNode node = root;

        while (node!=null)
        {
            if(node.getData() == searchKey)
            {
                break;
            }
            else if(searchKey <= node.getData())
            {
                node = node.getLeft();
            }
            else
            {
                node = node.getRight();
            }
        }
        return node;
    }

    public static void getCustomInOrderTraversal(TreeNode root ,  List<Integer> leafSeq)
    {
        if(root==null)
        {
            return;
        }
        getCustomInOrderTraversal(root.getLeft(),leafSeq);
        if(root.getLeft()==null &&  root.getRight()==null)
        {
         leafSeq.add(root.data);
        }
        getCustomInOrderTraversal(root.getRight(),leafSeq);
    }

    public  static List<Integer> getLeafSequence(TreeNode node)
    {
        List<Integer> leafSeq = new ArrayList<>();
        if(node==null)
        {
            return leafSeq;
        }
        getCustomInOrderTraversal(node,leafSeq);
        return leafSeq;
    }

    public static boolean doLeafSequenceExistsInTrees(TreeNode root1 , TreeNode root2)
    {
        boolean response = false;
        if(root1==null || root2==null)
        {
            return false;
        }
        List<Integer> t1LeafSeq =  getLeafSequence(root1);
        List<Integer> t2LeafSeq =  getLeafSequence(root2);

        if(t1LeafSeq.size()!=t2LeafSeq.size())
        {
            return false;
        }

        for(int i = 0 ; i<t1LeafSeq.size() ; i++)
        {
            if(!t1LeafSeq.get(i).equals(t2LeafSeq.get(i)))
            {
                return false;
            }
            else
            {
                response = true;
            }
        }
        return response;
    }

    public static int getSumBinaryRootToLeafPath(TreeNode treeNode)
    {
        //only root node contains key value and has no leaf nodes
        if(treeNode ==null)
        {
            return 0;
        }
        //insert till u get all left child
        int[] path =  new int[30];
        AtomicInteger sum = new AtomicInteger(0);
        getPathSum(sum,treeNode,path,0);
        return sum.get();
    }

    private static void getPathSum(AtomicInteger sum,TreeNode treeNode,int[] pathList,int pathLength)
    {
        if(treeNode == null)
        {
            return ;
        }
        pathList[pathLength] = treeNode.getData();
        pathLength++;
        if(treeNode.getLeft() == null && treeNode.getRight() ==null)
        {
          sum.set(sum.get() + getDecimalValue(pathList,pathLength));
        }
        getPathSum(sum,treeNode.getLeft(),pathList,pathLength);
        getPathSum(sum,treeNode.getRight(),pathList,pathLength);
    }

    private static int getDecimalValue(int []pathList , int pathLen)
    {
        int sum = 0;
        int size  = pathLen - 1;
        for(int i = 0 ; i<pathLen ; i++)
        {
            sum+=Math.pow(2,size--) * pathList[i];
        }
        return sum;
    }

    public static void getInvertedTree(TreeNode node)
    {
        if(node==null)
        {
            return;
        }

        TreeNode leftNode = node.getLeft();
        TreeNode rightNode = node.getRight();

        node.setRight(leftNode);
        node.setLeft(rightNode);

        getInvertedTree(node.getLeft());
        getInvertedTree(node.getRight());
    }

    public static int getMinimumDifference(TreeNode root)
    {
        if(root==null || (root.left==null && root.right==null))
        {
            return 0;
        }
        List<Integer>arr = new ArrayList<>();
        getArrInorderTraversal(arr,root);
        int diff = Integer.MAX_VALUE;
        for(int i = 0 ; i<arr.size() - 1 ; i++)
        {
            if(arr.get(i+1) - arr.get(i)<diff)
            {
                diff = arr.get(i+1) - arr.get(i);
            }
        }
        return diff;
    }

    private static void getArrInorderTraversal(List<Integer>arr,TreeNode node)
    {
        if(node == null)
        {
            return;
        }
        getArrInorderTraversal(arr,node.left);
        arr.add(node.data);
        getArrInorderTraversal(arr,node.right);
    }

    public static TreeNode convertSortedArrayToBST(int[] nums)
    {
        if(nums.length ==0)
        {
            return null;
        }

        return getBST(nums,0,   nums.length-1);
    }

    private static TreeNode getBST(int[] arr, int start , int end)
    {
        if(start > end)
        {
            return null;
        }

        int mid = (start + end)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = getBST(arr,start,mid-1);
        node.right = getBST(arr,mid+1 , end);
        return node;
    }
}

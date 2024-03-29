import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class  TraversalUtil {

    public static int preIndex = 0 ;

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

    public static void getPreOrderIterativeTraversal(TreeNode node)
    {
        if(node == null)
        {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (node!=null || !stack.isEmpty())
        {
            if(node!=null)
            {
                System.out.print(node.getData() + " ");
                stack.push(node);
                node = node.left;
            }
            else
            {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    public static void getPostOrderIterativeTraversal(TreeNode node)
    {
        if(node == null)
        {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        while (node!=null || !stack1.isEmpty())
        {
            if(node!=null)
            {
                stack1.push(node);
                stack2.push(node);
                node = node.right;
            }
            else
            {
                node = stack1.pop();
                node = node.left;
            }
        }
     while (!stack2.isEmpty())
     {
         node = stack2.pop();
         System.out.print(node.getData() + " ");
     }
    }

    public static int[] findMostlyOccurringElements(TreeNode treeNode)
    {
        int[] arr = {};
        if(treeNode == null)
        {
            return arr;
        }
        List<Integer>elemList = new ArrayList<>();
        getArrInorderTraversal(elemList,treeNode);
        Map<Integer,Integer>freqMap = new HashMap<>();
        final int[] maxOccurringElemFreq = {0};
        elemList.forEach(e->{
            int freq = 1;
            if(freqMap.containsKey(e))
            {
                freq+=freqMap.get(e);
            }
            freqMap.put(e,freq);
            if(maxOccurringElemFreq[0] <freq)
            {
                maxOccurringElemFreq[0] = freq;
            }
        });
        Map<Integer,List<Integer>> freqToElemMap = new HashMap<>();
        freqMap.forEach((k,v)->{
            if(maxOccurringElemFreq[0]==v) {
                List<Integer> tempElemList = new ArrayList<>();
                if (freqToElemMap.containsKey(v)) {
                    tempElemList = freqToElemMap.get(v);
                }
                tempElemList.add(k);
                freqToElemMap.put(v, tempElemList);
            }
        });

        List<Integer> mostlyOccurringElem = freqToElemMap.get(maxOccurringElemFreq[0]);
        arr = new int[mostlyOccurringElem.size()];
        for(int i = 0 ; i<mostlyOccurringElem.size() ; i++)
        {
            arr[i] = mostlyOccurringElem.get(i);
        }
        return arr;
    }

    public static boolean isValidBST(TreeNode root){
        if(root==null)
        {
            return true;
        }
        List<Integer>elemList = new ArrayList<>();
        getArrInorderTraversal(elemList,root);
        boolean _isTreeValid = true;

        for(int i = 0 ; i<elemList.size() -1 ; i++)
        {
            if(elemList.get(i) >= elemList.get(i+1))
            {
                _isTreeValid = false;
                break;
            }
        }
        return _isTreeValid;
    }

    public static void getVerticalOrderTraversal(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        Map<Integer,List<TreeNode>> sortedDistanceMap = new TreeMap<>();
        TreeNodeDistance rootNodeDistConfig = new TreeNodeDistance(0,root);
        List<TreeNodeDistance>queue = new ArrayList<>();
        queue.add(rootNodeDistConfig);

        while (!queue.isEmpty())
        {
            TreeNodeDistance nodeDistance = queue.get(0);
            queue.remove(0);
            TreeNode treeNode = nodeDistance.getTreeNode();
            List<TreeNode>treeNodeList = new ArrayList<>();
            if(sortedDistanceMap.containsKey(nodeDistance.getDistance()))
            {
                treeNodeList = sortedDistanceMap.get(nodeDistance.getDistance());
            }
            treeNodeList.add(treeNode);
            sortedDistanceMap.put(nodeDistance.getDistance(),treeNodeList);
            if(treeNode.getLeft()!=null)
            {
                queue.add(new TreeNodeDistance(nodeDistance.getDistance() - 1,treeNode.getLeft()));
            }
            if(treeNode.getRight()!=null)
            {
                queue.add(new TreeNodeDistance(nodeDistance.getDistance() + 1,treeNode.getRight()));
            }
        }

        for(int distanceKey :sortedDistanceMap.keySet())
        {
            for(TreeNode treeNode : sortedDistanceMap.get(distanceKey)) {
                System.out.print(treeNode.getData() + " , ");
            }
            System.out.println();
        }
    }

    public static void getTopViewBinaryTree(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        List<TreeNodeDistance> levelQueue = new ArrayList<>();
        levelQueue.add(new TreeNodeDistance(0,root));

        Map<Integer,Integer> sortedDistanceMap = new TreeMap<>();
        while (!levelQueue.isEmpty())
        {
            //pop the queue
            TreeNodeDistance treeNodeDistance = levelQueue.remove(0);
            TreeNode treeNode = treeNodeDistance.getTreeNode();
            sortedDistanceMap.putIfAbsent(treeNodeDistance.getDistance(),treeNode.getData());

            if(treeNode.getLeft()!=null)
            {
                levelQueue.add(new TreeNodeDistance(treeNodeDistance.getDistance()-1,treeNode.getLeft()));
            }

            if(treeNode.getRight()!=null)
            {
                levelQueue.add(new TreeNodeDistance(treeNodeDistance.getDistance()+1,treeNode.getRight()));
            }
        }

        sortedDistanceMap.entrySet().forEach(e->{
            System.out.print(e.getValue() + " ");
        });
    }

    public static TreeNode getBinaryTreeFromInorderAndPreOrderTraversals(int[] preorder, int[] inorder)
    {
        if(preorder.length == 0 || inorder.length ==0)
        {
            return null;
        }
        Map<Integer,Integer>valIndMap = new HashMap<>();
        for(int i = 0; i<inorder.length ;i++)
        {
         valIndMap.put(inorder[i],i);
        }
        preIndex = 0;
        return buildTreeFromInAndPreOrder(preorder,inorder,valIndMap,0,inorder.length-1);
    }

    private static TreeNode buildTreeFromInAndPreOrder(int[] preorder, int[] inorder,Map<Integer,Integer>valIndMap,int inStr , int inEnd)
    {
     if(inStr > inEnd)
     {
         return  null;
     }
     TreeNode node = new TreeNode(preorder[preIndex++]);
     if(inStr == inEnd)
     {
         return node;
     }
     int index = valIndMap.get(node.data);
      node.left = buildTreeFromInAndPreOrder(preorder , inorder , valIndMap , inStr , index - 1);
      node.right = buildTreeFromInAndPreOrder(preorder , inorder , valIndMap , index+1 , inEnd);
      return node;
    }

    public static TreeNode bstFromPreOrder(int[] preorder)
    {
        if(preorder.length==0)
        {
            return null;
        }

        TreeNode rootNode = new TreeNode(preorder[0]);
        int value ;
        for(int i = 1 ; i<preorder.length ; i++)
        {
            value = preorder[i];
            rootNode = insertIntoBST(rootNode,value);
        }
        return rootNode;
    }
    private static TreeNode insertIntoBST(TreeNode node , int val)
    {
        if(node == null)
        {
            return new TreeNode(val);
        }
        if(val <node.data)
        {
            node.left = insertIntoBST(node.left,val);
        }
        else
        {
            node.right = insertIntoBST(node.right,val);
        }
        return node;
    }

    public static void getLeftView(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        List<TreeNode> levelQueue = new ArrayList<>();
        levelQueue.add(root);
        int queueCnt , cnt;
        while (!levelQueue.isEmpty())
        {
            cnt = queueCnt = levelQueue.size();
            while (cnt>0) {
                TreeNode treeNode =  levelQueue.remove(0);
                if (cnt == queueCnt) {
                    System.out.print(treeNode.getData() + " ");
                }
                if (treeNode.getLeft() != null) {
                    levelQueue.add(treeNode.getLeft());
                }
                if (treeNode.getRight() != null) {
                    levelQueue.add(treeNode.getRight());
                }
                cnt--;
            }
        }
    }
    public static void getRightView(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        List<TreeNode> levelQueue = new ArrayList<>();
        levelQueue.add(root);
        int  cnt;
        while (!levelQueue.isEmpty())
        {
            cnt  = levelQueue.size();
            while (cnt>0) {
                TreeNode treeNode =  levelQueue.remove(0);
                if (cnt == 1) {
                    System.out.print(treeNode.getData() + " ");
                }
                if (treeNode.getLeft() != null) {
                    levelQueue.add(treeNode.getLeft());
                }
                if (treeNode.getRight() != null) {
                    levelQueue.add(treeNode.getRight());
                }
                cnt--;
            }
        }
    }
    public static void getDiagonalView(TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        List<TreeNodeDistance> queue = new ArrayList<>();
        queue.add(new TreeNodeDistance(0,root));
        Map<Integer,List<Integer>> distanceMap = new TreeMap<>();

        while (!queue.isEmpty())
        {
            TreeNodeDistance treeNodeDistance = queue.remove(0);
            TreeNode treeNode = treeNodeDistance.getTreeNode();
            if(!distanceMap.containsKey(treeNodeDistance.getDistance()))
            {
                distanceMap.put(treeNodeDistance.getDistance(),new ArrayList<>(Arrays.asList(treeNode.getData())));
            }
            else
            {
                List<Integer>distanceList = distanceMap.get(treeNodeDistance.getDistance());
                distanceList.add(treeNode.getData());
                distanceMap.put(treeNodeDistance.getDistance(),distanceList);
            }

            if(treeNode.getLeft()!=null)
            {
                queue.add(new TreeNodeDistance(treeNodeDistance.getDistance()+1,treeNode.getLeft()));
            }
            if(treeNode.getRight()!=null)
            {
                queue.add(new TreeNodeDistance(treeNodeDistance.getDistance(),treeNode.getRight()));
            }
        }

        distanceMap.forEach((k,v)->{
            v.forEach(distance->{
                System.out.print(distance + " ");
            });
            System.out.println();
        });
    }
    public static void getBottomView(TreeNode root)
    {
        if(root==null)
        {
            return;
        }

        Map<Integer,List<TreeNode>> sortedDistanceMap = new TreeMap<>();
        TreeNodeDistance rootNodeDistConfig = new TreeNodeDistance(0,root);
        List<TreeNodeDistance>queue = new ArrayList<>();
        queue.add(rootNodeDistConfig);

        while (!queue.isEmpty())
        {
            TreeNodeDistance nodeDistance = queue.get(0);
            queue.remove(0);
            TreeNode treeNode = nodeDistance.getTreeNode();
            List<TreeNode>treeNodeList = new ArrayList<>();
            if(sortedDistanceMap.containsKey(nodeDistance.getDistance()))
            {
                treeNodeList = sortedDistanceMap.get(nodeDistance.getDistance());
            }
            treeNodeList.add(treeNode);
            sortedDistanceMap.put(nodeDistance.getDistance(),treeNodeList);
            if(treeNode.getLeft()!=null)
            {
                queue.add(new TreeNodeDistance(nodeDistance.getDistance() - 1,treeNode.getLeft()));
            }
            if(treeNode.getRight()!=null)
            {
                queue.add(new TreeNodeDistance(nodeDistance.getDistance() + 1,treeNode.getRight()));
            }
        }

        for(int distanceKey :sortedDistanceMap.keySet())
        {
            List<TreeNode>treeNodeList = sortedDistanceMap.get(distanceKey);
            System.out.print(treeNodeList.get(treeNodeList.size() - 1).getData() + " ");
        }
    }

    /**
     * This problem calculates boundary of a tree by splitting tree into three parts
     * left , leaf and right boundary
     * we move fro top to left direction for finding left boundary
     * and then from left to right direction to find all the leaf nodes
     * and finally from bottom to top to find right boundary
     * @param treeNode
     */
    public static void getBoundaryTraversal(TreeNode treeNode)
    {
        if(treeNode == null)
        {
            return;
        }

        TreeNode node = treeNode;
        ArrayList<Integer>list = new ArrayList<>();

        //This segment gets left boundary
        while (node!=null)
        {
            list.add(node.getData());
            node = node.getLeft();
        }

        //This segment gets leaf boundary
        list = getLeafNodes(list,treeNode);

        node = treeNode;
        ArrayList<Integer>rightList = new ArrayList<>();

        //This segment gets right boundary
        while (node!=null)
        {
            rightList.add(node.getData());
            node = node.getRight();
        }

        //merging three boundary result and ensuring all the left + leaf + right boundary gets merged in clockwise direction
        Set<Integer>printingSet = new HashSet<>();
        for(int k : list)
        {
            if(!printingSet.contains(k))
            {
                System.out.print(k + " ");
                printingSet.add(k);
            }
        }

        for(int k = rightList.size() - 1 ; k>=0 ; k--)
        {
            if(!printingSet.contains(rightList.get(k)))
            {
                System.out.print(rightList.get(k) + " ");
                printingSet.add(rightList.get(k));
            }
        }
    }

    private static ArrayList<Integer> getLeafNodes(ArrayList<Integer> list,TreeNode root)
    {
        if(root == null)
        {
            return list;
        }

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove(0);
            if(node.getLeft() == null && node.getRight() == null)
            {
                list.add(node.getData());
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
        return list;
    }

    /**
     * Mirror of a binary tree means that on observing tree left child become right child while
     * right child becomes left child
     *
     * Simply done by using post-order traversal ( LRV) and instead of printing the node we swap the right and left child
     *
     * Also the inorder traversal of original tree and mirror tree are reversed of one another
     * Inorder of original tree : 1 , 2 , 3 , 4 , 5
     * Inorder of mirrored tree : 5 , 4 , 3, 2 , 1
     * @param root
     * @return
     */
    public static TreeNode getMirrorBinaryTree(TreeNode root)
    {
        if(root == null)
        {
            return null;
        }

        getMirrorBinaryTree(root.getLeft());
        getMirrorBinaryTree(root.getRight());

        TreeNode rootLeft = root.getLeft();
        TreeNode rootRight = root.getRight();
        root.setLeft(rootRight);
        root.setRight(rootLeft);
        return root;
    }

    /**
     * get a level order traversed list including all null values for each level from left to right
     * considered max elements for a level to determine level change example 3 level can have max 8 elements
     * using this level change is detected
     *
     * Spiral order says move from left to right for odd level and from right to left in even level
     * @param root
     */
    public static void getSpiralOrderTraversal(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        List<Integer> levelOrderTraversalList = getLevelTraversal(root);

        int levelNum = 0;
        int levelMaxChild = 0;
        for(int i = 0; i< levelOrderTraversalList.size() ; )
        {
            levelMaxChild = (int)Math.pow(2,levelNum++);
            if(levelNum%2!=0) {
                for (int j = 0; j < levelMaxChild; j++) {
                    if(levelOrderTraversalList.get(i)!=null) {
                        System.out.print(levelOrderTraversalList.get(i) + " ");
                        i++;
                    }
                    else
                    {
                        i++;
                    }
                }
            }
            else
            {
                List<Integer>tempList = new ArrayList<>();
                for (int j = 0; j < levelMaxChild; j++) {
                    if(levelOrderTraversalList.get(i)!=null) {
                        tempList.add(levelOrderTraversalList.get(i));
                        i++;
                    }
                    else
                    {
                        i++;
                    }
                }
                for(int j = tempList.size() - 1 ; j>=0 ; j--)
                {
                    System.out.print(tempList.get(j) + " ");
                }
            }
            System.out.println();
        }
    }
}

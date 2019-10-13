public class TreeNodeDistance {
    private int distance;
    private TreeNode treeNode;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public TreeNodeDistance(){}

    public TreeNodeDistance(int distance, TreeNode treeNode) {
        this.distance = distance;
        this.treeNode = treeNode;
    }
}

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        // Leaf node
        if (root.left == null && root.right == null) return 1;

        // If one child is missing, go through the other child
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
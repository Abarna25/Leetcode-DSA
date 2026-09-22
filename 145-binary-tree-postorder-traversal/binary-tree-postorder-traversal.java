class Solution {

    List<Integer> ans = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {

        if (node == null) return;

        dfs(node.left);
        dfs(node.right);

        ans.add(node.val);
    }
}
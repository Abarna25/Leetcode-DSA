class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum, new ArrayList<>());

        return ans;
    }

    private void dfs(TreeNode node, int target, List<Integer> path) {

        if (node == null)
            return;

        path.add(node.val);

        // Leaf node
        if (node.left == null && node.right == null) {

            if (target == node.val) {
                ans.add(new ArrayList<>(path));
            }

        } else {

            dfs(node.left, target - node.val, path);
            dfs(node.right, target - node.val, path);
        }

        // Backtrack
        path.remove(path.size() - 1);
    }
}
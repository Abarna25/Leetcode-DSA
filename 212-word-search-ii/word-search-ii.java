class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }

            node = node.child[idx];
        }

        node.word = word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words) {
            insert(word);
        }

        List<String> ans = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, ans);
            }
        }

        return ans;
    }

    private void dfs(char[][] board, int r, int c,
                     TrieNode node, List<String> ans) {

        if (r < 0 || c < 0 ||
            r >= board.length || c >= board[0].length ||
            board[r][c] == '#') {
            return;
        }

        char ch = board[r][c];
        TrieNode next = node.child[ch - 'a'];

        if (next == null) {
            return;
        }

        if (next.word != null) {
            ans.add(next.word);
            next.word = null; // avoid duplicates
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, next, ans);
        dfs(board, r - 1, c, next, ans);
        dfs(board, r, c + 1, next, ans);
        dfs(board, r, c - 1, next, ans);

        board[r][c] = ch;
    }
}
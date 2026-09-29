class Solution {
    private int m, n;
    private char[][] g;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        g = grid;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if ((len & 1) == 1) return false;

        if (g[0][0] == ')' || g[m - 1][n - 1] == '(') return false;

        memo = new Boolean[m][n][len + 1];

        return dfs(0, 0, 1);
    }

    private boolean dfs(int r, int c, int balance) {
        if (balance < 0) return false;

        int remaining = (m - 1 - r) + (n - 1 - c);

        // Not enough cells left to close all opens
        if (balance > remaining + 1) return false;

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean ans = false;

        if (r + 1 < m) {
            int nb = balance + (g[r + 1][c] == '(' ? 1 : -1);
            ans |= dfs(r + 1, c, nb);
        }

        if (!ans && c + 1 < n) {
            int nb = balance + (g[r][c + 1] == '(' ? 1 : -1);
            ans |= dfs(r, c + 1, nb);
        }

        return memo[r][c][balance] = ans;
    }
}
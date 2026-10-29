import java.util.*;
class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] tin = new int[n], tout = new int[n], parent = new int[n];
        int[] depth = new int[n], iter = new int[n], stack = new int[n];
        Arrays.fill(parent, -1);
        int timer = 0, top = 0;
        stack[0] = 0;
        while (top >= 0) {
            int u = stack[top];
            if (iter[u] == 0) tin[u] = timer++;
            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);
                if (v == parent[u]) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
        int LOG = 17;
        int[][] up = new int[LOG][n];
        for (int i = 0; i < n; i++) up[0][i] = parent[i] == -1 ? i : parent[i];
        for (int j = 1; j < LOG; j++)
            for (int i = 0; i < n; i++)
                up[j][i] = up[j - 1][up[j - 1][i]];
        int[] bit = new int[n + 1], mask = new int[n];
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            mask[i] = 1 << (chars[i] - 'a');
            add(bit, tin[i], mask[i]);
            add(bit, tout[i] + 1, mask[i]);
        }
        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                add(bit, tin[u], mask[u]);
                add(bit, tout[u] + 1, mask[u]);
                chars[u] = c;
                mask[u] = 1 << (c - 'a');
                add(bit, tin[u], mask[u]);
                add(bit, tout[u] + 1, mask[u]);
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v, depth, up);
                int path = prefix(bit, tin[u]) ^ prefix(bit, tin[v])
                         ^ mask[l];
                ans.add((path & (path - 1)) == 0);
            }
        }
        return ans;
    }
    private void add(int[] bit, int i, int val) {
        for (i++; i < bit.length; i += i & -i) bit[i] ^= val;
    }
    private int prefix(int[] bit, int i) {
        int res = 0;
        for (i++; i > 0; i -= i & -i) res ^= bit[i];
        return res;
    }
    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < up.length; j++)
            if ((diff & (1 << j)) != 0) u = up[j][u];
        if (u == v) return u;
        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return up[0][u];
    }
}
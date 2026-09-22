class Solution {

    static class Node {
        int prod;
        long[] cnt;

        Node(int k) {
            cnt = new long[k];
        }
    }

    int k;
    Node[] seg;

    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node res = new Node(k);

        res.prod = (a.prod * b.prod) % k;

        for (int i = 0; i < k; i++) {
            res.cnt[i] += a.cnt[i];
        }

        for (int t = 0; t < k; t++) {
            int nr = (a.prod * t) % k;
            res.cnt[nr] += b.cnt[t];
        }

        return res;
    }

    private void build(int idx, int l, int r, int[] nums) {
        if (l == r) {
            seg[idx] = new Node(k);

            int val = nums[l] % k;
            seg[idx].prod = val;
            seg[idx].cnt[val] = 1;

            return;
        }

        int mid = (l + r) >> 1;

        build(idx * 2, l, mid, nums);
        build(idx * 2 + 1, mid + 1, r, nums);

        seg[idx] = merge(seg[idx * 2], seg[idx * 2 + 1]);
    }

    private void update(int idx, int l, int r, int pos, int value) {
        if (l == r) {
            seg[idx] = new Node(k);

            int val = value % k;
            seg[idx].prod = val;
            seg[idx].cnt[val] = 1;

            return;
        }

        int mid = (l + r) >> 1;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, value);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, value);
        }

        seg[idx] = merge(seg[idx * 2], seg[idx * 2 + 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (r < ql || l > qr) return null;

        if (ql <= l && r <= qr) return seg[idx];

        int mid = (l + r) >> 1;

        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        int n = nums.length;

        this.k = k;
        seg = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = (int) res.cnt[x];
        }

        return ans;
    }
}
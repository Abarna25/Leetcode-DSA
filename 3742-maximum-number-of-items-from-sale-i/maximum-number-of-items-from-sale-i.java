import java.util.*;
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = 1;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    value[i]++;
                }
            }
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) ->
            Integer.compare(items[b][1], items[a][1])
        );
        int[] dpHigher = new int[budget + 1];
        Arrays.fill(dpHigher, -1000000);
        dpHigher[0] = 0;
        int ans = 0;
        int i = 0;
        while (i < n) {
            int price = items[order[i]][1];
            int[] dp = new int[budget + 1];
            Arrays.fill(dp, -1000000);
            int j = i;
            while (j < n && items[order[j]][1] == price) {
                int idx = order[j];
                int cost = items[idx][1];
                int val = value[idx];
                for (int b = budget; b >= cost; b--) {
                    dp[b] = Math.max(dp[b],
                        dpHigher[b - cost] + val);
                    dp[b] = Math.max(dp[b],
                        dp[b - cost] + val);
                }
                j++;
            }
            for (int b = 0; b <= budget; b++) {
                if (dp[b] >= 0) {
                    int extra = (budget - b) / price;
                    ans = Math.max(ans, dp[b] + extra);
                }
            }
            for (int k = i; k < j; k++) {
                int idx = order[k];
                int cost = items[idx][1];
                int val = value[idx];
                for (int b = budget; b >= cost; b--) {
                    dpHigher[b] = Math.max(
                        dpHigher[b],
                        dpHigher[b - cost] + val
                    );
                }
            }
            i = j;
        }
        return ans;
    }
}
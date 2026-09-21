class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int cur = num % k;

            long[] ndp = new long[k];

            // Start a new subarray
            ndp[cur]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nr = (r * cur) % k;
                    ndp[nr] += dp[r];
                }
            }

            // Add all subarrays ending here to answer
            for (int r = 0; r < k; r++) {
                ans[r] += ndp[r];
            }

            dp = ndp;
        }

        return ans;
    }
}
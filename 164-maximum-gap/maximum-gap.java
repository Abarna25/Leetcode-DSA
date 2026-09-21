class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) return 0;

        int bucketSize = (int) Math.ceil((double) (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int idx = (num - min) / bucketSize;

            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        int ans = 0;
        int prevMax = bucketMax[0];

        for (int i = 1; i < bucketCount; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) continue;

            ans = Math.max(ans, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return ans;
    }
}
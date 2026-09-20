class Solution {
    public int numDecodings(String s) {

        if (s.charAt(0) == '0')
            return 0;

        int prev2 = 1; // dp[0]
        int prev1 = 1; // dp[1]

        for (int i = 2; i <= s.length(); i++) {

            int curr = 0;

            if (s.charAt(i - 1) != '0')
                curr += prev1;

            int two =
                (s.charAt(i - 2) - '0') * 10 +
                (s.charAt(i - 1) - '0');

            if (two >= 10 && two <= 26)
                curr += prev2;

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            next[i] = low;
        }

        long[][] dp = new long[n + 1][5];

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[][] best = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                best[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                long skip = dp[i + 1][k];
                long take = arr[i][2] + dp[next[i]][k - 1];

                if (take > skip) {
                    dp[i][k] = take;

                    best[i][k] = new ArrayList<>(
                        best[next[i]][k - 1]
                    );

                    best[i][k].add(arr[i][3]);

                } else if (skip > take) {
                    dp[i][k] = skip;

                    best[i][k] = new ArrayList<>(
                        best[i + 1][k]
                    );

                } else {
                    dp[i][k] = skip;

                    ArrayList<Integer> a =
                        new ArrayList<>(best[i + 1][k]);

                    ArrayList<Integer> b =
                        new ArrayList<>(best[next[i]][k - 1]);

                    b.add(arr[i][3]);

                    Collections.sort(a);
                    Collections.sort(b);

                    if (compare(a, b) <= 0) {
                        best[i][k] = a;
                    } else {
                        best[i][k] = b;
                    }
                }
            }
        }

        ArrayList<Integer> answer = best[0][4];

        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}
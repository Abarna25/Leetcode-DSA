import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!st.isEmpty() && heights[st.peek()] > currHeight) {
                int h = heights[st.pop()];

                int right = i;
                int left = st.isEmpty() ? -1 : st.peek();

                long width = right - left - 1;
                maxArea = Math.max(maxArea, (long) h * width);
            }

            st.push(i);
        }

        return (int) maxArea;
    }
}
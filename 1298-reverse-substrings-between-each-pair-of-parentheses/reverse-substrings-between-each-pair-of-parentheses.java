class Solution {
    public String reverseParentheses(String s) {
        StringBuilder curr = new StringBuilder();
        java.util.Stack<StringBuilder> stack = new java.util.Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(curr);
                curr = new StringBuilder();
            } else if (ch == ')') {
                curr.reverse();
                StringBuilder prev = stack.pop();
                prev.append(curr);
                curr = prev;
            } else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}
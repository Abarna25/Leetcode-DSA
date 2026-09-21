class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for (String token : tokens) {

            switch (token) {
                case "+":
                    st.push(st.pop() + st.pop());
                    break;

                case "-":
                    int b1 = st.pop();
                    int a1 = st.pop();
                    st.push(a1 - b1);
                    break;

                case "*":
                    st.push(st.pop() * st.pop());
                    break;

                case "/":
                    int b2 = st.pop();
                    int a2 = st.pop();
                    st.push(a2 / b2);
                    break;

                default:
                    st.push(Integer.parseInt(token));
            }
        }

        return st.pop();
    }
}
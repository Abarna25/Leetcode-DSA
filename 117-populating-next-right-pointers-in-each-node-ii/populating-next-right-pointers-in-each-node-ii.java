/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        Node curr = root;

        while (curr != null) {

            Node head = null; // first node of next level
            Node prev = null; // previous node on next level

            while (curr != null) {

                if (curr.left != null) {
                    if (head == null) {
                        head = curr.left;
                    }

                    if (prev != null) {
                        prev.next = curr.left;
                    }

                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (head == null) {
                        head = curr.right;
                    }

                    if (prev != null) {
                        prev.next = curr.right;
                    }

                    prev = curr.right;
                }

                curr = curr.next;
            }

            curr = head;
        }

        return root;
    }
}
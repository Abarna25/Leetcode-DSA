class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();

        Node clone = new Node(node.val);
        map.put(node, clone);
        q.offer(node);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (Node nei : curr.neighbors) {
                if (!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.val));
                    q.offer(nei);
                }

                map.get(curr).neighbors.add(map.get(nei));
            }
        }

        return clone;
    }
}
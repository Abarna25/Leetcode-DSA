class Trie {
    class Node {
        Node[] children=new Node[26];
        boolean end;
    }
    Node root;
    public Trie() {
        root=new Node();
    }
    public void insert(String word) {
        Node curr=root;
        for(char ch:word.toCharArray()) {
            int i=ch-'a';
            if(curr.children[i]==null) curr.children[i]=new Node();
            curr=curr.children[i];
        }
        curr.end=true;
    }
    public boolean search(String word) {
        Node curr=root;
        for(char ch:word.toCharArray()) {
            int i=ch-'a';
            if(curr.children[i]==null) return false;
            curr=curr.children[i];
        }
        return curr.end;
    }
    public boolean startsWith(String prefix) {
        Node curr=root;
        for(char ch:prefix.toCharArray()) {
            int i=ch-'a';
            if(curr.children[i]==null) return false;
            curr=curr.children[i];
        }
        return true;
    }
}

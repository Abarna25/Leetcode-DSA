class WordDictionary {
    class Node {
        Node[] children=new Node[26];
        boolean end;
    }
    Node root;
    public WordDictionary() {
        root=new Node();
    }
    public void addWord(String word) {
        Node curr=root;
        for(char ch:word.toCharArray()) {
            int i=ch-'a';
            if(curr.children[i]==null) curr.children[i]=new Node();
            curr=curr.children[i];
        }
        curr.end=true;
    }
    public boolean search(String word) {
        return dfs(root,word,0);
    }
    private boolean dfs(Node node,String word,int index) {
        if(index==word.length()) return node.end;
        char ch=word.charAt(index);
        if(ch=='.') {
            for(Node next:node.children) {
                if(next!=null && dfs(next,word,index+1)) return true;
            }
            return false;
        }
        int i=ch-'a';
        return node.children[i]!=null && dfs(node.children[i],word,index+1);
    }
}
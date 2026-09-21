class Solution {

    Map<String, List<String>> parents = new HashMap<>();
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord))
            return ans;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        boolean found = false;

        while (!q.isEmpty() && !found) {

            int size = q.size();
            Set<String> usedThisLevel = new HashSet<>();

            for (int s = 0; s < size; s++) {

                String word = q.poll();
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {

                    char old = arr[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        arr[i] = ch;
                        String next = new String(arr);

                        if (!dict.contains(next))
                            continue;

                        if (!usedThisLevel.contains(next)) {
                            usedThisLevel.add(next);
                            q.offer(next);
                        }

                        parents.computeIfAbsent(next, k -> new ArrayList<>())
                               .add(word);

                        if (next.equals(endWord))
                            found = true;
                    }

                    arr[i] = old;
                }
            }

            for (String w : usedThisLevel)
                dict.remove(w);
        }

        if (!found)
            return ans;

        List<String> path = new ArrayList<>();
        path.add(endWord);

        dfs(endWord, beginWord, path);

        return ans;
    }

    private void dfs(String word, String beginWord, List<String> path) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            ans.add(temp);
            return;
        }

        if (!parents.containsKey(word))
            return;

        for (String p : parents.get(word)) {
            path.add(p);
            dfs(p, beginWord, path);
            path.remove(path.size() - 1);
        }
    }
}
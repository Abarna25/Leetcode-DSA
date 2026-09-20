class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtrack(s, 0, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(String s, int index, int parts,
                           List<String> curr, List<String> ans) {

        if (parts == 4) {
            if (index == s.length()) {
                ans.add(String.join(".", curr));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) break;

            String part = s.substring(index, index + len);

            // leading zero check
            if (part.length() > 1 && part.charAt(0) == '0')
                continue;

            int value = Integer.parseInt(part);

            if (value > 255)
                continue;

            curr.add(part);
            backtrack(s, index + len, parts + 1, curr, ans);
            curr.remove(curr.size() - 1);
        }
    }
}
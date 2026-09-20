class Solution {
    public List<Integer> grayCode(int n) {

        List<Integer> res = new ArrayList<>();
        res.add(0);

        for (int bit = 0; bit < n; bit++) {
            int add = 1 << bit;

            for (int i = res.size() - 1; i >= 0; i--) {
                res.add(res.get(i) + add);
            }
        }

        return res;
    }
}
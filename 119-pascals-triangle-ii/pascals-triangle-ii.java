class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(1);

            for (int j = 1; j < i; j++) {
                next.add(row.get(j - 1) + row.get(j));
            }

            next.add(1);
            row = next;
        }

        return row;
    }
}
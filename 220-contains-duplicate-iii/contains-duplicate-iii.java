class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set=new TreeSet<>();
        for(int i=0;i<nums.length;i++) {
            long num=nums[i];
            Long val=set.ceiling(num);
            if(val!=null && val-num<=valueDiff) return true;
            val=set.floor(num);
            if(val!=null && num-val<=valueDiff) return true;
            set.add(num);
            if(set.size()>indexDiff) {
                set.remove((long)nums[i-indexDiff]);
            }
        }
        return false;
    }
}
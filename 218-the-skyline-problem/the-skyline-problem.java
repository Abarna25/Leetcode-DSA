class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events=new ArrayList<>();
        for(int[] b:buildings) {
            events.add(new int[]{b[0],-b[2]});
            events.add(new int[]{b[1],b[2]});
        }
        events.sort((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        List<List<Integer>> ans=new ArrayList<>();
        Map<Integer,Integer> count=new HashMap<>();
        count.put(0,1);
        int prev=0;
        for(int[] e:events) {
            int x=e[0],h=e[1];
            if(h<0) {
                pq.offer(-h);
                count.put(-h,count.getOrDefault(-h,0)+1);
            } else {
                count.put(h,count.get(h)-1);
            }
            while(!pq.isEmpty() && count.getOrDefault(pq.peek(),0)==0) {
                pq.poll();
            }
            int curr=pq.peek();
            if(curr!=prev) {
                ans.add(Arrays.asList(x,curr));
                prev=curr;
            }
        }
        return ans;
    }
}
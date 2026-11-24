class Solution {
    public int maxLength(int[] nums) {
        int n=nums.length,ans=1;
        for(int i=0;i<n;i++) {
            long prod=1,lcm=1;
            int g=0;
            for(int j=i;j<n;j++) {
                prod*=nums[j];
                g=gcd(g,nums[j]);
                lcm=lcm(lcm,nums[j]);
                if(prod==lcm*g) {
                    ans=Math.max(ans,j-i+1);
                }
            }
        }
        return ans;
    }
    private int gcd(long a,long b) {
        while(b!=0) {
            long t=b;
            b=a%b;
            a=t;
        }
        return (int)a;
    }
    private long lcm(long a,long b) {
        return a/gcd(a,b)*b;
    }
}
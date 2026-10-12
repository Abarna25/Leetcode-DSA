import java.util.*;
class Solution{
    public int maxSum(List<Integer> nums,int k){
        long mod=1000000007L,ans=0;
        int[] bits=new int[31];
        for(int x:nums){
            for(int i=0;i<31;i++){
                if((x&(1<<i))!=0)bits[i]++;
            }
        }
        for(int j=0;j<k;j++){
            long x=0;
            for(int i=30;i>=0;i--){
                if(bits[i]>0){
                    x|=1L<<i;
                    bits[i]--;
                }
            }
            ans=(ans+x*x)%mod;
        }
        return (int)ans;
    }
}
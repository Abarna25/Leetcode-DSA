import java.util.*;
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n=nums.length;
        int[] sa=suffixArray(nums);
        int[] rank=new int[n];
        int[] lcp=new int[n];
        for(int i=0;i<n;i++) rank[sa[i]]=i;
        int k=0;
        for(int i=0;i<n;i++) {
            int r=rank[i];
            if(r==n-1) {
                k=0;
                continue;
            }
            int j=sa[r+1];
            while(i+k<n && j+k<n && nums[i+k]==nums[j+k]) k++;
            lcp[r]=k;
            if(k>0) k--;
        }
        int ans=n;
        for(int i=0;i<n;i++) {
            int r=rank[i];
            int left=r>0 ? lcp[r-1] : 0;
            int right=r<n-1 ? lcp[r] : 0;
            int len=Math.max(left,right)+1;
            if(i+len<=n) {
                ans=Math.min(ans,len);
            }
        }
        return ans;
    }
    private int[] suffixArray(int[] nums) {
        int n=nums.length;
        int N=n+1;
        int[] a=new int[N];
        for(int i=0;i<n;i++) a[i]=nums[i];
        int[] p=new int[N];
        int[] c=new int[N];
        int[] cnt=new int[Math.max(N,100001)];
        for(int x:a) cnt[x]++;
        for(int i=1;i<cnt.length;i++) cnt[i]+=cnt[i-1];
        for(int i=N-1;i>=0;i--) p[--cnt[a[i]]]=i;
        int classes=1;
        c[p[0]]=0;
        for(int i=1;i<N;i++) {
            if(a[p[i]]!=a[p[i-1]]) classes++;
            c[p[i]]=classes-1;
        }
        int[] pn=new int[N];
        int[] cn=new int[N];
        for(int len=1;len<N;len*=2) {
            for(int i=0;i<N;i++) {
                pn[i]=p[i]-len;
                if(pn[i]<0) pn[i]+=N;
            }
            Arrays.fill(cnt,0,classes,0);
            for(int i=0;i<N;i++) cnt[c[pn[i]]]++;
            for(int i=1;i<classes;i++) cnt[i]+=cnt[i-1];
            for(int i=N-1;i>=0;i--) {
                int x=pn[i];
                p[--cnt[c[x]]]=x;
            }
            cn[p[0]]=0;
            int newClasses=1;
            for(int i=1;i<N;i++) {
                int cur=p[i],prev=p[i-1];
                if(c[cur]!=c[prev] ||
                   c[(cur+len)%N]!=c[(prev+len)%N]) {
                    newClasses++;
                }
                cn[cur]=newClasses-1;
            }
            int[] temp=c;
            c=cn;
            cn=temp;
            classes=newClasses;
            if(classes==N) break;
        }
        return Arrays.copyOfRange(p,1,N);
    }
}
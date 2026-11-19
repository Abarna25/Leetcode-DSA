import java.util.*;
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int a=0,b=0,c1=0,c2=0;
        for(int num:nums){
            if(c1>0 && num==a) c1++;
            else if(c2>0 && num==b) c2++;
            else if(c1==0){
                a=num;
                c1=1;
            }else if(c2==0){
                b=num;
                c2=1;
            }else{
                c1--;
                c2--;
            }
        }
        c1=0;
        c2=0;
        for(int num:nums){
            if(num==a) c1++;
            else if(num==b) c2++;
        }
        List<Integer> ans=new ArrayList<>();
        if(c1>nums.length/3) ans.add(a);
        if(c2>nums.length/3) ans.add(b);
        return ans;
    }
}
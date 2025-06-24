class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) 
    {
        int n=nums.length;

        List<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(nums[i]==key){list.add(i);}
        }

        int size=list.size();
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(Math.abs(i-list.get(j))<=k){ans.add(i);break;}
            }
        }

        return ans;
    }
}

//another sol

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) 
    {
        int n=nums.length;

        int freq[]=new int[1001];
        for(int i=0;i<n;i++)
        {
            if(nums[i]==key){freq[i]++;}
        }

        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<freq.length;j++)
            {
                if(freq[j]>0)
                {
                    if(Math.abs(i-j)<=k){ans.add(i);break;}
                }
            }
        }

        return ans;
    }
}
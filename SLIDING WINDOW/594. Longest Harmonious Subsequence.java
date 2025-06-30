//video link-https://youtu.be/M-MMjMe0P0U?si=RJ3iURjdVeprlELr

//1st sol- sorting +sliding window
class Solution {
    public int findLHS(int[] nums) 
    {
        int n=nums.length;      
        if(n==1){return 0;}     //edge case if only one element

        Arrays.sort(nums);      //sorting 

        int max=0;
        int i=0,j=0;
        while(j<n)
        {
            while(nums[j]-nums[i]>1)
            {
                i++;
            }

            if(nums[j]-nums[i]!=0) max=Math.max(max,j-i+1);

            j++;
        }

        return max;
    }
}

//2nd--
class Solution {
    public int findLHS(int[] nums) 
    {
        int n=nums.length;      
        if(n==1){return 0;}     //edge case if only one element

        Arrays.sort(nums);      //sorting 

        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++)
        {
            //for storing last index where element was found
            map.put(nums[i],i);    
        }

        int max=0;
        for(int i=0;i<n;i++)
        {
            if(map.containsKey(nums[i]+1))
            {
                max=Math.max(max,map.get(nums[i]+1)-i+1);
            }
        }

        return max;
    }
}

//3rd using map
class Solution {
    public int findLHS(int[] nums) 
    {
        int n=nums.length;      
        if(n==1){return 0;}     //edge case if only one element

        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++)
        {
            //for storing frequencies
            map.merge(nums[i],1,Integer::sum);  
        }

        int max=0;
        for(int key:map.keySet())
        {
            if(map.containsKey(key+1))
            {
                max=Math.max(max,map.get(key)+map.get(key+1));
            }
        }

        return max;
    }
}

// 🌟 Intuition:
// We need two numbers where difference = 1 (like 2 and 3, or 4 and 5 etc)
// So, we count how many times each number appears
// Then for each number, check if (num+1) exists, and take sum of both frequencies

// ✅ Why this works?
// Because we're not interested in positions, just how many such valid elements exist
// This is the most optimal approach (no need to sort the array)

// ⏱ Time Complexity: O(n)
// 🧠 Space Complexity: O(n)

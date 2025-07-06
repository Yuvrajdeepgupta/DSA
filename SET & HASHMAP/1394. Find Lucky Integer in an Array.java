//problem link-https://leetcode.com/problems/find-lucky-integer-in-an-array/?envType=daily-question&envId=2025-07-05

//video link-https://youtu.be/cGGJPSeHYaw?si=sPm2AtNIDhWGdzXw

class Solution {
    public int findLucky(int[] arr) 
    {
        int freq[]=new int[501];

        for(int i:arr)
        {
            freq[i]++;
        }

        for(int i=500;i>=0;i--)
        {
            if(freq[i]>0 && freq[i]==i)
            {
                return i;
            }
        }

        return -1;
    }
}

// Step 1: Make freq array of size 501 (value limits are <=500)
// Step 2: Count how many times each number came
// Step 3: Go from biggest to smallest, if freq[i] == i → that’s a lucky number!
// Step 4: Return the first such lucky number found (as we go from big to small)
// Step 5: If nothing found, return -1

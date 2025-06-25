//linear search sol--

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) 
    {
        int ans=0;
        for(int i:arr1)
        {
            if(check(i,arr2,d)){ans++;}
        }

        return ans;
    }

    public boolean check(int key,int arr[],int d)
    {
        for(int i:arr)
        {
            if(Math.abs(key-i)<=d)
            {
                return false;
            }
        }

        return true;
    }
}

//BINARY SEARCH SOL---
/*
We are given:

-A number key from arr1
-A sorted array arr2
-A number d

-We want to check:
"Is there any number in arr2 such that |key - that number| ≤ d?"
    If yes, then this key is not allowed (return false).
    If no, then it's valid (return true)
    
    ✅ Correct Binary Search Approach:
    We want to search if any element in arr2 lies in this range:
        [key - d , key + d]
    Since arr2 is sorted, we can do binary search to check if any element is in that range.
*/

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) 
    {
        //first sorting for applying binary search
        Arrays.sort(arr2);
        
        //traversing over arr1
        int ans=0;
        for(int i:arr1)
        {
            if(check(i,arr2,d)){ans++;}
        }

        return ans;
    }

    public boolean check(int key,int arr[],int d)
    {
        //applying binary search on arr2
        int low=0,high=arr.length-1;
        while(low<=high)
        {
            int mid=low + (high-low)/2;

            if(arr[mid]>=key-d && arr[mid]<=key+d)
            {
                return false;
            }
            else if(arr[mid]<key-d)
            {
                low=mid+1;      //too low go right
            }
            else
            {               
                high=mid-1;     //too high go left
            }                   
        }

        return true;
    }
}
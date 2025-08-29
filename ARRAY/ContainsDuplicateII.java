/*
============================================================================
📝 Problem: Contains Duplicate II
LeetCode Link: https://leetcode.com/problems/contains-duplicate-ii/description/?envType=problem-list-v2&envId=sliding-window
Solution Video Link 1: https://youtu.be/B5v3KZpRreQ?si=nBfdvif94xvh6ml-
Solution Video Link 2: https://youtu.be/AyiGBwFlMb8?si=X8mm7xPYxTtdXJ3Q
============================================================================

📌 Problem Statement:
We are given an integer array `nums` and an integer `k`. 
We need to check if there exist two distinct indices i and j in the array such that:
    - nums[i] == nums[j]
    - |i - j| <= k

Return true if such indices exist, otherwise return false.

----------------------------------------------------------------------------

💡 Intuition:
We need to check for duplicates within a window of size `k`.
There are two main approaches:

1️⃣ HashSet (Sliding Window):
   - Keep a sliding window of size <= k using a HashSet.
   - If the current element already exists in the set → duplicate found.
   - Otherwise, add it to the set.
   - If the window exceeds k elements, remove the (i-k)th element.
   - Efficient for checking duplicates in a sliding window.

2️⃣ HashMap (Optimal Index Tracking):
   - Use a HashMap to store each number and its latest index.
   - While iterating, if nums[i] is already in the map:
       → check if (i - previousIndex) <= k
       → if yes, return true.
   - Always update the number’s index in the map.
   - More direct and slightly more intuitive because it avoids window maintenance.

----------------------------------------------------------------------------

✅ Why HashMap works well:
- Instead of keeping all elements of the last k window, 
  we just track the **latest index** of each number.
- As soon as we see a duplicate, we check the distance condition.
- This avoids extra removals and makes the solution neat and efficient.

----------------------------------------------------------------------------

🚀 Java Code (HashMap Optimal Solution):
*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) 
    {
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(nums[i]) && Math.abs(map.get(nums[i])-i)<=k)
            {
                return true;
            }
            else
            {
                map.put(nums[i],i);
            }
        }

        return false;
    }
}

/*
----------------------------------------------------------------------------

📊 Time Complexity:
- O(n), where n = length of nums
  Each element is inserted/updated/checked in HashMap in O(1) average time.

💾 Space Complexity:
- O(min(n, k)), because HashMap can store at most k+1 elements in sliding window scenario.

----------------------------------------------------------------------------

🧩 Example Walkthrough:
nums = [1,2,3,1], k = 3
- i=0 → map = {1:0}
- i=1 → map = {1:0, 2:1}
- i=2 → map = {1:0, 2:1, 3:2}
- i=3 → nums[3]=1 is in map with index=0 → distance = 3-0=3 <= k
        ✅ return true

----------------------------------------------------------------------------

🎯 Summary (Yuvraj way):
We just track indices of numbers using HashMap.
If the same number appears again, check distance.
If distance ≤ k → true, else keep updating index.
Neat, fast, and memory efficient. 😎

============================================================================
*/

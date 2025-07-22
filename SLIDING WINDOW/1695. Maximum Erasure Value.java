//que link - https://leetcode.com/problems/maximum-erasure-value/description/?envType=daily-question&envId=2025-07-22
//sol link- https://youtu.be/V7ZmYjVdZSU?si=X3DgmibO37dJ-TiN


/*********************************************************************************************/
/*
🔥 Leetcode 1695: Maximum Erasure Value (Maximum Unique Subarray Sum)

🧠 Problem Statement:
You're given an integer array 'nums'. You need to find the maximum sum of any subarray (contiguous) that contains only unique elements.

-------------------------------------------------------------------

🔍 Intuition:
We need to maintain a window (subarray) where all elements are unique.  
As soon as a duplicate is found, we shrink the window from the left until the duplicate is removed.  
During this process, we keep track of the sum of elements in the window and update the max sum whenever it's higher.

-------------------------------------------------------------------


-------------------------------------------------------------------

💡 Approach 1:
- Use two pointers (`i` and `j`) for sliding window.
- Use a `HashSet` to maintain only unique elements inside the current window.
- If a duplicate is found (`set.contains(nums[j])`), remove elements from the left (`set.remove(nums[i])`) and reduce the sum.
- Keep updating the max sum.

-------------------------------------------------------------------

✅ Java Code (Clean + Optimal + Khandani):
*/

import java.util.*;

class Solution {
    public int maximumUniqueSubarray(int[] nums) 
    {
        Set<Integer> set = new HashSet<>();

        int n = nums.length;
        int sum = 0, maxsum = 0;
        int i = 0, j = 0;

        while (j < n)
        {
            int curr = nums[j];

            // Remove duplicates from the left until current is unique
            while (!set.isEmpty() && set.contains(curr))
            {
                set.remove(nums[i]);
                sum -= nums[i];
                i++;
            }

            sum += nums[j];
            maxsum = Math.max(maxsum, sum);
            set.add(curr);
            j++;
        }

        return maxsum;
    }
}

/*


__________________________________________________________________

⏱️ Time Complexity:
O(N)  
- Each element is added and removed from the set at most once.

📦 Space Complexity:
O(N)  
- In the worst case, all elements are unique → HashSet grows to size N.

-------------------------------------------------------------------


💡 Approach 2:
- Use the sliding window technique with two pointers (i and j).
- Use a boolean[] array of size 10001 as a fast HashSet since 1 <= nums[i] <= 10^4.
- At each step:
  → If nums[j] is already in the window, remove elements from the left (i++) until it's gone.
  → Add nums[j] to the sum.
  → Update max sum.

-------------------------------------------------------------------

✅ Java Code (Clean + Optimal + Khandani):
*/

class Solution {
    public int maximumUniqueSubarray(int[] nums) 
    {
        boolean set[] = new boolean[10001];  // Constraints: nums[i] ≤ 10^4

        int n = nums.length;
        int sum = 0, maxsum = 0;
        int i = 0, j = 0;

        while (j < n)
        {
            int curr = nums[j];

            // If current element is already in window, shrink window from left
            while (set[curr])
            {
                set[nums[i]] = false;
                sum -= nums[i];
                i++;
            }

            // Add current element and update max sum
            maxsum = Math.max(maxsum, sum += nums[j]);
            set[curr] = true;
            j++;
        }

        return maxsum;
    }
}

/*
-------------------------------------------------------------------

🧪 Example Dry Run:

Input: nums = [4,2,4,5,6]

Window Evolves:
- [4]     → sum = 4
- [4,2]   → sum = 6
- [2,4]   → 4 was duplicate → remove old 4 → sum = 6
- [2,4,5] → sum = 11
- [2,4,5,6] → sum = 17 ✅ max

Output: 17

-------------------------------------------------------------------

⏱️ Time Complexity:
O(N)  
Each element is added and removed from the window at most once → Linear time

📦 Space Complexity:
O(1)  
We use a fixed-size boolean array of size 10001 → constant space (does not grow with input)

-------------------------------------------------------------------

✨ Yuvraj Style Summary:
- ✅ Use sliding window with two pointers i and j
- ✅ Use boolean[10001] for fast lookup instead of HashSet
- ✅ When duplicate appears, remove elements from start until duplicate is gone
- ✅ Update maxsum every time a unique element is added
- ✅ Works in O(N) time and O(1) space

*/

// 🔗 Problem Link: https://leetcode.com/problems/remove-element/description/
// 🎥 Solution Video: https://youtu.be/Pcd1ii9P9ZI?si=T12VVDf5ZdnbpDaU

/* ✅ Problem: Remove Element
You are given an integer array `nums` and an integer `val`.
Remove all occurrences of `val` in-place and return the new length of the array.
You must do it in O(1) extra space, and the order of elements may be changed.
The elements beyond the returned length don't matter.

Example:
Input: nums = [3,2,2,3], val = 3
Output: 2 (array becomes [2,2,_,_])

-----------------------------------------
👀 Intuition:
- We want to ignore all occurrences of `val` and keep the rest.
- Overwrite valid elements (`!= val`) at the front using a pointer `k`.
- Use one forward loop, when we find non-val → assign it to `nums[k]` and increment `k`.
- At the end, first `k` elements will be valid, and rest can be ignored.

This is optimal and in-place.
*/

class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // Points to the next position to insert valid element

        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] != val)
            {
                nums[k] = nums[i];
                k++;
            }
        }

        return k; // First k elements are the result
    }
}


/* 🧪 Dry Run:

Input: nums = [3, 2, 2, 3], val = 3

i = 0 → nums[0] == 3 → skip  
i = 1 → nums[1] == 2 → nums[0] = 2, k = 1  
i = 2 → nums[2] == 2 → nums[1] = 2, k = 2  
i = 3 → nums[3] == 3 → skip  

Result: k = 2, array becomes [2,2,_,_]

*/


/* ⏱️ Time Complexity:
- O(n), where n = nums.length
- Each element is checked once

🧠 Space Complexity:
- O(1), done in-place with no extra array
*/


// 📝 Yuvraj-style Summary:
/*
→ Ek pointer rakho 'k' jahan valid element ko overwrite karna hai
→ Agar current nums[i] != val hai to nums[k] = nums[i] kar do and k++
→ Baaki values humko nahi chahiye so unko ignore karo
→ Return k, which is the count of valid (non-val) elements
→ Optimal hai, simple hai, space bhi bach gaya 💯
*/


/*
🔗 Question Link: https://leetcode.com/problems/minimum-removals-to-balance-array/description/
🎥 Solution Video: https://youtu.be/0qnm1_5cSIc?si=4YhJJ1uPww5rCGVR

__________________________________________________________________________________________
🔥 Problem Summary:
You are given an integer array `nums` and an integer `k`.
You can remove elements from the array. The goal is to make the remaining array "balanced".

An array is "balanced" if:
    For every i and j in the array, nums[j] <= nums[i] * k

❓ Task: Return the **minimum number of elements to remove** to make the array balanced.

__________________________________________________________________________________________
🧠 Intuition (Yuvraj style):
- Sort the array → so the elements go from smallest to largest.
- Now, the question becomes finding the **largest window** (subarray) where:
    max_element ≤ min_element * k
- That means if you fix the left end (`i`), you can try to move the right end (`j`) and check:
    nums[j] <= nums[i] * k → using helper function `isValid()`

We slide `i` forward whenever the window becomes invalid.
The **goal** is to keep the window as big as possible (maximize j - i + 1).

❗ Final answer = total elements - max valid window size

__________________________________________________________________________________________
✅ Code: (Sliding Window Approach)
*/

import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) 
    {
        int n = nums.length;
        if (n == 1) return 0;

        Arrays.sort(nums); // Sort for easier control on min/max

        int i = 0, j = 0;
        int max = 0;

        while (j < n)
        {
            while (i <= j && !isValid(nums[i], nums[j], k))
            {
                i++;
            }

            max = Math.max(max, j - i + 1); // Track max balanced window
            j++;
        }

        return n - max; // Remove all elements outside the best window
    }

    // Helper to check if window is valid (handles overflow using long)
    public boolean isValid(long a, long b, int k)
    {
        return a * k >= b;
    }
}

__________________________________________________________________________________________
🧪 Example Walkthrough:

Input: nums = [1,3,5,7], k = 2  
Sorted: [1,3,5,7]  
→ Try all windows [i..j] where max ≤ min * k  
→ Best window = [1,3,5] ⇒ length = 3  
→ Min removal = 4 - 3 = 1

__________________________________________________________________________________________
⏱️ Time Complexity:
- Sorting: O(n log n)
- Sliding window: O(n)
✔️ Total: O(n log n)

📦 Space Complexity:
- O(1) extra (in-place sorting, constant variables)

__________________________________________________________________________________________
✍️ Summary (Yuvraj Way):

// Step 1: Sort the array  
// Step 2: Use two pointers (i, j) to maintain a window  
// Step 3: Slide i forward if window is invalid  
// Step 4: Track max valid window length  
// Step 5: Result = n - max window size  

Perfect combo of sorting + sliding window to reduce unnecessary elements.
*/

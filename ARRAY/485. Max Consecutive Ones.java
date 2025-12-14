/*
Problem ➝ https://leetcode.com/problems/max-consecutive-ones/description/
Solution ➝ https://youtu.be/9g1dV8UuYiM?si=hGsIbBW7FEV-8E2N

✅ Intuition:
We are given a binary array nums, and the task is to find the maximum number of consecutive 1's.
Approach:
- Traverse through the array while counting consecutive 1's.
- Reset the count to 0 whenever a 0 is encountered.
- Keep track of the maximum count observed during the traversal.
This ensures that we efficiently find the longest sequence of consecutive 1's in a single pass.

✅ Code:
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == 1 ? 1 : 0; // Edge case for single element
        }

        int max = 0; // To store the maximum count of consecutive 1's
        int count = nums[0] == 1 ? 1 : 0; // Initialize count based on first element
        max = Math.max(count, max); // Update max if needed

        // Traverse the array starting from index 1
        for (int i = 1; i < n; i++) {
            if (nums[i] == 1) {
                count++; // Increment count when current element is 1
                max = Math.max(count, max); // Update max if needed
            } else {
                count = 0; // Reset count when current element is 0
            }
        }

        return max;
    }
}

✅ Example Walkthrough:
Example: nums = [1,1,0,1,1,1]
- Start with count = 1 for index 0.
- Index 1 is 1 → count = 2 → max = 2.
- Index 2 is 0 → count reset to 0.
- Index 3 is 1 → count = 1 → max = 2.
- Index 4 is 1 → count = 2 → max = 2.
- Index 5 is 1 → count = 3 → max = 3.
Final answer ➝ 3.

✅ Time Complexity:
O(N), where N is the length of the array.
We traverse the array once, updating count and max at each step.

✅ Space Complexity:
O(1), only a few variables are used (no extra data structures).

✅ Why this approach works:
By tracking the count of consecutive 1's and resetting it whenever a 0 is encountered, we ensure that we always know the length of the current sequence. Updating the max at each step guarantees that we get the largest sequence by the end.

✅ Yuvraj Summary:
This is a simple sliding window problem where we count 1's and reset on 0. We use two variables — one for the current sequence length and another for the maximum found so far. It's an optimal O(N) time and O(1) space solution and perfect practice for handling array traversal with conditional counting.
*/

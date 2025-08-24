# Problem: Longest Subarray of 1's After Deleting One Element
# Question Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
# Solution Video Link: https://youtu.be/SQ8tY9nxeZU?si=qtGGwvE1D66_BSoj

------------------------------------------------------------
## Problem Breakdown:
- We are given a binary array (only 0's and 1's).
- We need to find the longest subarray consisting of only 1's **after deleting exactly one element** (which could be a 0 or a 1).
- Edge case: If the array has no 0, we must still delete one element, so the answer will be (n - 1).

------------------------------------------------------------
## Intuition:
We need the maximum window of consecutive 1's with at most one zero inside, because we can delete that zero to merge two 1-blocks.
There are multiple ways to achieve this optimally (O(n) time, O(1) space). We'll cover three optimal approaches.

------------------------------------------------------------
## Approach 1: Split Around Zero (Counting Consecutive 1's)

- Maintain two counters:
  - prevones: length of consecutive 1's before the last zero.
  - currones: length of consecutive 1's currently counting.
- When a zero is found:
  - merge prevones and currones for max.
  - reset currones and set prevones.
- If no zero exists, return n - 1.

### Code:
class Solution 
{
    public static int longestSubarray(int[] nums) 
    {
        int prevones = 0, currones = 0;
        int max = 0;
        boolean flag = true; // check if any zero exists

        for (int curr : nums)
        {
            if (curr == 1)
            {
                currones++;
            }
            else
            {
                prevones = currones;
                currones = 0;
                flag = false;
            }

            max = Math.max(max, currones + prevones);
        }

        if (flag) return nums.length - 1; // all ones case
        return max;
    }
}

------------------------------------------------------------
## Approach 2: Sliding Window (Count Zeros)

- Maintain a sliding window with at most one zero.
- Use two pointers i and j; count zeros in the window.
- If zeros > 1, shrink the window from left.
- Result is (max window size - 1) because one element must be deleted.

### Code:
class Solution 
{
    public int longestSubarray(int[] nums) 
    {
        int count = 0;
        int window = 0;
        int i = 0;

        for (int j = 0; j < nums.length; j++)
        {
            if (nums[j] == 0) count++;

            while (i < j && count > 1)
            {
                if (nums[i] == 0) count--;
                i++;
            }

            window = Math.max(window, j - i + 1);
        }

        return window - 1;
    }
}

------------------------------------------------------------
## Approach 3: Sliding Window (Last Zero Index Trick)

- Track the last seen zero index.
- When a new zero is encountered, move left pointer to lastZero + 1.
- Maintain max window size and subtract 1 at end.

### Code:
class Solution 
{
    public int longestSubarray(int[] nums) 
    {
        int i = 0;
        int lastzero = -1;
        int max = 0;

        for (int j = 0; j < nums.length; j++)
        {
            if (nums[j] == 0)
            {
                i = lastzero + 1;
                lastzero = j;
            }

            max = Math.max(max, j - i + 1);
        }

        return max - 1;
    }
}

------------------------------------------------------------
## Time Complexity Analysis:
- All three approaches iterate over the array once.
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) (only a few variables)

------------------------------------------------------------
## Example Walkthrough:
Input: nums = [1,1,0,1,1,1]
- Split around zero: prev=2, curr=3, max=5
- Sliding window: window max size 6 → return 5
Output: 5

------------------------------------------------
COMPARISON OF APPROACHES:

1) Prefix-Suffix Merge:
   - Easy to understand.
   - Tracks previous and current streak.
   - Needs a check for all 1s (flag).

2) Sliding Window (count zeros ≤ 1):
   - More generic & flexible (works if we had to delete k zeros with small change).
   - Handles window dynamically.
   - Extra while loop, but still O(n).

3) Sliding Window with lastzero:
   - Cleanest and most concise.
   - Tracks last zero index only.
   - Best readability and least code.

All are optimal O(n), O(1). For interviews, Approach 2 (count zeros sliding window) is most standard, but any of these are perfectly fine.

------------------------------------------------------------
## Key Edge Cases:
- All ones: [1,1,1] → output n-1
- All zeros: [0,0,0] → output 0
- Single zero: [1,0,1] → output 2
- Zero at ends: [0,1,1,1] or [1,1,1,0]

------------------------------------------------------------
# Final Takeaway:
- Know all three variations; sliding window is most standard.
- Always handle the all-ones case separately.

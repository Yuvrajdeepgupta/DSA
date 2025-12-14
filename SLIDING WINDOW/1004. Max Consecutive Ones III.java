/*
Problem ➝ https://leetcode.com/problems/max-consecutive-ones-iii/description/
Solution ➝ https://youtu.be/Y4SrfoquEpk?si=DTN4G6B2cgz4rc-k

✅ Intuition:
We need to find the longest subarray containing only 1s after flipping at most 'k' zeros. 
This is a sliding window problem where:
- We expand the window by moving the right pointer 'j'.
- Whenever the number of zeros in the window exceeds 'k', we shrink the window by moving the left pointer 'i'.
- We keep track of the maximum length of valid windows (where count of zeros <= k).
This ensures that at every step, we maintain the longest possible subarray of 1s after flipping zeros.

✅ Code:
class Solution {
    public int longestOnes(int[] nums, int k) {
        // If allowed flips 'k' are enough to cover entire array, return full length
        if(k == nums.length) {
            return nums.length;
        }
        
        int count = 0; // To count zeros in the current window
        int max = 0;   // To store the maximum window length
        int i = 0;     // Left pointer of sliding window
        
        // Iterate over the array with right pointer 'j'
        for(int j = 0; j < nums.length; j++) {
            int curr = nums[j];

            // Count zeros in the window
            if(curr == 0) {
                count++;
            }
            
            // If zeros exceed 'k', shrink the window from the left
            while(count > k && i < j) {
                if(nums[i] == 0) {
                    count--;
                }
                i++;
            }
            
            // Update max length if current window is valid
            if(count <= k) {
                max = Math.max(max, j - i + 1);
            }
        }
        
        return max;
    }
}

✅ Example Walkthrough:
Example: nums = [1,1,0,0,1,1,1,0,1,1], k = 2
- We expand the window by moving 'j'.
- When we include zeros, 'count' increases.
- Once 'count > k', we shrink from left using 'i'.
- We update 'max' with valid window sizes.
Final answer ➝ 6.

✅ Time Complexity:
O(N), where N is the length of the array.
Each element is processed at most twice — once when expanding and once when shrinking the window.

✅ Space Complexity:
O(1), only a few variables are used (no extra data structures).

✅ Why this approach works:
The sliding window ensures we always keep track of the largest valid window while dynamically adjusting to meet the problem’s constraints. It’s optimal because:
- We never recheck unnecessary elements.
- We ensure the solution runs in linear time.

✅ Yuvraj Summary:
This problem is all about using the sliding window technique to count zeros and decide when to shrink or expand the window. By keeping track of zeros using a simple counter and adjusting the window boundaries, we find the longest segment efficiently. The solution is optimal with O(N) time and O(1) space, and it’s a great problem to practice handling constraints dynamically while traversing the array.
*/

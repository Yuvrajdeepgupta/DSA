/*
💻 Problem: Monotonic Array
🔗 Leetcode Link: https://leetcode.com/problems/monotonic-array/description/
🎥 Solution Link: https://youtu.be/LfYSyPP6YOA?si=9ePJPgAxdqF01MQc

👀 Problem Statement:
An array is monotonic if it is either entirely non-increasing or entirely non-decreasing.
Return true if the given array is monotonic.

--------------------------------------------------------
🧠 Intuition:

We want to check if the array is:
➡️ Always increasing or ➡️ Always decreasing.

We track the "tone" or direction:
- tone = -1 → undecided
- tone =  1 → increasing
- tone =  0 → decreasing

While iterating:
- If we see nums[i] < nums[i-1] → means decreasing
  → If tone was increasing → ❌ not monotonic
- If we see nums[i] > nums[i-1] → means increasing
  → If tone was decreasing → ❌ not monotonic
- Else, continue and update tone accordingly.

If we complete the loop, it's monotonic ✅

--------------------------------------------------------
✅ Code:
*/

class Solution {
    public boolean isMonotonic(int[] nums) 
    {
        int n = nums.length;

        // Edge case: Only 1 element
        if(n == 1) return true;

        int tone = -1; // -1 = undecided, 0 = decreasing, 1 = increasing

        for(int i = 1; i < n; i++)
        {
            if(nums[i] < nums[i - 1])
            {
                if(tone == 1) return false; // tone conflict
                tone = 0; // set tone to decreasing
            }
            else if(nums[i] > nums[i - 1])
            {
                if(tone == 0) return false; // tone conflict
                tone = 1; // set tone to increasing
            }
        }

        return true; // If we reached here, it’s monotonic
    }
}

--------------------------------------------------------
📦 Time Complexity: O(n)
→ We go through the array only once.

🧠 Space Complexity: O(1)
→ Only a few variables used.

--------------------------------------------------------
🧪 Example Dry Run:

nums = [1, 2, 2, 3]

→ i = 1 → 2 > 1 → tone = 1 (increasing)
→ i = 2 → 2 == 2 → no change
→ i = 3 → 3 > 2 → still increasing → ✅

So, return true.

--------------------------------------------------------
👑 Summary (Yuvraj Style):

→ Ek hi pass mei dekh liya increasing hai ya decreasing  
→ Jis side jhuka, usi side chalte gaye  
→ Agar opposite direction mila → return false  
→ Nahi mila to bhai monotonic hai ✅  
→ Optimal hai, seedha O(n) time, O(1) space

*/

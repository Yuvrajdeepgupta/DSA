/*
------------------------------------------------------------
🧮 Problem: 643. Maximum Average Subarray I
------------------------------------------------------------
Given an integer array nums and an integer k, 
find the contiguous subarray of length k that has 
the maximum average value. Return this value as a double.

Example:
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Subarray [12, -5, -6, 50] has the maximum average = 12.75
------------------------------------------------------------
*/

/*
------------------------------------------------------------
💡 Intuition
------------------------------------------------------------
Brute force would mean checking every possible subarray of size k 
and calculating their average one by one → O(n*k) time.

Instead, we use a **Sliding Window**:
→ Keep a running sum of 'k' consecutive elements.
→ Move the window by one element at a time.
→ Subtract the element that leaves the window and add the new one.
→ Track the maximum average.

This approach reduces complexity to O(n).
------------------------------------------------------------
*/

/*
------------------------------------------------------------
⚙️ Step-by-Step Working
------------------------------------------------------------
1️⃣ Initialize two pointers (i, j) for the sliding window.
2️⃣ Maintain a running sum of the current window.
3️⃣ Once window size = k (i.e., j - i + 1 == k):
    → Calculate avg = sum / k
    → Update max average if it's greater.
    → Slide the window ahead by doing sum -= nums[i] and i++.
------------------------------------------------------------
*/

class Solution 
{
    public double findMaxAverage(int[] nums, int k) 
    {
        double avg = Integer.MIN_VALUE; // max average
        double sum = 0;
        int i = 0, j;

        for (j = 0; j < nums.length; j++)
        {
            sum += nums[j];

            // When window size reaches k
            if (j - i + 1 >= k)
            {
                avg = Math.max(avg, sum / k); // update max avg
                sum -= nums[i]; // slide window
                i++;
            }
        }

        return avg;
    }
}

/*
------------------------------------------------------------
🧩 Example Walkthrough
------------------------------------------------------------
nums = [1,12,-5,-6,50,3], k = 4

Window 1 → [1,12,-5,-6] → sum=2 → avg=0.5
Window 2 → [12,-5,-6,50] → sum=51 → avg=12.75 ✅ (max)
Window 3 → [-5,-6,50,3] → sum=42 → avg=10.5

Final Answer → 12.75
------------------------------------------------------------
*/

/*
------------------------------------------------------------
⏱️ Time Complexity
------------------------------------------------------------
O(n) → Each element enters and leaves the window once.

💾 Space Complexity
------------------------------------------------------------
O(1) → Only a few variables used.
------------------------------------------------------------
*/

/*
------------------------------------------------------------
🗣️ Yuvraj Summary (Khandani Style)
------------------------------------------------------------
👉 Brute force O(n*k) hota, but we used sliding window for O(n).
👉 Window sum maintain kiya, har step pe average nikala aur max store kiya.
👉 Har element sirf ek baar window me aata hai.
👉 Efficient, clean, and best possible solution ✅
------------------------------------------------------------
*/

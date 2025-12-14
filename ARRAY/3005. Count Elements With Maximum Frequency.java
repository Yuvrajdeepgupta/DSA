/*
📝 Problem Breakdown
We are given an integer array `nums`. 
We need to find the total number of elements that have the highest frequency.
Steps:
1. Count frequency of each element.
2. Find the maximum frequency.
3. Add up all occurrences of elements that have this maximum frequency.

💡 Intuition
- First, count how many times each number appears → use a frequency counter.
- Then, find the highest frequency.
- Finally, sum up all occurrences that match this maximum.
👉 Answer is not the count of elements with max frequency, but the sum of their occurrences.

⚙️ Why Array Frequency is Used
- Constraint: nums[i] ≤ 100.
- Instead of HashMap, we can directly use an int[] freq of size 101.
- This makes the solution constant space and very fast.

📜 Code (Java)
*/
class Solution {
    public int maxFrequencyElements(int[] nums) 
    {
        int freq[] = new int[101];  // store frequencies of numbers
        int max = 0;

        // Step 1: Count frequencies and track maximum frequency
        for(int curr : nums) {
            freq[curr]++;
            max = Math.max(max, freq[curr]);
        }

        int count = 0;
        // Step 2: Sum frequencies of all elements with max frequency
        for(int curr : freq) {
            if(curr == max) {
                count += curr;
            }
        }

        return count;
    }
}

/*
🧮 Example Walkthrough
Input: nums = [1, 2, 2, 3, 1, 4]
Frequencies = {1 → 2, 2 → 2, 3 → 1, 4 → 1}
Max frequency = 2
Elements with max frequency = 1, 2
Answer = 2 + 2 = 4

⏱️ Time Complexity: O(n) + O(101) ≈ O(n)
💾 Space Complexity: O(1) (since freq size is fixed)

✅ Summary (Yuvraj Way):
We count frequencies, find the highest frequency, 
and then add up all the occurrences of elements having that frequency. 
Since nums[i] ≤ 100, array-based frequency counting is best and optimal.
*/

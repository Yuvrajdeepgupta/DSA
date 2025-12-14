/*
Problem:
https://leetcode.com/problems/smallest-absent-number/description/
(If link is different, update accordingly)

Intuition:
We need to find the smallest positive integer that is not present in the array.
The approach used here is:
1. Calculate the average of the array elements (sum / length) and start from one more than the average.
2. Use a HashSet to store all elements for O(1) lookups.
3. Keep incrementing the candidate until it's not present in the set.
This works because the absent number will definitely be close to or above the average.

Why this approach:
- Using a HashSet allows constant time checking for presence.
- Starting from sum/length + 1 gives us a reasonable starting point instead of starting from 1.
- This helps in reducing the number of checks in many cases.

Code:
*/

class Solution {
    public int smallestAbsent(int[] nums) {
        // Calculate sum and store elements in set for fast lookup
        double sum = 0;
        Set<Integer> set = new HashSet<>();
        for(int curr : nums) {
            sum += curr;
            set.add(curr);
        }

        // Start from (sum/length) + 1
        int curr = (int)((sum / nums.length) + 1);

        // Ensure the starting candidate is at least 1
        if(curr <= 0) {
            curr = 1;
        }

        // Keep incrementing until we find a number that's not in the set
        while(set.contains(curr)) {
            curr++;
        }

        return curr;
    }
}

/*
Example Walkthrough:
Input: nums = [1, 2, 3, 5]
Step 1: sum = 1+2+3+5 = 11
Step 2: average = 11 / 4 = 2.75
Step 3: curr = 2.75 + 1 = 3.75 → 3
Step 4: curr = 3 → present → increment
Step 5: curr = 4 → not present → return 4
Output: 4

Time Complexity:
- O(n) → for iterating through the array and adding elements to the set.
- The while loop worst case runs for O(n) steps (if the numbers are contiguous).
Overall: O(n)

Space Complexity:
- O(n) → due to the HashSet storing all elements.

Summary:
✔ We use a HashSet to efficiently check for existing numbers.
✔ We intelligently choose a starting point using average + 1.
✔ We increment until we find the missing number.
✔ This solution works in linear time and space in most scenarios.
✔ It's simple, efficient, and easy to understand for interview discussions.
*/

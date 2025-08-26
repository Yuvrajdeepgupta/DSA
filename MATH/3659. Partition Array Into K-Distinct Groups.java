# Question Link:
https://leetcode.com/problems/partition-array-into-k-distinct-groups/

# Solution Video Link:
https://www.youtube.com/watch?v=IJs2xKNbR5g&ab_channel=RajanKeshari%28CSE-IITDhanbad%29
https://youtu.be/quujgfoQ9UQ?si=8O2qCrtAZV19tpBY


WHAT IS PIGEONHOLE PRINCIPLE???
------------------------------------------------------------
## Problem Breakdown:
- The Pigeonhole Principle states:
  "If n items are put into m containers, with n > m, then at least one container must contain more than one item."
- In competitive programming:
  - This is used to **prove existence** of some value, arrangement, or repetition.
  - Often applied to arrays, numbers, or objects to guarantee a duplicate or constraint.

------------------------------------------------------------
## Intuition & Why This Works:
- Consider `n` pigeons (elements) and `m` holes (groups/slots).  
- If n > m, some hole must have at least `ceil(n/m)` pigeons.  
- Examples:
  1) Any array of size n+1 with elements from 1..n → must have a duplicate.
  2) If you choose k numbers from 1..2k → at least one number must repeat modulo k.  

- In problems:
  - Count items and containers.
  - Check if n > m → existence is guaranteed.
  - Sometimes, calculating `ceil(n/m)` gives minimum occurrences required in one bucket.



------------------------------------------------------------------------------------------------------


ACTUAL QUESTION
------------------------------------------------------------
## Problem Breakdown:
- We are given an integer array `nums` and an integer `k`.
- We need to check whether it is possible to partition the array into some groups such that:
    1) Each group has exactly `k` elements.
    2) No two equal elements appear in the same group.

------------------------------------------------------------
## Intuition & Why This Works:
Key Observations:
1) Total number of groups = n / k (where n is nums.length).
2) Every group has k elements, so:
   - If `n % k != 0` or `n < k`, it's impossible to divide evenly → return false.
3) We must ensure:
   - There are at least k distinct elements (otherwise we cannot fill one group properly).
   - The most frequent element must not appear more than the number of groups; otherwise, it would have to go into the same group multiple times.

Mathematical insight:
- Number of groups = n / k.
- If an element appears more than n/k times, it's impossible to distribute it without repetition in any group.

Hence conditions:
- n % k == 0
- distinct >= k
- max frequency <= n/k

If all hold true → return true, else false.

------------------------------------------------------------
## Code Implementation (Java):

class Solution {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        
        // Condition 1: total elements must divide evenly
        if (n % k != 0 || n < k) {
            return false;
        }

        int distinct = 0;
        int maxfreq = 0;
        int freq[] = new int[100001]; // constraint: nums[i] <= 10^5

        // Count frequency & distinct elements
        for (int curr : nums) {
            if (freq[curr] == 0) distinct++;
            freq[curr]++;
            maxfreq = Math.max(maxfreq, freq[curr]);
        }

        // Condition 2: at least k distinct elements
        if (distinct < k) {
            return false;
        }

        // Condition 3: max frequency <= number of groups
        return maxfreq <= n / k;
    }
}

------------------------------------------------------------
## Example Dry Run:

Example 1:
nums = [1,2,3,4,5,6], k = 3
- n = 6, n % k = 0 (good)
- freq: all numbers 1 time, distinct = 6, maxfreq = 1
- n/k = 2, maxfreq=1 <= 2 → true (possible partition)

Example 2:
nums = [1,1,1,2,2,2], k = 3
- n=6, n%k=0
- freq: 1 appears 3 times, 2 appears 3 times, distinct=2
- distinct < k (2<3) → false

------------------------------------------------------------
## Time Complexity:
- Counting frequency: O(n)
- Finding max freq: O(n) (during the same pass)
Overall: O(n)

## Space Complexity:
- Extra array freq[100001] → O(1) constant (since max size is fixed by constraints)

------------------------------------------------------------
## Yuvraj Way Summary (Easy Words):
- We need to divide array into groups of size k with no repeats.
- Check 3 things:
  1) n divisible by k
  2) at least k distinct elements
  3) max freq <= n/k
- If all pass → return true; else false.
- Simple O(n) solution using frequency array.

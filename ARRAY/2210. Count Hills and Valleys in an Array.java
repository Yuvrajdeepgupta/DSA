// ✅ Leetcode Daily Challenge - Count Hills and Valleys in an Array
// 🔗 Problem Link: https://leetcode.com/problems/count-hills-and-valleys-in-an-array/description/?envType=daily-question&envId=2025-07-27
// 📽️ Video Solution: https://youtu.be/NK83TS3xi0c?si=c8FvjYa7VWP-cxhu

/* ⭐ Problem Statement:
You are given a 0-indexed integer array nums. An index i is part of a hill if:
    - nums[i - 1] < nums[i] > nums[i + 1]
And it's a valley if:
    - nums[i - 1] > nums[i] < nums[i + 1]
Ignore equal adjacent elements when evaluating.

Return the number of hills and valleys in the array.
*/


// ✅✅ APPROACH 1: Preprocess to remove duplicates → Then check triplets
// Intuition:
// - Repeated values like 2,2,2 don't help in hill/valley formation.
// - So we first create a simplified list without consecutive duplicates.
// - Then for each triplet (i-1, i, i+1), check if it's a hill or valley.

class Solution {
    public int countHillValley(int[] nums) 
    {
        int n = nums.length;
        int ans = 0;
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        // Step 1: Remove consecutive duplicates
        for (int i = 1; i < n; i++) 
        {
            if (nums[i] != nums[i - 1]) 
            {
                list.add(nums[i]);
            }
        }

        // Step 2: Check hill/valley in the cleaned list
        n = list.size();
        for (int i = 1; i < n - 1; i++) 
        {
            int prev = list.get(i - 1);
            int curr = list.get(i);
            int next = list.get(i + 1);

            if ((prev < curr && curr > next) || (prev > curr && curr < next)) 
            {
                ans++;
            }
        }

        return ans;
    }
}


// ✅✅ APPROACH 2: Optimal (Without Using Extra Space)
// Intuition:
// - Avoid making a new list.
// - Keep track of last unique element (`prev`).
// - Compare each triplet (prev, curr, next) in a single pass, skipping duplicates.

class Solution {
    public int countHillValley(int[] nums) 
    {
        int n = nums.length;
        int ans = 0;

        int prev = nums[0]; // last unique number seen
        for (int i = 1; i < n - 1; i++) 
        {
            // Skip duplicates on the right side
            if (nums[i] == nums[i + 1]) continue;

            if ((prev < nums[i] && nums[i] > nums[i + 1]) || 
                (prev > nums[i] && nums[i] < nums[i + 1])) 
            {
                ans++;
            }

            prev = nums[i]; // update last unique
        }

        return ans;
    }
}


/* ✅ Dry Run for Input: [1,1,10,10,10,10]
After duplicate removal → [1, 10]
→ No i exists where i-1, i, i+1 present → Answer: 0


✅ Dry Run for Input: [2,1,3,2,1,3]
No duplicates → Check (2,1,3): valley ✅
Check (1,3,2): hill ✅
Check (3,2,1): valley ✅
Check (2,1,3): valley ✅
→ Total = 4 hills/valleys
*/


/* ✅ Time & Space Complexity:

Approach 1 (using ArrayList):
- Time: O(n)
- Space: O(n) → due to list

Approach 2 (optimal):
- Time: O(n)
- Space: O(1)

*/


/* ✅ Yuvraj's Summary (Notepad Copy):

- Ignore equal adjacent elements while checking.
- For every triple (a,b,c), b is hill if a<b>c and valley if a>b<c.
- If needed, first remove duplicates to simplify triplet comparisons.
- Second solution skips duplicates on-the-fly using `prev` pointer.

→ Second solution is optimal in both time and space (O(n), O(1))
→ Just do one clean left-to-right pass keeping previous unique number
*/

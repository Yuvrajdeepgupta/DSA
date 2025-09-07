/*
✅ Problem: Minimum Operations to Equalize Array
👉 Problem Link: https://leetcode.com/problems/minimum-operations-to-equalize-array/
👉 Solution Video: https://youtu.be/3OwyPpkc2Hg?si=RVPiDXE0dzKPvcYL

📖 Intuition:
- The problem asks for the minimum number of operations needed to make all elements equal.
- If all elements are already equal, then 0 operations are needed.
- If there's at least one element different from others, we can make all equal in 1 operation by changing all to one of the existing numbers.
- So, we just need to check if there's any difference between adjacent elements to decide if 1 operation is needed or not.

✅ Approach:
1. Traverse the array from index 1 to end.
2. If any element is different from its previous element, return 1.
3. If no differences are found after the loop, return 0.

📌 Code:
class Solution {
    public int minOperations(int[] nums) 
    {
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] != nums[i - 1]) {
                return 1; // Found different element, so one operation is enough
            }
        }
        return 0; // All elements are equal
    }
}

📊 Time Complexity:
- O(n), where n is the length of nums.
- In the worst case, we have to check all elements once.

📊 Space Complexity:
- O(1), constant space is used as we only use a few variables.

🟠 Summary (Yuvraj way):
- Check if all elements are same → return 0.
- If any element is different → return 1.
- Simple linear scan solves it in O(n) time.
- No extra space needed, only comparison logic.
- Great question to spot patterns and edge cases easily!
*/

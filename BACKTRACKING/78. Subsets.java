/*
✅ LeetCode Problem: Subsets
🔗 Problem Link: https://leetcode.com/problems/subsets/description/
🎥 Solution Video: https://youtu.be/p4bP_FIXGWw?si=Xgu7FpxRTlKxGrgu

📖 Problem Statement:
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. You can return the solution in any order.

---

💡 Intuition:
We are asked to generate all possible subsets of a given set of numbers.  
This is a classical recursion and backtracking problem where:

✔ At each step, we decide to either include or skip an element  
✔ We build subsets by exploring every possibility  
✔ We use recursion to go deeper into choices  
✔ We use backtracking (undo step) to explore other branches of choices after returning from recursion

The idea is to start from the first index, try adding each element one by one, explore the next possibilities, and then remove the element to backtrack and explore other options.

We don’t need a separate visited structure because each recursive call keeps its own state using the current subset list.

---
✅ Example:
Input: nums = [1, 2, 3]
Output:
[]
[1]
[1, 2]
[1, 2, 3]
[1, 3]
[2]
[2, 3]
[3]
---

✅ Approach Explained Step by Step:

1. We initialize an answer list `ans` to store all subsets.
2. We start recursion from index 0 with an empty subset `list`.
3. At every recursive call:
   ✔ We first add the current state of the subset into `ans` (even if it’s empty).
   ✔ We then loop from index `i` to the end of the array:
      - Add the current element `nums[j]` to `list`.
      - Explore deeper by recursively calling the function with `j + 1`.
      - Once recursion returns, remove the last element (undo step) to explore other possibilities.

This ensures that:
✔ All possible subsets are generated  
✔ No duplicates occur because we only move forward in the array  
✔ Backtracking properly restores the state before exploring the next choice

---

✅ Code with detailed comments:

class Solution  
{
    // List to store all subsets
    List<List<Integer>> ans;

    public List<List<Integer>> subsets(int[] nums) 
    {
        ans = new ArrayList<>();
        
        // Start recursion with empty subset and index 0
        solve(nums, new ArrayList<Integer>(), 0);
        
        return ans;
    }

    public void solve(int nums[], List<Integer> list, int i)
    {
        // Add a copy of the current subset to the answer list
        ans.add(new ArrayList<>(list));
        
        // Explore all elements starting from index i
        for(int j = i; j < nums.length; j++)
        {
            int curr = nums[j];
            
            // Include the current element in the subset
            list.add(curr);
            
            // Explore further subsets including this element
            solve(nums, list, j + 1);
            
            // Undo the inclusion for backtracking
            list.remove(list.size() - 1);
        }
    }
}

---

📊 Time Complexity:
- Every element has two choices → include or exclude → total of 2^n subsets
- For each subset, copying the subset into the answer takes O(n) time  
✔ Total time → O(n * 2^n)

📂 Space Complexity:
- Recursion stack can go as deep as O(n)
- The answer list stores up to 2^n subsets, each with up to n elements  
✔ Total space → O(n * 2^n)

---

✅ Dry Run Example:

For nums = [1, 2]:

Step 1 → start with empty subset []

Step 2 → include 1 → [1], explore further  
 → include 2 → [1, 2], explore → end → add [1, 2]  
 → undo → [1], exclude 2 → add [1]

Step 3 → undo → [], exclude 1 → explore  
 → include 2 → [2], explore → end → add [2]  
 → undo → [], exclude 2 → add []

Final output → [[], [1], [1, 2], [2]]

---

✅ Why this approach is correct:
✔ It explores every possible subset by including or excluding elements at every step  
✔ The recursion depth and backtracking ensure that all branches are covered without duplicates  
✔ Using `new ArrayList<>(list)` ensures that the current state is saved and not altered in future calls

---

📌 Yuvraj-style Summary:
This problem is a perfect way to practice recursion and backtracking. At each step, we decide whether to include the current number or not, and we explore all paths by going deeper with recursion. The undo step is critical because it ensures that after exploring one path, we can remove the last added element and explore other possibilities without interference. We generate all subsets and copy them into the answer list at every step, covering subsets of all sizes. The problem has exponential complexity, but it’s ideal for understanding recursion patterns!

✔ Ready to copy-paste in your notes!
*/

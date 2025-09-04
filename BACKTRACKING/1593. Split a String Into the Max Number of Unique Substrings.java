📌 Question Link: https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/description/
🎥 Solution Link: https://youtu.be/sTzh1L7BkQA?si=YDkolYrKUquo7HwW

------------------------------------------------------------
🔹 Problem: Split a String Into the Max Number of Unique Substrings
------------------------------------------------------------

You are given a string s. 
Your task is to split s into as many unique substrings as possible.
Return the maximum number of unique substrings that can be obtained.

------------------------------------------------------------
🔹 Intuition
------------------------------------------------------------

1. We need to split the string into substrings such that:
   - Every substring is **unique**.
   - We maximize the number of substrings.

2. This clearly hints at a **backtracking problem**:
   - At each index, we can cut the string in multiple ways.
   - If the chosen substring is not already taken, we continue exploring.
   - Use a HashSet to keep track of substrings already used.

3. Our answer is the maximum count we can achieve across all valid partitions.

------------------------------------------------------------
🔹 Approach
------------------------------------------------------------

- Start from index `i = 0`.
- At each step, try all possible substrings `s.substring(i, j+1)` (where j ≥ i).
- If substring is not in HashSet:
    - Add it (do).
    - Recursively call for the next index (explore).
    - Remove it back (undo → backtracking).
- Keep track of the maximum count found.

This ensures we explore all possible splits while enforcing uniqueness.

------------------------------------------------------------
🔹 Dry Run (Example)
------------------------------------------------------------

s = "aba"

- Start i=0
   - Substring "a"
       → set = {"a"}, count=1
       → move to i=1
         - Substring "b"
             → set={"a","b"}, count=2
             → move to i=2
               - Substring "a"
                 → already in set → skip
             → Backtrack
         - Substring "ba"
             → set={"a","ba"}, count=2
             → move to i=3 (end)
             → max=2
       → Backtrack
   - Substring "ab"
       → set={"ab"}, count=1
       → move to i=2
         - Substring "a"
             → set={"ab","a"}, count=2
             → move to i=3 → max=2
       → Backtrack
   - Substring "aba"
       → set={"aba"}, count=1
       → move to end → max=1

✅ Answer = 2

------------------------------------------------------------
🔹 Code (Java)
------------------------------------------------------------

class Solution 
{
    public int maxUniqueSplit(String s) 
    {
        return solve(0, s, s.length(), new HashSet<>(), 0);
    }

    public int solve(int i, String s, int n, HashSet<String> set, int count)
    {
        if(i >= n) return count;
        
        int max = 0;
        for(int j = i; j < n; j++)
        {
            String sb = s.substring(i, j+1);

            if(!set.contains(sb))
            {
                // do
                set.add(sb);

                // explore
                max = Math.max(solve(j+1, s, n, set, count+1), max);

                // undo
                set.remove(sb);
            } 
        }

        return max;
    }
}

------------------------------------------------------------
🔹 Time Complexity
------------------------------------------------------------

- At each index, we try all possible substrings (O(n) choices).
- Each recursive call explores further splits.
- Worst-case complexity = **O(n * 2^n)**  
   (since in the worst case we may explore almost all partitions).

------------------------------------------------------------
🔹 Space Complexity
------------------------------------------------------------

- HashSet stores substrings → O(n) (in terms of recursion depth and storage).
- Recursion stack depth → O(n).

Overall: **O(n)** auxiliary space.

------------------------------------------------------------
🔹 Yuvraj Style Summary
------------------------------------------------------------

- Question is about **splitting string into max unique parts**.
- Straightforward **backtracking with HashSet**.
- Try all substrings → pick only unique → recurse → backtrack.
- Answer = maximum count of substrings we can achieve.
- Time is exponential but that’s fine since input size is small.

🔥 Main punchline: "Cut the string in every possible way, but only keep unique pieces. Use backtracking + HashSet, and boom, max split mil gaya."

# Problem: Combination Sum
LeetCode Link: https://leetcode.com/problems/combination-sum/
Solution Video Link: https://youtu.be/jkgZw2WEaqA?si=Y8XACv6ZVeQq2ILa

-------------------------------------------------------------------------------
## 🔎 Problem Breakdown
- We are given an array of distinct integers `candidates` and a target integer.
- We need to return all unique combinations of candidates where the chosen numbers sum to the target.
- Each number in candidates can be chosen an unlimited number of times.
- The order of elements in a combination does not matter (i.e., [2,3] and [3,2] are considered the same).

-------------------------------------------------------------------------------
## 🧠 Intuition
- This is a **backtracking problem**.
- At every step, for each candidate, we have two choices:
  1. **Pick** the candidate → reduce target and continue with the same index (since reuse is allowed).
  2. **Skip** the candidate → move to the next index.
- If the running sum matches the target, we found a valid combination.
- If the sum exceeds target, we stop exploring that path.

-------------------------------------------------------------------------------
## ⚡ Why This Approach Works
- Backtracking systematically explores all possible combinations.
- By starting the loop from `i` (not 0), we avoid duplicate permutations like [2,3] and [3,2].
- Using a copy of the current path (`new ArrayList<>(ans)`) ensures results are stored correctly without being overwritten by future changes.

-------------------------------------------------------------------------------
## ✅ Java Code (Backtracking with sum tracking)

class Solution 
{
    List<List<Integer>> list;

    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {   
        list = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int i = 0, sum = 0;

        solve(i, sum, candidates, target, ans);
        return list;
    }

    public void solve(int i, int sum, int arr[], int target, List<Integer> ans)
    {
        if(i >= arr.length || sum > target)
        {
            return;
        }

        if(sum == target)
        {
            list.add(new ArrayList<>(ans)); // store a copy
            return;
        }

        for(int j = i; j < arr.length; j++)
        {
            int curr = arr[j];

            ans.add(curr);
            sum += curr;

            solve(j, sum, arr, target, ans); // stay at j because reuse allowed

            ans.remove(ans.size() - 1); // backtrack
            sum -= curr;
        }
    }
}

-------------------------------------------------------------------------------
## 📝 Example Dry Run
candidates = [2,3,6,7], target = 7

- Start with [] sum=0
- Pick 2 → [2], sum=2
  - Pick 2 → [2,2], sum=4
    - Pick 2 → [2,2,2], sum=6
      - Pick 2 → [2,2,2,2], sum=8 > target ❌
      - Try 3 → [2,2,3], sum=7 ✅ add
- Backtrack…
- Pick 3 → [3], sum=3
  - Pick 3 → [3,3], sum=6
    - Pick 3 → [3,3,3], sum=9 > target ❌
- Backtrack…
- Pick 7 → [7], sum=7 ✅ add

Final Answer = [[2,2,3], [7]]

-------------------------------------------------------------------------------
## ⏱ Time Complexity
- Exponential in nature.
- In the worst case, each candidate can be used multiple times until the target is exceeded.
- Rough bound: **O(2^T)** where T = target value.
- More practically, complexity depends on branching factor and target/candidates.

-------------------------------------------------------------------------------
## 💾 Space Complexity
- O(target) → recursion depth (since in the worst case, we may keep adding the smallest candidate until we exceed target).
- Plus extra space for storing valid combinations in the result.

-------------------------------------------------------------------------------
## ✨ Yuvraj Style Summary
- Yeh question ek standard **backtracking** template hai.
- Har step pe option hai: pick karo ya skip karo.
- Target zero hua toh list me add kardo (new ArrayList<>(ans) is must).
- For loop ko `i` se start karna important hai warna duplicate combinations aayenge.
- TC exponential hai but acceptable for given constraints.
- SC recursion stack + result storage hai.
- Backtracking + reuse allowed = **Combination Sum** 🏆

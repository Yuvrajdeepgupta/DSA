📌 Question Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
🎥 Solution Reference: https://youtu.be/vgnhZzw-kfU?si=EvucZfUatSuo3VPe

--------------------------------------------------------------------------------
🔥 Problem Breakdown:
We are given a digit string (digits 2–9) representing keys on a phone keypad. 
Each digit maps to certain letters:
2 -> "abc", 3 -> "def", ..., 9 -> "wxyz".

We need to return all possible letter combinations that the digits could represent.

Example:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

--------------------------------------------------------------------------------
💡 Intuition:
This is a classic "all possible combinations" problem.
- At each digit, we can choose one letter from its mapping.
- Once chosen, we move to the next digit and repeat.
- This forms a recursive tree structure — making it a perfect fit for **backtracking**.

Key idea:
- Build partial strings using a StringBuilder.
- Recurse for next digit.
- Backtrack by removing last char and trying the next option.

--------------------------------------------------------------------------------
🛠️ Why Backtracking + StringBuilder?
- We must generate all k^n combinations anyway (where n = length of digits, k = avg letters per digit).
- StringBuilder is mutable → avoids creating new string objects on every recursion step.
- Backtracking ensures we explore all paths efficiently and undo changes after exploring.

This is the **most optimal approach** possible.

--------------------------------------------------------------------------------
✅ Code (Java):

class Solution 
{
    String arr[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) 
    {
        if (digits.isEmpty()) return new ArrayList<>();

        return solve(digits, 0, new ArrayList<>(), new StringBuilder());
    }

    public List<String> solve(String digits, int idx, List<String> ans, StringBuilder sb)
    {
        if (idx >= digits.length())
        {
            ans.add(sb.toString());
            return ans;
        }

        String curr = arr[digits.charAt(idx) - '0'];
        for (int i = 0; i < curr.length(); i++)
        {
            sb.append(curr.charAt(i));
            solve(digits, idx + 1, ans, sb);
            sb.setLength(sb.length() - 1); // backtrack
        }

        return ans;
    }
}

--------------------------------------------------------------------------------
🔎 Example Walkthrough:
Input: "23"

- Start at idx=0 → digit '2' → "abc"
   - pick 'a' → recurse idx=1 → digit '3' → "def"
       - pick 'd' → "ad" → add to ans
       - backtrack → "a"
       - pick 'e' → "ae" → add to ans
       - pick 'f' → "af" → add to ans
   - backtrack to root
   - pick 'b' → repeat → "bd","be","bf"
   - pick 'c' → repeat → "cd","ce","cf"

Final ans = ["ad","ae","af","bd","be","bf","cd","ce","cf"]

--------------------------------------------------------------------------------
⏱️ Time Complexity:
- Each digit has up to 4 letters.
- For n digits → total combinations = O(4^n).
- Building each string takes O(n).
- Final complexity: O(4^n * n)

📦 Space Complexity:
- Recursion depth = O(n).
- Output list stores O(4^n) strings, each of length n.
- Overall: O(n) auxiliary + O(4^n * n) output.

--------------------------------------------------------------------------------
🎯 Yuvraj-Summary (Easy Style):
- This is a **backtracking problem** where every digit maps to letters.
- We try every letter for the current digit, recurse for the next, and backtrack.
- StringBuilder helps in avoiding extra string creations.
- TC is exponential anyway (O(4^n * n)), so this backtracking solution is already optimal.
- Clean, interview-ready code ✅

====================================================
LeetCode 3803 – Count Residue Prefixes
URL: https://leetcode.com/problems/count-residue-prefixes/
====================================================

🔹 Problem Breakdown
-------------------
You are given a string s consisting of lowercase English letters.

A prefix of s is called a residue if:
Number of distinct characters in the prefix
== (length of the prefix) % 3

We need to count how many such prefixes exist.

Important:
- Prefix must be non-empty
- Prefix always starts from index 0

----------------------------------------------------

🔹 Core Observation / Intuition
------------------------------
For each prefix ending at index i:
- Prefix length = i + 1
- We track the number of distinct characters so far

Key insight:
- (i + 1) % 3 can be 0, 1, or 2
- If (i + 1) % 3 == 0, then distinct characters must be 0
- This is impossible for a non-empty prefix

Hence:
→ Prefixes whose length is divisible by 3 are automatically invalid
→ We just simulate prefixes and apply the rule directly

----------------------------------------------------

🔹 Approach 1: HashSet Based (Simple & Intuitive)
------------------------------------------------
Idea:
- Use HashSet to store distinct characters in the current prefix
- For every character added, check:
      set.size() == (i + 1) % 3

Why HashSet?
- Stores only unique characters
- Easy to understand
- Works well for small constraints

----------------------------------------------------

🔹 Java Code (HashSet Version)
------------------------------
class Solution 
{
    public int residuePrefixes(String s) 
    {
        Set<Character> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++)
        {
            set.add(s.charAt(i));

            if (set.size() == ((i + 1) % 3))
            {
                count++;
            }
        }

        return count;
    }
}

----------------------------------------------------

🔹 Approach 2: Boolean Array Based (Most Optimized)
--------------------------------------------------
Idea:
- Since characters are lowercase English letters,
  we can replace HashSet with boolean[26]
- Maintain a running count of distinct characters
- Faster and more memory-efficient (constant factors)

Why better?
- No hashing overhead
- Faster in practice
- Interview-preferred solution

----------------------------------------------------

🔹 Java Code (Optimized Version)
--------------------------------
class Solution 
{
    public int residuePrefixes(String s) 
    {
        boolean[] set = new boolean[26];
        int distinct = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++)
        {
            int idx = s.charAt(i) - 'a';

            if (!set[idx])
            {
                set[idx] = true;
                distinct++;
            }

            if (distinct == (i + 1) % 3)
            {
                count++;
            }
        }

        return count;
    }
}

----------------------------------------------------

🔹 Example Walkthrough
---------------------
Input: "bob"

Prefix "b"
- distinct = 1
- length % 3 = 1
→ valid

Prefix "bo"
- distinct = 2
- length % 3 = 2
→ valid

Prefix "bob"
- distinct = 2
- length % 3 = 0
→ invalid

Answer = 2

----------------------------------------------------

🔹 Time & Space Complexity
-------------------------
Both Approaches:
Time Complexity: O(n)
Space Complexity: O(1)

----------------------------------------------------

🔹 Final Verdict (When to Use What)
----------------------------------
- HashSet version → clarity & readability
- Boolean array version → maximum optimization & interview favorite

----------------------------------------------------

🔹 Yuvraj-style Summary
----------------------
Prefix bante jao,
distinct characters count karte jao,
aur bas ek rule lagao:

distinct == (length % 3)

Length agar 3 ka multiple hua,
toh prefix khud hi reject ho jayega.

HashSet wala simple hai,
boolean array wala fastest hai.

Logic same,
implementation better.
====================================================

//que link-https://leetcode.com/problems/delete-characters-to-make-fancy-string/description/?envType=daily-question&envId=2025-07-21
//video link- https://youtu.be/LEc1tjoXV-A?si=1zzWmbeioSaOuLKu

/*
🔶 PROBLEM: Make Fancy String
LeetCode: You are given a string s. A fancy string is a string where no three consecutive characters are the same.
Return the longest possible fancy string that can be made by deleting some characters from the original string.

🎯 GOAL: Remove characters so that no 3 same characters appear consecutively.
         Keep the string as long as possible, just remove extra duplicates.

----------------------------------------------------------------------------------------------------

🔍 INTUITION:
- We need to make sure that we don’t have 3 same characters in a row.
- This can be done by keeping a counter for how many times the current character is appearing in a row.
- If count < 3 → we append it.
- If count == 3 → we skip appending.

----------------------------------------------------------------------------------------------------

💡 WHY THIS APPROACH:
- No need to track total frequency (brute-force overkill idea).
- We only care about **consecutive** same characters.
- So we track:
  - `last` character
  - `count` of how many times that same character has occurred in a row

----------------------------------------------------------------------------------------------------

🧪 DRY RUN:
Input: "aaabaaaaaabb"

Step-by-step:
→ a → append (count = 1)
→ a → append (count = 2)
→ a → skip (count = 3)
→ b → append, reset count (count = 1)
→ a → append (count = 1)
→ a → append (count = 2)
→ a → skip
→ a → skip
→ a → skip
→ b → append, reset count (count = 1)
→ b → append (count = 2)

✅ Final Output: "aabaaabb"

----------------------------------------------------------------------------------------------------

✅ FINAL OPTIMAL CODE (O(n)) – CLEAN & EFFICIENT
*/

class Solution 
{
    public String makeFancyString(String s) 
    {
        int n = s.length();
        if(n == 1) return s;   // Base case: only one character

        char last = s.charAt(0);          // Last character seen
        StringBuilder ans = new StringBuilder();
        int count = 1;                    // How many times current char repeated
        ans.append(last);                // Append first char

        for(int i = 1; i < n; i++)
        {
            char curr = s.charAt(i);

            if(curr == last && count < 2)    // Less than 2 repeated same chars
            {
                ans.append(curr);
                count++;
            }
            else if(curr != last)           // New char → reset count
            {
                ans.append(curr);
                count = 1;
                last = curr;
            }
            // else case: curr == last && count == 2 → skip it
        }

        return ans.toString();               // Final fancy string
    }
}

----------------------------------------------------------------------------------------------------

📊 TIME COMPLEXITY:
- O(n), where n is the length of the input string
- We traverse the string once

📦 SPACE COMPLEXITY:
- O(n), for the StringBuilder used to store the result

----------------------------------------------------------------------------------------------------

🧾 YUVRAJ STYLE SUMMARY FOR NOTEPAD:

// ✅ "Make Fancy String" – remove 3 or more same consecutive chars
// ✅ Used: Simple character counter & StringBuilder
// ✅ Track last character and how many times it appeared in a row
// ✅ Skip adding character if count is 2 and same char comes again
// ✅ No need of frequency array or HashMap (overkill for this problem)
// ✅ Time: O(n), Space: O(n)
// ✅ Clean, fast, optimal – perfect for interviews

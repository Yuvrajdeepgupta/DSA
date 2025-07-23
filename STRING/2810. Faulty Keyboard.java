🧠 Leetcode Question:
https://leetcode.com/problems/reverse-prefix-of-word/

🎥 Solution Video:
https://youtu.be/mp6m6XKhQQs?si=m2ISxDk5F7DXxCOD

🧾 Problem:
Given a string word and a character ch, reverse the segment from start of the string to the first occurrence of ch (inclusive).
Return the resulting string. If ch is not found, return the word as it is.

Example:
Input: word = "abcdefd", ch = 'd'
Output: "dcbaefd"

--------------------------------------------------

🧠 Intuition:
- Hume sirf starting se leke first 'ch' tak reverse karna hai.
- Uske baad ka part jaisa ka waisa hi rehna hai.
- StringBuilder ka use karenge prefix reverse karne ke liye.
- indexOf() se ch ka first position mil jaata hai.

--------------------------------------------------

✅ Steps:
1. Find the index of first occurrence of 'ch' using indexOf().
2. If not found, return the word as is.
3. Reverse the prefix (0 to index).
4. Append the rest of the string (index+1 to end).
5. Return combined string.

--------------------------------------------------

✅ Java Code:
class Solution {
    public String reversePrefix(String word, char ch) 
    {
        int idx = word.indexOf(ch);  // find first occurrence

        if (idx == -1) 
        {
            return word;  // if not found, return original
        }

        StringBuilder sb = new StringBuilder(word.substring(0, idx + 1));
        sb.reverse();  // reverse prefix part

        return sb.toString() + word.substring(idx + 1);  // combine with rest
    }
}

--------------------------------------------------

🧪 Dry Run:
Input: word = "abcdefd", ch = 'd'

Step 1: index = 3
Step 2: prefix = "abcd" → reversed = "dcba"
Step 3: suffix = "efd"
Final = "dcbaefd"

--------------------------------------------------
⏱️ Time Complexity:
O(n) → indexOf + reversing + substring

📦 Space Complexity:
O(n) → StringBuilder and final result string

--------------------------------------------------

🧠 Yuvraj Style Summary:

// Problem:
// - Reverse from start till first occurrence of 'ch'
// - Append rest as-is

// Intuition:
// - Use indexOf(ch) → gives the position to reverse
// - StringBuilder helps to reverse efficiently
// - Just join reversed prefix and suffix

// Code:
// 1. Find idx = word.indexOf(ch)
// 2. If idx == -1 → return word
// 3. Reverse prefix using StringBuilder
// 4. Return reversed prefix + rest of word

// Time: O(n)
// Space: O(n)

--------------------------------------------------

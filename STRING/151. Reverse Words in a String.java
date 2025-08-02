/*
🔗 Question Link: https://leetcode.com/problems/reverse-words-in-a-string/description/
🎥 Solution Video (by Aditya Verma): https://youtu.be/mdej8UiRCkI?si=uvgHQTfmXkxLjLkn

================================================================
🧠 Problem Statement:
Given an input string `s`, reverse the order of the **words**.

- A word is defined as a sequence of **non-space characters**.
- The words in `s` will be separated by at least one space.
- The returned string should only have **a single space** separating words.
- **No leading or trailing spaces** should be in the result.

Example:
Input:  "  hello   world  "
Output: "world hello"

================================================================
🔍 Breakdown:
There are 2 approaches:

---------------------------------------------------------------
✅ Approach 1: Using `trim()` and `split("\\s+")` (Simple & Clean)
- Use `trim()` to remove leading/trailing spaces.
- Use regex `split("\\s+")` to split the string into words (handles multiple spaces).
- Reverse the array of words and join them with a single space.

🕰️ Time Complexity: O(N) → where N is the length of string `s`
📦 Space Complexity: O(N) → storing words in array and building final string

💡 Note: This is not "optimal-optimal" but clean and acceptable for interviews.

🔑 Code:
*/
class Solution {
    public String reverseWords(String s) 
    {
        s = s.trim(); // remove leading/trailing spaces
        String[] arr = s.split("\\s+"); // split by one or more spaces

        StringBuilder ans = new StringBuilder();
        for(int i = arr.length - 1; i >= 0; i--)
        {
            ans.append(arr[i]).append(" ");
        }

        return ans.toString().trim(); // final cleanup
    }
}

/*
---------------------------------------------------------------
✅ Approach 2: Optimal — In-place Scanning from Right to Left

📌 Intuition:
Instead of splitting and storing words, scan from right to left:
- When we hit a valid word (non-space), mark its end.
- When we hit a space after a word, mark the start and add that word to result.
- Continue until the start of the string.
- This avoids creating extra arrays and uses `StringBuilder` directly.

🛠️ Helper:
isValid(char c): checks if c is alphanumeric → for cleaner parsing

🕰️ Time Complexity: O(N)
📦 Space Complexity: O(1) extra (only output space used)

🔑 Code:
*/
class Solution {
    public String reverseWords(String s) 
    {
        boolean flag = false;
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        int start = -1, end = -1;

        for(int i = n - 1; i >= 0; i--)
        {
            char curr = s.charAt(i);

            if(curr == ' ' && flag)
            {
                flag = false;
                start = i + 1;
                ans.append(s.substring(start, end + 1)).append(" ");
            }
            else if(isValid(curr) && !flag)
            {
                flag = true;
                end = i;
            }
        }

        // Handle the first word (if loop ended while reading a word)
        if(flag)
        {
            ans.append(s.substring(0, end + 1));
        }

        return ans.toString().trim(); // remove trailing space
    }

    private boolean isValid(char c)
    {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }
}

/*
================================================================
🧾 Summary (Yuvraj Way):
- Question is about reversing words, not characters.
- First approach uses trim + regex split + join — clean but uses space.
- Second approach scans from right to left, builds result without splitting — optimal.
- Always trim your final answer to avoid extra spaces.
- Important string parsing logic — good for interviews.

🔥 Tagline: Not best, But gettin better ; )
*/

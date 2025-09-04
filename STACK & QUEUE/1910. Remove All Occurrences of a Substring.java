🔥 Problem: Remove All Occurrences of a Substring
Link: https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/
Solution Video: https://youtu.be/v6_q_fu1ev0?si=M4qv4grQYvJpJXKr

-------------------------------------------------------------------------------
📝 Problem Breakdown:
We are given a string `s` and a substring `part`.  
We need to repeatedly remove all occurrences of `part` from `s` until no more are left.  
Return the final string after all removals.

-------------------------------------------------------------------------------
💡 Intuition:
This problem is basically about:
- Iteratively scanning characters.
- Keeping track of the "current built string".
- Whenever the suffix of the built string matches `part`, we delete it.

We can implement this using:
1. A stack (store characters, pop when a match is found).
2. A StringBuilder (easier for checking/removing suffix).
3. An array-based simulation of a stack (optimal in terms of memory).

-------------------------------------------------------------------------------
🔨 Approaches & Code:

---------------------------------------------------
1️⃣ Stack-Based Solution (Using Deque)
---------------------------------------------------
class Solution 
{
    public String removeOccurrences(String s, String part) 
    {
        Deque<Character> stack = new ArrayDeque<>();
        int i = part.length() - 1;

        for (char curr : s.toCharArray())
        {
            stack.push(curr);

            if (!stack.isEmpty() && stack.peek() == part.charAt(i))
            {
                Deque<Character> temp = new ArrayDeque<>();

                while (!stack.isEmpty() && i >= 0 && stack.peek() == part.charAt(i))
                {
                    temp.push(stack.pop());
                    i--;
                }

                if (i >= 0) // partial match → push back
                {
                    while (!temp.isEmpty())
                    {
                        stack.push(temp.pop());
                    }
                }
            }

            i = part.length() - 1; // reset matcher
        }

        char ans[] = new char[stack.size()];
        int k = ans.length - 1;
        while (!stack.isEmpty())
        {
            ans[k--] = stack.pop();
        }

        return new String(ans);
    }
}

---------------------------------------------------
2️⃣ StringBuilder-Based Solution
---------------------------------------------------
class Solution 
{
    public String removeOccurrences(String s, String part) 
    {
        StringBuilder sb = new StringBuilder();
        int m = part.length();

        for (char curr : s.toCharArray())
        {
            sb.append(curr);

            if (sb.length() >= m && sb.substring(sb.length() - m).equals(part))
            {
                sb.setLength(sb.length() - m);
            }
        }

        return sb.toString();
    }
}

---------------------------------------------------
3️⃣ Optimal Array Simulation (Stack in O(1) space reuse)
---------------------------------------------------
class Solution 
{
    public String removeOccurrences(String s, String part) 
    {
        char[] arr = s.toCharArray();
        char[] ans = new char[arr.length];
        int i = 0;  // stack pointer
        int m = part.length();

        for (int j = 0; j < arr.length; j++) 
        {
            ans[i] = arr[j];
            i++;

            // check if last m characters form "part"
            if (i >= m) 
            {
                boolean match = true;
                for (int k = 0; k < m; k++) 
                {
                    if (ans[i - m + k] != part.charAt(k)) 
                    {
                        match = false;
                        break;
                    }
                }

                if (match) 
                {
                    i -= m; // pop m chars
                }
            }
        }

        return new String(ans, 0, i);
    }
}

-------------------------------------------------------------------------------
⏱️ Time Complexity:
- Stack & StringBuilder: O(n * m) in worst case  
  (for each new character we may check suffix of length m).
- Optimal Array Simulation: O(n * m) worst case too, but with less overhead.

📦 Space Complexity:
- Stack: O(n) for storing characters.
- StringBuilder: O(n).
- Array Simulation: O(n) for `ans[]`, no extra dynamic data structure.

-------------------------------------------------------------------------------
🧪 Example Walkthrough:
Input: s = "daabcbaabcbc", part = "abc"

Step 1: "daabcbaabcbc"
         → remove "abc" → "dabaabcbc"

Step 2: "dabaabcbc"
         → remove "abc" → "dabcbc"

Final Answer = "dabcbc"

-------------------------------------------------------------------------------
🎯 Yuvraj Way Summary:
- Think of this as **"build the answer as you go"**.
- Whenever the suffix matches `part`, delete it.
- Stack/StringBuilder both work, but the **array simulation** is neatest (direct stack emulation).
- Time ~ O(n*m), space ~ O(n).
- Clean & safe → StringBuilder,  
  Ultra-optimal & interview-swag → Array simulation.

-------------------------------------------------------------------------------

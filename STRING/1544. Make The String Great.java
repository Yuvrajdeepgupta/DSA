🔗 LeetCode Link: https://leetcode.com/problems/make-the-string-great/description/
📺 Solution Video: https://youtu.be/J43ZIltH3AY?si=HJEPSGTLerPhXjbh

🧠 Problem:
------------------
You are given a string `s`. A string is considered **good** if there are no two adjacent characters `s[i]` and `s[i + 1]` such that:
- One is a lowercase letter and the other is the same letter in uppercase (or vice versa).

👉 You need to keep removing such adjacent pairs until the string becomes good and return the resulting string.

🪄 Example:
------------------
Input:  s = "leEeetcode"  
Output: "leetcode"

Input:  s = "abBAcC"  
Output: ""

Input:  s = "s"  
Output: "s"

-----------------------------------------------
💡 Intuition:
-----------------------------------------------
- You can treat this problem like a stack.
- Add characters one by one:
  - If the top of the stack and current character cancel out (like 'a' and 'A'), then pop the top.
  - Else, push the character.

⚙️ The trick to check cancellation:
- They are the same letter but in opposite cases:
  - Their ASCII diff will be exactly 32.
  - So, `abs(a - b) == 32` also works.



-----------------------------------------------
✅ Approach 1: Using Stack
-----------------------------------------------
class Solution {
    public String makeGood(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char curr : s.toCharArray()) {
            if (!stack.isEmpty() && check(stack.peek(), curr)) {
                stack.pop();
            } else {
                stack.push(curr);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pollLast()); // so that order remains correct
        }

        return ans.toString();
    }

    public boolean check(char a, char b) {
        return (Math.min(a,b) <= 90 && Math.max(a,b) >= 97) ? 
            (Math.min(a,b) - 65) == (Math.max(a,b) - 97) : false;
    }
}


-----------------------------------------------
✅ Approach 2: Using StringBuilder like a Stack
-----------------------------------------------
- We use StringBuilder to simulate stack behaviour.
- Whenever a pair like `aA` or `Bb` is found, we delete the previous character.
- Otherwise, just append the character.

📦 Why StringBuilder?
- Efficient for appending and deleting last character.
- Better than actual Stack or Deque in terms of memory + speed here.

-----------------------------------------------
🧮 Time and Space Complexity:
-----------------------------------------------
Time:  O(n)  
- Every character is pushed and popped at most once.

Space: O(n)  
- In worst case, all characters are kept in the StringBuilder (when no cancellations happen).

-----------------------------------------------
🧾 Code (Optimal - Final Approach):
-----------------------------------------------
class Solution {
    public String makeGood(String s) 
    {
        StringBuilder ans = new StringBuilder();

        for (char curr : s.toCharArray())
        {
            int n = ans.length();

            if (n > 0 && check(ans.charAt(n - 1), curr))
            {
                ans.deleteCharAt(n - 1); // remove last char if it cancels with curr
            }
            else
            {
                ans.append(curr); // else add curr to result
            }
        }

        return ans.toString();
    }

    // Helper function to check if characters cancel each other
    public boolean check(char a, char b)
    {
        return (Math.min(a, b) <= 90 && Math.max(a, b) >= 97) ? (Math.min(a, b) - 65) == (Math.max(a, b) - 97) : false;
    }
}

-----------------------------------------------
🧠 Yuvraj-style Summary:
-----------------------------------------------
- Use StringBuilder like a stack.
- Keep checking if last char and current cancel out -> remove last.
- Otherwise just add.
- Fast, clean and O(n) time.
- StringBuilder is better than Deque or Stack here.

🔥 Tip: Remember, `char` diff of 32 means opposite case same letter!

✅ Ready for interviews. Copy-paste this and you’re sorted 💪


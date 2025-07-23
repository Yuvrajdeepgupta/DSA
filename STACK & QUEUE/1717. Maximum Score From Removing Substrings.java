/*******************************************************************************************

🔗 Leetcode Link:https://leetcode.com/problems/maximum-score-from-removing-substrings/?envType=daily-question&envId=2025-07-23
🎥 Solution Video: https://youtu.be/WTAjAcjSTqM?si=TuXfRNfkahstJUzj

🧠 Problem: Maximum Gain by Removing Substrings

You're given a string `s`, and two integers `x` and `y`.

You can perform the following operations as many times as you like:
- Remove substring "ab" and gain `x` points.
- Remove substring "ba" and gain `y` points.

Goal: Return the maximum points you can gain by removing these substrings optimally.

================================================================================================

🧠 Intuition:

- You can only remove one substring at a time, and you must do it greedily.
- So always remove the substring with **higher value first** (either "ab" or "ba") to maximize points.
- Use a stack to simulate removal.
- After removing the higher-value pair, run again on the remaining string for the second pair.

================================================================================================

🧰 Approach (Greedy + Stack):
1️⃣ If x > y → remove "ab" first  
2️⃣ If y > x → remove "ba" first  
3️⃣ Use a stack to process each pair in left-to-right order.
4️⃣ Once one round is done, push remaining chars into a second stack and repeat for the other pair.

================================================================================================

✅ Java Code:
*/

class Solution {
    public int maximumGain(String s, int x, int y) 
    {
        Deque<Character> stack1 = new ArrayDeque<>();

        // Determine which pair gives more score
        char behind = (x < y) ? 'b' : 'a';
        char front  = (x < y) ? 'a' : 'b';
        int inc = Math.max(x, y);
        int ans = 0;

        // First pass - remove max value substring
        for (char curr : s.toCharArray()) 
        {
            if (!stack1.isEmpty() && curr == front && stack1.peek() == behind) 
            {
                stack1.pop();    // remove the pair
                ans += inc;
            } 
            else 
            {
                stack1.push(curr);
            }
        }

        // Second pass - remove the remaining substring
        Deque<Character> stack2 = new ArrayDeque<>();
        behind = (x < y) ? 'a' : 'b';
        front  = (x < y) ? 'b' : 'a';
        inc = Math.min(x, y);

        while (!stack1.isEmpty()) 
        {
            char curr = stack1.pollLast();  // preserve left-to-right order

            if (!stack2.isEmpty() && curr == front && stack2.peek() == behind) 
            {
                stack2.pop();
                ans += inc;
            } 
            else 
            {
                stack2.push(curr);
            }
        }

        return ans;
    }
}

/*

================================================================================================

🧪 Dry Run:

Input: s = "cdbcbbaaabab", x = 4, y = 5

y > x → remove "ba" first
First pass:
  Remove as many "ba" substrings as possible using stack
Second pass:
  Remove remaining "ab" substrings

Output: total score from all successful removals

================================================================================================

⏱️ Time Complexity: O(n)
- Two passes over the string
- Each character pushed and popped at most once

💾 Space Complexity: O(n)
- Stack used to store unprocessed characters

================================================================================================

🧃 Yuvraj-style Summary:

// Question basically says: remove "ab" and "ba" substrings to get x or y points
// But order matters — so remove the one with higher points first
// Step 1: Choose the higher value pair (ab or ba)
// Step 2: Use a stack to remove it greedily
// Step 3: Whatever chars remain, run stack again to remove the other pair
// pollLast() helps us keep original order in second round
// Total score is returned

*******************************************************************************************/


//BETTER SOLUTION (USING STRINGBUILDER)

class Solution {
    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder();

        // Step 1: Determine which pair to remove first based on max points
        char behind = (x < y) ? 'b' : 'a';
        char front = (x < y) ? 'a' : 'b';
        int inc = Math.max(x, y);
        int ans = 0;

        // First pass: remove higher valued substrings (greedy)
        for (char curr : s.toCharArray()) {
            int n = sb.length();

            if (n > 0 && curr == front && sb.charAt(n - 1) == behind) {
                sb.deleteCharAt(n - 1);
                ans += inc;
            } else {
                sb.append(curr);
            }
        }

        // Step 2: Now remove the lower valued pair
        behind = (x < y) ? 'a' : 'b';
        front = (x < y) ? 'b' : 'a';
        inc = Math.min(x, y);

        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            char curr = sb.charAt(i);
            int n = temp.length();

            if (n > 0 && curr == front && temp.charAt(n - 1) == behind) {
                temp.deleteCharAt(n - 1);
                ans += inc;
            } else {
                temp.append(curr);
            }
        }

        return ans;
    }
}

🕒 Time Complexity:
O(n)
Each character is processed at most twice → first and second passes.

🗃️ Space Complexity:
O(n)
Using StringBuilder to simulate stack behavior.

// Step 1: Remove costly pair first using greedy logic
// Step 2: Use stack (StringBuilder) to simulate adjacent removal
// Step 3: Do second pass for remaining pairs
// Time is O(n), fast as hell!
// No extra trick, just smart logic and 2-pass greedy

/*******************************************************************************************/


//OPTIMAL SOLUTION

// 🔥 Khandani Template 🔥
// ✅ Problem: Maximum Gain After Removing Substrings
// ✅ LeetCode: https://leetcode.com/problems/maximum-score-from-removing-substrings/
// ✅ Approach: Greedy + Stack-like StringBuilder
// ✅ Time Complexity: O(n)
// ✅ Space Complexity: O(n)
// ✅ Author: Yuvrajdeep (Gupta Ji style 💪)

class Solution {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        // Step 1: Decide which pair gives more score
        String maxStr = (x > y) ? "ab" : "ba";
        String minStr = maxStr.equals("ab") ? "ba" : "ab";

        // Step 2: First pass - remove high scoring pairs greedily
        String tempFirst = removeSubstring(s, maxStr);
        int L = tempFirst.length();
        int removedPairsCount = (n - L) / 2;
        score += removedPairsCount * Math.max(x, y);

        // Step 3: Second pass - remove the remaining lower scoring pairs
        String tempSecond = removeSubstring(tempFirst, minStr);
        removedPairsCount = (L - tempSecond.length()) / 2;
        score += removedPairsCount * Math.min(x, y);

        return score;
    }

    // ✅ Helper Function to Remove Substring using Stack-like StringBuilder
    private String removeSubstring(String inputString, String matchStr) {
        StringBuilder sb = new StringBuilder();
        int j = 0;

        for (int i = 0; i < inputString.length(); i++) {
            sb.append(inputString.charAt(i));
            j++;

            // If last 2 characters form the target pair, remove them
            if (j > 1 && sb.charAt(j - 2) == matchStr.charAt(0) && sb.charAt(j - 1) == matchStr.charAt(1)) {
                sb.delete(j - 2, j);
                j -= 2; // Decrease j since we deleted two characters
            }
        }

        return sb.toString();
    }
}

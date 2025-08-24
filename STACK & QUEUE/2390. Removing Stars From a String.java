Question Link: https://leetcode.com/problems/removing-stars-from-a-string/description/
Solution Video Link: https://youtu.be/s1pcdsRCVDg?si=wBBNgJ0ma-Mq1jbf

------------------------------------------------------------
PROBLEM BREAKDOWN:
------------------------------------------------------------
- We are given a string s consisting of lowercase letters and stars '*'.
- For every '*', we have to remove the closest non-star character to its left.
- Return the final string after all '*' operations are done.
- Example:
  Input: s = "leet**cod*e"
  Steps:
    "leet**cod*e" → remove t (due to *) → "lee*cod*e"
    remove e (due to *) → "lecod*e"
    remove d (due to *) → "lecoe"
  Output: "lecoe"

------------------------------------------------------------
INTUITION:
------------------------------------------------------------
- '*' always removes the last valid character added before it.
- This is exactly the LIFO (Last In First Out) behavior of a stack.
- We can simulate it using:
  1) Explicit stack (Deque/Stack).
  2) StringBuilder (using its setLength to mimic stack).
  3) Char array as manual stack (most optimal).

------------------------------------------------------------
APPROACHES & COMPARISON:
------------------------------------------------------------

1) GOOD APPROACH (Using Deque as a Stack):
------------------------------------------------------------
class Solution {
    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char curr : s.toCharArray()) {
            if (curr == '*' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(curr);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pollLast()); // reversing order since stack was LIFO
        }

        return ans.toString();
    }
}

- Intuition: Use stack push/pop for characters; pop when '*' found.
- Time Complexity: O(n) [Push & Pop for each char]
- Space Complexity: O(n) [Stack stores all characters]
- Pros: Easy to understand, directly maps to LIFO behavior.
- Cons: Uses extra space for stack.

------------------------------------------------------------

2) BETTER APPROACH (Using StringBuilder as Stack):
------------------------------------------------------------
class Solution {
    public String removeStars(String s) {
        StringBuilder ans = new StringBuilder();

        for (char curr : s.toCharArray()) {
            if (curr == '*') {
                ans.setLength(ans.length() - 1); // remove last char
            } else {
                ans.append(curr);
            }
        }

        return ans.toString();
    }
}

- Intuition: Instead of stack, use StringBuilder to store result dynamically.
- Time Complexity: O(n) 
- Space Complexity: O(n) [Result stored in StringBuilder]
- Pros: Cleaner and faster than explicit stack.
- Cons: Slightly less intuitive for beginners, but very efficient.

------------------------------------------------------------

3) OPTIMAL APPROACH (Manual Stack using Char Array):
------------------------------------------------------------
class Solution {
    public String removeStars(String s) {
        char arr[] = new char[s.length()];

        int n = s.length();
        int j = 0; // acts as stack pointer
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);

            if (curr == '*') {
                j--; // pop
                continue;
            }
            arr[j] = curr; // push
            j++;
        }

        return new String(arr, 0, j);
    }
}

- Intuition: Avoids using any extra dynamic data structure. Treat char array itself as a stack.
- Time Complexity: O(n)
- Space Complexity: O(n) [Only for final result array; minimal overhead]
- Pros: Most memory-efficient and fastest in practice.
- Cons: Less flexible compared to dynamic structures; but works perfectly here.

------------------------------------------------------------
COMPARISON SUMMARY:
------------------------------------------------------------
- All three have O(n) time & O(n) space, but overhead differs.
- GOOD: Easiest for understanding; uses stack explicitly.
- BETTER: Cleaner and faster using StringBuilder.
- OPTIMAL: Lowest overhead; manual array simulates stack directly.

------------------------------------------------------------
TIME & SPACE COMPLEXITY ANALYSIS:
------------------------------------------------------------
- Time: O(n) for all approaches (single pass over string).
- Space: O(n) for all (result must be stored); optimal reduces overhead.

------------------------------------------------------------
YUVRAJ-STYLE SUMMARY:
------------------------------------------------------------
Bhai simple funda: Har '*' pichle valid char ko uda dega.  
Stack jaisa kaam karna hai - jo last add kiya, wahi remove hoga.  
Teen tareeke hai:
1) Stack/Deque → Easy samajhne ke liye.
2) StringBuilder → Cleaner aur tez.
3) Char Array (optimal) → Sabse kam overhead.

Interview me **StringBuilder approach** ya **char array approach** best hai.  
Optimal wala likhoge toh interviewer impress ho jayega. 😎

------------------------------------------------------------

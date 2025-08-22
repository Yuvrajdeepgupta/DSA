────────────────────────────────────────────
📌 Problem: Valid Parentheses
🔗 Question Link: https://leetcode.com/problems/valid-parentheses/
🔗 Solution Video: https://youtu.be/3ssh_mk4LqY?si=VHfcniceNYRzDKUU
────────────────────────────────────────────

📝 Problem Breakdown:
We are given a string s containing only the characters '(', ')', '{', '}', '[' and ']'.
We need to determine if the input string is valid.

A string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every closing bracket has a corresponding opening bracket.

────────────────────────────────────────────
💡 Intuition:
- This is a **stack problem** because we need to match brackets in LIFO (last-in-first-out) order.
- Each time we see an opening bracket, we push it onto the stack.
- Each time we see a closing bracket, we check if it matches the top of the stack:
  - If yes → pop it.
  - If no → string is invalid.
- At the end, if the stack is empty → all brackets matched properly → valid string.

────────────────────────────────────────────
⚙️ Approach:
1. Create a stack (Deque for efficiency).
2. Store mapping of closing → opening brackets using an array (ASCII-based) or HashMap.
3. Traverse string:
   - If it’s an opening bracket → push onto stack.
   - If it’s a closing bracket:
     - Check if stack is empty or top of stack doesn’t match → return false.
     - Else pop from stack.
4. Finally, return true if stack is empty.

────────────────────────────────────────────
✅ Code (Java):

class Solution {
    public boolean isValid(String s) {
        if(s.length() <= 1) return false;
        
        Deque<Character> stack = new ArrayDeque<>();
        
        int[] store = new int[128];
        store[')'] = '(';
        store[']'] = '[';
        store['}'] = '{';
        
        for (char curr : s.toCharArray()) {
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else if (stack.isEmpty() || stack.pop() != store[curr]) {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
}

────────────────────────────────────────────
🧮 Dry Run Intuition:
Example: s = "{[()]}"
- '{' → push → stack = { '{' }
- '[' → push → stack = { '[', '{' }
- '(' → push → stack = { '(', '[', '{' }
- ')' → pop '(' → match ✔
- ']' → pop '[' → match ✔
- '}' → pop '{' → match ✔
Stack is empty → Valid string ✅

Example: s = "([)]"
- '(' → push
- '[' → push
- ')' → expected '(' but top is '[' → mismatch → return false ❌

────────────────────────────────────────────
⏱️ Time Complexity:
- O(n) → traverse string once, each character processed at most once.

📦 Space Complexity:
- O(n) → in worst case (all opening brackets), stack holds n characters.

────────────────────────────────────────────
😎 Yuvraj Summary (Easy Way):
- Use a stack to match brackets.
- Push opening, check closing.
- Mismatch → false, leftover → false.
- If everything pops perfectly → true.
- Runs in O(n) time and O(n) space.
Simple, clean, and interview-perfect ✅

────────────────────────────────────────────

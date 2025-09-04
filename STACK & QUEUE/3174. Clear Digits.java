/*
🔗 Question Link: https://leetcode.com/problems/clear-digits/description/
🎥 Solution Link: https://youtu.be/WI9PhnDxAik?si=q7FxKPaHMPAN-jzG

----------------------------------------------------------------------
📝 Problem Breakdown:
You are given a string `s`. 
- Whenever a digit appears in the string, we have to remove it AND also remove the closest character to its left (if it exists).
- Return the final string after processing all characters.

👉 Example:
s = "abc1d2"
- '1' → remove 'c' and '1'
- '2' → remove 'd' and '2'
Final answer = "ab"

---------------------------------------------------------------------- 
💡 Intuition:
- We need a way to build the final string while being able to "undo" the last added character when we see a digit.
- This is exactly like using a stack:
    - Push letters onto the stack.
    - When a digit comes, pop the last pushed letter (if stack not empty).
- At the end, the stack contains the answer characters.

---------------------------------------------------------------------- 
✅ Approach 1 (Brute / Stack):
1. Use a stack (Deque<Character>).
2. Traverse each character in the string:
   - If it’s a digit → pop one character from stack (if not empty).
   - Else → push character into stack.
3. Convert stack into string.

---------------------------------------------------------------------- 
🔄 Dry Run (Approach 1):
Input: "abc1d2"

Step 1: stack = []
'a' → push → ['a']
'b' → push → ['a','b']
'c' → push → ['a','b','c']
'1' → digit → pop 'c' → ['a','b']
'd' → push → ['a','b','d']
'2' → digit → pop 'd' → ['a','b']

Stack = ['a','b']
Answer = "ab"

---------------------------------------------------------------------- 
⏱️ Time Complexity (Approach 1):
- O(n), each char processed once.
📦 Space Complexity (Approach 1):
- O(n), stack used.

---------------------------------------------------------------------- 
🚀 Brute Implementation (Stack):
*/
class Solution 
{
    public String clearDigits(String s) 
    {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray())
        {
            if (Character.isDigit(c) && !stack.isEmpty())
            {
                stack.pop();
            }
            else
            {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? "" : convert(stack);
    }

    public String convert(Deque<Character> stack)
    {
        char ans[] = new char[stack.size()];
        int i = ans.length - 1;
        while (!stack.isEmpty())
        {
            ans[i--] = stack.pop();
        }

        return new String(ans);
    }
}

/*
---------------------------------------------------------------------- 
✅ Approach 2 (Optimal / Array + Pointer):
1. Instead of using a stack, use a char[] as storage.
2. Maintain a pointer `i` that tracks the "top".
3. Traverse each character:
   - If it’s a digit → move pointer back (i--).
   - Else → place character at ans[i] and increment pointer.
4. At the end, construct string from ans[0..i-1].

---------------------------------------------------------------------- 
🔄 Dry Run (Approach 2):
Input: "abc1d2"

Step 1: ans = [], i = 0
'a' → ans[0]='a' → i=1
'b' → ans[1]='b' → i=2
'c' → ans[2]='c' → i=3
'1' → digit → i=2
'd' → ans[2]='d' → i=3
'2' → digit → i=2

Final ans array = ['a','b', ...] → "ab"

---------------------------------------------------------------------- 
⏱️ Time Complexity (Approach 2):
- O(n), single pass.
📦 Space Complexity (Approach 2):
- O(n) for char[] (but no extra stack overhead, slightly better in practice).

---------------------------------------------------------------------- 
🚀 Optimal Implementation (Array + Pointer):
*/
class Solution 
{
    public String clearDigits(String s) 
    {
        char[] ans = new char[s.length()];
        int i = 0;

        for (char c : s.toCharArray())
        {
            if (Character.isDigit(c))
            {
                if (i > 0) i--; // remove last added char
            }
            else
            {
                ans[i++] = c; // store char
            }
        }

        return new String(ans, 0, i);
    }
}

/*
---------------------------------------------------------------------- 
📝 Summary (Yuvraj way):
- Basically, this is a "backspace problem".
- Brute → use stack to push chars, pop when digit found.
- Optimal → directly use array + pointer as a stack replacement.
- Both TC O(n), but optimal saves space (no extra stack object).
- Clean and intuitive once you visualize backspace effect.

----------------------------------------------------------------------
*/

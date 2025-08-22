🔗 Question: https://leetcode.com/problems/generate-parentheses/description/
🔗 Question: https://www.geeksforgeeks.org/problems/generate-all-possible-parentheses/1
🎥 Solution Video: https://youtu.be/7xkPbffc6w8?si=a3FP9DyJZR8B9e6H

------------------------------------------------------------
📝 Problem Statement:
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

------------------------------------------------------------
💡 Intuition:
We need to generate all possible strings of length 2*n using '(' and ')' 
such that the string is valid (balanced parentheses).  
This is a classic Catalan number problem.

------------------------------------------------------------
🚨 Brute Force Approach:
1. Generate all possible strings of length 2*n using recursion.
2. For each string, check if it is valid using a stack.
3. If valid, add to result list.

✅ Pros:
- Easy to come up with.
❌ Cons:
- Generates many invalid strings.
- Needs extra validation (O(n) per string).

------------------------------------------------------------
🧾 Brute Force Code:

class Solution 
{
    List<String> list=new ArrayList<>();

    public List<String> generateParenthesis(int n) 
    {
        StringBuilder ans=new StringBuilder();
        solve(n,ans);
        return list;
    }

    public void solve(int n,StringBuilder ans)
    {
        int size=ans.length();

        if(size==n*2)
        {
            if(isValid(ans.toString()))
            {
                list.add(ans.toString());
            }
            return;
        }

        ans.append('(');
        size++;
        solve(n,ans);
        ans.deleteCharAt(size-1);
        size--;

        ans.append(')');
        size++;
        solve(n,ans);
        ans.deleteCharAt(size-1);
        size--;
    }

    public boolean isValid(String s) 
    {
        if(s.length()<=1){return false;}
        Deque<Character> stack=new ArrayDeque<>();

        for(char curr:s.toCharArray())
        {
            if(curr=='(')
            {
                stack.push(curr);
            }
            else if(stack.isEmpty() || stack.pop()!='(')
            {
                return false;
            }
        }

        return stack.isEmpty();
    }
}

------------------------------------------------------------
⏱️ Brute Force Complexity:
- Time: O(2^(2n) * n)  
  (generate all strings of length 2n, check each in O(n)).
- Space: O(n) recursion + O(n) stack = O(n).

------------------------------------------------------------
🚀 Optimal Approach (Backtracking):
1. Instead of generating all strings, maintain counts of open and close parentheses.
2. Place '(' if we still have open < n.
3. Place ')' if close < open.
4. When length = 2*n, add to result.
This avoids generating invalid strings completely.

------------------------------------------------------------
🧾 Optimal Code:

class Solution 
{
    List<String> list=new ArrayList<>();

    public List<String> generateParenthesis(int n) 
    {
        solve(0,0,n,new StringBuilder());
        return list;
    }

    public void solve(int open,int close,int n,StringBuilder ans)
    {
        int size=ans.length();

        if(size==n*2)
        {
            list.add(ans.toString());
            return;
        }

        if(open<n)
        {
            ans.append('(');
            size++;
            solve(open+1,close,n,ans);
            ans.deleteCharAt(size-1);
            size--;
        }
        
        if(close<open)
        {
            ans.append(')');
            size++;
            solve(open,close+1,n,ans);
            ans.deleteCharAt(size-1);
            size--;
        }
    }
}

------------------------------------------------------------
⏱️ Optimal Complexity:
- Time: O(C_n * n)  
  where C_n = nth Catalan number ≈ 4^n / (n^(3/2) * √π).  
  Each valid string length = 2n, so building takes O(n).
- Space: O(n) recursion + O(n) for string builder = O(n).

====================================================================
CATALAN NUMBER CONCEPT
====================================================================

The Catalan Number is a sequence of natural numbers that shows up in many combinatorial problems (like valid parentheses, binary trees, paths, etc.).

First Few Catalan Numbers:
C0​=1,C1​=1,C2​=2,C3​=5,C4​=14,C5​=42,C6​=132,...

Formula:
C(n) = (1 / (n+1)) * (2n choose n)

Why important?
They count the number of valid ways to structure certain problems.
For example:
Valid Parentheses Problem (your code above)

Number of valid strings of length 
2n is the 𝑛𝑡ℎ Catalan number.
Example: For n=3, answer = 𝐶3=5

👉 Number of valid parentheses combinations for n pairs = nth Catalan number.

Examples:
n = 1 → 1
n = 2 → 2
n = 3 → 5
n = 4 → 14
n = 5 → 42

This sequence also counts:
- Number of structurally unique BSTs with n nodes.
- Number of ways to triangulate a polygon with (n+2) sides.
- Number of valid Dyck paths (balanced steps).

So here, the **number of valid parenthesis strings = Catalan number**,
and backtracking generates exactly those many.


------------------------------------------------------------------------------------------
🔑 Key Takeaways:
- Brute force → generate all → filter valid → slow.
- Optimal → guided generation using counts → direct valid results.
- This is a classic Catalan number problem.

/*
Question: Number of Steps to Reduce a Number to Zero
LeetCode Link: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
Solution Video Link: https://youtu.be/XrpZBBz4LvU?si=pbBtHWMI--FPhg9m
*/

/*
----------------------------------
📌 Problem Breakdown:
----------------------------------
We are given an integer `num`.
Rules:
- If num is even → divide it by 2.
- If num is odd → subtract 1.
Count how many steps it takes to make num = 0.

Example:
num = 14
Steps:
14 → 7  (even → divide by 2)
7  → 6  (odd  → subtract 1)
6  → 3
3  → 2
2  → 1
1  → 0
Total steps = 6

----------------------------------
💡 Intuition:
----------------------------------
We repeatedly check:
- If even → divide by 2.
- If odd → subtract 1.
Keep a counter for steps.
Stop when num becomes 0.

This is a simple simulation problem.
No need for extra data structures.

----------------------------------
✅ Why this approach:
----------------------------------
- Straightforward simulation.
- Each operation reduces the number, so it will terminate.
- Both recursive and iterative approaches work fine.

----------------------------------
⏳ Time Complexity:
----------------------------------
O(log n)  
Reason:  
- Each divide by 2 step halves the number.
- Odd steps only subtract 1, but after that the number becomes even, so division happens.
- Overall, the number of steps is proportional to log₂(n).

----------------------------------
💾 Space Complexity:
----------------------------------
Recursive:
O(log n) → due to recursion call stack.

Iterative:
O(1) → uses only a counter variable.

----------------------------------
📜 Recursive Approach (Passing count as Parameter):
----------------------------------
class Solution 
{
    public int numberOfSteps(int num) 
    {
        return number(num, 0);
    }

    public int number(int num, int count) 
    {
        if (num <= 0)
        {
            return count;
        }

        if (num % 2 != 0)
        {
            count++;
            return number(num - 1, count);
        }
        else 
        {
            count++;
            return number(num / 2, count);
        }
    }
}

----------------------------------
📜 Iterative Approach (More Efficient in Practice):
----------------------------------
class Solution 
{
    public int numberOfSteps(int num) 
    {
        int count = 0;

        while (num > 0)
        {   
            if (num % 2 == 0)
            {
                num /= 2;
            }
            else
            {
                num -= 1;
            }
            count++;
        }

        return count;
    }
}

----------------------------------
📝 Yuvraj Style Summary:
----------------------------------
Bhai, bas simple sa logic hai –
Jab even ho toh 2 se divide karo, jab odd ho toh 1 minus karo.
Counter badhate jao, jab 0 aajaye toh counter return.
Iterative version zyada safe hai kyunki recursion stack ka tension nahi.
TC O(log n), SC iterative O(1), recursive O(log n).
*/

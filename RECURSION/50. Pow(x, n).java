//que link- https://leetcode.com/problems/powx-n/description/
//sol link-https://youtu.be/7wcJXZoGKMI?si=-Z2mRnjWG3LlpG-B

/**************************************************************************************/

/*
🧠 PROBLEM: Implement pow(x, n), i.e., calculate x raised to the power n (x^n)

You're given:
- A double `x` (base)
- An integer `n` (exponent)

Return `x^n` using fast exponentiation (without using built-in pow).

⚠️ Edge Cases:
- n can be negative (like -2147483648)
- x can be a fraction or > 1
- n can be Integer.MIN_VALUE, which overflows if negated in int

----------------------------------------------------
✅ INTUITION:

We're calculating x^n efficiently using Binary Exponentiation:

- Instead of multiplying x n times (O(n)), we reduce the problem like:
  x^8 = (x^4)^2
  x^9 = x * (x^4)^2

- This divides the problem by 2 in each step ➜ O(log n)

Also,
- When n is negative, x^(-n) = 1 / x^n

BUT:
- Integer.MIN_VALUE = -2^31
- Math.abs(Integer.MIN_VALUE) overflows in int
→ So we convert n to long to safely negate it

----------------------------------------------------
📦 APPROACH USED: Recursive Binary Exponentiation

- If n < 0 → invert x and make n positive (using long)
- Use recursion to compute:
    x^n = (x^(n/2))^2          if n is even
    x^n = x * (x^(n/2))^2      if n is odd

----------------------------------------------------
✅ CODE (Recursive Version — Fast & Safe)
*/

class Solution {
    public double myPow(double x, int n) 
    {
        long N = n;   // convert to long to safely handle Integer.MIN_VALUE

        if (N < 0) 
        {
            x = 1 / x;
            N = -N;
        }

        return evaluate(x, N);
    }

    public double evaluate(double x, long n)
    {
        if (n == 0) return 1;

        double half = evaluate(x, n / 2);
        double result = half * half;

        if (n % 2 == 1) result *= x;

        return result;
    }
}

/*
----------------------------------------------------
📈 TIME COMPLEXITY:

- Each recursive step halves `n`
→ Total steps = O(log n)

✅ Time: O(log n)

----------------------------------------------------
🧠 SPACE COMPLEXITY:

- Recursive call stack = log n levels deep

✅ Space: O(log n)

👉 Can be optimized to O(1) with iterative approach (see below)

----------------------------------------------------
🔥 ITERATIVE VERSION (O(1) SPACE)
*/

class Solution {
    public double myPow(double x, int n) 
    {
        long N = n;
        if (N < 0) 
        {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;

        while (N > 0) 
        {
            if (N % 2 == 1) result *= x;

            x *= x;
            N /= 2;
        }

        return result;
    }
}

/*
----------------------------------------------------
📈 TIME COMPLEXITY (Iterative):

✅ Time: O(log n)

----------------------------------------------------
🧠 SPACE COMPLEXITY (Iterative):

✅ Space: O(1) — no recursion stack used

----------------------------------------------------
😅 FUN FACT: First solution (lol)
class Solution {
    public double myPow(double x, int n) {
        return Math.pow(x, n);  // 😂 built-in shortcut
    }
}

----------------------------------------------------
📌 SUMMARY:

1. Recursive Binary Exponentiation → O(log n) time, O(log n) space
2. Iterative Binary Exponentiation → O(log n) time, O(1) space
3. Avoid Math.pow in interviews unless asked

🚫 Don’t use Math.abs(n) directly when n == Integer.MIN_VALUE → will overflow!

----------------------------------------------------
🧪 EXAMPLES:

Input: x = 2.0, n = 10
Output: 1024.0

Input: x = 2.1, n = 3
Output: 9.261

Input: x = 2.0, n = -2
Output: 0.25

*/



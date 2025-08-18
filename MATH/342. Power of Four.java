/* 
🔗 Question: https://leetcode.com/problems/power-of-four/description/
🎥 Solution Reference: https://youtu.be/-2Z4ngPy4H0?si=GLqDzil0PWnEdwou
*/

/*  
----------------------------------------------------
 📝 Problem Breakdown:
We need to check if a given integer `n` is a power of 4.
That means n should be of the form 4^k where k >= 0.

----------------------------------------------------
 💡 Intuition:
- A number is a power of 4 if:
   1. It is positive (n > 0).
   2. It is a power of 2.
   3. Additionally, only powers of 4 satisfy a special property.

There are 3 popular approaches:

----------------------------------------------------
 ✅ Approach 1: Using Logarithms
Formula: log₄(n) should be an integer if n is a power of 4.
We use base change rule:
   log₄(n) = log₁₀(n) / log₁₀(4)
If this value is an integer → n is a power of 4.

⚠️ Caveat: Might fail for large n due to floating-point precision.

Code:
*/
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;

        double ans = Math.log10(n) / Math.log10(4);
        return ans == (int) ans;
    }
}

/*
----------------------------------------------------
 ✅ Approach 2: Iterative Division
Keep dividing n by 4 as long as it is divisible.
If we finally reach 1 → it’s a power of 4.

Code:
*/
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;

        while (n % 4 == 0) {
            n = n / 4;
        }
        return n == 1;
    }
}

/*
----------------------------------------------------
 ✅ Approach 3: Bit Manipulation (Best Approach)
1. A power of 4 is also a power of 2 → check with (n & (n-1)) == 0.
2. Among powers of 2, only powers of 4 satisfy: (n-1) % 3 == 0.

Code:
*/
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;

        return ((n & (n - 1)) == 0) && ((n - 1) % 3 == 0);
    }
}

/*
----------------------------------------------------
 ⏱️ Time & Space Complexity Analysis:
- Approach 1 (Logarithm): O(1) time, O(1) space. (But can suffer precision issues.)
- Approach 2 (Iterative Division): O(log₄(n)) time (divide by 4 repeatedly), O(1) space.
- Approach 3 (Bit Manipulation): O(1) time, O(1) space. ✅ Most optimal.

----------------------------------------------------
 📌 Summary (Yuvraj Way 😎):
- Log method → smart math trick but risky for large numbers due to floating-point errors.
- Division method → simple & reliable, but slightly slower (log₄(n) steps).
- Bit manipulation → king solution 👑 → O(1), no floating-point issues, interview favorite.

*/

# Question Link:
https://leetcode.com/problems/gcd-of-odd-and-even-sums/description/

# Solution Video Link:
https://youtu.be/VvLREw6C0CA?si=SEz_iE_qOMdyKlVK

------------------------------------------------------------
## Problem Breakdown:
- We are given a number n.
- We need to calculate the GCD (Greatest Common Divisor) of:
    1) Sum of all odd numbers till n
    2) Sum of all even numbers till n
- The task is to return this GCD.

------------------------------------------------------------
## Intuition & Why This Works:
- Sum of first k odd numbers = k^2
  For n numbers:
    - If n is odd -> odd count = (n+1)/2, even count = n/2
    - If n is even -> odd count = n/2, even count = n/2
- After derivation, GCD formula reduces to:
    GCD(n^2, n(n+1))
- Property: gcd(a*b, a*c) = a * gcd(b,c)
    Here: gcd(n^2, n(n+1)) = n * gcd(n, n+1)
- gcd(n, n+1) = 1 (consecutive numbers are always co-prime)
- Hence GCD = n for any input n.
- So we don't even need to calculate gcd explicitly; we can directly return n.

------------------------------------------------------------
## Why We Use Euclidean Algorithm (Optional):
- If asked to implement gcd manually, Euclidean algorithm (using modulo) is fastest.
- gcd(a,b) = gcd(b, a % b)
- But since result is always n, we can skip and return n directly.

------------------------------------------------------------
## Code Implementation (Java):

// Version 1: Using GCD function (subtraction method)
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return gcd(n * n, n * (n + 1)); // Always returns n
    }

    public int gcd(int m, int n) {
        while (m != n) {
            if (m > n) {
                m = m - n;
            } else {
                n = n - m;
            }
        }
        return n;
    }
}

// Version 2: Optimized GCD (modulo method)
public int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

// Version 3: Ultimate lol version (direct return)
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n; // lol
    }
}

------------------------------------------------------------
## Example Dry Run:
n = 5
- n^2 = 25
- n(n+1) = 30
gcd(25,30) = 5 (which is n)

n = 6
- n^2 = 36
- n(n+1) = 42
gcd(36,42) = 6 (which is n)

Hence always returns n.

------------------------------------------------------------
## Time Complexity:
- Version 1 (subtraction gcd): Worst O(max(a,b)) -> not efficient
- Version 2 (modulo gcd): O(log n)
- Version 3 (direct return): O(1) (best)

## Space Complexity:
- O(1), constant extra space.

------------------------------------------------------------
## Yuvraj Way Summary (Easy Words):
- Odd sum & even sum formulas simplify into n^2 and n(n+1).
- gcd(n^2, n(n+1)) always = n because gcd(n, n+1)=1.
- So answer is always n.
- Either write gcd function or just return n directly (lol).

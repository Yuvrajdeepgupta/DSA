Question Link: https://leetcode.com/problems/alice-and-bob-playing-flower-game/description/
Solution Link: https://youtu.be/8PuV3IvUgk8?si=ZP_p3MaFv7jVBJu1

----------------------------------------------------
 Problem Breakdown
----------------------------------------------------
- Alice and Bob play a game with n and m flowers.
- Alice picks from [1..n], Bob picks from [1..m].
- They win if the sum of chosen flowers (i + j) is odd.
- Task: Count the total number of winning pairs (i, j).

Key Insight:
- Sum (i + j) is odd if:
   (Odd + Even) OR (Even + Odd).
- So the problem reduces to counting odds and evens 
  in range 1..n and 1..m.

----------------------------------------------------
 Intuition
----------------------------------------------------
1. Count evens and odds in n:
   evens_n = n / 2
   odds_n  = n - evens_n

2. Count evens and odds in m:
   evens_m = m / 2
   odds_m  = m - evens_m

3. Valid winning pairs:
   = (evens_n * odds_m) + (evens_m * odds_n)

----------------------------------------------------
 Why This Works
----------------------------------------------------
- Direct combinatorial counting.
- No need to loop over all i, j (which would be O(n*m)).
- Instead, just count categories (even/odd).
- This makes solution O(1), perfectly optimal.

----------------------------------------------------
 Java Solutions
----------------------------------------------------

// Solution 1: Readable with variables
class Solution {
    public long flowerGame(int n, int m) {
        long even_n = n / 2;
        long odd_n = n - even_n;

        long even_m = m / 2;
        long odd_m = m - even_m;

        return (even_n * odd_m) + (even_m * odd_n);
    }
}

// Solution 2: One-liner (avoid int overflow with 1L*)
class Solution {
    public long flowerGame(int n, int m) {
        return (1L * (n/2) * (m - m/2)) + (1L * (m/2) * (n - n/2));
    }
}

// Solution 3: Shortest one-liner (Math trick)
class Solution {
    public long flowerGame(int n, int m) {
        return ((long) n * m) / 2;
    }
}

----------------------------------------------------
 Example Walkthrough
----------------------------------------------------
Input: n = 3, m = 4
- Odds in n = 2 (1,3), Evens in n = 1 (2)
- Odds in m = 2 (1,3), Evens in m = 2 (2,4)

Valid pairs = (2*2) + (1*2) = 6

Output: 6

----------------------------------------------------
 Time & Space Complexity
----------------------------------------------------
Time Complexity:
- O(1) → Only a few arithmetic operations.

Space Complexity:
- O(1) → Only fixed variables used.

----------------------------------------------------
 Yuvraj Style Summary
----------------------------------------------------
Bhai scene simple hai → 
odd + even = odd. 
So bas count n ke odds/evens aur m ke odds/evens, 
fir combinations nikalo.  
3 style ke solutions diye → readable, safe one-liner, 
aur lol math one-liner.  
Tension hi khatam, O(1) me solution.  
Ye hi hai asli DSA ka swag! 🚀🔥

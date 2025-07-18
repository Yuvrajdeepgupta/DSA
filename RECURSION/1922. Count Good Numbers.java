//que link - https://leetcode.com/problems/count-good-numbers/description/
//sol link - https://youtu.be/rYLRgdj0n4s?si=OTT396vmzwwDqZaN
//binary exponentiation - https://www.youtube.com/watch?v=D320QeHS0XQ

/******************************************************************************************/
// ✅ Problem: Count Good Numbers
// You are given a long integer 'n'. Each digit at even indices (0-based) can be one of [0,2,4,6,8] → 5 choices
// Each digit at odd indices can be one of [2,3,5,7] → 4 choices
// Goal: Return the total number of good digit strings of length n, modulo 1e9 + 7

// 💡 Intuition:
// For even positions → total (n + 1)/2 digits → 5 choices each
// For odd positions  → total n/2 digits → 4 choices each
// So, total good numbers = (5 ^ even) * (4 ^ odd)

// 🧠 Why Binary Exponentiation?
// Since n can go up to 10^15, direct multiplication or Math.pow() will overflow or lose precision
// Binary Exponentiation allows us to compute (a^b % MOD) in O(log b) time efficiently

// 📦 Java Code:
class Solution {
    static final int MOD = (int)(1e9+7);    //1000000007

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;  // Even indices
        long odd = n / 2;         // Odd indices

        long pow5 = pow(5, even);  // 5 choices for even places
        long pow4 = pow(4, odd);   // 4 choices for odd places

        long result = (pow5 * pow4) % MOD;

        return (int) result;
    }

    // Recursive Binary Exponentiation with MOD
    public long pow(long base, long exp) {
        if (exp == 0) return 1;

        long half = pow(base, exp / 2);
        long result = (half * half) % MOD;

        if (exp % 2 == 1)
            result = (result * base) % MOD;

        return result;
    }
}

// ⏱️ Time Complexity:
// pow(a, b) runs in O(log b) → total O(log n)

// 🧠 Space Complexity:
// O(log n) recursion stack (if constraints are high, prefer iterative approach)

// 📘 Summary (Yuvraj-style):
// - even = (n+1)/2 → 5 options at each even position
// - odd = n/2      → 4 options at each odd position
// - So total = (5^even) * (4^odd) % MOD
// - Use binary exponentiation to handle big powers efficiently

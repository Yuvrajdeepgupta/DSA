// Question Link: https://leetcode.com/problems/power-of-two/
// Solution Link: https://youtu.be/bVfA6_rDw4M?si=BgSWnUe-k17pJWoI

// ----------------------------------------------------
// 🔹 Problem Breakdown
// ----------------------------------------------------
// We need to check if a given integer n is a power of two.
// A number is a power of two if it can be expressed as 2^k (where k ≥ 0).
// Examples of powers of two: 1, 2, 4, 8, 16, 32, ...

// ----------------------------------------------------
// 🔹 Intuition
// ----------------------------------------------------
// ✅ The largest power of two that fits in a 32-bit signed int is 2^30 = 1073741824.
// ✅ If 'n' is a power of two, it will divide 1073741824 evenly (no remainder).
// ✅ Also, 'n' must be positive, because powers of two are always > 0.

// ----------------------------------------------------
// 🔹 Why This Works
// ----------------------------------------------------
// - We precompute the largest power of two within int range (2^30).
// - If n is a factor of that number, it must be one of the powers of two.
// - Example:
//   1073741824 % 16 == 0  ✅ (16 is a power of two)
//   1073741824 % 12 != 0  ❌ (12 is not a power of two)

// ----------------------------------------------------
// 🔹 Java Code
// ----------------------------------------------------



class Solution {
    public boolean isPowerOfTwo(int n) 
    {
        return n > 0 && 1073741824 % n == 0;
    }
}



// ----------------------------------------------------
// 🔹 Example Walkthrough
// ----------------------------------------------------
// Example 1:
// Input: n = 16
// Step 1: n > 0 → true
// Step 2: 1073741824 % 16 == 0 → true
// Result: true ✅

// Example 2:
// Input: n = 12
// Step 1: n > 0 → true
// Step 2: 1073741824 % 12 == 0 → false
// Result: false ❌

// ----------------------------------------------------
// 🔹 Time & Space Complexity
// ----------------------------------------------------
// Time Complexity: O(1) → Just one modulus operation.
// Space Complexity: O(1) → No extra space used.

✅ Approach 2 — Using Bit Manipulation
- Step 1: Check n > 0
- Step 2: Use the property: For powers of two, `n & (n - 1) == 0`

Why it works:
Example for n = 8 (1000 in binary)
n - 1 = 7 (0111 in binary)
1000 & 0111 = 0000 → hence power of two.



*/
class SolutionBit 
{
    public boolean isPowerOfTwo(int n) 
       {
        return n > 0 && (n & (n - 1)) == 0;
    }
}



/*
🕒 Time Complexity: O(1) — Single bitwise operation.
📦 Space Complexity: O(1) — No extra space used.

---

🔍 Example Walkthrough:
n = 8
Binary: 1000
Approach 1: 1073741824 % 8 = 0 → true
Approach 2: 8 & (8 - 1) → 1000 & 0111 = 0000 → true

---



// ----------------------------------------------------
// 🔹 Yuvraj-Style Summary
// ----------------------------------------------------
// Bhai simple funda — sabse bada power of two (2^30) le lo jo int me aata hai,
// usko n se divide karke dekh lo remainder 0 hai kya.  
// Agar 0 hai toh wo definitely power of two hai, warna nahi.
// Bas condition me n>0 bhi check karna na bhulna 😎

1. **Division Trick:** Largest power of 2 (2^30) ko n se divide karke dekh. Agar pura divide hota hai → power of 2.
2. **Bit Trick:** Agar n ka sirf ek hi '1' bit hai, to n & (n-1) zero ho jayega. Ye fastest & cleanest method hai.

// ```
---
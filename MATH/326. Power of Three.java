Q Link: https://leetcode.com/problems/power-of-three/description/?envType=daily-question&envId=2025-08-13
Sol Link: https://youtu.be/11lCatB_H_A?si=jYwQKL7N1A6O0hpH

------------------------------------------------------------
Approach 1: Iterative Division
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}

Explanation:
- Keep dividing n by 3 while it is divisible by 3.
- If after all divisions we end up with 1 → n is a power of 3.
- Works because powers of 3 are of the form 3^k and dividing repeatedly will eventually reach 1.

Time Complexity:  O(log₃ n)  → each step divides by 3
Space Complexity: O(1)       → constant extra space

------------------------------------------------------------
Approach 2: Recursive Division
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 3 != 0) return false;
        return isPowerOfThree(n / 3);
    }
}

Explanation:
- Base cases: n <= 0 → false, n == 1 → true.
- If n is divisible by 3, recursively check n/3.
- Same logic as iterative, but uses recursion.

Time Complexity:  O(log₃ n)
Space Complexity: O(log₃ n) → recursion stack

------------------------------------------------------------
Approach 3: Binary Search
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        int low = 0, high = 19; // because 3^19 < Integer.MAX_VALUE
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int ans = (int) Math.pow(3, mid);
            if (ans == n) return true;
            else if (ans > n) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }
}

Explanation:
- Search for exponent k where 3^k = n using binary search in range [0, 19].
- Upper bound is 19 because 3^20 > int range.

Time Complexity:  O(log k) = O(1) since k ≤ 19
Space Complexity: O(1)

------------------------------------------------------------
Approach 4: Logarithm with String Checking (Your Custom Approach)
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        double ans = Math.log(n) / Math.log(3);
        return isInt(ans);
    }
    public static boolean isInt(double ans) {
        String s = ans + "";
        String arr[] = s.split("\\.");
        return count(arr[1]) || arr[1].equals("0");
    }
    public static boolean count(String s) {
        int count = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '9') {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max > 10;
    }
}

Explanation:
- Uses log base change formula to compute exponent.
- Checks if decimal part is either 0 or has long sequence of 9’s (close to integer).
- Works but not optimal; string conversion is slower.

Time Complexity:  O(1)
Space Complexity: O(len(decimal part)) → due to string storage

------------------------------------------------------------
Approach 5: Optimal Logarithm (log10)
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        double ans = Math.log10(n) / Math.log10(3);
        return ans == (int) ans;
    }
}

Why log10 "works" here:
- By change of base: log₃ n = log₁₀ n / log₁₀ 3.
- Any log base works mathematically; but in Java, floating-point rounding for some values with log10 happens to give exact integers, so equality check sometimes passes without epsilon.
- This is coincidence, not guaranteed for all cases.

Time Complexity:  O(1)
Space Complexity: O(1)

------------------------------------------------------------
Approach 6: Best Logarithm with Epsilon Check
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        double ans = Math.log(n) / Math.log(3);
        return Math.abs(ans - Math.round(ans)) < 1e-10;
    }
}

Why this works:
- Floating-point math can produce values like 4.999999999999998 instead of 5.
- Rounding the result and comparing with an epsilon tolerance accounts for this precision error.
- Works reliably for all powers of 3 within int range.

Time Complexity:  O(1)
Space Complexity: O(1)

------------------------------------------------------------
Approach 7: "Optimal ka bhi Optimal" (Divisibility Trick)
------------------------------------------------------------
Code:
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}

Why this works:
- 1162261467 is the largest power of 3 within int range (3^19).
- If n is a power of 3, it must divide 3^19 exactly (no remainder).
- Very fast; purely integer arithmetic.

Time Complexity:  O(1)
Space Complexity: O(1)

------------------------------------------------------------
Summary Table:
------------------------------------------------------------
1) Iterative Division         → O(log₃ n), O(1)
2) Recursive Division         → O(log₃ n), O(log₃ n) stack
3) Binary Search              → O(1), O(1)
4) Log + String Check         → O(1), O(len(decimal))
5) Log10 + Int Cast           → O(1), O(1)
6) Log + Epsilon              → O(1), O(1)  ✅ Reliable
7) Divisibility by Max Power  → O(1), O(1)  ✅ Fastest

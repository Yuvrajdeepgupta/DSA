# Problem: Sqrt(x)
# LeetCode Link: https://leetcode.com/problems/sqrtx/description/
# Solution Video Link: https://youtu.be/Bsv3FPUX_BA?si=jDwNgHaruxlTtL_J

------------------------------------------------------------
# Problem Statement:
Given a non-negative integer x, compute and return the square root of x, 
rounded down to the nearest integer. 
The returned integer should be the floor value of sqrt(x).

Example:
Input: x = 8
Output: 2
Explanation: sqrt(8) ≈ 2.828... and floor(2.828) = 2.

------------------------------------------------------------
# Intuition / Approach:

1. We need the floor of the square root of x.
2. Brute force would check all numbers from 1 to x, but that's O(x) - too slow.
3. We observe that sqrt(x) lies between 1 and x/2 (for x >= 4).
4. We can apply **Binary Search** on this range:
   - Take mid = (low + high) / 2.
   - If mid*mid == x → return mid.
   - If mid*mid > x → move to the left half (high = mid - 1).
   - If mid*mid < x → move to the right half (low = mid + 1).
5. After the loop ends, 'high' will be the floor of sqrt(x).

Reason for using Binary Search:
- Monotonic property: As mid increases, mid*mid also increases.
- Hence binary search can efficiently find the largest number whose square <= x.

------------------------------------------------------------
# Why the chosen data structure works?
- We just need variables (low, high) to apply binary search.
- Using 'long' prevents overflow when computing mid*mid.

------------------------------------------------------------
# Java Code Implementation:
class Solution 
{
    public int mySqrt(int x) 
    {
        // Handle small cases
        if (x < 4) return x == 0 ? 0 : 1;

        long low = 0, high = x;
        while (low <= high)
        {
            long mid = low + (high - low) / 2;
            long square = mid * mid;

            if (square == x)
            {
                return (int) mid;
            }
            else if (square > x)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        // After loop ends, 'high' is floor(sqrt(x))
        return (int) high;
    }
}

------------------------------------------------------------
# Example Walkthrough:
x = 8
low = 0, high = 8
mid = 4, mid*mid = 16 > 8 → high = 3
mid = 1, mid*mid = 1 < 8 → low = 2
mid = 2, mid*mid = 4 < 8 → low = 3
mid = 3, mid*mid = 9 > 8 → high = 2
Loop ends → return high = 2 (floor sqrt)

------------------------------------------------------------
# Time Complexity:
- Binary search runs in O(log x), as we halve the search range each time.
- Multiplication mid*mid takes O(1).

Overall: **O(log x)**

# Space Complexity:
- We only use a few extra variables.
Overall: **O(1)**

------------------------------------------------------------
# Summary (Yuvraj Style):
- Use binary search to find the floor of sqrt(x).
- Check mid*mid and adjust low/high accordingly.
- TC: O(log x), SC: O(1)
- Edge cases: x=0 or x<4 handled separately.

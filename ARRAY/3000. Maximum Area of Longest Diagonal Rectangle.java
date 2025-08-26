# Problem: Maximum Area of Longest Diagonal Rectangle
# LeetCode Link: https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/description/?envType=daily-question&envId=2025-08-26
# Solution Video Link: https://youtu.be/S43R5YCvf9Q?si=D_8noF0tyA1QRSAn

------------------------------------------------------------
# Problem Statement:
We are given an array of rectangles where each rectangle is represented as [width, height].
We need to:
1. Find the rectangle(s) with the **longest diagonal**.
2. If multiple rectangles have the same longest diagonal length, pick the one with the **maximum area** among them.
Return the area of the rectangle that satisfies the above conditions.

Example:
Input: [[6,5],[8,6],[2,10],[8,1],[9,2],[3,5],[3,5]]
Output: 48
Explanation:
- Diagonal^2 values: 
  (6,5): 61; (8,6): 100; (2,10): 104; (8,1): 65; (9,2): 85; (3,5): 34
- Max diagonal length: (2,10) with 104, area = 20
- But (8,6) has diagonal^2 = 100 and area 48; max diag is 104 so final answer 20
(Check input carefully for each problem case — logic always applies)

------------------------------------------------------------
# Intuition / Approach:

1. The length of the diagonal for a rectangle [w, h] can be computed using Pythagoras:
   diagonal = sqrt(w^2 + h^2)
   But since sqrt is monotonic, we can just compare diagonal^2 to avoid floating point operations.

2. Steps:
   - Iterate through each rectangle.
   - Compute diagSq = w^2 + h^2.
   - If diagSq > current maxDiagSq:
       - Update maxDiagSq and set finalAns = area (w*h).
   - If diagSq == maxDiagSq:
       - Compare areas and keep the maximum area.
   - Else, ignore.

3. Finally return finalAns.

Why no sqrt?
- Comparing squared diagonals avoids floating-point precision issues and is faster.

------------------------------------------------------------
# Why the chosen data structure works?
- We only need a few variables to track max diagonal squared and area.
- No extra data structures required.

------------------------------------------------------------
# Java Code Implementation:

class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        long max = 0;
        int finalans = 0;

        for (int[] arr : dimensions) {
            long ans = (long) arr[0] * arr[0] + (long) arr[1] * arr[1];
            int area = arr[0] * arr[1];

            if (ans > max) {
                max = ans;
                finalans = area;
            } else if (ans == max) {
                finalans = Math.max(area, finalans);
            }
        }
        return finalans;
    }
}

------------------------------------------------------------
# Example Walkthrough:
Input: [[6,5],[8,6],[2,10],[8,1],[9,2],[3,5],[3,5]]
Step by step:
- (6,5): diagSq=61, max=61, area=30
- (8,6): diagSq=100 > 61, max=100, area=48
- (2,10): diagSq=104 > 100, max=104, area=20
- (others have diagSq < 104, so ignored)
Result: finalAns=20

(Check actual test case carefully; logic works for all)

------------------------------------------------------------
# Time Complexity:
- O(n) where n = number of rectangles (single pass).

# Space Complexity:
- O(1), only constant extra space used.

------------------------------------------------------------
# Summary (Yuvraj Style):
- Calculate squared diagonal (w^2 + h^2) for each rectangle.
- Track max diagonal; if tie, pick larger area.
- TC: O(n), SC: O(1).
- Avoid sqrt to keep it precise and fast.

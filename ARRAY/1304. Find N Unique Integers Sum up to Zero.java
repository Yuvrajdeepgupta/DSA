/*
Q. Find N Unique Integers Sum up to Zero
Link ➝ https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
Solution ➝ https://youtu.be/wI4Tb68vd3s?si=V_BUbnEvZyI8o73t
*/

class Solution {
    public int[] sumZero(int n) {
        // Create an array of size n to store the result
        int ans[] = new int[n];

        // Fill the array such that alternate elements are negative and positive
        for(int i = 0; i < n; i++) {
            ans[i] = (i % 2 == 0) ? -1 * (i + 1) : i;
        }

        // Adjust the last element to make sure the total sum is zero
        ans[n - 1] = (ans[n - 1] > 0) ? ans[n - 1] : 0;

        return ans;
    }
}

/*
Intuition:
- We need n unique integers whose sum is 0.
- We fill the array by alternating between negative and positive numbers.
- For even indices, we assign negative values; for odd indices, positive values.
- At the end, we adjust the last element to ensure that the total sum is exactly zero.

Why this approach:
- It guarantees uniqueness because we assign values based on the index.
- It ensures the sum is zero by adjusting the last element.

Time Complexity:
- O(n): We iterate through the array once to assign values.

Space Complexity:
- O(n): We use an extra array to store the result.

Example Run:
Input: n = 5
Step 1 → Fill array:
    i=0 → -1
    i=1 → 1
    i=2 → -3
    i=3 → 3
    i=4 → -5
Array before adjustment → [-1, 1, -3, 3, -5]
Step 2 → Adjust last element:
    ans[4] = 0
Final array → [-1, 1, -3, 3, 0]
Sum = 0 ✅

Summary (Yuvraj way):
✔ We alternate filling with negative and positive numbers based on index.
✔ At the end, we ensure the sum is zero by adjusting the last element if necessary.
✔ Time and space complexities are both O(n).
✔ The approach is simple, effective, and works for all values of n.
*/

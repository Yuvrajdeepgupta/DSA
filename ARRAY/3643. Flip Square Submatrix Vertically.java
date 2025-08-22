# Problem: Flip Square Submatrix Vertically
# Question Link: https://leetcode.com/problems/flip-square-submatrix-vertically/
# Solution Link (Video): https://youtu.be/mg-5rfRcTLU?si=NCcdo58DtWtoi3ix

------------------------------------------------
📌 Problem Breakdown:
We are given a 2D matrix `grid` of size m × n, and three integers:
- (x, y) → top-left coordinate of a k × k submatrix
- k → size of the square submatrix

We need to **flip this submatrix vertically**:
- Top row swaps with bottom row
- Second row swaps with second-last row
- Continue until the middle row

Important:
- Only rows inside the submatrix are swapped
- Columns are not reversed individually

------------------------------------------------
💡 Intuition:
- Flipping vertically means reversing the order of rows in the submatrix.
- So, we take the top row (`x`) and bottom row (`x + k - 1`), swap their k elements starting from column `y`.
- Move inward until all rows are flipped.
- This can be done in-place using two pointers (top & bottom).

------------------------------------------------
🛠️ Approach:
1. Find starting row `i = x` and ending row `m = x + k - 1`.
2. While `i < m`:
   - Swap k elements between row `i` and row `m`, starting from column `y`.
   - Increment `i` and decrement `m` to move towards the center.
3. Return updated grid.

------------------------------------------------
✅ Java Code:

class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) 
    {
        int i = x;
        int m = x + k - 1;

        while(i < m)
        {
            flip(grid[i], grid[m], k, y);
            i++;
            m--;
        }

        return grid;
    }

    public void flip(int a[], int b[], int k, int index)
    {
        for(int i = index; i < index + k; i++)
        {
            int temp = a[i];
            a[i] = b[i];
            b[i] = temp;
        }
    }
}

------------------------------------------------
🔍 Example Dry Run:

grid =
1  2  3  4
5  6  7  8
9 10 11 12
13 14 15 16

x = 1, y = 1, k = 2

Submatrix =
6  7
10 11

Flip vertically:
10 11
6  7

Updated grid =
1  2  3  4
5 10 11  8
9  6  7 12
13 14 15 16

------------------------------------------------
⏱️ Time Complexity:
- We swap about k/2 row pairs.
- Each swap touches k elements.
- So total operations = O(k * k) = O(k²)

📦 Space Complexity:
- Only a few temporary variables used for swapping.
- No extra data structures → O(1)

------------------------------------------------
📝 Yuvraj Summary (Easy Words):
Bhai, kaam simple hai → ek chhota square lena hai matrix ke andar aur usko vertical flip karna hai (top row bottom pe, bottom row top pe).  
Do pointer approach lagaya → ek top se, ek bottom se, dono rows swap kar diye column by column.  
Sab kuch in-place hua, extra memory nahi li. Time O(k²), space O(1).  
Bas hogaya mast solution 🔥

------------------------------------------------

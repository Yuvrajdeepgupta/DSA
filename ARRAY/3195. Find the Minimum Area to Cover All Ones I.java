# Problem: Find the Minimum Area to Cover All Ones I
# Question Link: https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/description/
# Solution Reference: https://youtu.be/fE0x8ZlyG_w?si=YX9Ks_34a5R3DbBc

------------------------------------------------------------
## Problem Breakdown:
We are given a binary matrix (grid) where each cell is either 0 or 1.
We need to find the smallest rectangle that covers all the 1s in the matrix 
and return its area.

Key points:
- Rectangle edges should be aligned with the matrix edges.
- If there are no 1s, the area would be 0 (handled implicitly here since there will always be at least one 1 as per problem statement).

------------------------------------------------------------
## Intuition:
To find the minimum bounding rectangle that covers all 1s:
- We need the **topmost**, **bottommost**, **leftmost**, and **rightmost** positions of cells containing 1.
- Once we have these extreme coordinates, we can calculate the rectangle's dimensions:
    height = (bottom_row - top_row + 1)
    width  = (right_col - left_col + 1)
- Area = height * width

------------------------------------------------------------
## Why this Approach?
- We just scan the entire grid once to track min and max rows & columns where 1 appears.
- No need for extra data structures.
- Optimal as it uses O(n*m) time (where n = rows, m = cols) which is unavoidable because we must check all cells.

------------------------------------------------------------
## Step-by-Step Approach:
1. Initialize:
    start_row = large value (min tracking for row index of 1)
    end_row = small value (max tracking for row index of 1)
    start_col = large value (min tracking for column index of 1)
    end_col = small value (max tracking for column index of 1)

2. Traverse the grid:
    - Whenever grid[i][j] == 1:
        start_row = min(start_row, i)
        end_row   = max(end_row, i)
        start_col = min(start_col, j)
        end_col   = max(end_col, j)

3. Compute area:
    (end_row - start_row + 1) * (end_col - start_col + 1)

------------------------------------------------------------
## Time Complexity:
- O(n * m): We traverse the entire grid once.

## Space Complexity:
- O(1): No extra space used except a few variables.

------------------------------------------------------------
## Code Implementation (Java):

class Solution {
    public int minimumArea(int[][] grid) 
    {
        int start_row = 1001, end_row = -1;
        int start_col = 1001, end_col = -1;
        
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid[i].length; j++) 
            {
                if (grid[i][j] == 1) 
                {
                    start_row = Math.min(start_row, i);
                    end_row = Math.max(end_row, i);
                    start_col = Math.min(start_col, j);
                    end_col = Math.max(end_col, j);
                }
            }
        }

        return (end_row - start_row + 1) * (end_col - start_col + 1);
    }
}

------------------------------------------------------------
## Example Walkthrough:
Input:
grid = [
 [0,0,1],
 [0,1,0],
 [0,0,0]
]

- 1s at (0,2) and (1,1)
- start_row = 0, end_row = 1
- start_col = 1, end_col = 2

Area = (1 - 0 + 1) * (2 - 1 + 1) = 2 * 2 = 4

------------------------------------------------------------
## Yuvraj Style Summary:
- Scan the whole grid once, track min/max rows and columns where 1 appears.
- Calculate rectangle area covering all 1s.
- Optimal: O(n*m) time, O(1) space.
- Simple & clean approach.

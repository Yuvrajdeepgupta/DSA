# Problem: Unique Paths III
# Problem Link: https://leetcode.com/problems/unique-paths-iii/
# Solution Reference (Video): https://youtu.be/eagGLroZXMk?si=iWjkFfXNCm5RtIdi

----------------------------------------------------------
## Problem Breakdown:
- Given a grid with:
  - 1: starting square (only one)
  - 2: ending square (only one)
  - 0: empty squares
  - -1: obstacles
- Goal:
  - Find the total number of unique paths from start to end
  - Must visit every empty square exactly once
  - Cannot visit any cell more than once
  - Must avoid obstacles

----------------------------------------------------------
## Intuition & Why This Works:
- This is a classic **backtracking/DFS** problem, similar to "Rat in a Maze".
- Idea:
  1. Find the start position and count total empty cells (0).
  2. Use DFS to explore all 4 directions (up, down, left, right).
  3. While exploring, mark the cell visited (`-1`), and restore it later (backtracking).
  4. When we reach the destination (2), check if all empty squares were visited.
- Backtracking ensures that every possible path is explored without revisiting cells.
- This approach is optimal for the given constraints (grid size max 20 cells).

----------------------------------------------------------
## Why Backtracking is Suitable:
- We need **all** valid paths, not just one.
- There are multiple branching possibilities; DP/memoization won’t help because the path validity depends on visited state.
- Backtracking explores all possibilities with state restoration.

----------------------------------------------------------
## Code (Java):

class Solution 
{
    int startrow, startcol;
    int count;
    int ans;
    
    public int uniquePathsIII(int[][] grid) 
    {
        count = 0;
        ans = 0;
        int zerocount = search(grid); // Count empty squares and locate start
        int row = startrow, col = startcol;
        solve(grid, row, col, zerocount);
        return ans;
    }

    public void solve(int[][] grid, int row, int col, int zerocount)
    {
        // Out of bounds or hitting obstacle
        if(row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1 || grid[row][col] == -1)
        {
            return;
        }

        // If reached destination
        if(grid[row][col] == 2)
        {
            if(count == zerocount) // Check if all empty cells visited
            {
                ans++;
            }
            return;
        }

        if(grid[row][col] == 0) { count++; }
        int temp = grid[row][col];
        grid[row][col] = -1; // Mark visited

        // Explore all directions
        solve(grid, row, col + 1, zerocount);
        solve(grid, row + 1, col, zerocount);
        solve(grid, row, col - 1, zerocount);
        solve(grid, row - 1, col, zerocount);

        // Backtrack
        grid[row][col] = temp;
        if(temp == 0) { count--; }
    }

    public int search(int[][] grid)
    {
        int othercount = 0;
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == 1) { startrow = i; startcol = j; }
                if(grid[i][j] != 0) { othercount++; }
            }
        }
        return (grid.length * grid[0].length) - othercount;
    }
}

----------------------------------------------------------
## Dry Run (Example):
Grid:
[ [1,0,0,0],
  [0,0,0,0],
  [0,0,2,-1] ]

- Start at (0,0), total empty cells = 8
- Explore all directions recursively
- Each time a valid full path is found where all 8 empty cells visited before reaching 2, increment ans.
- Final ans = 2

----------------------------------------------------------
## Time Complexity:
- Worst case: O(4^(m*n)) 
  - m*n = total cells
  - Each cell can branch in 4 directions
  - Max grid size is small, so it's acceptable.
  
## Space Complexity:
- O(m*n) due to recursion stack and visited marking (in-place).

----------------------------------------------------------
## Yuvraj Summary (Easy Language):
- Bhai ye simple rat in a maze jaisa hai.
- Start point dhoondo, count karo kitne empty squares hai.
- DFS chalao char directions me, har baar visited mark karo, backtrack karte time unmark.
- Jab destination 2 pe pahuncho aur sare empty visit ho gaye, ans++.
- Time bad hai theoretically but grid choti hai, so fine hai.
- Bilkul mast beginner-friendly aur correct approach hai.

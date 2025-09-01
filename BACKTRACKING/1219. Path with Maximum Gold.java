========================================================
🔗 Question Link:
https://leetcode.com/problems/path-with-maximum-gold/description/

📺 Solution Links:
1. https://youtu.be/qlgeXDM1z7A?si=DQZ9ySkUTndvqaF8
2. https://youtu.be/35TyXHK9TGY?si=ai2cEYxyBxYGaRSe
========================================================

💡 Problem Breakdown:
We are given a grid where each cell contains:
- A non-negative integer (gold amount) OR
- 0 (no gold)

Rules:
1. We can start from any cell that has gold (>0).
2. From a cell, we can move UP, DOWN, LEFT, or RIGHT.
3. Once we visit a cell, we cannot visit it again in the same path (must mark visited).
4. We must return the maximum gold that can be collected.

Goal: Find the maximum sum of gold collectible along any valid path.

========================================================

💡 Intuition:
This is a **DFS + Backtracking problem**.

- Since we can start from any gold cell, we try DFS from every gold cell.
- At each step:
  1. Pick up gold in the current cell.
  2. Mark the cell as visited (set it to 0).
  3. Explore all 4 directions (up, down, left, right).
  4. Track the best gold collected in this path.
  5. Undo the choice (restore cell gold).
- The maximum across all starting cells is our answer.

Backtracking is needed because:
- We want to explore **all possible paths**.
- After exploring one path, we must **restore the grid state** to explore another.

========================================================

💻 Why this approach works:
- DFS ensures we explore every possible path from a cell.
- Backtracking ensures correctness (no overlapping visits, proper restoration).
- Grid size is small (≤ 15x15), so even though complexity looks high, it runs within limits.

========================================================

✅ Java Code Implementation:

class Solution {
    public int getMaximumGold(int[][] grid) 
    {
        int max=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length;j++)
            {
                if(grid[i][j]>0)
                {
                    max=Math.max(max,solve(grid,i,j,0));
                }
            }
        }
        return max;
    }
    
    public static final int drn[][]={{0,-1},{-1,0},{1,0},{0,1}};
    
    public int solve(int grid[][],int i,int j,int sum)
    {
        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j]==0)
        {
            return sum;
        }

        //do
        int store=grid[i][j];
        grid[i][j]=0;   //mark this place visited
        sum+=store;

        int best=sum;

        //explore
        for(int arr[]:drn)
        {
            best=Math.max(best,solve(grid,i+arr[0],j+arr[1],sum));
        }

        //undo
        grid[i][j]=store;
        sum-=store;

        return best;
    }
}

========================================================

⏱️ Time Complexity:
- In the worst case, we try starting DFS from every cell → O(m*n).
- Each DFS can explore up to 4 directions recursively.
- Maximum recursion depth = m*n (visit all cells).
- So, worst case complexity: O((m*n) * 4^(m*n)) (exponential).
- But since grid size is small (≤ 15x15), this is acceptable.

🗂️ Space Complexity:
- Recursion stack can go as deep as O(m*n).
- No extra space except constants (directions array).
- So, space = O(m*n).

========================================================

📝 Yuvraj Summary (Easy Style):
Bhai simple hai —
- Har gold cell se DFS start karo.
- Visit karo, gold le lo, us cell ko 0 bana do.
- 4 direction explore karo, best sum nikal lo.
- Backtrack karke cell restore karo.
- Max of all paths = answer.

Grid chhota hai toh exponential DFS bhi chalega.  
Bas backtracking karna zaroori hai warna galat result aayega.

========================================================

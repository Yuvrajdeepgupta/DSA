// ✅ GFG Link - https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
// 🎥 Solution Video - https://youtu.be/wXwZSytYrIs?si=78Kw6M_AG4kkZzG3

/*
🧠 Intuition:
- A rat has to reach the bottom-right cell (n-1, n-1) from the top-left cell (0, 0).
- It can move in four directions: Down, Left, Right, Up (DLRU).
- Some cells are blocked (0), others are open (1).
- We use **Backtracking** to explore all possible valid paths.
- At each step, we:
  → Check if the current move is within bounds and on a 1-cell.
  → Mark it visited (to avoid cycles), try all 4 directions, and then unmark it (backtrack).

📌 Why Backtracking?
- We’re exploring **all paths** from source to destination and must **revert changes** to explore other branches — perfect case for backtracking.

👀 Notes:
- We use a shared `StringBuilder` to build the path string efficiently.
- Before each recursive call, append the current direction, and after it, set the length back to the previous length to backtrack safely.
- Use `maze[i][j]=0` to mark visited and restore it later to 1.
*/

class Solution 
{
    static StringBuilder path = new StringBuilder(); // to hold path globally
    
    public ArrayList<String> ratInMaze(int[][] maze) 
    {
        ArrayList<String> ans = new ArrayList<>();
        solve(0, 0, maze, ans);
        return ans;
    }
    
    public void solve(int i, int j, int[][] maze, ArrayList<String> ans)
    {
        // Base case: Out of bounds or blocked cell
        if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length || maze[i][j] == 0)
        {
            return;
        }
        
        // Destination reached
        if (i == maze.length - 1 && j == maze[0].length - 1)
        {
            ans.add(path.toString());
            return;
        }
        
        int len = path.length();    // store original length before appending
        maze[i][j] = 0;             // mark visited
        
        // Move Down
        path.append('D');
        solve(i + 1, j, maze, ans);
        path.setLength(len); // backtrack safely

        // Move Left
        path.append('L');
        solve(i, j - 1, maze, ans);
        path.setLength(len);

        // Move Right
        path.append('R');
        solve(i, j + 1, maze, ans);
        path.setLength(len);

        // Move Up
        path.append('U');
        solve(i - 1, j, maze, ans);
        path.setLength(len);
        
        maze[i][j] = 1;             // unmark (backtrack)
    }
}

/*
🧪 Dry Run Example:

maze = {
  {1, 0, 0, 0},
  {1, 1, 0, 1},
  {1, 1, 0, 0},
  {0, 1, 1, 1}
}

Valid paths will be:
["DDRDRR", "DRDDRR"]

We start at (0,0) → D → D → R → D → R → R → reach (3,3)

🕒 Time Complexity: O(4^(n^2))
→ In the worst case, from every cell, we explore 4 directions.
→ For an n x n grid, max recursion tree height is n^2.
→ Though we prune invalid/visited paths, worst case is exponential.

💾 Space Complexity: O(n^2)
→ Due to recursion stack + storing paths in the result list.
→ `StringBuilder` is reused, so no extra space for strings during recursion.
→ No extra visited[][] used; we modify maze[][] in-place.

📦 Summary (Yuvraj Style):
- This is a classic backtracking problem where we explore all directions.
- `StringBuilder` with `setLength()` gives us efficient and clean path handling.
- Don’t forget to mark cells as visited (0) and then backtrack (restore to 1).
- Order of directions should be D, L, R, U as required by the problem.
- Perfect example to master backtracking templates.
*/

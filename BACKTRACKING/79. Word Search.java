/*
====================================================================
Question: Word Search
Link: https://leetcode.com/problems/word-search/description/
Video Solution: https://youtu.be/whyax_vB8xY?si=YGmT1LoVjFdRYz0x
====================================================================

Approach 1: DFS + Visited Matrix (Backtracking)
------------------------------------------------
- We use a boolean visited[][] array to mark the cells during DFS.
- For every cell in the board, if it matches word[0], start DFS.
- Backtrack after exploring all 4 directions.

Code:
*/

class Solution 
{
    boolean ans;
    public boolean exist(char[][] board, String word) 
    {
        ans=false;
        int rows=board.length, cols=board[0].length;
        boolean visited[][]=new boolean[rows][cols];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(board[i][j]==word.charAt(0))
                {
                    travel(i,j,board,visited,0,word);
                    if(ans) return true;
                }
            }
        }
        return ans;
    }

    public void travel(int i,int j,char[][] board,boolean visited[][],int idx,String word)
    {
        if(ans) return; 
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j]) return;
        if(board[i][j]!=word.charAt(idx)) return;

        if(idx==word.length()-1)
        {
            ans=true;
            return;
        }

        visited[i][j]=true;

        travel(i-1,j,board,visited,idx+1,word);
        travel(i,j+1,board,visited,idx+1,word);
        travel(i+1,j,board,visited,idx+1,word);
        travel(i,j-1,board,visited,idx+1,word);

        visited[i][j]=false; // backtrack
    }
}

/*
------------------------------------------------
Approach 2: DFS + Modify Board In-place (No Extra Visited Array)
------------------------------------------------
- Instead of a visited array, temporarily mark the cell as '$' and restore it after DFS.
- Saves space compared to visited[][].

Code:
*/

class Solution 
{
    boolean ans;
    public boolean exist(char[][] board, String word) 
    {
        ans=false;
        int rows=board.length, cols=board[0].length;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(board[i][j]==word.charAt(0))
                {
                    travel(i,j,board,0,word);
                    if(ans) return true;
                }
            }
        }
        return ans;
    }

    public void travel(int i,int j,char[][] board,int idx,String word)
    {
        if(ans) return; 
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='$') return;
        if(board[i][j]!=word.charAt(idx)) return;

        if(idx==word.length()-1)
        {
            ans=true;
            return;
        }

        char save=board[i][j];
        board[i][j]='$';

        travel(i-1,j,board,idx+1,word);
        travel(i,j+1,board,idx+1,word);
        travel(i+1,j,board,idx+1,word);
        travel(i,j-1,board,idx+1,word);

        board[i][j]=save; // backtrack
    }
}

/*
------------------------------------------------
Approach 3: DFS + Modify Board + Direction Array
------------------------------------------------
- Same as Approach 2 but uses a direction array to reduce code duplication.
- Makes code cleaner and scalable for 8 directions if needed.

Code:
*/

class Solution 
{
    boolean ans;
    int[][] drn={{0,-1},{-1,0},{0,1},{1,0}}; // 4 directions

    public boolean exist(char[][] board, String word) 
    {
        ans=false;
        int rows=board.length, cols=board[0].length;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(board[i][j]==word.charAt(0))
                {
                    travel(i,j,board,0,word);
                    if(ans) return true;
                }
            }
        }
        return ans;
    }

    public void travel(int i,int j,char[][] board,int idx,String word)
    {
        if(ans) return; 
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='$') return;
        if(board[i][j]!=word.charAt(idx)) return;

        if(idx==word.length()-1)
        {
            ans=true;
            return;
        }

        char save=board[i][j];
        board[i][j]='$';

        for(int[] d:drn)
        {
            travel(i+d[0], j+d[1], board, idx+1, word);
        }

        board[i][j]=save; // backtrack
    }
}

/*
------------------------------------------------
Time Complexity Analysis:
------------------------------------------------
- Let N = number of rows, M = number of columns, L = length of word.
- Worst case: For each cell (N*M), we explore up to 4^L paths.
- TC: O(N*M * 4^L) 
  (In practice pruned early due to mismatches, so it's faster)
  
Space Complexity Analysis:
------------------------------------------------
- O(L) recursion stack depth.
- Approach 1: O(N*M) for visited[][] + O(L) recursion.
- Approach 2 & 3: O(L) recursion (modifies board in place, no visited[][] array).

Key Points:
------------------------------------------------
- Use DFS + Backtracking.
- Backtrack after visiting to restore state.
- Can optimize space by modifying board directly.
- Direction array makes the code cleaner.

====================================================================
*/

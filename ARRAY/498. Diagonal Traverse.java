Question Link - https://leetcode.com/problems/diagonal-traverse/description/
Solution Link - https://youtu.be/7HAKiGZSrWc?si=Mz0nNvm7vGf_YEKs

-----------------------------------------------------------
Problem Breakdown:
We are given a matrix and we need to traverse it diagonally.
The rule is: for every diagonal, the traversal direction alternates:
- Even diagonals -> traverse from bottom to top
- Odd diagonals -> traverse from top to bottom
The final output should be a 1D array containing all elements in this diagonal order.

-----------------------------------------------------------
Intuition:
1. Every diagonal in a matrix can be identified by (row + col) index.
   Example: element at (i, j) belongs to diagonal (i + j).
2. Store elements of the matrix grouped by (i + j).
3. While constructing the answer:
   - If diagonal index is even, reverse its elements before adding.
   - If diagonal index is odd, keep order as is.
4. This ensures the zig-zag traversal required by the problem.

-----------------------------------------------------------
Why HashMap (or Map) is appropriate here?
- Because we can directly group elements by diagonal index (i+j).
- HashMap stores diagonal lists efficiently without pre-allocating.
- Later, we just iterate diagonals in increasing order of keys.

-----------------------------------------------------------
Java Code:

class Solution {
    public int[] findDiagonalOrder(int[][] mat) 
    {
        if(mat.length == 1) return mat[0];
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < mat.length; i++)
        {
            for(int j = 0; j < mat[i].length; j++)
            {
                map.computeIfAbsent(i + j, k -> new ArrayList<>()).add(mat[i][j]);
            }
        }
        
        int[] ans = new int[mat.length * mat[0].length];
        int c = 0;
        
        for(int key = 0; key <= (mat.length - 1) + (mat[0].length - 1); key++)
        {
            ArrayList<Integer> list = map.get(key);
            if(list == null) continue;
            
            if(key % 2 == 0)
            {
                for(int i = list.size() - 1; i >= 0; i--)
                {
                    ans[c++] = list.get(i);
                }
            }
            else
            {
                for(int val : list)
                {
                    ans[c++] = val;
                }
            }
        }
        
        return ans;
    }
}

-----------------------------------------------------------
Example Walkthrough:
Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]

Step 1: Group by (i+j)
0 -> [1]
1 -> [2,4]
2 -> [3,5,7]
3 -> [6,8]
4 -> [9]

Step 2: Build result
Diagonal 0 (even) -> reverse [1] = [1]
Diagonal 1 (odd)  -> keep [2,4]
Diagonal 2 (even) -> reverse [3,5,7] = [7,5,3]
Diagonal 3 (odd)  -> keep [6,8]
Diagonal 4 (even) -> reverse [9] = [9]

Final Answer: [1,2,4,7,5,3,6,8,9]

-----------------------------------------------------------
Time Complexity:
- O(m*n) for traversing the matrix
- O(m*n) for building the final result
Total: O(m*n)

Space Complexity:
- O(m*n) extra space for storing diagonals in the map
- O(1) extra apart from output
Total: O(m*n)

-----------------------------------------------------------
Yuvraj Way (Summary):
We identify diagonals using (i+j), dump elements in a HashMap, then decide traversal order based on parity of diagonal index. Evens get reversed, odds remain the same. Finally stitch all diagonals into the answer. Easy grouping + alternating reversal = perfect zig-zag diagonal traversal.
-----------------------------------------------------------

// Problem Link: https://leetcode.com/problems/sort-matrix-by-diagonals/description/
// Solution Link: https://youtu.be/DALZicL-7Yg?si=yiuALdWTdjeM5zFR

/*
📌 Problem: Sort Matrix by Diagonals
Given a m x n matrix mat, sort each diagonal in ascending order and return the resulting matrix.

------------------------------------------------
💡 Intuition:
- Each element belongs to a diagonal identified by the difference (i-j).
- Elements with same (i-j) are on the same diagonal.
- If (i-j) < 0 → diagonal starts from first row, sort ascending.
- If (i-j) >= 0 → diagonal starts from first column, sort descending.
- Two main approaches:
    1. Using Pair + ArrayList with index tracking.
    2. Using PriorityQueue (heap) for automatic sorting.

------------------------------------------------
🧠 Approach 1: Pair + ArrayList
1. Create a `Pair` class to hold index and list of elements.
2. Use HashMap<Integer, Pair> to store elements of each diagonal.
3. Sort each diagonal (ascending for key<0, descending for key>=0).
4. Traverse matrix again and fill elements using Pair.idx.

------------------------------------------------
💻 Code (Pair + ArrayList Version):
*/

import java.util.*;

class Pair
{
    int idx;
    ArrayList<Integer> list;

    public Pair(int idx, ArrayList<Integer> list)
    {
        this.idx = idx;
        this.list = list;
    }
}

class SolutionPairArrayList {
    public int[][] sortMatrix(int[][] grid) 
    {
        HashMap<Integer, Pair> map = new HashMap<>();

        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Populate map with diagonals
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                map.computeIfAbsent(i-j, k -> new Pair(0, new ArrayList<>()))
                   .list.add(grid[i][j]);
            }
        }

        // Step 2: Sort diagonals based on key
        for(var entry : map.entrySet())
        {
            if(entry.getKey() < 0)
                Collections.sort(entry.getValue().list);
            else
                Collections.sort(entry.getValue().list, Collections.reverseOrder());
        }

        // Step 3: Place elements back into grid
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                Pair pair = map.get(i-j);
                grid[i][j] = pair.list.get(pair.idx);
                pair.idx++;
            }
        }

        return grid;
    }
}

/*
------------------------------------------------
🧠 Approach 2: PriorityQueue
1. Use HashMap<Integer, PriorityQueue<Integer>> to store diagonal elements.
2. For key<0 → min-heap (ascending), key>=0 → max-heap (descending).
3. Populate map with elements.
4. Traverse matrix and poll from heap for sorted placement.

------------------------------------------------
💻 Code (PriorityQueue Version):
*/

class SolutionPriorityQueue {
    public int[][] sortMatrix(int[][] grid) 
    {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Populate map with diagonals
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                final int key = i-j;
                map.computeIfAbsent(key, k -> (key < 0) ? new PriorityQueue<>() : new PriorityQueue<>((a,b)->b-a))
                   .offer(grid[i][j]);
            }
        }

        // Step 2: Place sorted elements back into grid
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                grid[i][j] = map.get(i-j).poll();
            }
        }

        return grid;
    }
}

/*
------------------------------------------------
📌 Time Complexity:
- Let m = rows, n = columns.
1. Pair + ArrayList:
   - O(m*n) to populate map.
   - O(diagonal_length * log(diagonal_length)) for sorting each diagonal.
   - Overall ~ O(m*n*log(min(m,n))) because max diagonal length = min(m,n).
2. PriorityQueue:
   - O(m*n*log(diagonal_length)) to add elements to heap.
   - O(m*n*log(diagonal_length)) to poll elements.
   - Overall ~ O(m*n*log(min(m,n))).

📌 Space Complexity:
- Both approaches: O(m*n) for storing diagonal elements in HashMap.

------------------------------------------------
💡 Summary (Yuvraj Way):
- Key insight: diagonals are uniquely identified by i-j.
- Approach 1 (Pair + ArrayList): manual sort, index-tracking for placement.
- Approach 2 (PriorityQueue): automatic sorting, simpler code.
- Both efficiently sort each diagonal and reconstruct the matrix.
- Use whichever suits your preference: heaps for cleaner code, Pair+ArrayList for step-by-step control.
*/

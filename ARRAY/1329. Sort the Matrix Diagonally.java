/*
Question Link: https://leetcode.com/problems/sort-the-matrix-diagonally/description/
Solution Video Link: https://youtu.be/mNWwJQ7_z4Q?si=8dJx2EM4n4M5wYgi

--------------------------------------------
Problem Breakdown:
- We have a matrix (m x n).
- We need to sort each diagonal (top-left to bottom-right) independently in ascending order.
- Diagonal elements are those where (i - j) remains constant.

--------------------------------------------
Intuition:
- All cells with the same (i - j) value lie on the same diagonal.
- If we group elements by their (i - j) value, sort each group, and then place them back, 
  we get diagonally sorted matrix.

--------------------------------------------
Why this approach works:
- Using HashMap<Integer, PriorityQueue<Integer>>:
  -> Key: (i - j) identifies each diagonal.
  -> Value: Min-Heap (PriorityQueue) stores all elements of that diagonal sorted automatically.
- While filling back, we simply poll from PQ to get sorted order.

Alternative Optimal Approach:
- Instead of PriorityQueue, we can use Counting Sort (since element range is 1–100).
- Counting sort is even faster (O(n + range)), but PQ is cleaner & simpler to code.

--------------------------------------------
Code (Using PriorityQueue for simplicity):
*/

class Solution 
{
    public int[][] diagonalSort(int[][] mat) 
    {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        // Step 1: Group all elements of same diagonal (i-j) into min-heaps
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                map.computeIfAbsent(i - j, k -> new PriorityQueue<>()).offer(mat[i][j]);
            }
        }

        // Step 2: Replace matrix values with sorted order by polling from PQ
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                mat[i][j] = map.get(i - j).poll();
            }
        }

        return mat;
    }
}

/*
--------------------------------------------
Alternative Counting Sort Implementation:
- Instead of PriorityQueue, we count frequencies of 1–100 and rebuild sorted order.
- This avoids O(log n) cost of PQ operations.

class Solution 
{
    public int[][] diagonalSort(int[][] mat) 
    {
        HashMap<Integer, Pair> map = new HashMap<>();

        // Step 1: Collect diagonals
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                map.computeIfAbsent(i - j, k -> new Pair(0, new ArrayList<>())).list.add(mat[i][j]);
            }
        }

        // Step 2: Sort each diagonal using counting sort
        for (Pair curr : map.values())
        {
            sort(curr.list);
        }

        // Step 3: Fill back
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                Pair pair = map.get(i - j);
                mat[i][j] = pair.list.get(pair.idx);
                pair.idx++;
            }
        }

        return mat;
    }

    // Counting sort for numbers 1–100
    public void sort(ArrayList<Integer> list)
    {
        if (list.size() == 1) return;

        int[] freq = new int[101];
        for (int curr : list) freq[curr]++;

        int j = 0;
        for (int i = 0; i < 101; i++)
        {
            while (freq[i] > 0)
            {
                list.set(j, i);
                freq[i]--;
                j++;
            }
        }
    }
}

--------------------------------------------
Time Complexity:
- PriorityQueue approach:
  * Collecting elements: O(m * n)
  * Each PQ insertion/removal: O(log k), where k = diagonal length
  * Overall: O(m * n * log(min(m,n)))

- Counting Sort approach:
  * Collecting elements: O(m * n)
  * Counting sort for each diagonal: O(k + 100)
  * Overall: O(m * n + d*100) ≈ O(m * n), where d = number of diagonals.

Space Complexity:
- HashMap for diagonals: O(m * n)
- PQ or frequency array per diagonal.

--------------------------------------------
Yuvraj Style Summary (Easy Words):
- Sab diagonals ko group kiya by (i-j)
- PQ me dala toh sorted aa gaya, ya counting sort se aur fast ho sakta hai
- Fill back krdiya sorted values
- PQ easy, Counting sort fastest (since values range 1–100)
- TC ~ O(m*n*log(min(m,n))) for PQ, O(m*n) for counting sort
- Done & dusted!
*/

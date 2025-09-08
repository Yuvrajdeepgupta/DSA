/*
✅ LeetCode Problem: Flood Fill
🔗 Problem Link: https://leetcode.com/problems/flood-fill/description/
🎥 Solution Video: https://youtu.be/W6nOvvWzZYg?si=m3CBFk8oYrUsnbTs

📖 Problem Statement:
Given a 2D grid representing an image where image[sr][sc] is the starting pixel, and a new color,
fill the connected area (all pixels with the same color as the starting pixel and connected 4-directionally) with the new color.

Return the modified image.

---

💡 Intuition:
We can solve this problem using both DFS (recursive backtracking) and BFS (iterative approach).
Starting from the initial cell, we explore neighbors in 4 directions, filling cells that match the original color.
For DFS, recursion explores neighbors depth-wise, while BFS uses a queue to explore breadth-wise.
Both methods avoid revisiting cells by marking them with the new color during traversal.

---

✅ DFS – Recursive / Backtracking Solution:

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) 
    {
        // If the starting cell already has the new color, return immediately
        if(image[sr][sc] == color) {
            return image;
        }
        // Start DFS from the initial cell
        solve(sr, sc, image, color, image[sr][sc]);
        return image;
    }
    
    // Direction vectors for exploring neighbors
    int drn[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    // Recursive function to explore and fill
    public void solve(int i, int j, int image[][], int color, int original)
    {
        // Boundary and color check
        if(i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != original) {
            return;
        }
        
        // Fill the current cell with the new color
        image[i][j] = color;
        
        // Explore all 4 directions
        for(int arr[] : drn) {
            int new_i = i + arr[0];
            int new_j = j + arr[1];
            solve(new_i, new_j, image, color, original);
        }
    }
}

📊 Time Complexity: O(N * M), where N is rows and M is columns  
📂 Space Complexity: O(N * M) due to recursion stack

---

✅ BFS – Iterative Solution:

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        
        // If the starting cell already has the new color, return immediately
        if(original == color) {
            return image;
        }
        
        int n = image.length;
        int m = image[0].length;
        
        // Direction vectors for neighbors
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        // Queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});
        image[sr][sc] = color; // Fill the starting cell
        
        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            
            // Explore neighbors
            for(int[] dir : directions) {
                int new_i = i + dir[0];
                int new_j = j + dir[1];
                
                // Check boundaries and if the neighbor has the original color
                if(new_i >= 0 && new_i < n && new_j >= 0 && new_j < m && image[new_i][new_j] == original) {
                    image[new_i][new_j] = color; // Fill neighbor
                    queue.add(new int[]{new_i, new_j}); // Add to queue
                }
            }
        }
        
        return image;
    }
}

📊 Time Complexity: O(N * M), where N is rows and M is columns  
📂 Space Complexity: O(N * M) due to the queue in the worst case

---

✅ Why both approaches work:
✔ They explore neighbors in all 4 directions only if the neighbor’s color matches the original color  
✔ Each cell is visited once and marked immediately to avoid revisiting  
✔ DFS explores depth-wise using recursion, while BFS explores breadth-wise using a queue  
✔ Both algorithms handle boundaries and already visited cells effectively

---

📌 Yuvraj-style Summary:
This problem is a classic example where both recursive DFS and iterative BFS can be used. DFS is elegant, easy to write, and follows the backtracking pattern where we explore neighbors and return when boundaries are hit. BFS is iterative, uses a queue, and is great when recursion depth might be an issue. Both solutions have the same time and space complexities, and choosing between them depends on constraints and personal preference. DFS is perfect when learning recursion and backtracking, while BFS is safer for large inputs.

✔ Ready to copy-paste in your notes!

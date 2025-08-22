────────────────────────────────────────────
📌 Problem: Count Submatrices With All Ones
🔗 Question Link: https://leetcode.com/problems/count-submatrices-with-all-ones/?envType=daily-question&envId=2025-08-20
🔗 Solution Video: https://youtu.be/dYsJpsgCPzw?si=ODCj__jYS75__DkG
────────────────────────────────────────────

📝 Problem Breakdown:
We are given a binary matrix (only 0 and 1).
We need to count how many submatrices (continuous rectangles) contain only 1’s.

────────────────────────────────────────────
💡 Intuition:
- A submatrix is valid if all rows in that submatrix have 1’s in a certain set of columns.
- If we fix two rows i..j, we can compress them using AND operation → this gives us a "compressed row".
- Then we just need to count subarrays of consecutive 1’s in that compressed row → each subarray corresponds to multiple submatrices.
- Repeat for all row pairs → add everything up.

────────────────────────────────────────────
⚙️ Approaches:

1️⃣ In-Place Row Compression (your version)
- Directly modify `mat[i]` by AND-ing with later rows.
- Safe because once we finish row i, we never need its original state.
- Space: O(1).

2️⃣ Copy-Array Row Compression (safer version)
- Make a copy of `mat[i]` into a temporary array.
- Do AND operations on that temporary array.
- Preserves the original matrix.
- Space: O(n).

Both work fine, just a trade-off: In-Place (memory efficient) vs Copy (safer to explain in interview).

────────────────────────────────────────────
✅ Code (In-Place O(1) space version):

class Solution 
{
    public int numSubmat(int[][] mat) 
    {
        int count=0;
        for(int i=0;i<mat.length;i++)
        {
            for(int j=i;j<mat.length;j++)
            {
                count+=calculate(mat[i],mat[j]);
            }
        }
        return count;
    }

    public int calculate(int a[],int b[])
    {
        int count=0, ans=0;
        for(int i=0;i<b.length;i++)
        {
            a[i] = a[i] & b[i];   // compress rows

            if(a[i]==1)
            {
                count++;
                ans+=count;
            }
            else
            {
                count=0;
            }
        }
        return ans;
    }
}

────────────────────────────────────────────
✅ Code (Copy Array version - safer):

class Solution 
{
    public int numSubmat(int[][] mat) 
    {
        int m = mat.length, n = mat[0].length;
        int count=0;

        for(int i=0;i<m;i++)
        {
            int[] arr = new int[n];
            for(int k=0;k<n;k++) arr[k]=mat[i][k]; // copy row i

            for(int j=i;j<m;j++)
            {
                if(j>i)
                {
                    for(int k=0;k<n;k++) arr[k] &= mat[j][k];
                }
                count += calculate(arr);
            }
        }
        return count;
    }

    public int calculate(int arr[])
    {
        int count=0, ans=0;
        for(int val: arr)
        {
            if(val==1)
            {
                count++;
                ans+=count;
            }
            else
            {
                count=0;
            }
        }
        return ans;
    }
}

────────────────────────────────────────────
🧮 Dry Run Intuition:
Matrix =
1 0 1
1 1 0
1 1 0

- i=0,j=0 → [1,0,1] → 2 submatrices
- i=0,j=1 → [1,0,0] → 1 submatrix
- i=0,j=2 → [1,0,0] → 1 submatrix
- i=1,j=1 → [1,1,0] → 3 submatrices
- i=1,j=2 → [1,1,0] → 3 submatrices
- i=2,j=2 → [1,1,0] → 3 submatrices
Total = 13

────────────────────────────────────────────
⏱️ Time Complexity:
- For each pair of rows (m²), we scan n columns.
- TC = O(m² * n)

📦 Space Complexity:
- In-Place version → O(1) extra space
- Copy version → O(n) extra space

────────────────────────────────────────────
😎 Yuvraj Summary (Easy Way):
- Fix 2 rows → compress using AND → now it’s like a 1D problem.
- Count consecutive 1’s subarrays → add to answer.
- Repeat for all row pairs.
- Works in O(m² * n), space can be O(1) if we overwrite rows.
- Clean, optimal enough for constraints, and easily explainable in interviews.

────────────────────────────────────────────

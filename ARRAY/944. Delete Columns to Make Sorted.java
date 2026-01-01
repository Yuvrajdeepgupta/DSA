========================================================
LeetCode 944 — Delete Columns to Make Sorted
========================================================
Link: https://leetcode.com/problems/delete-columns-to-make-sorted/

--------------------------------------------------------
PROBLEM BREAKDOWN
--------------------------------------------------------
You are given an array of strings where:
- All strings have equal length
- You compare characters column-wise (top to bottom)

Goal:
Count how many columns are NOT sorted in non-decreasing order.
Those columns must be deleted.

--------------------------------------------------------
INTUITION (COMMON FOR BOTH SOLUTIONS)
--------------------------------------------------------
Since all strings have the same length:
- Each column is independent
- A column is VALID if characters from top to bottom are sorted
- A column is INVALID if at any point:
    previous_char > current_char

If a column is invalid even once → it must be deleted.

--------------------------------------------------------
SOLUTION 1: MY SOLUTION (COLUMN STATE TRACKING)
--------------------------------------------------------

INTUITION:
- Maintain an array to track the state of each column
- If a column ever violates sorting, permanently mark it as invalid
- At the end, count how many columns are invalid

WHY THIS WORKS:
- Once a column is broken, it will always remain broken
- No need to re-check already invalid columns

--------------------------------------------------------
CODE (JAVA)
--------------------------------------------------------
class Solution 
{
    public int minDeletionSize(String[] strs) 
    {
        if(strs.length == 1){ return 0; }

        int arr[] = new int[strs[0].length()];

        for(int i = 1; i < strs.length; i++)
        {
            for(int j = 0; j < strs[i].length(); j++)
            {
                if(arr[j] != -1 && strs[i-1].charAt(j) <= strs[i].charAt(j))
                {
                    arr[j]++;
                }
                else
                {
                    arr[j] = -1;
                }
            }
        }

        int count = 0;
        for(int x : arr)
        {
            if(x < 0)
                count++;
        }

        return count;
    }
}

--------------------------------------------------------
DRY RUN (MY SOLUTION)
--------------------------------------------------------
Input:
["cba","daf","ghi"]

Columns:
0: c ≤ d ≤ g  → valid
1: b > a      → invalid
2: a ≤ f ≤ i  → valid

arr = [2, -1, 2]
Answer = 1

--------------------------------------------------------
TIME & SPACE COMPLEXITY
--------------------------------------------------------
Time  : O(rows × columns)
Space : O(columns)

--------------------------------------------------------
NOTES
--------------------------------------------------------
- arr[j]++ is not logically required, only -1 matters
- Still fully correct and optimal
- Slightly more stateful than needed

========================================================
SOLUTION 2: OPTIMAL & CLEAN APPROACH (DIRECT CHECK)
========================================================

INTUITION:
- Check column by column
- The moment a column breaks sorted order, delete it
- Stop checking that column immediately

WHY THIS IS CLEANER:
- No extra array
- No state tracking
- Break prevents double counting
- Very interview-friendly

--------------------------------------------------------
CODE (JAVA)
--------------------------------------------------------
class Solution 
{
    public int minDeletionSize(String[] strs) 
    {
        int rows = strs.length;
        int cols = strs[0].length();
        int count = 0;

        for(int j = 0; j < cols; j++)
        {
            for(int i = 1; i < rows; i++)
            {
                if(strs[i-1].charAt(j) > strs[i].charAt(j))
                {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}

--------------------------------------------------------
DRY RUN (OPTIMAL SOLUTION)
--------------------------------------------------------
Input:
["zyx","wvu","tsr"]

Column 0:
z > w → violation → count++ → break

Column 1:
y > v → violation → count++ → break

Column 2:
x > u → violation → count++ → break

Answer = 3

--------------------------------------------------------
WHY COUNT DOES NOT INCREASE MULTIPLE TIMES
--------------------------------------------------------
- Each column is handled in ONE outer loop iteration
- break exits the inner loop after first violation
- So count++ can happen at most once per column

--------------------------------------------------------
TIME & SPACE COMPLEXITY
--------------------------------------------------------
Time  : O(rows × columns)
Space : O(1)

========================================================
FINAL VERDICT (YUVRAJ STYLE SUMMARY)
================================================--------
- Both solutions are OPTIMAL in time
- My solution works perfectly and is logically sound
- Cleaner solution is preferred in interviews due to:
  - Less memory
  - Clear intent
  - Easier explanation

If writing in interview → use CLEAN approach  
If solving fast → both are equally valid

========================================================

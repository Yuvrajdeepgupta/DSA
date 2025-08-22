/*
Question Link: https://www.naukri.com/code360/problems/common-element-in-rows_975481?leftPanelTabValue=PROBLEM
Similar Question: LeetCode 1198 - Find Smallest Common Element in All Rows(its premium)
*/

/*
------------------------------------------------
📝 Problem Breakdown
------------------------------------------------
We are given a matrix where each row contains integers. 
We need to find the smallest integer that appears in **all rows**. 
If no such integer exists, return -1.

------------------------------------------------
💡 Intuition
------------------------------------------------
- The naive way is to check each element of the first row in every other row → O(m*n).
- But we can optimize using a HashMap to keep track of how many rows have seen a particular element.
- Key Idea: 
  - If an element exists in every row, its frequency across rows will be equal to the total number of rows (m).
  - So, we maintain counts and only increment them if the element was already seen in previous rows in sequence.

------------------------------------------------
⚙️ Why HashMap Works Here?
------------------------------------------------
- A HashMap allows O(1) updates and lookups.
- By tracking counts row by row, we avoid checking unnecessary elements from later rows that never existed in earlier rows.
- This way, we only "carry forward" potential candidates that can still become common elements.

------------------------------------------------
✅ Approach
------------------------------------------------
1. Use a HashMap<Integer, Integer> → stores element → how many rows have seen it.
2. Traverse each row:
   - If this is not the first row, ignore elements that are not in the map (they can never be common).
   - For valid elements, check if they were last seen in the previous row (count == i-1), then increment.
3. After all rows are processed:
   - Find the smallest key in the map with count == total number of rows.
   - If none found, return -1.

------------------------------------------------
👨‍💻 Code Implementation
------------------------------------------------
*/

import java.util.* ;
import java.io.*; 
import java.util.Map.Entry;

public class Solution 
{
    static int smallestCommonElement(int m, int n, ArrayList<ArrayList<Integer>> mat) 
    {
        HashMap<Integer,Integer> map=new HashMap<>();
        int i=1;

        for(ArrayList<Integer> arr:mat)
        {
            for(int curr:arr)
            {
                // Skip elements that were never seen in earlier rows
                if(i>1 && !map.containsKey(curr))
                {
                    continue;
                }

                // Only increment if it was present till previous row
                if(map.getOrDefault(curr,0)==i-1)
                {
                    map.merge(curr,1,Integer::sum);
                }
            }
            i++;
        }

        int min=Integer.MAX_VALUE;
        for(Map.Entry<Integer,Integer> pair:map.entrySet())
        {
            if(pair.getValue()==m)
            {
                min=Math.min(min,pair.getKey());
            }
        }

        return min==Integer.MAX_VALUE ? -1 : min;
    }
}

/*
------------------------------------------------
📊 Example Dry Run
------------------------------------------------
Matrix:
[
 [1, 2, 3],
 [2, 3, 4],
 [2, 3, 5]
]

Row 1 → map = {1:1, 2:1, 3:1}
Row 2 → 2 and 3 exist in map → map = {2:2, 3:2}
Row 3 → 2 and 3 exist in map → map = {2:3, 3:3}
Final → both 2 & 3 are common, smallest = 2.

Answer = 2 ✅

------------------------------------------------
⏱️ Time Complexity
------------------------------------------------
- Traversing each element: O(m*n)
- Finding min at the end: O(unique elements)
Overall: O(m*n)

------------------------------------------------
💾 Space Complexity
------------------------------------------------
- HashMap stores counts of at most O(n) elements (since each row has n elements).
Overall: O(n)

------------------------------------------------
⚡ Yuvraj Style Summary
------------------------------------------------
- Bhai, har row ke elements ko ek HashMap me dalte gaye.
- Agar koi element pehle ke rows me nahi tha, usko chhod diya.
- Sirf wahi elements bache jinke counts har row ke sath increment hote rahe.
- Last me jo elements ka count == m mila, unme se smallest nikala.
- Simple aur effective solution → O(m*n) time me kaam khatam 🚀
*/

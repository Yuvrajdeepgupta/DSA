========================================================
LeetCode: Minimum Absolute Difference
Link: https://leetcode.com/problems/minimum-absolute-difference/
========================================================

🔹 PROBLEM BREAKDOWN
Given an integer array `arr`, you have to find all pairs of elements with the
minimum absolute difference. Each pair should be returned in ascending order,
and the final answer should also be sorted.

--------------------------------------------------------
🔹 INTUITION (SOCH)
- Absolute difference minimum tabhi milega jab numbers paas-paas hon
- Unsorted array me har pair check karna = O(n^2) ❌ (too slow)
- Agar array sort kar do, toh:
  👉 minimum difference sirf adjacent elements ke beech hoga

Isliye:
1) Sort karo
2) Adjacent differences me minimum nikalo
3) Fir wahi minimum diff wale pairs collect karo

--------------------------------------------------------
🔹 WHY THIS APPROACH WORKS
- Sorting ke baad elements increasing order me hote hain
- Agar arr[i] aur arr[j] (j > i+1) ka diff minimum hota,
  toh beech ke elements aur chhota diff bana dete
- Isliye sirf neighbors check karna enough hai

--------------------------------------------------------
🔹 APPROACH
STEP 1: Array ko sort karo
STEP 2: Ek loop se minimum difference find karo
STEP 3: Dusre loop se saare pairs collect karo jinka diff = mindiff

--------------------------------------------------------
🔹 JAVA CODE
--------------------------------------------------------
class Solution 
{
    public List<List<Integer>> minimumAbsDifference(int[] arr) 
    {
        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Find minimum absolute difference
        int mindiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++)
        {
            mindiff = Math.min(mindiff, arr[i] - arr[i - 1]);
        }

        // Step 3: Collect all pairs with minimum difference
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] - arr[i - 1] == mindiff)
            {
                ans.add(new ArrayList<>(Arrays.asList(arr[i - 1], arr[i])));
            }
        }

        return ans;
    }
}

--------------------------------------------------------
🔹 EXAMPLE WALKTHROUGH
Input: [4, 2, 1, 3]

After sorting:
[1, 2, 3, 4]

Adjacent differences:
2 - 1 = 1
3 - 2 = 1
4 - 3 = 1

Minimum difference = 1

Valid pairs:
[1,2], [2,3], [3,4]

--------------------------------------------------------
🔹 TIME & SPACE COMPLEXITY
Time Complexity:
- Sorting: O(n log n)
- Two linear scans: O(n)
- Overall: O(n log n)

Space Complexity:
- Extra space only for output list: O(n)

--------------------------------------------------------
🔹 YUVRAJ STYLE SUMMARY (FINAL WORDS)
- Brute force chhod diya, sorting ka fayda uthaya
- Adjacent elements hi kaam ke nikle
- Code simple, logic strong, interview-proof solution
- Ye approach optimal hai aur LeetCode standard bhi 👍
========================================================

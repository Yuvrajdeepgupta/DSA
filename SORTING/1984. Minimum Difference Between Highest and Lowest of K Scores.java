========================================================
LeetCode: Minimum Difference Between Highest and Lowest of K Scores
Link: https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
========================================================

🔹 PROBLEM BREAKDOWN
You are given an integer array `nums` and an integer `k`.
You have to pick any `k` elements such that the difference between the
maximum and minimum elements among those `k` elements is minimized.

Return that minimum possible difference.

--------------------------------------------------------
🔹 INTUITION (SOCH)
- Kisi bhi group of k numbers ka difference = (max − min)
- Agar array sorted ho, toh:
  👉 kisi bhi consecutive k elements ka max = last element
  👉 aur min = first element
- Matlab sorted array me sliding window of size `k` chala do,
  aur har window ka difference check karo

Brute force saare subsets try karna = exponential ❌  
Sorting + window = efficient ✔️

--------------------------------------------------------
🔹 WHY THIS APPROACH WORKS
- Sorting ke baad elements order me aa jaate hain
- Best (minimum) difference tab milega jab k elements paas-paas hon
- Isliye non-adjacent combinations check karna bekaar hai

--------------------------------------------------------
🔹 APPROACH
STEP 1: Array ko sort karo  
STEP 2: Sliding window (size = k) use karo  
STEP 3: Har window me:
        difference = nums[j] - nums[i]
STEP 4: Sabse minimum difference track karo

--------------------------------------------------------
🔹 IMPORTANT NOTE (MINOR FIX 🔧)
Your logic is **correct and optimal**, bas ek chhoti si cheez:
- Difference hamesha `nums[j] - nums[i]` hoga (max − min)
- Sorting ascending order me hona chahiye

Baaki structure, quicksort usage, sliding window — sab solid hai 👍

--------------------------------------------------------
🔹 JAVA CODE (CORRECTED & OPTIMAL)
--------------------------------------------------------
class Solution 
{
    public int minimumDifference(int[] nums, int k) 
    {
        if (k == 1) return 0;

        // Step 1: Sort the array
        quickSort(nums, 0, nums.length - 1);

        // Step 2: Sliding window of size k
        int i = 0, j = 0;
        int min_diff = Integer.MAX_VALUE;

        while (j < nums.length)
        {
            if (j - i + 1 == k)
            {
                // max - min
                min_diff = Math.min(min_diff, nums[j] - nums[i]);
                i++;
            }
            j++;
        }

        return min_diff;
    }

    // QuickSort (Ascending)
    public static void quickSort(int[] arr, int low, int high) 
    {
        if (low < high) 
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) 
    {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) 
        {
            if (arr[j] < pivot)   // ascending order
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}

--------------------------------------------------------
🔹 EXAMPLE WALKTHROUGH
nums = [9,4,1,7], k = 2

After sorting:
[1,4,7,9]

Windows of size 2:
[1,4] → diff = 3
[4,7] → diff = 3
[7,9] → diff = 2  ✅ minimum

Answer = 2

--------------------------------------------------------
🔹 TIME & SPACE COMPLEXITY
Time Complexity:
- Sorting (QuickSort avg): O(n log n)
- Sliding window: O(n)
- Overall: O(n log n)

Space Complexity:
- In-place sorting
- Extra space only for variables: O(1)

--------------------------------------------------------
🔹 YUVRAJ STYLE SUMMARY (FINAL WORDS)
- Problem ka core sorting ke baad clear ho jaata hai
- Sliding window + sorted array = guaranteed minimum difference
- Code efficient hai, interview-friendly hai
- Bas max − min ka dhyaan rakho, baaki approach full marks 💯
========================================================

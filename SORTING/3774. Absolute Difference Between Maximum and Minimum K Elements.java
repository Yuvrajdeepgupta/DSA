🔗 Question Link:
https://leetcode.com/problems/absolute-difference-between-maximum-and-minimum-k-elements/

==================================================
🧩 Problem Breakdown
==================================================
Given an integer array `nums` and an integer `k`:

- Select **k smallest elements**
- Select **k largest elements**
- Find:
  
  | (sum of k largest elements) − (sum of k smallest elements) |

Return this absolute difference.

==================================================
💡 Intuition (Simple Thinking)
==================================================
Gupta Ji, seedha logic hai 👇

- Agar array sorted ho:
  - **k smallest** → starting se mil jaate hain
  - **k largest** → end se mil jaate hain
- Dono ka sum nikal lo
- Absolute difference return kar do

Sorting ke baad koi confusion nahi rehta 🔥

==================================================
🛠️ Why Sorting Works?
==================================================
Sorting ke baad:
- Smallest elements automatically left side pe
- Largest elements automatically right side pe

Isse selection easy + clean ho jaata hai  
No extra data structure needed ✅

==================================================
✅ Approach (Step-by-Step)
==================================================
1. Array sort karo
2. Do pointers rakho:
   - `i` → start se (smallest)
   - `j` → end se (largest)
3. `k` times:
   - `sum1 += nums[i]`
   - `sum2 += nums[j]`
4. Absolute difference return karo

==================================================
💻 Java Code (Clean & Optimal)
==================================================
class Solution 
{
    public int absDifference(int[] nums, int k) 
    {
        Arrays.sort(nums);

        int sum1 = 0, sum2 = 0;
        int i = 0, j = nums.length - 1;

        while (k != 0)
        {
            sum1 += nums[i++];
            sum2 += nums[j--];
            k--;
        }

        return Math.abs(sum1 - sum2);
    }
}

==================================================
🧪 Example Walkthrough
==================================================
Input:
nums = [1, 4, 2, 3]
k = 1

Sorted nums = [1, 2, 3, 4]

k smallest sum = 1  
k largest sum = 4  

Answer = |4 - 1| = 3 ✅

==================================================
⏱️ Time & Space Complexity
==================================================
Time Complexity:
- Sorting → O(n log n)
- Loop → O(k)
➡ Overall: **O(n log n)**

Space Complexity:
- Sorting in-place
➡ **O(1)** extra space

==================================================
🧾 Yuvraj-Style Summary
==================================================
- Sorting = clarity
- Start se k uthao (min)
- End se k uthao (max)
- Dono ka sum lo
- Absolute difference maaro

Simple, readable, interview-safe solution 💯  
Bilkul khandani logic 👑

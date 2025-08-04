/*
🌟 Problem: Fruit Into Baskets
🔗 Leetcode: https://leetcode.com/problems/fruit-into-baskets/description/?envType=daily-question&envId=2025-08-04
🎥 Solution Video: https://youtu.be/QBi5_btsse4?si=R6fyoDgZoea2UTs8

--------------------------------------------------------
🧠 Intuition:
--------------------------------------------------------
We are given an array representing fruits on trees. Each tree gives only one type of fruit.

✅ We are allowed to carry **2 baskets** only.
✅ Each basket can only carry **one type of fruit**.
✅ We need to pick fruits **from a subarray** such that:
    - At most 2 types of fruits are included.
    - We pick the **maximum number of fruits** in total (i.e., max window size).

This is a classic case of:
    ✅ Longest subarray with at most 2 distinct elements → use **sliding window**.

There are two approaches:
1. ✅ HashMap: Handles any fruit values.
2. ✅ Frequency Array: Works when fruit values are in range [0...N]

--------------------------------------------------------
🧪 Dry Run Example:
--------------------------------------------------------
Input: [1,2,1,2,3]

j=0: map={1:1}, ans=1  
j=1: map={1:1,2:1}, ans=2  
j=2: map={1:2,2:1}, ans=3  
j=3: map={1:2,2:2}, ans=4  
j=4: map={1:2,2:2,3:1} → map.size > 2 → shrink window  
  - i=0 → reduce map[1] to 1  
  - i=1 → reduce map[1] to 0 → remove key  
Now: map={2:2,3:1}, window is [2,3], ans=4

Output: 4

--------------------------------------------------------
📦 Approach 1: Java Code using HashMap (Universal)
--------------------------------------------------------
*/

class Solution {
    public int totalFruit(int[] nums) 
    {
        int n = nums.length;
        int k = 2;  // max 2 fruit types allowed
        int ans = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0;

        while (j < n) 
        {
            map.merge(nums[j], 1, Integer::sum);  // add current fruit

            // Shrink window if more than 2 types of fruits
            while (!map.isEmpty() && map.size() > k) 
            {
                map.compute(nums[i], (key, value) -> (value == 1) ? null : value - 1);
                i++;
            }

            ans = Math.max(ans, j - i + 1);  // update max window
            j++;
        }

        return ans;
    }
}

/*
⏱ Time Complexity: O(N)
📦 Space Complexity: O(K) → O(2) = constant
*/


/*
--------------------------------------------------------
📦 Approach 2: Java Code using Frequency Array (if fruit[i] <= N)
--------------------------------------------------------
*/

class Solution {
    public int totalFruit(int[] fruits) 
    {
        int n = fruits.length;
        int[] freq = new int[n]; // works only if fruit values are ≤ n
        int i = 0, size = 0, max = 0;

        for (int j = 0; j < n; j++) 
        {
            int curr = fruits[j];
            freq[curr]++;
            if (freq[curr] == 1) 
            {
                size++;  // new fruit type added
            }

            while (i <= j && size > 2) 
            {
                freq[fruits[i]]--;
                if (freq[fruits[i]] == 0) 
                {
                    size--;  // fruit type completely removed
                }
                i++;
            }

            max = Math.max(max, j - i + 1);
        }

        return max;
    }   
}

/*
⏱ Time Complexity: O(N)
📦 Space Complexity: O(N) for freq[] (if fruit values go up to N)
*/

--------------------------------------------------------
✅ Summary (Yuvraj way):
--------------------------------------------------------
👉 You have to find the longest subarray with **at most 2 types** of fruits.
👉 Use sliding window — expand `j`, shrink `i` when size > 2.
👉 `HashMap` works for all fruit values; `freq[]` is good for small bounded input.
👉 Update max window size at every step.
👉 Easy logic, powerful concept. Most interviewers love this type of sliding window!

*/


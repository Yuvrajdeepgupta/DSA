/*
Problem Link: https://leetcode.com/problems/single-number/
Solution Link: https://youtu.be/sFBCAl8yBfE?si=aw1MsLjZfM2xhxmw

--------------------------------------------------------------------
📝 Problem Statement:
Given a non-empty array of integers `nums`, every element appears twice except for one. 
Find that single one.

You must implement a solution with O(n) runtime complexity and O(1) extra space.

--------------------------------------------------------------------
🔍 Example:
Input: nums = [4,1,2,1,2]
Output: 4

Explanation:
- All numbers except 4 occur twice, so answer = 4.

--------------------------------------------------------------------
💡 Approaches:

1️⃣ Brute Force using HashMap
   - Count frequency of each element
   - Return the element with count == 1
   Time Complexity: O(n)
   Space Complexity: O(n)

2️⃣ Sorting-based
   - Sort the array
   - Scan through to find the element not equal to neighbors
   Time Complexity: O(n log n) (due to sort)
   Space Complexity: O(1) or O(n) depending on sort implementation

3️⃣ Optimal using Bitwise XOR (Recommended)
   - XOR all numbers
   - Property: a ^ a = 0, a ^ 0 = a
   - Since duplicates cancel out, only the single number remains
   Time Complexity: O(n)
   Space Complexity: O(1)

--------------------------------------------------------------------
💡 Why XOR works here:
- If you XOR a number with itself, you get 0.
- XOR with 0 returns the number itself.
- Since every number except one appears exactly twice, 
  all duplicates cancel out and we’re left with the single number.
*/

/* -------------------------------------------------
1️⃣ Brute Force Approach: HashMap frequency count
------------------------------------------------- */
import java.util.*;

class SolutionBrute {
    public int singleNumber(int[] nums) 
    {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i : nums)
        {
            freq.merge(i, 1, Integer::sum);
        }

        for (Map.Entry<Integer, Integer> pair : freq.entrySet())
        {
            if (pair.getValue() == 1)
            {
                return pair.getKey();
            }
        }
        return -1; // just in case, though problem guarantees an answer
    }
}

/*
Time Complexity: O(n)
Space Complexity: O(n)
*/

/* -------------------------------------------------
2️⃣ Sorting-based Approach
------------------------------------------------- */
import java.util.*;

class SolutionSort {
    public int singleNumber(int[] nums) 
    {
        if (nums.length == 1)
        {
            return nums[0];
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++)
        {
            if (i == 0)
            {
                if (nums[i + 1] != nums[i])
                {
                    return nums[i];
                }
            }
            else if (i == nums.length - 1)
            {
                if (nums[i - 1] != nums[i])
                {
                    return nums[i];
                }
            }
            else if (nums[i - 1] != nums[i] && nums[i + 1] != nums[i])
            {
                return nums[i];
            }
        }
        return -1;
    }
}

/*
Time Complexity: O(n log n)
Space Complexity: O(1) or O(n) depending on sort algorithm
*/

/* -------------------------------------------------
3️⃣ Optimal Approach: Bitwise XOR
------------------------------------------------- */
class SolutionXOR {
    public int singleNumber(int[] nums) 
    {
        int xor = 0;
        for (int i = 0; i < nums.length; i++)
        {
            xor = xor ^ nums[i];
        }
        return xor;
    }
}

/*
Time Complexity: O(n)
Space Complexity: O(1)
*/

/*
--------------------------------------------------------------------
📌 Summary (Yuvraj way):
Brute force mein HashMap leke frequency count karo – kaam ho jayega but space O(n) lagta.
Sorting se bhi kar sakte ho but O(n log n) lagta.
XOR trick mast hai – duplicates cancel ho jate hai, bas ek loop, O(n) time & O(1) space, fastest aur cleanest.
*/

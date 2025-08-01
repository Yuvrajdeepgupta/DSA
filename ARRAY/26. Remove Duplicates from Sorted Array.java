// ✅ Leetcode 26: Remove Duplicates from Sorted Array
// 🔗 https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
// 🎥 Solution: https://youtu.be/06ALbFrgIoQ?si=nwsIETzqbD_IvU4s

// --------------------------------------------------
// ✅ Brute Force (Using LinkedHashSet):
// - Uses extra space to store unique elements
// - Not in-place technically

class Solution {
    public int removeDuplicates(int[] nums) 
    {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        for(int i = 0; i < nums.length; i++)
        {
            set.add(nums[i]);
        }

        int i = 0;
        for(int val : set)
        {
            nums[i++] = val;
        }

        return set.size();
    }
}

// 🧠 TC: O(n), SC: O(n)


// --------------------------------------------------
// ✅ Optimal Approach (In-place Two Pointers):
// - Traverse with j, shift unique values with i
// - No extra space used

class Solution {
    public int removeDuplicates(int[] nums) 
    {
       int last = -200;
       int i = 0, j = 0, n = nums.length;

       while(j < n)
       {
            if(nums[j] != last)
            {
                nums[i] = nums[j];
                last = nums[j];
                i++;
            }

            j++;
       }

       return i;
    }
}

// 🧠 TC: O(n), SC: O(1)
// --------------------------------------------------

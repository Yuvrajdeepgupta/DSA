Que Link - https://leetcode.com/problems/maximum-product-subarray/
Sol Link - https://youtu.be/Y6B-7ZctiW8?si=82Wxgzv2qTDVLIpU

-----------------------
Approach : Prefix & Suffix Product
-----------------------
Idea:
- Multiplication is tricky because a negative number can turn the product larger if multiplied with another negative.
- So we track both prefix product (from start) and suffix product (from end) in the same loop.
- At each step:
  1) Multiply prefix product by nums[i].
  2) Multiply suffix product by nums[n-1-i].
  3) Update max product as the max of prefix, suffix, and current max.
  4) If prefix or suffix becomes 0 → reset it to 1 (since product with zero will break the chain).
- This way we handle negative numbers and zeros without extra arrays.

-----------------------
Code :
-----------------------
class Solution {
    public int maxProduct(int[] nums) 
    {
        int prefix = 1, suffix = 1;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) 
        {
            prefix *= nums[i];
            suffix *= nums[nums.length - 1 - i];
            
            max = Math.max(max, Math.max(prefix, suffix));
            
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
        }
        
        return max;
    }
}

-----------------------
Time Complexity :
-----------------------
O(n) → We scan the array once from start and once from end (done in the same loop).

-----------------------
Space Complexity :
-----------------------
O(1) → No extra space used apart from a few variables.

-----------------------
Edge Cases :
-----------------------
1) All negative numbers.
2) Zeros in between (reset needed).
3) Single element array.
4) Large positive & negative mix.

-----------------------
Example Dry Run :
-----------------------
nums = [2,3,-2,4]
prefix pass:
  i=0 → prefix=2, suffix=4, max=4
  i=1 → prefix=6, suffix=-8, max=6
  i=2 → prefix=-12, suffix=-16, max=6
  i=3 → prefix=-48, suffix=-32, max=6
Answer → 6

/*
✅ LeetCode Problem: Convert Integer to the Sum of Two No-Zero Integers
🔗 Problem Link: https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/description/
🎥 Solution Video: https://youtu.be/ig4llTdg6_E?si=Uh_mE7Hielx9s71a

📖 Problem Statement:
Given an integer n, return two integers a and b such that:
1. a + b == n
2. Both a and b do not contain any 0 in their decimal representation.

There may be multiple valid answers. You can return any of them.

💡 Intuition:
We iterate from 1 to n-1 and for each possible split (i, n-i), check if both numbers do not contain '0' in their digits.
The helper function 'check()' confirms that a number doesn’t contain the digit '0'.
We return the first valid pair found.

📂 Code with detailed comments:
*/

class Solution 
{
    // Main function to find two no-zero integers that sum to n
    public int[] getNoZeroIntegers(int n) 
    {
        // Iterate through possible splits
        for(int i = 1; i < n; i++) 
        {
            int a = i;
            int b = n - i;
            
            // Check if both numbers don't contain '0'
            if(check(a) && check(b)) 
            {
                return new int[]{a, b};
            }
        }
        
        // Default return, though for valid input this won't be used
        return new int[]{1, 1};
    }

    // Helper function to check if a number has a '0' digit
    public boolean check(int n) 
    {
        while(n > 0) 
        {
            if(n % 10 == 0) 
            {
                return false; // Found '0' in digits
            }
            n = n / 10;
        }
        return true; // No '0' digit found
    }
}

/*
📊 Time Complexity:
- The loop runs up to n, so O(n).
- The check function runs in O(log n) where log n is the number of digits.
- Overall complexity is O(n * log n).

📂 Space Complexity:
- Constant extra space → O(1).

✅ Why this approach is valid:
- Problem constraints ensure at least one valid pair exists.
- Checking every split guarantees correctness.
- The helper function efficiently checks for zeros.

📌 Yuvraj-style Summary:
This problem is solved by iterating through all possible splits of n and checking if both numbers do not contain zeroes in their digits. The check is simple and efficient using modulus and division. It’s intuitive and works well within problem constraints. The helper function is reusable and makes the solution clean and modular.
*/

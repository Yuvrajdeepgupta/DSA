/*
🔷 PROBLEM: Check if a number is divisible by the sum and product of its digits
que link-https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/description/

Given an integer `n`, return true if the number is divisible by the sum + product of its digits.

-------------------------------------------------------------
🧠 INTUITION:

We're asked to check:
    if `n % (sum of digits + product of digits) == 0`

So:
1. Extract digits one by one from the number.
2. Maintain:
   - `sum` of digits
   - `product` of digits
3. After extracting all digits, check:
       return n % (sum + product) == 0;

----------------------------------------------------------------
🔍 EXAMPLE WALKTHROUGH:

Input: n = 122
Digits: 1, 2, 2

sum = 1 + 2 + 2 = 5  
product = 1 * 2 * 2 = 4  
sum + product = 9  

Check: 122 % 9 == 5 → ❌ return false

Input: n = 36  
Digits: 3, 6  
sum = 9, product = 18 → 27  
Check: 36 % 27 == 9 ❌

Input: n = 27  
Digits: 2, 7 → sum = 9, product = 14 → 23  
Check: 27 % 23 ❌

Input: n = 4  
sum = 4, product = 4 → 8  
Check: 4 % 8 ❌

----------------------------------------------------------------
✅ CODE: Iterative Approach
*/

class Solution {
    public boolean checkDivisibility(int n) 
    {
        int temp = n;
        int prod = 1, sum = 0;

        while (n > 0)
        {
            int digit = n % 10;
            prod *= digit;
            sum += digit;
            n = n / 10;
        }

        return temp % (prod + sum) == 0;
    }
}

/*
---------------------------------------------------------------
⏱️ TIME COMPLEXITY:

Let `d` be the number of digits in `n` (max ~10 for int range)

→ Extracting digits, computing sum & product = O(d) = O(1)  
→ Modulo operation = O(1)

✅ Final Time Complexity: O(1)

---------------------------------------------------------------
📦 SPACE COMPLEXITY:

→ Only a few integer variables used (sum, prod, temp)  
✅ Space Complexity: O(1)

---------------------------------------------------------------
📝 YUVRAJ-STYLE SUMMARY:

- Nikaal le digits ek-ek karke.
- Bana sum aur product dono.
- Ab dekh bhai: (original number) divisible hai kya (sum + product) se?
- Agar hai, return true — nahi toh false.

Perfect example of basic digit extraction + modular check 💡

🔥 Sasta, Sundar, Tikau solution. Binary exponentiation nahi hai, lekin still clean AF.

*/

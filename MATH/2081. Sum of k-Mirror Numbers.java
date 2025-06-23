//link - https://youtu.be/j_cFgoSJqDQ?si=_hNYpnK-DwsTMc9g


/*
  ✅ Learned Concepts in This Problem:
  
  1. Generate Only Palindromes:
     - Instead of checking every number from 1 onwards, generate only base-10 palindromes.
     - Done by mirroring the first half of a number.

  2. Avoid Repeating Middle Digit:
     - For odd-length palindromes, skip the first digit of the reversed half while appending.
     - Avoids duplicating the center digit.

  3. Base Conversion:
     - Convert base-10 palindrome to base-k using manual division and modulus.
     - Reverse the result to get correct base-k order.

  4. Efficient Palindrome Check:
     - Use two-pointer technique on StringBuilder to check if it reads same forward and backward.

  5. Safe Parsing:
     - Convert StringBuilder to string when needed using .toString().
     - Avoid NumberFormatException by ensuring string contains only digits.

  6. Optimization:
     - Stop the loop early when 'n' valid k-mirror numbers are found.
     - This avoids unnecessary computation.

*/


//MY CODE - WITH EXPLANATION

class Solution {

    public long kMirror(int k, int n) 
    {
        int L = 1;          // Length of palindromic numbers we're generating
        long sum = 0;       // To store the sum of k-mirror numbers

        // ✅ Instead of checking every number, we generate only valid base-10 palindromes
        while (n > 0)
        {
            int half = (L + 1) / 2;  // We only need to generate the "first half" of the palindrome

            long start = (long) Math.pow(10, half - 1);   // Starting number for the half
            long end   = (long) Math.pow(10, half) - 1;   // Ending number for the half

            for (long i = start; i <= end; i++)
            {
                // ✅ Generate the full base-10 palindrome by appending the reverse of the first half
                StringBuilder s = new StringBuilder();
                s.append(i);
                
                // ✅ If length is odd, skip the middle digit while reversing to avoid duplication
                StringBuilder rev = new StringBuilder(s).reverse();
                s.append(L % 2 != 0 ? rev.substring(1) : rev);

                // ✅ Convert the generated base-10 palindrome to base-k and check if that is also a palindrome
                if (isPalindrome(convert(s, k)))
                {
                    // ✅ If both base-10 and base-k are palindromes, add to the sum
                    sum += Long.parseLong(s.toString());
                    n--;
                    if (n == 0) break;  // Break early if we’ve found enough numbers
                }
            }

            L++;  // Increase the length of palindromes to explore next
        }

        return sum;
    }

    // ✅ Efficient palindrome check for StringBuilder (base-k string or base-10 string)
    public boolean isPalindrome(StringBuilder s)
    {
        int i = 0, j = s.length() - 1;
        while (i < j)
        {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    // ✅ Converts a number (represented as StringBuilder) from base-10 to base-k and returns it as a reversed StringBuilder
    public StringBuilder convert(StringBuilder snum, int k)
    {
        long num = Long.parseLong(snum.toString());
        StringBuilder s = new StringBuilder();

        while (num > 0)
        {
            s.append(num % k);   // appending remainder (digit in base-k)
            num = num / k;
        }

        return s.reverse();      // ✅ reverse to get correct base-k representation
    }
}

//que link-https://leetcode.com/contest/biweekly-contest-160/problems/hexadecimal-and-hexatrigesimal-conversion/description/

/*-------------------------------------------------------------
 *  Convert n² to Hexadecimal and n³ to Base‑36, then concatenate
 *-------------------------------------------------------------
 *
 *  Example:  n = 36
 *    36² = 1296  → Hexadecimal      = "510"
 *    36³ = 46656 → Base‑36 (digits) = "3O0"
 *    Result = "5103O0"
 *
 *  Why the custom helpers?
 *    –  We’re building the digit characters ourselves so you
 *       see exactly how base‑conversion works under the hood.
 *    –  StringBuilder is used because it’s mutable (faster than
 *       repeated string concatenation).
 */
class Solution {

    /* Top‑level method: just glue the two converted strings */
    public String concatHex36(int n) {
        String hexadecimal     = toHex(n * n);       // n² → base‑16
        String hexatrigesimal  = toBase36(n * n * n); // n³ → base‑36
        return hexadecimal + hexatrigesimal;
    }

    /*--------------------  Base‑16  --------------------*/
    private String toHex(int n) {
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            int digit = n % 16;              // grab last hex digit
            ans.append(toAlphaHex(digit));   // map 0‑15 → '0'..'F'
            n /= 16;                         // drop that digit
        }
        return ans.reverse().toString();     // digits were added LSD→MSD
    }

    /*--------------------  Base‑36  --------------------*/
    private String toBase36(int n) {
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            int digit = n % 36;               // grab last base‑36 digit
            ans.append(toAlpha36(digit));     // map 0‑35 → '0'..'Z'
            n /= 36;
        }
        return ans.reverse().toString();
    }

    /* Map 0‑15 to single hex character */
    private char toAlphaHex(int a) {
        return (a < 10) ? (char) (a + '0')    // 0‑9 → '0'..'9'
                        : (char) (a - 10 + 'A'); // 10‑15 → 'A'..'F'
    }

    /* Map 0‑35 to single base‑36 character */
    private char toAlpha36(int a) {
        return (a < 10) ? (char) (a + '0')    // 0‑9
                        : (char) (a - 10 + 'A'); // 10‑35 → 'A'..'Z'
    }
}


/*1. Square the number → n², cube the number → n³
2. Convert n² to base‑16:
      while (n > 0) { digit = n % 16; append char; n /= 16; }
   – use '0'..'9' for 0‑9, 'A'..'F' for 10‑15
3. Convert n³ to base‑36 the same way, but modulus/divisor is 36
   – for 10‑35 use 'A'..'Z'
4. Reverse each StringBuilder (because we built digits from
   least‑significant to most‑significant)
5. Concatenate hex + base36 and return
*/

//que link- https://leetcode.com/problems/shifting-letters/description/

// ─────────────────────────────────────────────────────────
// BRUTE   |  TC: O(n)  |  SC: O(n)
// Builds cumulative shifts first, THEN loops again to build string.
// ─────────────────────────────────────────────────────────

class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;

        /* STEP‑1: Convert shifts[] into “total shift needed at i” by
           walking from right → left and keeping a running sum.            */
        int total = 0;
        for (int i = n - 1; i >= 0; i--) {
            total = (total + shifts[i]) % 26;   // wrap at 26 so it never explodes
            shifts[i] = total;                  // store back (now a prefix‑sum array)
        }

        /* STEP‑2: Build result string in a fresh StringBuilder (O(n) space). */
        StringBuilder ans = new StringBuilder(s.length());
        for (int i = 0; i < n; i++) {
            int newPos = (s.charAt(i) - 'a' + shifts[i]) % 26;
            ans.append((char) (newPos + 'a'));
        }

        return ans.toString();
    }
}

// ─────────────────────────────────────────────────────────
// BETTER  |  TC: O(n)  |  SC: O(n)
// Builds answer while scanning right→left, BUT appends in reverse
// order so we call reverse() at the end (still O(n) space).
// ─────────────────────────────────────────────────────────

class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();
        int total = 0;
        StringBuilder ans = new StringBuilder(n);   // build backward

        for (int i = n - 1; i >= 0; i--) {
            total = (total + shifts[i]) % 26;       // cumulative shift
            int newPos = (s.charAt(i) - 'a' + total) % 26;
            ans.append((char) (newPos + 'a'));      // push in reverse order
        }

        return ans.reverse().toString();            // flip back to normal
    }
}


// ─────────────────────────────────────────────────────────
// MORE‑BETTER | TC: O(n) | SC: O(n)
// Drops editing of shifts[], keeps one running total →
// but still uses StringBuilder + reverse (so O(n) space).
// ─────────────────────────────────────────────────────────

class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();
        int total = 0;
        StringBuilder ans = new StringBuilder(n);

        for (int i = n - 1; i >= 0; i--) {
            total = (total + shifts[i]) % 26;       // running total only
            int newPos = (s.charAt(i) - 'a' + total) % 26;
            ans.append((char) (newPos + 'a'));
        }

        return ans.reverse().toString();
    }
}

// ─────────────────────────────────────────────────────────
// OPTIMAL | TC: O(n) | SC: O(1)
// Edit a char[] in place while scanning right→left.
// No StringBuilder, no reverse, no extra array.
// ─────────────────────────────────────────────────────────

class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();
        int total = 0;
        char[] ch = s.toCharArray();          // mutable buffer

        for (int i = n - 1; i >= 0; i--) {
            total = (total + shifts[i]) % 26; // update running shift (0‑25)
            int newPos = (ch[i] - 'a' + total) % 26;
            ch[i] = (char) (newPos + 'a');    // write back directly
        }
        return new String(ch);                // wrap char[] into final String
    }
}

// Brute: prefix‑sum array + second loop → O(n) time, O(n) space
// Better: build while scanning R→L, use reverse() → O(n) time, O(n) space
// More‑Better: drop shifts[], still StringBuilder + reverse → O(n) time, O(n) space
// Optimal: mutate char[] in place, no reverse → O(n) time, O(1) space

//que link- https://leetcode.com/problems/reverse-vowels-of-a-string/
//sol link-https://youtu.be/pqzvpK8zTHU?si=FOxPbr5y3mY4ClHu

/*******************************************************************************************/

// ✅ Problem: Reverse Vowels of a String
// Leetcode Link: https://leetcode.com/problems/reverse-vowels-of-a-string/

// ------------------- ✅ Intuition -------------------
/*
We are asked to reverse only the vowels in a given string.
That means, all vowels (both lowercase and uppercase) should swap positions
with each other — like mirroring from both ends.

So we use two pointers:
  - One from start (i) and one from end (j)
  - Move i forward until we find a vowel
  - Move j backward until we find a vowel
  - Swap arr[i] and arr[j] if both are vowels
  - Repeat until i >= j

To make vowel-checking fast, we avoid regex and use a simple switch statement.
*/

// ------------------- ✅ Dry Run -------------------
/*
Input: "hello"
Vowels: 'e' and 'o'
Step 1: i = 1 ('e'), j = 4 ('o') → swap → "holle"
Done.

Input: "leetcode"
Vowels: e, e, o, e
Final: "leotcede"
*/

// ------------------- ✅ Code -------------------

class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray(); // convert to char array to make swapping easy
        int i = 0, j = arr.length - 1;

        while (i < j) {
            char left = arr[i], right = arr[j];

            if (isVowel(left) && isVowel(right)) {
                // Swap vowels
                arr[i] = right;
                arr[j] = left;
                i++;
                j--;
            } else if (!isVowel(left)) {
                i++; // skip non-vowel from left
            } else {
                j--; // skip non-vowel from right
            }
        }

        return new String(arr); // build and return the new string
    }

    public boolean isVowel(char c) {
        // Fast vowel check using switch-case
        switch (Character.toLowerCase(c)) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
                return true;
            default:
                return false;
        }
    }
}

// ------------------- ✅ Time Complexity -------------------
/*
O(n) — where n is the length of the string
- Each character is visited at most once using the two pointers
- Vowel check is O(1)

No nested loops = Linear time
*/

// ------------------- ✅ Space Complexity -------------------
/*
O(n) — for char[] array to convert string for swapping
- If interviewer allows in-place modification, this is acceptable.
- The final result string is also O(n)
*/

// ------------------- ✅ Yuvraj Style Summary -------------------
/*
- Two pointer approach to reverse only vowels in string.
- Don't use regex for vowel checking (slow). Use switch or Set instead.
- Convert string to char array → swap vowels → build back string.
- This is very common pattern in LeetCode for string manipulation.

🔥 If you see "reverse only selected characters" — go for two pointers + condition check.

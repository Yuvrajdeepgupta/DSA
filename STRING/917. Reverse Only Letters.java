/*====================================================================================================================
✅ PROBLEM LINK: https://leetcode.com/problems/reverse-only-letters/
🎥 SOLUTION VIDEO: https://youtu.be/iiogx861st8?si=rEEcBScFRaZtIhpc
🔁 PROBLEM: LeetCode 917 - Reverse Only Letters
====================================================================================================================*/

/*
🧮 DESCRIPTION:
--------------------
Given a string s, reverse only the letters and leave all other characters (like digits or symbols) at their positions.

📌 Example 1:
Input:  s = "ab-cd"
Output: "dc-ba"

📌 Example 2:
Input:  s = "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

📌 Example 3:
Input:  s = "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
*/


/*====================================================================================================================
🧠 INTUITION:
====================================================================================================================*/
// This is a classic two-pointer string problem.
// → Convert string to char[] for in-place operations.
// → Start two pointers from front (i=0) and end (j=length-1).
// → Move i forward until it points to a letter.
// → Move j backward until it points to a letter.
// → Swap letters at i and j.
// → Skip over non-letter characters.
// → Repeat till i < j.
// Use Character.isLetter() to check if a char is a valid alphabet letter.


/*====================================================================================================================
💻 CODE:
====================================================================================================================*/

class Solution {
    public String reverseOnlyLetters(String s) 
    {
        char arr[] = s.toCharArray();   // Convert to character array
        int i = 0, j = arr.length - 1;

        while(i < j)
        {
            if(isValid(arr[i]) && isValid(arr[j]))
            {
                // Both characters are letters → swap
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
            else if(!isValid(arr[i]))
            {
                // Move i forward if it's not a letter
                i++;
            }
            else
            {
                // Move j backward if it's not a letter
                j--;
            }
        }

        return new String(arr);  // Convert char[] back to String and return
    }

    public static boolean isValid(char c)
    {
        return Character.isLetter(c);  // Built-in method to check if character is a letter
    }
}


/*====================================================================================================================
🧪 DRY RUN (Example: s = "a-bC-dEf-ghIj")
====================================================================================================================*/

// Step-by-step pointers:
// i=0, j=11 → 'a' and 'j' are letters → swap → "j-bC-dEf-ghIa"
// i=1 ('-') → not letter → i++
// i=2 ('b'), j=10 ('I') → both letters → swap → "j-IC-dEf-ghba"
// ...
// Continue until i >= j
// Final string → "j-Ih-gfE-dCba"


/*====================================================================================================================
⏱ TIME COMPLEXITY:
====================================================================================================================*/
// O(n)
// → We scan each character at most once using two pointers.

📦 SPACE COMPLEXITY:
====================================================================================================================*/
// O(n)
// → We convert input string to char array, which uses O(n) extra space.
// → If modifying input directly allowed, then space could be O(1).



/*====================================================================================================================
🧾 YUVRAJ STYLE SUMMARY (Easy Language 🧠):
====================================================================================================================*/
// 🔸 Make char array of the string so we can swap directly.
// 🔸 Put 2 pointers: one from start (i), one from end (j).
// 🔸 If both are letters → swap them.
// 🔸 If left one is not letter → i++
// 🔸 If right one is not letter → j--
// 🔸 Keep going until both meet.
// 🔸 Use Character.isLetter() to check if a char is a-z or A-Z.
// 🔸 At the end, return new string from the updated array.

// ✅ Perfect use-case for two-pointer + string manipulation.
// ✅ No stack, no extra logic — simple and clean.
// ✅ Use this pattern for similar problems like "reverse vowels", "reverse only digits", etc.

// 💯 Clean, efficient, and interview-ready solution 🔥

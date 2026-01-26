====================================================
LeetCode 3813: Vowel–Consonant Score
Link:
https://leetcode.com/problems/vowel-consonant-score/
====================================================

PROBLEM STATEMENT (Simple Words):
--------------------------------
You are given a string s containing:
- lowercase English letters
- spaces
- digits

Let:
v = number of vowels in the string
c = number of consonants in the string

Vowel = a, e, i, o, u  
Consonant = any other lowercase English letter

Score definition:
- If c > 0 → score = floor(v / c)
- If c == 0 → score = 0

Return the score as an integer.

----------------------------------------------------

INTUITION (Soch ka tareeka):
----------------------------
1. Traverse the string character by character.
2. Count vowels separately.
3. Count consonants only if the character is:
   - a lowercase English letter
   - NOT a vowel
4. Ignore spaces and digits automatically.
5. If consonants exist, divide vowels by consonants.
6. Integer division already gives floor value in Java.

----------------------------------------------------

WHY THIS APPROACH WORKS:
-----------------------
- We scan the string only once → efficient.
- Direct character comparison avoids extra data structures.
- Integer division (v / c) already behaves like floor.
- Constraints are small, but solution is still optimal.

----------------------------------------------------

JAVA CODE (Clean & Optimal):
----------------------------

class Solution
{
    public int vowelConsonantScore(String s)
    {
        char arr[] = s.toCharArray();

        int v = 0, c = 0;

        for (char ch : arr)
        {
            if (isVowel(ch))
            {
                v++;
            }
            else if (ch >= 'a' && ch <= 'z')
            {
                c++;
            }
        }

        return c != 0 ? v / c : 0;
    }

    public boolean isVowel(char c)
    {
        return (c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u');
    }
}

----------------------------------------------------

DRY RUN EXAMPLE:
----------------
Input: "cooear"

Characters:
c → consonant → c = 1
o → vowel     → v = 1
o → vowel     → v = 2
e → vowel     → v = 3
a → vowel     → v = 4
r → consonant → c = 2

Result = v / c = 4 / 2 = 2

----------------------------------------------------

EDGE CASE:
----------
Input: "au 123"

v = 2
c = 0

Since c == 0 → answer = 0

----------------------------------------------------

TIME & SPACE COMPLEXITY:
------------------------
Time Complexity: O(n)
Space Complexity: O(1)

----------------------------------------------------

YUVRAJ (KHANDANI) SUMMARY:
-------------------------
- Single pass solution
- No extra space
- Clear vowel & consonant separation
- Integer division handles floor automatically
- Exam + Interview + LeetCode perfect solution

====================================================

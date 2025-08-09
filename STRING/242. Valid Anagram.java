/*
====================================================================
✅ Problem: Valid Anagram
🔗 Problem Link: https://leetcode.com/problems/valid-anagram/description/
🎥 Solution Video Link: https://youtu.be/1tmEKyRAMuY?si=KMbKL55sN7dCviTd
====================================================================

📌 Problem Statement:
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
typically using all the original letters exactly once.

--------------------------------------------------------------------
📌 Example:
Input:  s = "anagram", t = "nagaram"
Output: true

Input:  s = "rat", t = "car"
Output: false
--------------------------------------------------------------------

====================================================================
💡 Intuition:
1. If both strings have different lengths → immediately false.
2. An anagram means:
   - Every character appears the same number of times in both strings.
   - Order doesn't matter.
3. We can verify by:
   - Sorting and comparing (brute force)
   - Counting characters with frequency arrays (better & optimal)
4. Optimal way:
   - Use a single frequency array, increment counts for s, decrement for t.
   - If final array is all zeros → anagram confirmed.

====================================================================
💡 Why This Approach Works:
- If each character count in s and t is balanced out to 0, 
  that means they contain exactly the same characters in the same frequency.

====================================================================
📌 Approach 1: Brute Force (Sorting)
- Convert both strings to char arrays.
- Sort them.
- Compare sorted arrays.

⏱ Time Complexity:  O(n log n)   (due to sorting)
📦 Space Complexity: O(n)         (char arrays storage)
--------------------------------------------------------------------
class Solution 
{
    public boolean isAnagram(String s, String t) 
    {
        if(s.length()!=t.length()){return false;}

        char array1[]=s.toCharArray();
        char array2[]=t.toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        return Arrays.equals(array1,array2);
    }
}
--------------------------------------------------------------------

📌 Approach 2: Better (Two Frequency Arrays)
- Maintain two int arrays (size 26 for lowercase letters).
- Count frequency of each char in s and t separately.
- Compare both arrays.

⏱ Time Complexity:  O(n)   (single pass for counting + comparison)
📦 Space Complexity: O(1)   (fixed size arrays)
--------------------------------------------------------------------
class Solution 
{
    public boolean isAnagram(String s, String t) 
    {
        if(s.length()!=t.length()){return false;}

        int freq1[]=new int[27];
        int freq2[]=new int[27];

        int n=s.length();
        for(int i=0;i<n;i++)
        {
            freq1[s.charAt(i)-'a']++;
            freq2[t.charAt(i)-'a']++;
        }

        for(int i=0;i<27;i++)
        {
            if(freq1[i]!=freq2[i]){return false;}
        }

        return true;
    }
}
--------------------------------------------------------------------

📌 Approach 3: Optimal (Single Frequency Array)
- Maintain one int array (size 26).
- Increment counts for chars from s.
- Decrement counts for chars from t.
- At the end, check if all counts are 0.

⏱ Time Complexity:  O(n)   (single pass + final check)
📦 Space Complexity: O(1)   (fixed size array)
--------------------------------------------------------------------
class Solution 
{
    public static boolean isAnagram(String s, String t) 
    {
        if(s.length()!=t.length()){return false;}

        int freq[]=new int[27];

        int n=s.length();
        for(int i=0;i<n;i++)
        {
            freq[s.charAt(i)-'a']++;
            freq[t.charAt(i)-'a']--;
        }

        for(int curr:freq)
        {
            if(curr!=0){return false;}
        }

        return true;
    }
}
--------------------------------------------------------------------

====================================================================
📊 Time & Space Complexity Summary:
1️⃣ Brute Force:  
   - Time:  O(n log n)  
   - Space: O(n)
2️⃣ Better:  
   - Time:  O(n)  
   - Space: O(1)
3️⃣ Optimal:  
   - Time:  O(n)  
   - Space: O(1)

====================================================================
📝 Yuvraj-Style Summary:
- Sabse pehle length check → agar mismatch → return false.
- Optimal method mein ek hi freq array rakho, 
  s ke liye increment karo, t ke liye decrement karo.
- Agar final mein saare 0 → strings anagram hain.
- Sorting se bhi kar sakte ho (brute) but n log n lagega.
- Frequency array ka use karke O(n) time mein solve ho jayega.

====================================================================
*/

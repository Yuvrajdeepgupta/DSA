// 📌 Problem link: https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/description/
// 📹 Solution link: https://youtu.be/yglCwkEM4Kg?si=cHIp3OHcu1rwhCtw

/*
✅ Intuition:
- We need to count the frequency of each character in the string.
- Separate characters into vowels and consonants.
- Find the maximum frequency among vowels and among consonants.
- Return the sum of these two maximum frequencies.

This approach is efficient because it processes the string once to count frequencies and then checks the frequencies in constant time.
*/

class Solution {
    public int maxFreqSum(String s) 
    {
        // Step 1 - Frequency array to count occurrences of each letter
        int freq[] = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2 - Variables to track max frequency of vowels and consonants
        int max_vowel = 0, max_consonant = 0;
        
        for (int i = 0; i < 26; i++) {
            char curr = (char) (i + 'a');
            
            if (isVOC(curr)) {
                max_vowel = Math.max(max_vowel, freq[i]);
            } else {
                max_consonant = Math.max(max_consonant, freq[i]);
            }
        }

        // Step 3 - Return the sum of max frequencies
        return max_consonant + max_vowel;
    }

    // Helper function to check if a character is a vowel
    public boolean isVOC(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}

/*
✅ Example Walkthrough:
Input: "ababacd"
Frequencies:
- 'a' → 3
- 'b' → 2
- 'c' → 1
- 'd' → 1

Max vowel = 3 ('a'), max consonant = 2 ('b')
Output = 3 + 2 = 5

✅ Time Complexity:
- O(n), where n is the length of the string.
- We traverse the string once and then iterate over 26 letters, which is constant.

✅ Space Complexity:
- O(1), since we only use a fixed-size array of length 26 for frequency counting.

📌 Summary:
- Count frequencies of each character using an array.
- Separate characters into vowels and consonants.
- Find the maximum frequency in both categories and return their sum.
- The approach is optimal, simple, and efficient with O(n) time and constant space.
*/

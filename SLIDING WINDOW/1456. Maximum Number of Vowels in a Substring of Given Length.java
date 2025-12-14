/*
Problem link: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
Solution link: https://youtu.be/CAVnGkDzqAs?si=Pwb0qVCGhZbRcaYc

Intuition:
- We need to find the maximum number of vowels in any substring of size 'k'.
- Sliding window technique is perfect because we only need information about 'k' consecutive characters at a time.
- We expand the window to 'k' size, then slide it one character at a time, updating the vowel count efficiently.
- We keep track of the max vowels seen so far while sliding the window.

Approach:
1. Use two pointers, 'i' and 'j', representing the start and end of the window.
2. As we move through the string, count vowels when expanding the window.
3. When the window size reaches 'k', compare and update the maximum count.
4. Slide the window by removing the first character's effect and moving 'i' forward.
5. Continue until we've processed the whole string.

Code:
*/
class Solution 
{
    public int maxVowels(String s, int k) 
    {
        int i = 0, j = 0;
        int count = 0; // current number of vowels in window
        int max = 0;   // maximum vowels found in any window

        for (char curr : s.toCharArray()) 
        {
            // Expand the window: check if the current character is a vowel
            if (isVowel(curr)) {
                count++;
            }

            // Check if the window size is exactly 'k'
            if (j - i + 1 == k) 
            {   
                // Update the max vowels count
                max = Math.max(max, count);

                // Before sliding the window, remove the effect of the outgoing character
                char temp = s.charAt(i);
                if (isVowel(temp)) {
                    count--;
                }
                // Move the start of the window forward
                i++;
            }

            // Expand the end of the window
            j++;
        }

        return max;
    }

    // Helper function to check if a character is a vowel
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}

/*
Example Walkthrough:
Input: s = "abciiidef", k = 3
- Window 1: "abc" → 1 vowel → max = 1
- Window 2: "bci" → 1 vowel → max = 1
- Window 3: "cii" → 2 vowels → max = 2
- Window 4: "iii" → 3 vowels → max = 3
- Window 5: "iid" → 2 vowels → max = 3
- Window 6: "ide" → 2 vowels → max = 3
- Window 7: "def" → 1 vowel → max = 3
Output: 3

Time Complexity:
- O(n), where n is the length of the string 's'.
- We iterate over each character once while expanding and sliding the window.

Space Complexity:
- O(1), as we use only a fixed number of extra variables.

Summary (Yuvraj way):
✔ This is a sliding window problem where you track vowels in each window of size 'k'.
✔ Expand the window while counting vowels, then slide by removing the effect of the outgoing character.
✔ It's optimal with O(n) time and O(1) space.
✔ Very clean and interview-friendly solution!
*/

/*
Problem link: https://leetcode.com/problems/sort-vowels-in-a-string/description/
Solution link: https://youtu.be/nEDXPkGsjXo?si=tDjvRMXmsYzP3XoO

Detailed Approach:
✔ The problem requires us to sort only the vowels in the input string, while keeping other characters in the same position.
✔ First, we count how many times each vowel appears in the string using a frequency array based on ASCII values.
✔ Next, we traverse the string again, and whenever we encounter a vowel, we replace it with the smallest available vowel from the frequency array.
✔ This way, vowels are sorted without using extra space like a separate list or array for vowels.
✔ We keep track of which vowels have been used by decrementing their frequency after using them.
✔ For checking whether a character is a vowel, we write a helper method that compares the character with all possible vowels (both lowercase and uppercase).

Step-by-step explanation:
1. Initialize a frequency array of size 123 (to cover ASCII values up to 'z').
2. Traverse the string and count the occurrences of vowels using this array.
3. Create a result array of the same size as the input string.
4. Traverse the string again:
   - If the current character is a vowel, find the smallest vowel from the frequency array and insert it at this position in the result.
   - If it's not a vowel, insert it as it is.
5. After filling the result array, convert it to a string and return.

Code:
*/
class Solution 
{
    public String sortVowels(String s) 
    {
        // Step 1: Count frequency of vowels in the string
        int freq[] = countVowels(s);

        // Step 2: Prepare result array
        char ans[] = new char[s.length()];

        int prev = 0; // Tracks the last used vowel index in the frequency array
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(isVowel(c))
            {
                // Find the smallest vowel still available
                for(int j = prev; j < 123; j++)
                {
                    if(freq[j] > 0)
                    {
                        ans[i] = (char)j;
                        freq[j]--;
                        prev = j; // Update last used index
                        break;
                    }
                }
            }
            else
            {
                // Non-vowels are directly copied
                ans[i] = c;
            }
        }

        // Convert result array to string and return
        return new String(ans);
    }

    // Count frequency of vowels in the string
    private int[] countVowels(String s)
    {
        int freq[] = new int[123]; // ASCII size
        for(char c : s.toCharArray())
        {
            if(isVowel(c))
            {
                freq[c]++;
            }
        }
        return freq;
    }

    // Check if the character is a vowel (case insensitive)
    private boolean isVowel(char ch) 
    {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
            || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}

/*
Example walkthrough:
Input: "leetcode"
1. Count vowels: e=3, o=1
2. Traverse and fill:
   - l → keep as is
   - e → first smallest vowel 'e'
   - e → second 'e'
   - t → keep as is
   - c → keep as is
   - o → third 'e'
   - d → keep as is
   - e → last 'o'
Output: "leotcede"

Time Complexity:
✔ O(n), where n is the length of the input string.
✔ Each character is visited twice — once to count vowels and once to fill them.

Space Complexity:
✔ O(1), because the frequency array is fixed in size (123), independent of input length.

Summary (Yuvraj way):
✔ We count vowels using a frequency array and fill them back in sorted order.
✔ No extra space is used apart from the fixed-size array.
✔ The solution runs in linear time and is very efficient.
✔ Clear and easy to implement for both interviews and coding practice!
*/

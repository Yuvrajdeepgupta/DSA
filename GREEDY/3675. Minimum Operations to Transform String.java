/*
Problem: Minimum Operations to Transform String
Problem Link: https://leetcode.com/problems/minimum-operations-to-transform-string/
Solution Video Link: https://youtu.be/3OwyPpkc2Hg?si=Qi1aJ8DZniJx2w-9&t=154

✅ Intuition:
- We are given a string 's' containing lowercase English letters.
- We can perform operations to change a character to the next character in the alphabet.
- Our goal is to make all characters unique using minimum operations.
- The idea is to count the frequency of each character and start resolving conflicts from the character 'z' going backward to 'a'.
- By ensuring that each character occupies a unique position in the alphabet, and always choosing the next valid lower character, we minimize the number of changes needed.
- The greedy approach of handling higher characters first avoids unnecessary conflicts.

✅ Approach:
1. Create a frequency array `freq` of size 26 to count how many times each character appears in the string.
   Example: if 'a' appears 2 times, `freq[0] = 2`.
2. We initialize a variable `prev` to 26, which represents an index beyond 'z'.
   It will be used to keep track of the previous unique character’s index.
3. We process characters from 'z' (index 25) to 'b' (index 1):
   - If a character is present (`freq[i] > 0`), we need to ensure it is placed uniquely by checking the distance from the previous unique character.
   - We add the difference `(prev - i)` to the answer, which tells how many steps we need to adjust the current character to make it unique.
   - We then update `prev = i` to mark this character’s new position.
4. Finally, we return the total steps required to make all characters unique.

This approach is greedy, simple, and efficient because:
- We always prioritize higher characters first.
- By moving backwards, we prevent clashes for lower characters.
- We ensure uniqueness while minimizing the total number of operations.

✅ Step-by-step example:
For input "aabb":
- Frequency array: [2, 2, 0, 0, ..., 0]
- Start from 'z' → 'b'
- Process 'b' first → needs to be unique → assign at index 1 → no conflict, set `prev = 1`
- Process 'a' → also needs to be unique → must be lower than 'b' → assign at index 0 → set `prev = 0`
- Total operations calculated during assignment → output result.

✅ Code with detailed comments:
*/
class Solution {
    public int minOperations(String s) 
    {
        // Step 1: Count frequency of each character
        int freq[] = new int[26];
        for(char c : s.toCharArray())
        {
            freq[c - 'a']++;
        }

        int max = 0;     // Store total operations required
        int prev = 26;   // Initialize previous character index to 26 (out of bounds)

        // Step 2: Iterate from 'z' to 'b'
        for(int i = freq.length - 1; i > 0; i--)
        {
            if(freq[i] > 0)  // If this character exists in the string
            {
                // Step 3: Add difference to ensure this character is placed uniquely
                max += (prev - i);

                // Step 4: Update 'prev' to current index
                prev = i;
            }
        }

        // Step 5: Return the total number of operations
        return max;
    }
}

/*
✅ Time Complexity:
- O(n) where n is the length of the string.
  - We traverse the string once to build the frequency array → O(n)
  - We traverse the fixed-size frequency array (length 26) → O(26), which is constant.
- So overall complexity is O(n).

✅ Space Complexity:
- O(1) because the frequency array size is constant (26), independent of input size.

✅ Why this works:
- By resolving conflicts from the highest character downward, we ensure uniqueness for each character.
- Greedily assigning characters based on the latest available position ensures minimal operations.
- Using a frequency array helps us efficiently count occurrences and avoid repeated scanning.

✅ Summary (Yuvraj way):
- Count how many times each character appears.
- Start from 'z' and make sure each character has a unique place.
- Keep track of the last assigned position using 'prev'.
- Add the difference to the answer whenever a conflict occurs.
- This greedy way of handling conflicts guarantees minimal steps.
- Works in O(n) time and constant space — super efficient!

*/

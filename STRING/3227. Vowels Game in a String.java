/*
Problem Link: https://leetcode.com/problems/vowels-game-in-a-string/description/
Solution Link: https://youtu.be/vJuMCE8ZiD4?si=dnOYY_fDbjl84OFS

Intuition:
The problem asks to check if a given string contains at least one vowel (a, e, i, o, u).
If any vowel exists in the string, Alice wins, so we need to detect the presence of a vowel efficiently.
From understanding the problem, I found that Alice wins every time if at least one vowel is present in the string.
Therefore, the solution reduces to simply checking whether any vowel exists in the string.

Approach:
1. Iterate over each character of the string.
2. Check if it's a vowel by comparing with a set of vowels.
3. If found, return true immediately since Alice wins.
4. If the loop ends without finding a vowel, return false.

We are providing two solutions:
1. Optimal iterative approach using a for-loop and switch case.
2. Stream-based solution using Java's functional programming style.

Code:
*/

// Optimal Iterative Solution
class Solution {
    public boolean doesAliceWin(String str) {
        for (char c : str.toCharArray()) {
            switch(c) {
                case 'a','e','i','o','u':
                    return true;
            }
        }
        return false;
    }
}

/*
Time Complexity:
- O(n), where n is the length of the string.
- In the worst case, we have to scan the entire string once.

Space Complexity:
- O(1), we use no extra space except for variables and iterators.
*/


// Stream-based Solution
class Solution {
    public boolean doesAliceWin(String str) {
        return str.chars() // convert the string into an IntStream of character codes
            .mapToObj(c -> (char) c) // convert each int to a Character
            .anyMatch(c -> "aeiou".indexOf(c) != -1); // check if the character is a vowel
    }
}

/*
Time Complexity:
- O(n), where n is the length of the string.
- Streams also process each character once.

Space Complexity:
- O(1), as the stream operations use constant space overhead.
*/


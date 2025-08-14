📌 Problem Link: https://leetcode.com/problems/largest-3-same-digit-number-in-string/description/?envType=daily-question&envId=2025-08-14  
🎥 Solution Link: https://youtu.be/euheCzFYWDs?si=LjUBZiAG28arD_tC  

-------------------------------------------------
📝 Problem Breakdown:
We are given a numeric string `s`.  
A "good integer" is defined as a substring of length 3 consisting of the same digit (e.g., "777").  
We need to find the **largest** such good integer and return it.  
If there’s no such substring, return an empty string `""`.

-------------------------------------------------
💡 Intuition:
- There are only 10 possible "good integers": "000", "111", ..., "999".
- If we check from largest ("999") to smallest ("000"),  
  the first one we find in the string is guaranteed to be the largest good integer.
- This removes the need for a complex sliding window or integer parsing.

-------------------------------------------------
✅ Why This Approach Works:
- Only 10 possible candidates.
- Checking from highest to lowest ensures we stop at the largest match.
- `String.contains()` runs in O(n) time where n = length of the input string.
- Looping 10 times → O(10 * n) ≈ O(n), which is optimal.

-------------------------------------------------
💻 Java Code (Optimal Approach):

class Solution {
    public String largestGoodInteger(String num) 
    {
        String nums[] = {"000","111","222","333","444","555","666","777","888","999"};

        for (int i = nums.length - 1; i >= 0; i--) 
        {
            if (num.contains(nums[i])) 
            {
                return nums[i];
            }
        }

        return "";
    }
}

-------------------------------------------------
⏳ Time Complexity:
- O(10 * n) → O(n), since 10 is constant.
  (n = length of input string)

💾 Space Complexity:
- O(1), as we only use a fixed-size array of 10 strings.

-------------------------------------------------
🔍 Example Walkthrough:
Input: "6777133339"

1. Start checking from "999" → not found.
2. "888" → not found.
3. ...
4. "777" → found at index 1.
Return "777".

-------------------------------------------------
🆚 Alternate Approach (Sliding Window):
We can use a window of size 3 and check if all characters are the same.
Keep track of the largest integer found.
This works fine but is overkill for this problem compared to the optimal approach.

Code (Sliding Window Approach):

class Solution 
{
    public String largestGoodInteger(String s) 
    {
        int n = s.length();
        int max = -1, l = -1, r = -1;
        int i = 0, j = 0;
        int prev = -1;
        int count = 0;

        while (j < n) 
        {
            int curr = s.charAt(j) - '0';

            if (curr != prev) { count = 1; }
            else { count++; }

            if (j - i + 1 == 3) 
            {
                if (count == 3) 
                {
                    int number = toInt(s.substring(i, j + 1));
                    if (number > max) 
                    {
                        max = number;
                        l = i;
                        r = j;
                    }
                }
                i++;
            }

            prev = curr;
            j++;
        }

        return (l != -1) ? s.substring(l, r + 1) : "";
    }

    public int toInt(String s) 
    {
        int i = 0, result = 0, n = s.length();
        while (i < n) 
        {
            int c = s.charAt(i) - '0';
            result = result * 10 + c;
            i++;
        }
        return result;
    }
}

e:
s = "6777133339"
Good integers → "777", "333"
Largest → "777"

________________________________________________________________________________________________

OPTIMAL APPROACH-

----------------------------------------
Intuition:
----------------------------------------
- We need to find triples of the same digit in a row.
- We'll scan the string from index 2 onwards (0-based), checking if:
  s[i] == s[i-1] == s[i-2].
- If found, we compare its digit value with the maximum found so far.
- Finally, we return the largest triple found.

Why this works:
- The problem is small enough to be solved in O(n) by a single pass.
- We only need to remember the largest digit forming a valid triple.
- Using `String.valueOf(max).repeat(3)` easily constructs the answer.

----------------------------------------
Time Complexity:
----------------------------------------
O(n) — Single scan of the string.

Space Complexity:
----------------------------------------
O(1) — Only storing a few variables.

----------------------------------------
Code:
----------------------------------------
*/
class Solution {
    public String largestGoodInteger(String s) 
    {
        int max = -1;
        int n = s.length();

        // Check every group of 3 consecutive characters
        for (int i = 2; i < n; i++)
        {
            if (s.charAt(i) == s.charAt(i-1) && s.charAt(i) == s.charAt(i-2))
            {
                int c = s.charAt(i) - '0'; // Convert char to digit
                max = Math.max(c, max);
            }
        }

        // Return based on the largest found
        return (max == -1) ? "" : (max == 0) ? "000" : String.valueOf(max).repeat(3);
    }
}

-------------------------------------------------
🎯 Summary (Yuvraj Style):
Bhai, yeh problem sirf 10 possible "good integers" ke saath aati hai.  
Sabse bada se start karo ("999"), pehla jo mile wahi answer.  
Sliding window banane ka scene mat lo, direct contains lagao aur kaam khatam.  
Time O(n), space O(1) → mast fast.

OPTIMAL EXPLANATION -
----------------------------------------
Yuvraj Style Summary:
----------------------------------------
- Bhot simple: bas 3-3 ka group check karo, agar teeno same hai to compare with max.
- Max me sabse bada digit store karo.
- Last me us digit ko teen baar repeat karke answer banao.
- Edge case: agar koi triple na mile → "".
----------------------------------------

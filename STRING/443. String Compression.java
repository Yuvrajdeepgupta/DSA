📝 Problem: String Compression  
LeetCode: https://leetcode.com/problems/string-compression/description/  
Solution Ref: 
1) https://youtu.be/I7drewKcN1Y?si=F_Tzp4BE9Q9LYyBt  
2) https://youtu.be/cAB15h6-sWA?si=rnGm5Y4BZu6I49Ml  

---------------------------------------------------------
🔹 Problem Statement
We are given a character array `chars`. We need to compress it in-place such that:
- Consecutive duplicate characters are replaced with:
  character + count (if count > 1).
- Only the new length of compressed array is returned.

Example:  
Input:  ["a","a","b","b","c","c","c"]  
Output: ["a","2","b","2","c","3"], length = 6  

Constraints:  
- 1 <= chars.length <= 2000  
- chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.  

---------------------------------------------------------
🔹 Intuition
- We traverse the array and group consecutive repeating characters.
- For each group:
  - Write the character once.
  - If the frequency > 1, write its count digit by digit.
- Do all this in-place (constant space) or using extra space (StringBuilder).

Key Points:
- We must handle single characters differently (just write char, no count).
- For counts, we must split into digits and place individually.
- The final index `i` represents the compressed length.

---------------------------------------------------------
🔹 Approaches

✅ 1. Extra Space Approach (StringBuilder)
- Use a StringBuilder to construct the compressed version.
- At the end, copy result back into the original `chars[]`.

Code:
---------------------------------------------------------
class Solution {
    public int compress(char[] chars) 
    {
        StringBuilder sb=new StringBuilder();

        char prev='&';
        int count=0;
        for(char c:chars)
        {
            if(c!=prev && count>1)
            {
                sb.append(count);
                sb.append(c);
                count=0;
                prev=c;
            }
            else if(c!=prev && count<=1)
            {
                sb.append(c);
                prev=c;
                count=0;
            }
            count++;
        }

        if(count>1) sb.append(count);

        fill(chars,sb.toString().toCharArray());
        return sb.length();
    }

    public void fill(char ans[],char arr[])
    {
        int i=0;
        for(char c:arr)
        {
            ans[i++]=c;
        }
    }
}
---------------------------------------------------------

✅ 2. Optimal Constant Space Approach (In-place)
- Use two pointers:
  - `j` for scanning the array
  - `i` for writing compressed result
- Keep a counter of current group size.
- Whenever the group ends, write char + count (if >1).

Code:
---------------------------------------------------------
class Solution {
    public int compress(char[] chars) 
    {
        int n=chars.length;
        int i=0;        // write index
        int count=1;    // frequency

        for(int j=1;j<=n;j++)
        {
            if(j==n || chars[j]!=chars[j-1])
            {
                chars[i++]=chars[j-1];

                if(count>1)
                {
                    String num=String.valueOf(count);
                    for(char c:num.toCharArray())
                    {
                        chars[i++]=c;
                    }
                    count=1;
                }
            }
            else
            {
                count++;
            }
        }
        return i;
    }
}
---------------------------------------------------------

🔹 Dry Run
Input: ["a","a","b","b","c","c","c"]

Step1: count a's → 2 → write "a2"  
Step2: count b's → 2 → write "b2"  
Step3: count c's → 3 → write "c3"  

Final chars = ["a","2","b","2","c","3"], length = 6  

---------------------------------------------------------
🔹 Time & Space Complexity
Extra Space Approach:
- Time: O(n)  
- Space: O(n) for StringBuilder  

Constant Space Approach:
- Time: O(n) (single pass)  
- Space: O(1) (in-place, just pointers and counters)  

---------------------------------------------------------
🔹 Summary (Yuvraj Way 😎)
- Simple question of compressing chars with counts.  
- Extra space wala is easy to write but not optimal.  
- Constant space approach uses two pointers, directly modifies array.  
- Final `i` is the answer → compressed length.  
- Very standard pattern for in-place array compression type problems.  

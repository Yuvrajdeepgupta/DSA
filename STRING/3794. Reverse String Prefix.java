🔗 Question Link:
https://leetcode.com/problems/reverse-string-prefix/

==================================================
🧩 Problem Breakdown
==================================================
Given a string `s` and an integer `k`:

- Reverse the **first k characters** of the string
- Keep the remaining characters **as it is**
- Return the final string

==================================================
💡 Intuition (Seedhi Bhasha)
==================================================
Gupta Ji, yahan koi heavy logic nahi hai 👇

- Sirf **prefix (starting part)** reverse karna hai
- Full string ko touch karne ki zarurat nahi
- First `k` characters ko reverse karo, baaki same rahenge

Classic **two-pointer + array swap** problem 👍

==================================================
🛠️ Why Character Array?
==================================================
Java strings are **immutable** ❌  
Isliye:
- String → `char[]`
- Us array me swaps easy ho jaate hain
- End me new String bana dete hain

Efficient + clean approach ✅

==================================================
✅ Approach (Step-by-Step)
==================================================
1. String ko `char[]` me convert karo
2. Two pointers rakho:
   - `i = 0` (start)
   - `j = k - 1` (prefix end)
3. Jab tak `i < j`:
   - `arr[i]` aur `arr[j]` swap karo
   - `i++`, `j--`
4. Updated array se new String return karo

==================================================
💻 Java Code (Optimal & Clean)
==================================================
class Solution 
{
    public String reversePrefix(String s, int k) 
    {
        char arr[] = s.toCharArray();

        int i = 0, j = k - 1;
        while (i < j)
        {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return new String(arr);
    }
}

==================================================
🧪 Example Walkthrough
==================================================
Input:
s = "abcdef"
k = 3

Prefix = "abc"
Reversed prefix = "cba"

Final string = "cbadef" ✅

==================================================
⏱️ Time & Space Complexity
==================================================
Time Complexity:
- Reversing k characters → **O(k)**

Space Complexity:
- Char array → **O(n)**

==================================================
🧾 Yuvraj-Style Summary
==================================================
- Sirf prefix reverse karna hai
- Two pointers best weapon 🔫
- String → char array → swap → string

Short, clean, interview-ready solution 💯  
Pure khandani template 😎

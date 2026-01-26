🔗 Question Link:
https://leetcode.com/problems/largest-even-number/

==================================================
🧩 Problem Breakdown
==================================================
Given a numeric string `s`:

- You can **remove some digits from the end** (i.e., take a prefix)
- The remaining number must be **even**
- Return the **largest possible even number** (as a string)
- If not possible, return `""`

==================================================
💡 Intuition (Seedha Logic)
==================================================
Gupta Ji, even number ka ek hi rule hota hai 👇

👉 **Last digit even honi chahiye**

Largest number chahiye ⇒
- Maximum digits rakhni hain
- Matlab **rightmost even digit** tak ka prefix lo

Isliye:
- End se scan karo
- Jaise hi even digit mile → wahi best answer 🔥

==================================================
🛠️ Why Right-to-Left Scan?
==================================================
- Left se aayoge → chhota prefix milega ❌
- Right se aayoge → **longest prefix** milega ✅
- Longest prefix ⇒ largest number

No sorting, no conversions, no extra space 💯

==================================================
✅ Approach (Step-by-Step)
==================================================
1. `idx = -1` rakho (even digit nahi mila abhi)
2. String ko **right se left** traverse karo
3. Har character ko digit me convert karo
4. Agar digit even ho:
   - `idx = i`
   - break (best possible mil gaya)
5. Agar `idx == -1`:
   - return empty string
6. Else:
   - `s.substring(0, idx + 1)` return karo

==================================================
💻 Java Code (Optimal & Clean)
==================================================
class Solution 
{
    public String largestEven(String s) 
    {   
        int idx = -1;

        for (int i = s.length() - 1; i >= 0; i--)
        {
            int num = s.charAt(i) - '0';

            if (num % 2 == 0)
            {
                idx = i;
                break;
            }
        }

        return idx == -1 ? "" : s.substring(0, idx + 1);
    }
}

==================================================
🧪 Example Walkthrough
==================================================
Input:
s = "35427"

Right to left scan:
7 ❌
2 ✅ → stop

Answer = "3542" ✔

Input:
s = "135"

No even digit found ❌  
Answer = "" ✔

=====================================

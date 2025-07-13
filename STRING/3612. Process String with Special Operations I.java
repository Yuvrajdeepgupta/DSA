//que link-https://leetcode.com/problems/process-string-with-special-operations-i/description/

//sol link-https://www.youtube.com/watch?v=3nHr5oF-jfc&ab_channel=ProgrammingLivewithLarry

import java.util.*;

class Solution {

    // 💡 Main function to process the string
    public String processStr(String s) {
        StringBuilder ans = new StringBuilder();

        for (char c : s.toCharArray()) {
            int n = ans.length();

            if (Character.isLowerCase(c)) {
                // ✅ Append lowercase character
                ans.append(c);
            }
            else if (c == '#' && n > 0) {
                // ✅ Duplicate the result so far
                ans.append(ans.toString());
            }
            else if (c == '%' && n > 0) {
                // ✅ Reverse the result
                ans.reverse();
            }
            else if (c == '*' && n > 0) {
                // ✅ Remove last character if exists
                ans.deleteCharAt(n - 1);
            }
        }

        return ans.toString();
    }

    // 🧪 Sample driver code
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.processStr("a#b%*"));     // Output: "ba"
        System.out.println(obj.processStr("cd%#*#"));    // Output: "dcddcd"
        System.out.println(obj.processStr("z*#"));       // Output: ""
    }
}


// ✅ Step-by-step plan:
// 1. Traverse char by char
// 2. If 'a'-'z' → append to ans
// 3. If '#' → double the string
// 4. If '%' → reverse string
// 5. If '*' → remove last char
// 6. Return the final result

// ⚙️ Time: O(n) to O(n^2) worst case due to reverse/duplication
// 🧠 Space: O(n) for result string

// 📌 Use StringBuilder for efficient string operations
// 📌 Clean simulation approach for interview-friendly code

// link - https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
//sol link-https://youtu.be/sUf_-_c1zYI?si=KZ5Y5Mc2FiNVI4ml
/*
💡 INTUITION:
You're given a singly linked list representing a binary number (MSB at the head).
We have to convert this binary representation to its decimal (base-10) value.

👉 The linked list contains only 0 and 1 values.
👉 The most significant bit is at the head.
👉 We can traverse and simulate the decimal building process from left to right.

📚 NEW CONCEPTS / IMPORTANT IDEAS:
- Bit manipulation: `(num << 1) | head.val` helps build the binary number efficiently.
- Stack-based reverse reading: Binary from head → tail is MSB → LSB.
- `Integer.parseInt(binaryString, 2)` is a shortcut for base-2 to decimal.

🧠 EXPLANATION:
1. Traverse the list:
   - Bit-by-bit, add value to a result.
   - Build it using left-shift (multiply by 2).
2. You can also use a StringBuilder to collect binary string and parse it.
3. Or push values in a stack to read from LSB to MSB and use `Math.pow`.

*/

/////////////////////////////////////////////////////////////////////////////////////////
// 🔴 Brute Force - O(N^2) using Stack + Math.pow
/////////////////////////////////////////////////////////////////////////////////////////

class Solution {
    public int getDecimalValue(ListNode head) 
    {
        if (head.next == null) return head.val;  // Edge case: only 1 node

        Deque<Integer> stack = new ArrayDeque<>();
        ListNode temp = head;

        // Push all bits to the stack (MSB to LSB)
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        int ans = 0;
        int bit = 0;

        while (!stack.isEmpty()) {
            int val = stack.pop();
            if (val == 1) {
                ans += (int) Math.pow(2, bit);  // Add power of 2 only for 1s
            }
            bit++;
        }

        return ans;
    }
}

/*
🧠 TIME COMPLEXITY: O(N^2) — due to Math.pow inside loop
🧠 SPACE COMPLEXITY: O(N) — for the stack
*/

/////////////////////////////////////////////////////////////////////////////////////////
// 🟡 Better Approach - Using StringBuilder + Integer.parseInt
/////////////////////////////////////////////////////////////////////////////////////////

class Solution {
    public int getDecimalValue(ListNode head) 
    {
        if (head.next == null) return head.val;

        StringBuilder sb = new StringBuilder();
        ListNode temp = head;

        // Build binary string from linked list
        while (temp != null) {
            sb.append(temp.val);
            temp = temp.next;
        }

        // Convert binary string to decimal using base 2
        return Integer.parseInt(sb.toString(), 2);
    }
}

/*
🧠 TIME COMPLEXITY: O(N)
🧠 SPACE COMPLEXITY: O(N) — due to StringBuilder
✔️ Clean, readable, but uses extra space
*/

/////////////////////////////////////////////////////////////////////////////////////////
// ✅ Optimal Approach - Bit Manipulation (Left Shift)
/////////////////////////////////////////////////////////////////////////////////////////

class Solution {
    public int getDecimalValue(ListNode head) 
    {
        int num = 0;

        // Traverse the list and build decimal using left shift
        while (head != null) {
            num = (num << 1) | head.val;
            head = head.next;
        }

        return num;
    }
}

/*
🧠 TIME COMPLEXITY: O(N)
🧠 SPACE COMPLEXITY: O(1)
✔️ Most efficient — no extra space, fast bit operations
✔️ Safe for large linked lists
*/


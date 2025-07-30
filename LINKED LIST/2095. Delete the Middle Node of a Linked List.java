/*
🔗 Question: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
🎥 Solution: https://youtu.be/KEcXDqthMLE?si=EAnuC4i3kBYN_8l6

🧠 Problem:
Given the head of a singly linked list, delete the middle node and return the head of the modified list.

- If there are two middle nodes, delete the second one.
- Return null if there's only one node.

------------------------------------------------------------
🧠 Intuition:

- We can't access the middle directly in O(1), so we use the classic **fast and slow pointer** technique.
- `slow` moves 1 step, `fast` moves 2 steps.
- When `fast` reaches the end, `slow` will be right before the middle.
- We delete the middle by skipping `slow.next`.

📌 Edge case:
- If there's only one node → return null.

------------------------------------------------------------
🛠️ Approach:

1. Handle single node case: if `head.next == null`, return null.
2. Initialize:
   - `slow = head`
   - `fast = head.next.next`
3. Move `slow` and `fast`:
   - While `fast != null && fast.next != null`
   - `slow = slow.next`, `fast = fast.next.next`
4. After loop, `slow.next` is the middle → remove it by doing:
   - `slow.next = slow.next.next`

------------------------------------------------------------
✅ Java Code:
*/

class Solution {
    public ListNode deleteMiddle(ListNode head) 
    {
        if (head.next == null) {
            return null;
        }

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}

/*
------------------------------------------------------------
🧪 Dry Run:

Input: [1 → 2 → 3 → 4 → 5]
Initial: slow = 1, fast = 3

Loop:
- 1st Iter: slow = 2, fast = 5
- Exit loop

Now: slow is at 2 → delete slow.next = 3
New List: [1 → 2 → 4 → 5]

------------------------------------------------------------
⏱️ Time Complexity: O(N)
- We traverse the list once with fast and slow pointers.

📦 Space Complexity: O(1)
- No extra space used, only pointers.

------------------------------------------------------------
👑 Summary (Yuvraj Style):

// Use fast and slow pointers
// When fast reaches end → slow is just before mid
// Delete slow.next (middle node)
// Return modified head
*/

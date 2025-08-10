/*
Question Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Solution Link: https://youtu.be/ZQizovB_UdI?si=PcRrSTOdA-5nJonw

-------------------------
✅ Problem Breakdown
-------------------------
We are given the head of a singly linked list and an integer n.  
We need to remove the nth node from the END of the list and return the modified list.

Example:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Explanation: The 2nd node from the end is 4, so we remove it.

Key Points:
- Need to find the nth node from the end efficiently.
- Can't traverse backwards (Singly Linked List).
- Should be done in ONE pass ideally.

-------------------------
💡 Intuition
-------------------------
We can use a two-pointer technique:
1. Move a `temp` pointer n steps ahead from head.
2. If n is more than the length of the list, return head as is.
3. Use another pointer (`behind`) starting from head and move both `temp` and `behind` until `temp` reaches null.
4. Maintain a `prev` pointer to track the node before `behind`.
5. Remove the nth node by adjusting `prev.next`.

Why this works:
- When `temp` reaches the end, `behind` will be exactly at the nth node from the end.
- We avoid extra passes and achieve O(L) time.

-------------------------
⏳ Time Complexity
-------------------------
O(L) — L is the number of nodes in the linked list.  
We only traverse the list once.

-------------------------
📦 Space Complexity
-------------------------
O(1) — No extra data structures used.

-------------------------
🚀 Code Implementation
-------------------------
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        // Step 1: Move temp n steps ahead
        ListNode temp = head;
        while(n != 0 && temp != null)
        {
            temp = temp.next;
            n--;
        }

        // If n > length of list, return head as is
        if(n > 0)
        {
            return head;
        }
        
        // Step 2: Dummy node to handle deletion of head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode behind = head;
        ListNode prev = dummy;

        // Step 3: Move both pointers until temp reaches null
        while(temp != null)
        {
            prev = behind;
            behind = behind.next;
            temp = temp.next;
        }

        // Step 4: Delete nth node from end
        if(prev.next != null)
        {
            prev.next = prev.next.next;
        }

        // Step 5: Return new head
        return dummy.next;
    }
}

-------------------------
📝 Example Walkthrough
-------------------------
head = [1,2,3,4,5], n = 2

1. Move `temp` 2 steps → temp at node 3.
2. Move both `temp` and `behind`:
   - temp: 4 → 5 → null
   - behind: 2 → 3 → 4
3. prev is at 3, behind is at 4 (nth from end).
4. Remove 4 → list becomes [1,2,3,5].
5. Return head.

-------------------------
😎 Yuvraj Style Summary
-------------------------
Bhai simple logic hai —
1 pointer ko n steps aage bhaga do.
Phir dono pointers ko saath-saath chalao.
Jab aage wala end pe pohonch jaye, peeche wala wahi node hoga jo delete karna hai.
Prev ka next adjust karo, kaam khatam.
Ek pass, O(1) space, mast solution 🚀

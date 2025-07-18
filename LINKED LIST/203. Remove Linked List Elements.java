//Que link - https://leetcode.com/problems/remove-linked-list-elements/submissions/1702860266/
//sol link 1- https://youtu.be/WskxWADk6T4?si=PVnuTuBeu9jJYktr
//sol link 2- https://youtu.be/uIcClozwlxc?si=qGoVpdCl32EtMkOx

/***************************************************************************************/

// ✅ Problem: Remove Linked List Elements
// Given the head of a linked list and an integer `val`,
// remove all nodes from the list that have `val` as their value,
// and return the new head of the list.

// 💡 Intuition:
// We want to skip all nodes whose value == val
// While preserving the rest of the linked list structure

// ✅ Recursive Approach
// We recursively remove the target nodes from the tail,
// then decide whether to keep or skip the current head.

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 🛑 Base Case: if list is empty
        if (head == null) return null;

        // 🔁 Recurse on rest of list
        head.next = removeElements(head.next, val);

        // 🔍 If current node's value matches target → skip it
        return (head.val == val) ? head.next : head;
    }
}

// ⏱️ Time Complexity: O(n)
//   - One pass through the list
// 🧠 Space Complexity: O(n) (due to recursion stack)

// 📘 Yuvraj-style Summary:
// - Go till end and clean from back
// - Fix head.next first
// - Then if head.val == val, return head.next (skip current)
// - Else return head (keep it)

/*************************************************************************************/

// ✅ Iterative Approach (Using Dummy Node)
// Handles edge cases cleanly (like when head itself needs to be deleted)

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        // 🔧 Dummy node before head to handle deletion at head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 🧱 Pointers: prev trails behind curr
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                // 🎯 Skip current node
                prev.next = curr.next;
                curr = curr.next; // move curr only
            } else {
                // ✅ Keep node, slide both pointers
                prev = curr;
                curr = curr.next;
            }
        }

        return dummy.next; // return new head (might be different)
    }
}

// ⏱️ Time Complexity: O(n)
// 🧠 Space Complexity: O(1)

// 📘 Yuvraj-style Summary:
// - Use dummy node to avoid edge cases
// - Loop through list with curr and prev
// - If curr.val == val → skip
// - Else → move ahead
// - Return dummy.next as head may have changed

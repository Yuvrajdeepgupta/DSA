// ✅ Question Link: https://leetcode.com/problems/reverse-linked-list/
// 🎥 Solution Video: https://youtu.be/RreHsOfi14w?si=T4Axzf3XXzM8dfJO

/* ✅ PROBLEM:
You are given the head of a singly linked list.
Reverse the list, and return the head of the reversed linked list.
*/


// ✅ INTUITION:
/*
We need to reverse the links (next pointers) in a singly linked list.

🔸 Iterative Approach:
   - Use three pointers: prev, curr, next
   - At each step, reverse the direction of curr.next to point to prev
   - Move curr forward and repeat

🔸 Recursive Approach:
   - Reach the last node using recursion
   - While backtracking, reverse the direction by pointing next node’s next to current
   - Disconnect current node from the next by setting current.next = null
*/


// ✅ ITERATIVE SOLUTION
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
    public ListNode reverseList(ListNode head) 
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        while(curr != null)
        {
            ListNode store = curr.next;
            curr.next = prev;
            prev = curr;
            curr = store;
        }

        return prev; // prev becomes new head
    }
}

// ✅ RECURSIVE SOLUTION
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
    public ListNode reverseList(ListNode head) 
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        ListNode store = head.next;
        ListNode temp = reverseList(head.next); // temp is new head

        head.next = null;
        store.next = head;

        return temp;
    }
}

/* ✅ EXAMPLE:

Input: 1 -> 2 -> 3 -> 4 -> 5
Output: 5 -> 4 -> 3 -> 2 -> 1

We reverse the `next` pointers at each step so the direction of the list flips.
*/


/* ✅ TIME AND SPACE COMPLEXITY:

🔸 Iterative:
    Time: O(N) – we visit each node once
    Space: O(1) – constant space

🔸 Recursive:
    Time: O(N)
    Space: O(N) – due to recursion stack
*/


// ✅ Yuvraj-style Summary:
/*
-> Har node ka next peeche walay pe point karna hai
-> Iterative me 3 pointer: prev, curr, next use karke direction change karte jaate hai
-> Recursion me end tak pahuch jao, wapas aate waqt link ulta karo
-> Base case: head == null ya head.next == null
-> Final answer wahi node hoti hai jiska next null ho jaata hai (pehle ka last node)

➡️ Time: O(N)
➡️ Space: Iterative → O(1), Recursive → O(N)
*/

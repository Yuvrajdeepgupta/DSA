/*
====================================================================
📝 Problem: Finding Middle Element in a Linked List
====================================================================

Que Link: https://www.geeksforgeeks.org/problems/finding-middle-element-in-a-linked-list/1
Sol Link: https://youtu.be/nzaHG0dme4g?si=CqeK2qLWCZr7r8oD

====================================================================
📌 Problem Statement:
====================================================================
Given a singly linked list, find the data in the middle node.
If there are two middle nodes, return the second middle node.

====================================================================
💡 Approach:
====================================================================
We use the Fast & Slow Pointer technique:
- Slow pointer moves 1 step at a time.
- Fast pointer moves 2 steps at a time.
- When the fast pointer reaches the end, the slow pointer will be at the middle.

Why this works:
- Fast moves twice the distance of slow.
- By the time fast finishes the list, slow has only traversed half.

====================================================================
✅ Steps:
====================================================================
1. Initialize slow and fast pointers at the head.
2. Traverse the list:
   - Move fast by 2 steps and slow by 1 step.
   - Continue until fast reaches null or fast.next reaches null.
3. Slow now points to the middle node.
4. Return slow.data.

====================================================================
📊 Complexity Analysis:
====================================================================
- Time Complexity: O(N) — Each node visited at most once.
- Space Complexity: O(1) — No extra data structures.

====================================================================
📌 Code:
====================================================================
*/

/* Node of a linked list
class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}
*/

class Solution {
    int getMiddle(Node head) {
        if (head.next == null) return head.data;  // Only one element
        
        Node slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // Move fast by 2
            slow = slow.next;      // Move slow by 1
        }
        
        return slow.data; // Middle element
    }
}

====================================================================
📌 Related Problems Using Same Concept:
====================================================================
1. Detect Cycle in Linked List
2. Start of Cycle in Linked List
3. Check if Linked List is Palindrome
4. Happy Number Problem
5. Find Loop Length in Linked List
====================================================================
*/

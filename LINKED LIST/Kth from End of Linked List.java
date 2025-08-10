/*
====================================================================
📝 Problem:
====================================================================
Nth Node from End of Linked List

Que Link: https://www.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1
Sol Link: https://youtu.be/zsT8-dieppw?si=X3o-m9FAch9ybgza

====================================================================
📌 Problem Statement:
====================================================================
Given a singly linked list and an integer k, 
find the kth node from the end of the linked list.
If k is greater than the number of nodes, return -1.

====================================================================
💡 Approach (Two Pointer Technique / Gap Method):
====================================================================
We use two pointers with a gap of k nodes between them.

1️⃣ Move the first pointer k steps ahead.
2️⃣ If during this movement the pointer reaches null before k steps,
    it means k > length of list → return -1.
3️⃣ Initialize the second pointer at head.
4️⃣ Move both pointers one step at a time until the first pointer reaches null.
5️⃣ The second pointer will now be pointing at the kth node from the end.

Why it works:
- The gap ensures that when the first pointer hits the end, 
  the second pointer is exactly k nodes from the end.

====================================================================
✅ Steps:
====================================================================
1. Create two pointers: temp (fast) and behind (slow).
2. Advance temp by k steps.
3. If temp becomes null before completing k steps → return -1.
4. Move both temp and behind one step at a time until temp reaches null.
5. Return behind.data.

====================================================================
📊 Complexity Analysis:
====================================================================
- Time Complexity: O(N) — At most two passes over the list.
- Space Complexity: O(1) — Constant extra space.

====================================================================
📌 Code:
====================================================================
*/

/* Structure of node
class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}
*/

class Solution {
    int getKthFromLast(Node head, int k) {
        Node temp = head;
        
        // Step 1: Move temp k steps ahead
        while (k > 0 && temp != null) {
            temp = temp.next;
            k--;
        }
        
        // Step 2: If k > length, return -1
        if (k > 0 && temp == null) {
            return -1;
        }
        
        // Step 3: Move both pointers together
        Node behind = head;
        while (temp != null) {
            temp = temp.next;
            behind = behind.next;
        }
        
        // Step 4: behind is now the kth node from end
        return behind.data;
    }
}

====================================================================
📌 Related Problems Using Same Concept:
====================================================================
1. Remove Nth Node from End of List
2. Detect Cycle in Linked List
3. Find Start Node of Cycle in Linked List
4. Find Middle Element in Linked List
5. Rotate Linked List
====================================================================
*/

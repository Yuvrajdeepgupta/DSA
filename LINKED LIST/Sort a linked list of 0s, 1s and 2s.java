/*
✅ Problem: Given a Linked List of 0s, 1s and 2s, sort it
Que Link: https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
Sol Link: https://youtu.be/c2C4lbstw1w?si=VXHphkhRcS5FFDkc

---

📌 Problem Statement:
You are given a linked list containing only 0s, 1s, and 2s.
Your task is to sort the linked list in ascending order.

---

💡 Example:
Input:  1 -> 2 -> 0 -> 1 -> 2 -> 0 -> null
Output: 0 -> 0 -> 1 -> 1 -> 2 -> 2 -> null

---

📊 Constraints:
1 <= N <= 10^5

---

====================================================================
🚀 Approach 1: Counting & Overwriting (Data Mutation Allowed)
====================================================================

1️⃣ Traverse the linked list once and count the frequency of 0s, 1s, and 2s.
2️⃣ Traverse the linked list again and overwrite the node values in sorted order.
3️⃣ This does not change pointers — only data inside nodes.

---

💻 Code:
*/

/* class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        next = null;
    }
} */
class Solution {
    static Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        int[] count = new int[3];
        Node temp = head;

        // Count occurrences
        while (temp != null) {
            count[temp.data]++;
            temp = temp.next;
        }

        // Overwrite data in sorted order
        temp = head;
        for (int i = 0; i < 3; i++) {
            while (count[i]-- > 0) {
                temp.data = i;
                temp = temp.next;
            }
        }
        return head;
    }
}

---

/*
⏳ Time Complexity:  O(N) — Two passes through the list
💾 Space Complexity: O(1) — Constant extra space
✅ Best for: When modifying node data is allowed
*/

====================================================================
🚀 Approach 2: Pointer Rearrangement (No Data Mutation)
====================================================================

1️⃣ Create three dummy nodes: `zeroDummy`, `oneDummy`, `twoDummy`.
2️⃣ Traverse the original list and attach each node to the correct dummy list.
3️⃣ Merge the three lists: 0s → 1s → 2s.
4️⃣ Return the head of the new sorted list.

---

💻 Code:
*/

/* class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        next = null;
    }
} */
class SolutionPointer {
    static Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        Node zero = zeroDummy, one = oneDummy, two = twoDummy;

        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        // Merge lists
        zero.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        one.next = twoDummy.next;
        two.next = null;

        return zeroDummy.next;
    }
}

---

/*
⏳ Time Complexity:  O(N) — One pass through the list
💾 Space Complexity: O(1) — Constant extra space
✅ Best for: When modifying node data is NOT allowed
*/

---

📌 Key Notes:
- Approach 1 is shorter and cleaner if node values can be changed.
- Approach 2 is safer for immutable data or when node data should not be altered.
- Both are O(N) time and O(1) space.

---

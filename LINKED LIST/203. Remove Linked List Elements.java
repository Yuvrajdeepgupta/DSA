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

****************************************************************************************

//OPTIMAL APPROACH

-----------------------------

💡 Intuition:

This is a classic 2D linked list problem.
- Think of the list as a matrix where each row is sorted.
- We need to merge all rows into one sorted vertical list (`bottom` pointers only).
- The idea is to **merge bottom-wise** two lists at a time, starting from the **last list**, and work our way back up.
- This is efficiently done using **right recursion + merge two sorted lists** logic.

-----------------------------

🧠 Why right recursion works better here:
We first flatten the right sublists (root.next), then merge them bottom-wise into the current list.
This ensures at every step, you're merging only two sorted lists.

-----------------------------

🧾 Code (Right Recursive + Clean Merge using Bottom Pointers Only)
*/

class Solution 
{
    // Function to flatten a linked list
    Node flatten(Node root) 
    {
        if(root == null || root.next == null)
        {
            return root;
        }
        
        // Recurse on next first (rightmost list)
        root.next = flatten(root.next);
        
        // Merge current list with already flattened right list
        root = merge(root, root.next);
        
        return root;
    }
    
    // Standard merge for two sorted linked lists using bottom pointer
    private Node merge(Node h1, Node h2)
    {
        if(h1 == null) return h2;
        if(h2 == null) return h1;
        
        if(h1.data <= h2.data)
        {
            h1.bottom = merge(h1.bottom, h2);
            return h1;
        }
        else
        {
            h2.bottom = merge(h1, h2.bottom);
            return h2;
        }
    }
}

-----------------------------

📘 Dry Run:

Input:
5 -> 10 -> 19 -> 28
|     |     |     |
7     20    22    35
|           |     |
8           50    40
|                 |
30                45

After flatten:
5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 28 -> 30 -> 35 -> 40 -> 45 -> 50

(All connected through bottom pointers)

-----------------------------

⏱️ Time Complexity:

- Let `n` be the number of `next` chains (rows).
- Let `m` be the total number of nodes.
- Merging takes **O(m log n)** time in the worst case because:
  - Each merge (2 sorted lists) takes O(m)
  - We're merging `n` lists in divide & conquer style (like merge sort → log n levels)

📦 Space Complexity:

- **O(1)** auxiliary (no extra space used in iterative merge)
- But due to recursion, the **call stack space is O(n)** in worst case for `flatten()` calls.

-----------------------------

📝 Yuvraj-style Summary:

/*
Flattening a 2D linked list (next + bottom) into a single sorted list using bottom pointer only.
Used:
- Right recursion: flatten right side first then merge
- Standard merge of 2 sorted lists using bottom
- Recursion makes merging simpler and natural

Approach:
- flatten(root.next) → gives flattened rest
- merge(root, root.next) → gives full flattened list till current

🧠 Tip: Right recursion ensures we always merge only 2 sorted lists.
TC: O(m log n)
SC: O(n) (recursion stack)
*/

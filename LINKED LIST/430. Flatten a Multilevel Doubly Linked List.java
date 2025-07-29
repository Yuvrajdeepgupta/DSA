// ✅ Leetcode: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/
// 🎥 Solution video: https://youtu.be/I8b0rff5F9M?si=1LUUKUB9FVST18J2

/* ✅ PROBLEM STATEMENT:
You are given a doubly linked list, where in addition to the next and previous pointers,
each node has a child pointer, which may or may not point to a separate doubly linked list.

These child lists may have one or more child doubly linked lists of their own, and so on,
to produce a multilevel data structure.

Flatten the list so that all the nodes appear in a single-level, doubly linked list.
You must do it in place — you cannot use extra space for another list.

Return the head of the flattened list.
*/


// ✅ INTUITION (Iterative approach):
/*
- Use a stack to simulate recursion.
- While traversing the list, if a node has a child:
    -> If there's a `next` node, push it to stack (we’ll return to it later).
    -> Connect `child` as `next` of current node, fix `prev` link.
    -> Set child = null (since it's flattened now).
- If we reach null and stack isn’t empty, pop the next node and continue.

This simulates DFS and ensures child nodes are inserted in place.
*/


// ✅ ITERATIVE SOLUTION (using stack)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) 
    {
        if(head == null) return head;

        Deque<Node> stack = new ArrayDeque<>();
        Node temp = head;

        while(temp != null)
        {
            // If current node has a child
            if(temp.child != null)
            {
                // If there's a next node, save it for later
                if(temp.next != null)
                {
                    stack.push(temp.next);
                }

                // Connect child in place of next
                temp.next = temp.child;
                temp.next.prev = temp;
                temp.child = null; // remove child link after connecting
            }

            // If end of current level and stack is not empty, connect next level
            if(temp.next == null && !stack.isEmpty())
            {
                Node storedNext = stack.pop();
                temp.next = storedNext;
                storedNext.prev = temp;
            }

            // Move forward
            temp = temp.next;
        }

        return head;
    }
}


// ✅ RECURSIVE APPROACH (returns tail node each time)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution 
{
    public Node flatten(Node head) 
    {
        if(head == null) return head;

        givetail(head); // recursive function that flattens in-place

        return head;
    }

    // Recursively flattens the list and returns the tail node
    public Node givetail(Node head)
    {
        Node temp = head;
        Node last = head; // to keep track of tail node

        while(temp != null)
        {
            Node next = temp.next;

            if(temp.child != null)
            {
                Node childTail = givetail(temp.child); // flatten the child list

                // insert the child list between temp and next
                temp.next = temp.child;
                temp.child.prev = temp;
                temp.child = null;

                if(next != null)
                {
                    childTail.next = next;
                    next.prev = childTail;
                }

                last = childTail;
                temp = next;
            }
            else
            {
                last = temp;
                temp = temp.next;
            }
        }

        return last;
    }
}


/* ✅ EXAMPLE:

Input:
1 - 2 - 3
        |
        7 -   8
             |
             11 - 12

Representation:
1 -> 2 -> 3
           |
           7 -> 8
                 |
                 11 -> 12

After flatten:
1 -> 2 -> 3 -> 7 -> 8 -> 11 -> 12

All child pointers are removed.
All nodes are connected via next and prev pointers.
*/


/* ✅ TIME AND SPACE COMPLEXITY:

🔸 Time Complexity: O(N)
    - We visit each node exactly once
    - N is the total number of nodes across all levels

🔸 Space Complexity:

    - Iterative:
        O(H) where H = depth of the nested structure
        (due to stack used for saving "next" nodes)

    - Recursive:
        O(H) due to recursion call stack (maximum depth of nested children)
*/


// ✅ Yuvraj-style Summary:
/*
-> Jaise hi child dikhe, turant usko next bana do aur child pointer null kar do
-> Agar next tha, usko stack me daal lo (iterative) ya recursion ke through baad me jod lo
-> DFS jaisa treat karo — sabse pehle child ko flatten karo fir baaki
-> Recursion me tail wapas bhejo to maintain proper linking
-> Stack-based is iterative DFS, elegant and fast
-> Final answer hamesha doubly linked list banega ek level ka

➡️ Time: O(N)
➡️ Space: O(H) due to stack (iterative) or recursion (recursive)
*/

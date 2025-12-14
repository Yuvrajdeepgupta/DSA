---------------------------------------------
LeetCode Q: Delete Node in a Linked List (237)
https://leetcode.com/problems/delete-node-in-a-linked-list/

Solution Link (Video):
https://youtu.be/TvLWOECe8mQ?si=Nzapi1zLXWnqucZX
---------------------------------------------

📝 Problem Statement:
You are given a node of a singly linked list.
You are NOT given the head of the linked list.
Delete the given node from the linked list.
The given node is guaranteed not to be the last node.

---------------------------------------------
🔍 Problem Breakdown:
Normally, to delete a node in a linked list, we need access to the previous node.
Here, we do not have the head of the list, so we cannot access the previous node.
Therefore, the usual deletion approach (prev.next = curr.next) cannot be applied.

---------------------------------------------
💡 Intuition:
Since we cannot delete the given node directly,
we copy the value of the next node into the current node,
and then delete the next node instead.
From outside, it appears that the given node has been removed.

---------------------------------------------
🧠 Approach:
1. Copy the value of node.next into node.
2. Change node.next to node.next.next.
3. This removes the next node and replaces the current node’s value.

---------------------------------------------
✅ Java Code:
---------------------------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution 
{
    public void deleteNode(ListNode node) 
    {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
---------------------------------------------
🧪 Example Walkthrough:
Linked List: 4 → 5 → 1 → 9
Given node = 5

Step 1: Copy next value
4 → 1 → 1 → 9

Step 2: Skip next node
4 → 1 → 9

Result: Node 5 is deleted.

---------------------------------------------
⏱ Time Complexity:
O(1) → Only constant time operations

---------------------------------------------
📦 Space Complexity:
O(1) → No extra space used

---------------------------------------------
🎯 Interview Strategy:
- Mention that head and previous node are not given.
- Explain why normal deletion won’t work.
- Use the value-copy trick.
- Highlight O(1) time and space.

---------------------------------------------
🔥 Yuvraj Style Summary:
Head nahi diya.
Previous node nahi diya.
Bas ek node diya.

Toh kya kiya?
Next ka value uthaya,
current me daala,
next ko skip kar diya.

Node gayab.
Simple. Clean. O(1).
---------------------------------------------

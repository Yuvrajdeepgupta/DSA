/*
🔗 Question Link: https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
🎥 Solution Video Link: https://youtu.be/MRWz6sbRDxM?si=BtohvcOpCQBruVcc

-------------------------------------------------
📝 Problem Statement:
Given a singly linked list, modify it so that all even-valued nodes appear before all odd-valued nodes.
The relative order of even and odd nodes from the original list must be preserved.

Example:
Input: 17 -> 15 -> 8 -> 9 -> 2 -> 4 -> 6
Output: 8 -> 2 -> 4 -> 6 -> 17 -> 15 -> 9

-------------------------------------------------
📌 Key Points:
1. "Even" or "Odd" refers to the node value, NOT its position.
2. The task must be done **in-place** without creating a new list of values.
3. Preserve the **relative order** of even and odd nodes.

-------------------------------------------------
💡 Approach: Two Dummy Lists (Even-First)
- Create two dummy nodes:
  - `evenDummy` to store the start of the even list.
  - `oddDummy` to store the start of the odd list.
- Traverse the original list:
  - If a node has an even value → append it to the even list.
  - If a node has an odd value → append it to the odd list.
- At the end, connect the tail of the even list to the head of the odd list.
- Return the head of the even list.

⚠️ Important:
- Before re-linking nodes, **break their next pointer** to avoid old links corrupting the structure.

-------------------------------------------------
⏳ Time Complexity:
O(N) — single pass through the list.

📦 Space Complexity:
O(1) — only constant extra pointers used.

-------------------------------------------------
✅ Code Implementation:
*/

/*
class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}
*/
class Solution 
{
    Node divide(Node head) 
    {
        if(head == null || head.next == null) {
            return head;
        }
        
        Node oddDummy = new Node(-1);
        Node evenDummy = new Node(-1);
        
        Node odd = oddDummy;
        Node even = evenDummy;
        
        Node temp = head;
        while(temp != null)
        {
            Node nextNode = temp.next; // store next node
            temp.next = null; // break the link
            
            if(temp.data % 2 != 0) // odd value
            {
                odd.next = temp;
                odd = odd.next;
            }
            else // even value
            {
                even.next = temp;
                even = even.next;
            }
            
            temp = nextNode;
        }
        
        even.next = oddDummy.next; // connect even list to odd list
        return evenDummy.next; // head of new list
    }
}

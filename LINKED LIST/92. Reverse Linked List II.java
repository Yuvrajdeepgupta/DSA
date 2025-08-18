Question Link: https://leetcode.com/problems/reverse-linked-list-ii/?envType=problem-list-v2&envId=linked-list
Solution Link: https://youtu.be/ykxvY2ZUfM4?si=x2L6h42bU8MlDaZI

------------------------------------------------
Problem Breakdown:
We are given a singly linked list and two integers left and right. 
We need to reverse the nodes of the list from position left to right, 
and return the modified list. (Positions are 1-indexed.)

Example:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

------------------------------------------------
Intuition:
The linked list can be broken into 3 parts:
1. The part before 'left'
2. The sublist between 'left' and 'right' (this part needs to be reversed)
3. The part after 'right'

Steps:
- Traverse once to find the node just before 'left' and the node at 'right'.
- Cut the list into 3 parts.
- Reverse the middle sublist.
- Stitch them back together.
- Use a dummy node to handle the case when left=1.

------------------------------------------------
Approach:
1. Create a dummy node pointing to head.
2. Traverse the list while decrementing left and right. 
   - When left==2 → store 'prev' (node before reversal).
   - When right==1 → store 'a' (last node of sublist).
3. After traversal, 
   - 'idhr' = prev.next (start of sublist),
   - 'udhr' = a.next (node after sublist).
4. Disconnect the sublist.
5. Reverse the sublist using a helper function.
6. Reconnect everything: prev.next → reversed head, sublist tail → udhr.
7. Return dummy.next as new head.

------------------------------------------------
Java Code:

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
    public ListNode reverseBetween(ListNode head, int left, int right) 
    {
        if(head==null || head.next==null){return head;}
        if(left==right){return head;}

        ListNode dummy=new ListNode(-1);
        dummy.next=head;

        ListNode prev=dummy;
        ListNode idhr=prev.next,a=null,udhr=null;
        ListNode temp=head;

        while(temp!=null)
        {
            if(left==2){prev=temp;}
            if(right==1){a=temp;}

            left--;
            right--;

            temp=temp.next;
        }

        idhr=prev.next;
        udhr=a.next;

        prev.next=null;
        a.next=null;

        prev.next=reverse(idhr);
        idhr.next=udhr;

        return dummy.next;
    }

    public ListNode reverse(ListNode head)
    {
        if(head==null || head.next==null)
        {
            return head;
        }

        ListNode prev=null,curr=head;
        while(curr!=null)
        {
            ListNode succ=curr.next;
            curr.next=prev;
            prev=curr;
            curr=succ;
        }

        return prev;
    }
}

------------------------------------------------
Example Dry Run:
Input: head = [1,2,3,4,5], left = 2, right = 4

- prev stops at node 1
- idhr = 2 (start of reversal), a = 4 (end of reversal), udhr = 5
- Reverse sublist [2,3,4] → [4,3,2]
- Connect back: 1 → 4 → 3 → 2 → 5

Output: [1,4,3,2,5]

------------------------------------------------
Time Complexity:
O(N) → Traverse once to find boundaries + reverse sublist
N = number of nodes in linked list

Space Complexity:
O(1) → Constant extra space (pointers only)

------------------------------------------------
Yuvraj Style Summary:
- Break LL into 3 parts (before, between, after).
- Reverse the middle part.
- Stitch everything back.
- Dummy node handles left=1 case easily.
- Code is O(N), O(1), neat and optimal 🚀

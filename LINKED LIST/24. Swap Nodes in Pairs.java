📝 Question Link: https://leetcode.com/problems/swap-nodes-in-pairs/description/?envType=problem-list-v2&envId=linked-list
🎥 Solution Link: https://youtu.be/8yLiGS4ntHw?si=4vx7xVJOueVd3WsF

--------------------------------------------------------------------------------
INTUITION:
We are asked to swap every two adjacent nodes in a singly linked list.
Key idea → group nodes in pairs and adjust their `next` pointers accordingly.

We can solve this in two ways:
1. Recursive approach (beautiful but uses extra stack space).
2. Iterative approach with dummy node (optimal O(1) space).

--------------------------------------------------------------------------------
RECURSIVE SOLUTION:
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
class Solution 
{
    boolean flag;
    public ListNode swapPairs(ListNode head) 
    {
        if(head==null || head.next==null){return head;}

        flag=false;
        return solve(head,0);
    }

    public ListNode solve(ListNode head,int count)
    {
        if(head==null || head.next==null)
        {
            count++;
            if(count%2==0){flag=false;}
            else{flag=true;}
            return head;
        }

        count++;
        ListNode store=solve(head.next,count);

        flag=!flag;

        if(flag)
        {
            head.next=store.next;
            store.next=head;
            return store;
        }
        else
        {
           head.next=store;
           return head;
        }
    }
}

--------------------------------------------------------------------------------
ITERATIVE SOLUTION (Optimal):
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
    public ListNode swapPairs(ListNode head) 
    {
        if(head==null || head.next==null){return head;}

        ListNode dummy=new ListNode(-1);
        dummy.next=head;

        ListNode curr=head.next,prev=head,behind=dummy;
        boolean flag=false;

        while(curr!=null)
        {
            ListNode store=curr.next;
            flag=!flag;
            
            if(flag)
            {
                curr.next=prev;
                prev.next=store;
                behind.next=curr;

                behind=curr;
                curr=store;
            }
            else
            {
                curr=curr.next;
                prev=prev.next;
                behind=behind.next;
            }
        }

        return dummy.next;
    }
}

--------------------------------------------------------------------------------
⏱️ TIME COMPLEXITY:
- Recursive: O(n) → each node visited once
- Iterative: O(n) → each node visited once

🗂️ SPACE COMPLEXITY:
- Recursive: O(n) → due to call stack
- Iterative: O(1) → only pointers used

--------------------------------------------------------------------------------
✅ KEY TAKEAWAYS:
- Dummy node helps simplify pointer manipulation in linked list problems.
- Recursive solution is elegant but not optimal in terms of space.
- Iterative solution with dummy node is the most optimal approach.

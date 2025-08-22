---------------------------------------------
LeetCode Q: Reorder List
https://leetcode.com/problems/reorder-list/submissions/1741234720/

Solution Link (Video):
https://youtu.be/7cp_HR1BT1E?si=0M_LUIPf28W3kHBg
---------------------------------------------

📝 Problem Statement:
Given the head of a singly linked list, reorder it in-place to follow the pattern:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …  

Do not create new nodes, just reorder by changing next pointers.

---------------------------------------------
🔹 Approaches Overview
1. Brute Force → Store nodes in a list and merge front-back using indices.
2. Good Approach → Find middle, reverse second half, recursively merge.
3. Mazedaar Recursive → Recursive backtracking with middle pointer and front pointer merge.

---------------------------------------------
💡 Intuition

- Brute Force: Convert list to ArrayList for easy front-back access. Merge by index.
- Good: Slow/Fast pointer to find middle, reverse second half, recursively merge two halves.
- Mazedaar Recursive: Use recursion to reach the end, merge nodes while unwinding, stop at middle.

---------------------------------------------
✅ Brute Force Approach
Code:
---------------------------------------------
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
    public void reorderList(ListNode head) 
    {
       if(head==null || head.next==null || head.next.next==null)
       {
            return;
       }

        List<ListNode> list=new ArrayList<>();
        ListNode temp=head;
        while(temp!=null)
        {
            list.add(temp);
            ListNode store=temp;
            temp=temp.next;
            store.next=null;
        }

        int i=0,n=list.size();
        int last=null;
        while(i<n/2)
        {
            ListNode idhr=list.get(i);
            ListNode udhr=list.get(n-1-i);

            if(last!=null){last.next=idhr;}
            idhr.next=udhr;
            last=udhr;
            i++;
        }

        if(n%2!=0)
        {
            last.next=list.get(i);
        }
    }
}
---------------------------------------------
Time Complexity: O(n)  
Space Complexity: O(n) → ArrayList storing nodes

---------------------------------------------
✅ Good Approach (Middle + Reverse + Merge Recursively)
Code:
---------------------------------------------
class Solution 
{
    public void reorderList(ListNode head) 
    {
        if(head==null || head.next==null || head.next.next==null) return;

        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null)
        {
            fast=fast.next.next;
            slow=slow.next;
        }

        ListNode store=slow.next;
        slow.next=null;
        reorder(head,reverse(store));
    }

    public ListNode reorder(ListNode head1,ListNode head2)
    {
        if(head2==null) return head1;

        ListNode store=head1.next;
        head1.next=head2;
        head2.next=reorder(store,head2.next);
        return head1;
    }

    public ListNode reverse(ListNode head)
    {
        ListNode prev=null,curr=head;
        while(curr!=null)
        {
            ListNode store=curr.next;
            curr.next=prev;
            prev=curr;
            curr=store;
        }
        return prev;
    }
}
---------------------------------------------
Time Complexity: O(n)  
Space Complexity: O(n) → recursion stack

---------------------------------------------
✅ Mazedaar Recursive Approach (Front + Back Pointer + Middle)
Code:
---------------------------------------------
class Solution 
{   
    ListNode curr=null,store=null,middle=null;

    public void reorderList(ListNode head) 
    {   
        if(head==null || head.next==null || head.next.next==null) return;

        curr=head;
        middle=getMiddle(head);
        recursion(head);
    }

    public void recursion(ListNode head)
    {
        if(head.next==null)
        {
            store=curr.next; 
            curr.next=head;
            head.next=store;
            curr=store;
            return;
        }

        recursion(head.next);
        if(curr!=middle)
        {
            store=curr.next;
            curr.next=head;
            head.next=store;
            curr=store;
        }
        else
        {
            curr.next=null;
        }
    }

    public ListNode getMiddle(ListNode head) 
    {
        if (head.next == null) return head;  // Only one element
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) 
        {
            fast = fast.next.next; // Move fast by 2
            slow = slow.next;      // Move slow by 1
        }
        return slow; // Middle element
    } 
}
---------------------------------------------
Time Complexity: O(n)  
Space Complexity: O(n) → recursion stack

---------------------------------------------
📊 Example Walkthrough (List: 1 → 2 → 3 → 4 → 5)
- Brute: store all nodes in list → merge indices 0&4, 1&3 → reordered: 1→5→2→4→3  
- Good: middle=3, reverse second half [4→5] → merge recursively: 1→5→2→4→3  
- Mazedaar: recursion reaches end 5, merge while unwinding with curr pointer, stop at middle → 1→5→2→4→3

---------------------------------------------
🕒 Complexity Summary
- Brute: O(n) time, O(n) space  
- Good: O(n) time, O(n) space (recursion)  
- Mazedaar: O(n) time, O(n) space (recursion)

---------------------------------------------
🎯 Interview Strategy
- Start with **brute force** to show simple solution.  
- Explain **good approach** with middle+reverse+merge recursively.  
- End with **mazedaar recursive** as a neat, creative solution.  
- Shows **structured thinking**: Brute → Better → Creative.

---------------------------------------------
🔥 Yuvraj Summary (easy way)
- Brute uses extra list → easy to code.  
- Better: find middle, reverse half, merge recursively.  
- Mazedaar: recursion + front pointer + middle → elegant merge.  
- Recursion stack uses O(n) space, iterative version can do O(1) space.  
- Pick mazadear recursive in interview if you want to **show originality and understanding**.  
---------------------------------------------

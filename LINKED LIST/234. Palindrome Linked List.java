---------------------------------------------
LeetCode Q: Palindrome Linked List
https://leetcode.com/problems/palindrome-linked-list/description/

Solution Link (Video):
https://youtu.be/Sgi2BHiW0-Q?si=xIkD24dv-ufavKLL
---------------------------------------------

📝 Problem Statement:
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

A palindrome is a sequence that reads the same backward as forward.

---------------------------------------------
🔹 Approaches Overview
1. Brute Force → Convert linked list into ArrayList, then check with two pointers.
2. Optimal 1 → Find middle, reverse second half, compare, then restore list.
3. Optimal 2 → Reverse first half on-the-fly while moving slow/fast pointers, then compare.
4. Recursive (Favorite neat one) → Use recursion stack to compare from both ends.

---------------------------------------------
💡 Intuition

- Brute Force: Linked list doesn't allow reverse traversal directly, so convert to an array to check easily.
- Optimal 1: Use fast/slow pointers to find middle. Reverse the second half and compare with first half.
- Optimal 2: While finding middle, reverse first half simultaneously. Then compare directly with second half.
- Recursive: Recursively reach the end, then while unwinding, compare with a global forward pointer.

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
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }

        ListNode temp=head;
        ArrayList<Integer> list=new ArrayList<>();
        while(temp!=null) {
            list.add(temp.val);
            temp=temp.next;
        }

        int i=0,j=list.size()-1;
        while(i<j) {
            if(list.get(i)!=list.get(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
---------------------------------------------
Time Complexity: O(n)  (n for copying, n for checking)
Space Complexity: O(n) (extra ArrayList)

---------------------------------------------
✅ Optimal Approach 1 (Reverse second half)
Code:
---------------------------------------------
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }

        ListNode slow=head,fast=head,prev=null;
        while(fast!=null && fast.next!=null) {
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode store=prev.next;
        ListNode newhead=reverse(prev.next);
        prev.next=null;

        slow=head;
        fast=newhead;

        boolean flag=true;
        while(slow!=null && fast!=null) {
            if(slow.val!=fast.val) {
                flag=false;
                break;
            }
            slow=slow.next;
            fast=fast.next;
        }

        prev.next=reverse(store); // restore
        return flag;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while(curr != null) {
            ListNode store = curr.next;
            curr.next = prev;
            prev = curr;
            curr = store;
        }
        return prev;
    }
}
---------------------------------------------
Time Complexity: O(n)
Space Complexity: O(1) (only pointers)

---------------------------------------------
✅ Optimal Approach 2 (Reverse first half while finding middle)
Code:
---------------------------------------------
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }

        ListNode slow=head,fast=head,prev=null;
        while(fast!=null && fast.next!=null) {
            ListNode store=slow.next;
            fast=fast.next.next;
            slow.next=prev;
            prev=slow;
            slow=store;
        }
        if(fast!=null) { slow=slow.next; }

        while(prev!=null && slow!=null) {
            if(prev.val!=slow.val) {
                return false;
            }
            prev=prev.next;
            slow=slow.next;
        }
        return true;
    }
}
---------------------------------------------
Time Complexity: O(n)
Space Complexity: O(1)

---------------------------------------------
✅ Recursive Approach (Favorite neat one)
Code:
---------------------------------------------
class Solution {
    ListNode curr=null;
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }
        curr=head;
        return recursion(head);
    }

    public boolean recursion(ListNode head) {
        if(head.next==null) {
            boolean temp=(head.val==curr.val);
            curr=curr.next;
            return temp;
        }

        boolean ans=recursion(head.next);
        if(ans) {
            ans=(head.val==curr.val);
            curr=curr.next;
        }
        return ans;
    }
}
---------------------------------------------
Time Complexity: O(n)
Space Complexity: O(n) (recursion stack)

---------------------------------------------
📊 Example Walkthrough (for list: 1 → 2 → 2 → 1)

- Brute: ArrayList = [1,2,2,1], check → palindrome.
- Optimal 1: slow/fast → middle at 2. Reverse second half [2,1]. Compare with [1,2].
- Optimal 2: While moving slow/fast, reverse first half [1]. At middle, slow points to second half [2,1]. Compare.
- Recursive: recursion reaches last node 1, compare with head 1, unwind comparing pairs.

---------------------------------------------
🕒 Complexity Summary
- Brute: O(n) time, O(n) space
- Optimal 1: O(n) time, O(1) space
- Optimal 2: O(n) time, O(1) space
- Recursive: O(n) time, O(n) space

---------------------------------------------
🎯 Interview Strategy
- Start with Brute → easy to explain.
- Mention Recursive → elegant but stack-heavy.
- End with Optimal 2 (reverse first half) → BEST, fully in-place, O(1) space.
- Shows progression: Brute → Better → Optimal.

---------------------------------------------
🔥 Yuvraj Summary (easy way)
- Linked list is hard to traverse backwards, so brute uses arraylist.
- Better way: find middle with slow/fast, reverse half, and compare.
- Even smarter: reverse first half while finding middle (one pass).
- Recursion is neat but not space optimal.
- Final pick in interview → Optimal 2 (reverse first half).
---------------------------------------------

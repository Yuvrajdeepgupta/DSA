// 🔗 Question: https://leetcode.com/problems/merge-two-sorted-lists/
// 🎥 Solution1: https://youtu.be/RreHsOfi14w?si=7v3D5KkiBef7obz9
// 🎥 Solution2: https://youtu.be/u4CoGMyPKR0?si=Eg1-ygQzBOjSZ9bB

/******************************************************************************************
🔸 Problem: Merge Two Sorted Lists

You are given the heads of two sorted linked lists `list1` and `list2`. 
Merge the two lists into one **sorted list**. 
Return the head of the merged linked list.

******************************************************************************************

✅ INTUITION (Yuvraj Style):

We are given 2 sorted linked lists. Our task is to merge them into a single sorted list.
At each step:
- Compare the current node of both lists.
- Append the smaller one to our result list.
- Move ahead in the list from which we took the node.
Repeat this until one list is completely traversed.
Finally, just attach the remaining part of the other list.

We can solve this using:
1️⃣ Recursion  
2️⃣ Iteration using a dummy node

******************************************************************************************

🔍 DRY RUN:

list1: 1 → 3 → 5  
list2: 2 → 4 → 6  

→ Compare 1 and 2 → pick 1  
→ Compare 3 and 2 → pick 2  
→ Compare 3 and 4 → pick 3  
→ Compare 5 and 4 → pick 4  
→ Compare 5 and 6 → pick 5  
→ list2 has 6 left → attach it  

Final merged list: 1 → 2 → 3 → 4 → 5 → 6

******************************************************************************************

✅ RECURSIVE CODE
*/

class Solution {
    public ListNode mergeTwoLists(ListNode h1, ListNode h2) 
    {
        if(h1 == null) return h2;
        if(h2 == null) return h1;

        if(h1.val < h2.val)
        {
            h1.next = mergeTwoLists(h1.next, h2);
            return h1;
        }
        else
        {
            h2.next = mergeTwoLists(h1, h2.next);
            return h2;
        }
    }
}

/******************************************************************************************

✅ ITERATIVE CODE (using dummy node)
*/

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) 
    {
        ListNode dummy = new ListNode(0);  // Dummy head
        ListNode t1 = list1, t2 = list2, t3 = dummy;

        while(t1 != null && t2 != null)
        {
            if(t1.val <= t2.val)
            {
                t3.next = t1;
                t1 = t1.next;
            }
            else
            {
                t3.next = t2;
                t2 = t2.next;
            }

            t3 = t3.next;
        }

        // Attach the remaining part
        if(t1 != null)
        {
            t3.next = t1;
        }
        else if(t2 != null)
        {
            t3.next = t2;
        }

        return dummy.next;
    }
}

/******************************************************************************************

⏱️ TIME COMPLEXITY:
- O(n + m), where n = length of list1, m = length of list2
- We visit each node exactly once

🗃️ SPACE COMPLEXITY:
- Recursive: O(n + m) due to recursion call stack
- Iterative: O(1) extra space (just using pointers)

******************************************************************************************

🧾 SUMMARY (Yuvraj Style):

- Compare both lists node by node.
- Pick the smaller one, move ahead.
- Attach remaining list when one finishes.
- Use recursion or dummy node-based iteration.
- Iterative is space-optimal, recursive is clean and elegant.

******************************************************************************************/

/*
Question Link: https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
Solution Link: https://youtu.be/TxryJMerDwE?si=QN5eoOF53R0IWpM-

-------------------------
✅ Problem Breakdown
-------------------------
We are given the head of a singly linked list and an integer k.  
We need to swap the values of the kth node from the START and the kth node from the END.

Example:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Explanation: The 2nd node from start is 2 and the 2nd node from end is 4 → swap their values.

Key Points:
- Need to find kth from start and kth from end in ONE pass.
- Swapping values is easier than swapping nodes.

-------------------------
💡 Intuition
-------------------------
Two-pointer trick:
1. Use `first` to track kth from start.
2. Move `second` k steps ahead from head.
3. Keep a `last` pointer at head and move both `second` and `last` together until `second` reaches null.
4. Now `last` is kth from end.
5. Swap `first.val` and `last.val`.

Why this works:
- The gap of k nodes ensures `last` lands exactly kth from end when `second` reaches the end.
- Only one traversal, O(1) extra space.

-------------------------
⏳ Time Complexity
-------------------------
O(L) — L is number of nodes.

-------------------------
📦 Space Complexity
-------------------------
O(1) — Only pointers used.

-------------------------
🚀 Code Implementation
-------------------------
*/

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
    public ListNode swapNodes(ListNode head, int k) 
    {
        ListNode first = head, second = head;

        // Step 1: Move to kth node from start
        while(k > 0 && second != null && first != null)
        {
            if(k > 1)
            {
                first = first.next;
            }
            second = second.next;
            k--;
        }

        // If k > list length, return head as is
        if(k > 0)
        {
            return head;
        }

        // Step 2: Find kth node from end
        ListNode last = head;
        while(second != null)
        {
            second = second.next;
            last = last.next;
        }

        // Step 3: Swap values
        int temp = first.val;
        first.val = last.val;
        last.val = temp;

        return head;
    }
}

-------------------------
📊 Pointer Movement Diagram
-------------------------

Initial: head = [1,  2,  3,  4,  5], k = 2
         idx   =  1   2   3   4   5

Step 1: Move `first` to kth node from start:
 first → (2)
 second → moved k steps ahead → (3)

Step 2: Move `second` and `last` together until `second` hits null:
 first → (2) stays
 last  → moves to (4) → kth from end
 second → null

Step 3: Swap values of `first` and `last`:
 Swap(2,4) → [1, 4, 3, 2, 5]

Visual:
 head → 1 → 2(f) → 3 → 4(l) → 5
                  ↑         ↑
                kth start  kth end

-------------------------
😎 Yuvraj Style Summary
-------------------------
Bhai simple —
Kth from start pakdo.
Dusra pointer se gap create karo.
Phir dono ko saath chalao till end, peeche wala kth from end ban jayega.
Values swap karo, kaam done ✅

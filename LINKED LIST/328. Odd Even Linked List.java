/*
🔗 Question Link: https://leetcode.com/problems/odd-even-linked-list/description/
🎥 Solution Video Link: https://youtu.be/kbcucihgO_I?si=rr3tSkKDcZTiBHhZ

-------------------------------------------------
📝 Problem Statement:
Given the head of a singly linked list, group all the nodes with odd indices together 
followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, the second node is even, and so on.

Example:
Input: 1 -> 2 -> 3 -> 4 -> 5
Output: 1 -> 3 -> 5 -> 2 -> 4

-------------------------------------------------
📌 Key Points:
1. Indices are considered based on position, not the value.
2. We must preserve the original relative order among odd and even nodes.
3. Must do it in-place → no need for extra linked list.

-------------------------------------------------
💡 Approach 1: Using Dummy Node for Even List (Extra Space Pointer)
Intuition:
- Maintain two separate lists: odd index nodes & even index nodes.
- Use a dummy node for even list and collect all even nodes while skipping them in the main list.
- Finally, attach even list after odd list.

Steps:
1. Create `dummy` node to store start of even list.
2. Traverse original list:
   - Connect even list nodes to `dummy`.
   - Skip them in main (odd) list.
3. Attach even list at the end of odd list.

Time Complexity: O(n)  
Space Complexity: O(1) extra space (only a few pointers)

Code:
-------------------------------------------------
class Solution 
{
    public ListNode oddEvenList(ListNode head) 
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode oddtemp = head;
        ListNode eventemp = dummy;

        while (eventemp.next != null && oddtemp.next != null)
        {
            eventemp.next = oddtemp.next;
            eventemp = eventemp.next;

            oddtemp.next = eventemp.next;
            if (oddtemp.next != null) oddtemp = oddtemp.next;
        }

        if (eventemp.next != null) eventemp.next = null;
        oddtemp.next = dummy.next;

        return head;
    }
}

-------------------------------------------------
💡 Approach 2: Optimal Two-Pointer Method (In-place)
Intuition:
- Keep two pointers:
  - `odd` for odd-position nodes.
  - `even` for even-position nodes.
- Keep a reference to the head of the even list (`evenhead`).
- Rearrange pointers so all odd nodes come first, followed by even nodes.

Steps:
1. Initialize `odd = head`, `even = head.next`, `evenhead = even`.
2. While `even` and `even.next` are not null:
   - Link odd’s next to even’s next, move odd forward.
   - Link even’s next to odd’s next, move even forward.
3. Attach even list (`evenhead`) after odd list.

Time Complexity: O(n)  
Space Complexity: O(1) extra space

Code:
-------------------------------------------------
class Solution 
{
    public ListNode oddEvenList(ListNode head) 
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode odd = head, even = head.next, evenhead = head.next;
        while (even != null && even.next != null)
        {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenhead;
        return head;
    }
}

-------------------------------------------------
🆚 Comparison:
- Approach 1 (Dummy node): More steps, slightly less intuitive, but works fine.
- Approach 2 (Optimal): Cleaner, fewer pointers to maintain, no dummy node, O(1) space.

-------------------------------------------------
🗣 Yuvraj-Style Summary:
Bhai easy hai —
Odd index wale nodes ka chain banake rakho, even wale ka alag.
Phir odd ke baad even list chipka do.
Optimal wale mein direct 2 pointer chala, 
odd aur even ke bich ka jump adjust kiya aur kaam khatam.  
*/

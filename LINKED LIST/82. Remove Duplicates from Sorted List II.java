/*
🔗 Question Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
🎥 Solution Video Link: https://youtu.be/eFPFwwojxGU?si=Rz--x5Qy_c1nCuhW

-------------------------------------------------
📝 Problem Statement:
Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list. Return the linked list sorted as well.

Example:
Input: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
Output: 1 -> 2 -> 5

-------------------------------------------------
📌 Key Points:
1. The linked list is sorted → duplicates will appear together.
2. We must remove *all occurrences* of duplicate values.
3. Output must still be sorted.

-------------------------------------------------
💡 Approach 1: Frequency Array Method (Extra Space)
Intuition:
- Since values are in range [-100, 100], we can use a frequency array of size 201.
- First pass → count frequency of each value.
- Second pass → build a new sorted list using only values with frequency = 1.

Steps:
1. Use `freq[201]` with offset = 100 to store counts.
2. Traverse list, count frequencies.
3. Iterate `freq[]` from smallest to largest, add values with freq = 1 to new list.

Time Complexity: O(n + 201) → O(n)  
Space Complexity: O(1) extra space (fixed size array)

Code:
-------------------------------------------------
class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        int freq[] = new int[201];
        int offset = 100;

        // Count frequencies
        ListNode temp = head;
        while (temp != null)
        {
            int idx = temp.val + offset;
            if (freq[idx] == 0)
            {
                freq[idx] = 1;
            }
            else
            {
                freq[idx] = -1;
            }
            temp = temp.next;
        }

        // Build sorted list from freq[]
        ListNode dummy = new ListNode(-1);
        temp = dummy;

        for (int i = 0; i < 201; i++)
        {
            if (freq[i] == 1)
            {
                temp.next = new ListNode(i - offset);
                temp = temp.next;
            }
        }

        return dummy.next;
    }
}

-------------------------------------------------
💡 Approach 2: Two-Pointer Method (O(1) Space, Optimal)
Intuition:
- Use two pointers: `prev` (last confirmed unique node) and `head` (current node).
- Skip over all duplicates when detected.
- Use a dummy node to handle edge cases where head itself is a duplicate.

Steps:
1. Create dummy node before head.
2. `prev` starts at dummy, `head` at actual head.
3. If current node has duplicates (head.val == head.next.val):
   → skip all occurrences of that value and connect prev to the node after duplicates.
4. Else, move prev forward.
5. Continue until list end.

Time Complexity: O(n)  
Space Complexity: O(1) extra space

Code:
-------------------------------------------------
class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null)
        {
            if (head.next != null && head.val == head.next.val)
            {
                int dupVal = head.val;
                while (head != null && head.val == dupVal)
                {
                    head = head.next;
                }
                prev.next = head; // Skip duplicates
            }
            else
            {
                prev = head;
                head = head.next;
            }
        }

        return dummy.next;
    }
}

-------------------------------------------------
🆚 Comparison:
- Approach 1 (freq array): Easy to implement, always returns sorted list, but uses extra space.
- Approach 2 (two-pointer): In-place, O(1) space, faster in practice, keeps original sorted order.

-------------------------------------------------
🗣 Yuvraj-Style Summary:
Bhai simple hai —
List sorted hai → duplicates saath-saath aayenge.
Ya toh freq[] leke count kar aur sirf unique walo ka naya list bana de (Approach 1).
Ya fir do pointer se in-place skip kar de duplicates ko (Approach 2, mast optimal).
Approach 2 interviews mein pasand aata hai kyunki extra space nahi leta.
*/

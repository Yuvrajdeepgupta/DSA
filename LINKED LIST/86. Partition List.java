/*
❓ Problem: Partition List
LeetCode Link: https://leetcode.com/problems/partition-list/?envType=problem-list-v2&envId=linked-list
Solution Video Link: https://youtu.be/NYZKRUT_8MY?si=DDex4g2CNgK9u0If

------------------------------------------------
📌 Problem Statement:
Given the head of a linked list and a value x, 
partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

------------------------------------------------
🧠 Intuition:
We want to split the linked list into two parts:
1. Nodes with values < x
2. Nodes with values >= x

We’ll create two separate linked lists using dummy nodes:
- One list for nodes < x
- One list for nodes >= x
At the end, we’ll connect these two lists together.

The key benefit of using dummy nodes is that it avoids extra conditions 
for handling the first node insertion and keeps the logic clean.

------------------------------------------------
💡 Why This Approach Works:
- By traversing the original list only once, we can directly place each node in the correct partition.
- Since we append nodes in the order we see them, the relative order within each partition is preserved.
- Dummy nodes make concatenation easy at the end.

------------------------------------------------
🛠 Approach:
1. Create two dummy nodes:
   - dummy1 for list of nodes < x
   - dummy2 for list of nodes >= x
2. Maintain two pointers (temp1, temp2) to build these lists.
3. Traverse the original list:
   - If current node value < x → append to list1
   - Else → append to list2
   - In both cases, detach node’s next to avoid old links.
4. Connect the end of list1 to the start of list2.
5. Return dummy1.next (head of the new partitioned list).

------------------------------------------------
⏱ Time Complexity:
- O(N), where N = number of nodes in the list (we visit each node once)

💾 Space Complexity:
- O(1), we only use a few extra pointers (dummy nodes), no extra data structure proportional to N

------------------------------------------------
📍 Example Walkthrough:
Example:
Input: head = 1 -> 4 -> 3 -> 2 -> 5 -> 2,  x = 3

Step-by-step:
List1 (<3): 1 -> 2 -> 2
List2 (>=3): 4 -> 3 -> 5
Final list: 1 -> 2 -> 2 -> 4 -> 3 -> 5

------------------------------------------------
✅ Code:
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
class Solution {
    public ListNode partition(ListNode head, int x) 
    {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);

        ListNode temp1 = dummy1, temp2 = dummy2, temp = head;

        while (temp != null)
        {
            if (temp.val < x)
            {
                temp1.next = temp;
                temp = temp.next;
                temp1 = temp1.next;
                temp1.next = null; // break old link
            }
            else
            {
                temp2.next = temp;
                temp = temp.next;
                temp2 = temp2.next;
                temp2.next = null; // break old link
            }
        }

        temp1.next = dummy2.next; // connect two lists
        return dummy1.next;
    }
}

------------------------------------------------
📝 Yuvraj-Style Summary:
Bhai simple funda — do alag lists banao: ek for < x, ek for >= x.
Dummy nodes use karo taaki insert karte time tension na ho.
Traverse karte hi har node ko apne list me chipka do, aur purane links tod do.
End me first wali list ka end dusri wali ke start se jod do — done! O(N) time, O(1) space, full clean.

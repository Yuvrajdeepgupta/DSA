// ======================================================================================
// 🔗 Problem Link: https://leetcode.com/problems/next-greater-node-in-linked-list/description/?envType=problem-list-v2&envId=linked-list
// 🎥 Solution Reference: https://youtu.be/8P3e34EgVyY?si=-qGgD0lQgik00Ajf
// ======================================================================================
//
// ✅ Problem Statement:
// Given the head of a linked list, for each node, find the value of the next greater node.
// The next greater node is the first node after the current one that has a strictly larger value.
// If no such node exists, the answer is 0.
//
// Return an integer array 'ans' where ans[i] is the next greater node value of the i-th node.
//
// Example:
// Input: head = [2,1,5]
// Output: [5,5,0]
//
// ======================================================================================
//
// 🎯 Problem Breakdown:
// - We need "Next Greater Element" type logic but applied on a linked list.
// - Direct traversal is tricky (no backward movement allowed).
// - Brute force = O(n^2), check each node with all nodes ahead → TLE for big inputs.
// - Optimized → Use Stack + Reverse traversal.
//
// ======================================================================================
//
// 💡 Intuition:
// - Classic **Next Greater Element** problem but on linked list instead of array.
// - If we had an array, we could solve easily using a monotonic stack.
// - Trick: Reverse the linked list to process from right to left (like we do in arrays).
// - Maintain a decreasing stack:
//     → Pop all smaller/equal elements than current (not useful as "next greater").
//     → If stack is not empty, top = next greater.
//     → Else, no greater → 0.
//
// ======================================================================================
//
// 🛠 Why this Approach Works?
// - By reversing the list, we mimic "right-to-left" traversal.
// - Stack stores only candidates for "next greater" in decreasing order.
// - Ensures O(n) because each element is pushed and popped at most once.
// - After building result array from back, we restore the linked list (optional).
//
// ======================================================================================
//
// 🧩 Java Code Implementation
// ======================================================================================

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
    int length;

    public int[] nextLargerNodes(ListNode head) 
    {
        // Step 1: Reverse the linked list for right-to-left processing
        length = 0;
        head = reverse(head);

        int ans[] = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 2: Traverse reversed list
        ListNode temp = head;
        int i = ans.length - 1; // Fill result array from back
        while (temp != null) 
        {
            int curr = temp.val;

            // Maintain decreasing stack
            while (!stack.isEmpty() && stack.peek() <= curr) 
            {
                stack.pop();
            }

            // If stack empty → no greater element, else stack top is answer
            ans[i--] = stack.isEmpty() ? 0 : stack.peek();

            // Push current element into stack
            stack.push(curr);

            temp = temp.next;
        }

        // Step 3: Restore original linked list (optional, for safety)
        reverse(head);

        return ans;
    }

    // Utility function to reverse the linked list
    public ListNode reverse(ListNode head) 
    {
        if (head == null) return head;

        ListNode curr = head, prev = null;
        while (curr != null) 
        {
            ListNode store = curr.next;
            curr.next = prev;
            prev = curr;
            curr = store;
            length++;
        }

        return prev;
    }
}

// ======================================================================================
//
// ⏱ Time Complexity:
// - Reversing list: O(n)
// - Traversal + stack push/pop (each element at most once): O(n)
// - Total: O(n)
//
// 📦 Space Complexity:
// - Result array: O(n)
// - Stack (worst case all elements decreasing): O(n)
// - Total: O(n)
//
// ======================================================================================
//
// 🔑 Summary (Yuvraj Way):
// - Reverse list → allows right-to-left processing.
// - Use monotonic decreasing stack → stores potential "next greater" elements.
// - While traversing: Pop smaller, stack top = next greater, else 0.
// - Fill result array from back.
// - Restore list (if needed).
// - Clean O(n) solution, much better than brute force O(n^2).
//
// ======================================================================================

/*
Problem link: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
Solution link: https://youtu.be/5UWEVMg10rY?si=wAjvWkahQ_LAURjP

Detailed Approach:
✔ The task is to remove all consecutive nodes whose sum is zero from a linked list.
✔ We use the concept of **prefix sums** to detect sublists that sum to zero.
✔ Prefix sum means the cumulative sum of node values from the start up to the current node.
✔ If at any point the prefix sum is the same as it was earlier, it means the nodes between the two occurrences sum to zero.
✔ We store the prefix sums along with their corresponding nodes in a hashmap.
✔ Whenever we find a repeating prefix sum, we:
   1. Identify the starting node (prev) where the prefix sum first occurred.
   2. Skip the nodes between prev.next and current by adjusting the pointers.
   3. Remove intermediate prefix sums from the hashmap to keep it clean.
✔ A dummy node is used before the head to handle cases where the beginning of the list is part of a zero-sum sequence.

Step-by-step:
1. Initialize a dummy node before the head node.
2. Create a hashmap to store prefix sums and nodes.
3. Traverse the list while maintaining the prefix sum:
   - If the sum is already in the map → zero-sum sequence detected → remove nodes in between.
   - Else, store the current sum and node in the map.
4. After processing, return the modified list starting from dummy.next.

---

Dry Run Example:

**Input:** [1, 2, -3, 3, 1]

Step 1 → prefix sum calculation:

| Node  | Value | Prefix Sum | Action         |
|------|------|------------|----------------|
| dummy | 0   | 0          | map.put(0, dummy) |
| 1    | 1    | 1          | map.put(1, node 1) |
| 2    | 2    | 3          | map.put(3, node 2) |
| -3   | -3   | 0          | prefix sum 0 found again → remove nodes between dummy.next and -3 |
| 3    | 3    | 3          | map.put(3, node 4) |
| 1    | 1    | 4          | map.put(4, node 5) |

Step 2 → Removing zero-sum nodes:

- When -3 is reached, prefix sum becomes 0 again → nodes [1,2,-3] sum to zero → remove them by setting dummy.next = node 4 (3).

Final List: [3,1]

---

Time Complexity:
✔ O(n), where n is the number of nodes.  
Each node is processed once, and hashmap operations are O(1) on average.

Space Complexity:
✔ O(n), used for storing prefix sums in the hashmap.

---

Summary (Yuvraj way):
✔ We use prefix sums to find sequences summing to zero in the list.  
✔ A hashmap helps track sums and quickly find zero-sum segments.  
✔ The dummy node ensures edge cases are handled smoothly.  
✔ This solution is efficient — O(n) time and space — and works for all cases including overlapping sequences.  
✔ The approach is clean, intuitive, and ideal for interviews!

*/
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) 
    {
        Map<Integer, ListNode> map = new HashMap<>();

        // Step 1: Use dummy node to handle edge cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int psum = 0;
        map.put(psum, dummy); // Initial prefix sum 0

        ListNode temp = head;
        while(temp != null) {
            ListNode store = temp.next;
            psum += temp.val;

            if(map.containsKey(psum)) {
                ListNode prev = map.get(psum);

                // Remove nodes between prev.next and temp (inclusive)
                ListNode traverse = prev.next;
                int tpsum = psum;
                while(traverse != temp) {
                    tpsum += traverse.val;
                    map.remove(tpsum);
                    traverse = traverse.next;
                }
                // Skip zero-sum sequence
                prev.next = store;
            } else {
                // Store the current prefix sum and node
                map.put(psum, temp);
            }
           
            temp = store;
        }

        return dummy.next;
    }
}

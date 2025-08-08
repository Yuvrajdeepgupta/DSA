/*
💥 Leetcode Question: Merge K Sorted Lists
🔗 Question Link: https://leetcode.com/problems/merge-k-sorted-lists/description/
🎥 Solution Video: https://youtu.be/Q64u-W3l3mA?si=MmNzqxOLQ5ZFfYju

👨‍🏫 Problem:
You are given an array of k linked-lists `lists`, each linked-list is sorted in ascending order. 
Merge all the linked-lists into one sorted linked-list and return it.

--------------------------------------------
🧠 APPROACH 1: Brute Force - Merge one-by-one
--------------------------------------------
🔍 Intuition:
We merge two lists at a time using the same logic as "Merge Two Sorted Lists".
Keep merging the result with the next list.

🧮 Time Complexity: O(k * n)  
- Each merge takes O(n), done k times.
- Worst-case if lists are almost same size.

🧮 Space Complexity: O(1) extra (we just relink nodes)

*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode result = null;
        for (ListNode list : lists) {
            result = mergeTwoLists(result, list);
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), temp = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}


/*
-------------------------------------------------------------
🧠 APPROACH 2: Divide & Conquer (Good)
-------------------------------------------------------------
🔍 Intuition:
We merge the lists in a divide-and-conquer fashion.
Divide the k lists into two halves, merge each half recursively and combine results.
Same concept as merge sort.

🧮 Time Complexity: O(n * log k)
- log k levels of merging, each level merges total n nodes.

🧮 Space Complexity: O(log k) (for recursion stack)
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int low, int high) {
        if (low >= high) return lists[low];
        int mid = low + (high - low) / 2;
        return mergeTwoLists(
            merge(lists, low, mid),
            merge(lists, mid + 1, high)
        );
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}


/*
-------------------------------------------------------------
🧠 APPROACH 3: Count Sort (Optimal for known constraints)
-------------------------------------------------------------
🔍 Intuition:
If we know value range is small (e.g., in constraints of question), we can count the frequency of each value using a count array, and build the final list from it.

🧮 Time Complexity: O(N + range)  
- N = total number of nodes
- range = (max - min), needed to loop over freq[].

🧮 Space Complexity: O(range)  
- Extra space for freq[] array
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find min and max value
        for (ListNode head : lists) {
            while (head != null) {
                min = Math.min(min, head.val);
                max = Math.max(max, head.val);
                head = head.next;
            }
        }

        int offset = Math.abs(min);  // for handling negative values
        int[] freq = new int[max + offset + 1];

        // Count frequencies
        for (ListNode head : lists) {
            while (head != null) {
                freq[head.val + offset]++;
                head = head.next;
            }
        }

        // Build result using freq array
        ListNode dummy = new ListNode(-1), temp = dummy;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                temp.next = new ListNode(i - offset);
                temp = temp.next;
            }
        }
        return dummy.next;
    }
}


/*
---------------------------------------------------------------------
🔚 YUVRAJ WAY SUMMARY:
---------------------------------------------------------------------
🧵 Question: Merge K sorted linked lists into one sorted list.

✅ Brute: Merge one by one (like chaining mergeTwoLists)
    - TC: O(k*n)
    - SC: O(1)
    
✅ Good: Divide & Conquer (like merge sort)
    - TC: O(n * log k)
    - SC: O(log k)
    
✅ Optimal (if constraints allow): Count sort based approach
    - TC: O(N + range)
    - SC: O(range) – use only if values are within a small range

🧠 Pick brute if small k, use divide & conquer as general solution,
and use count sort only when value range is tight and predictable.

🧑‍💻 Always use dummy node trick for clean and efficient merging!

🧾 Tip: Merge Two Lists logic is reused in all approaches – master that first.

*/

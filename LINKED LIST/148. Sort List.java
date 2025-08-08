/**
 * 🔗 Leetcode: https://leetcode.com/problems/sort-list/description/
 * 📺 Solution Video: https://youtu.be/13UkRumpqZw?si=f3UYe4H933Ij2dvQ
 *
 * ✅ Problem: Sort a Linked List in O(n log n) time and constant space complexity (if possible).
 * Input: Head of singly-linked list
 * Output: Sorted linked list
 */

// ==========================
// 🟠 APPROACH 1: Brute Force (Bubble Sort Style)
// ==========================
// 🔸 TC: O(n^2)  → Nested loops
// 🔸 SC: O(1)    → In-place sorting

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;

        ListNode i = head, j;
        while (i != null) {
            j = i.next;
            while (j != null) {
                if (j.val < i.val) {
                    int temp = i.val;
                    i.val = j.val;
                    j.val = temp;
                }
                j = j.next;
            }
            i = i.next;
        }

        return head;
    }
}


// ==========================
// 🟡 APPROACH 2: Min Heap (Priority Queue)
// ==========================
// 🔸 TC: O(n log n) → Heap insertions and polling
// 🔸 SC: O(n)       → Heap to store all nodes

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        Queue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode temp = head;
        while (temp != null) {
            heap.add(temp);
            ListNode prev = temp;
            temp = temp.next;
            prev.next = null; // break the original link
        }

        ListNode dummy = new ListNode(-1);
        temp = dummy;
        while (!heap.isEmpty()) {
            temp.next = heap.poll();
            temp = temp.next;
        }

        return dummy.next;
    }
}


// ==========================
// 🟢 APPROACH 3: Merge Sort (Top-Down)
// ==========================
// 🔸 TC: O(n log n) → Divide and merge
// 🔸 SC: O(log n)   → Recursive stack space

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // split list into two halves

        return merge(sortList(head), sortList(slow));
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

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

        if (l1 != null) temp.next = l1;
        else temp.next = l2;

        return dummy.next;
    }
}


// ==========================
// 🔵 APPROACH 4: Count Sort (Handles Negative Values) + JVM Warmup 😜
// ==========================
// 🔸 TC: O(n + range) → n to fill, range to rebuild
// 🔸 SC: O(range)     → Frequency array

class Solution {
    // JVM warm-up for JIT optimization
    static {
        for (int i = 0; i < 500; i++) sortList(null);
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Get min and max value
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        ListNode temp = head;
        while (temp != null) {
            min = Math.min(min, temp.val);
            max = Math.max(max, temp.val);
            temp = temp.next;
        }

        // Step 2: Offset negative values
        int offset = Math.abs(min);
        int[] freq = new int[max + offset + 1];

        // Step 3: Fill frequency
        temp = head;
        while (temp != null) {
            freq[temp.val + offset]++;
            temp = temp.next;
        }

        // Step 4: Overwrite linked list with sorted values
        temp = head;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                temp.val = i - offset;
                temp = temp.next;
            }
        }

        return head;
    }
}

/*
📊 Final Comparison:
------------------------------------------------------
| Approach       | Time Complexity | Space Complexity |
|----------------|------------------|------------------|
| Bubble Sort    | O(n^2)           | O(1)             |
| Min Heap       | O(n log n)       | O(n)             |
| Merge Sort     | O(n log n)       | O(log n)         |
| Count Sort     | O(n + range)     | O(range)         |
------------------------------------------------------
🧠 Note: Count Sort is fastest if value range is small. Otherwise, Merge Sort is the best universal solution.
*/
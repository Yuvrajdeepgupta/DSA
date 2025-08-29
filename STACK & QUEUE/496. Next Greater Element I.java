// Question Link: https://leetcode.com/problems/next-greater-element-i/
// Solution Reference: https://youtu.be/8P3e34EgVyY?si=gpvnrsv-tFc-Imeg

//------------------------------------------------------------
// 📌 Problem Breakdown
//------------------------------------------------------------
// We are given two arrays nums1 and nums2.
// - nums1 is a subset of nums2.
// - For each element in nums1, we need to find the Next Greater Element (NGE) in nums2.
// - NGE means: the first element greater than the current element that appears to its right in nums2.
// If no such element exists, return -1.
//
// Example:
// nums1 = [4,1,2], nums2 = [1,3,4,2]
// Answer = [-1,3,-1]
// -> For 4: No greater element to its right → -1
// -> For 1: Next greater is 3 → 3
// -> For 2: No greater element → -1
//
//------------------------------------------------------------
// 💡 Intuition
//------------------------------------------------------------
// 1. Brute Force → For each element of nums1, search in nums2 and check right side for next greater.
//    - Very slow (O(n^2)).
// 2. Optimized → Use **Monotonic Stack** + **HashMap**.
//    - Traverse nums2 from right to left.
//    - Maintain a stack such that top always stores "next greater element candidates".
//    - Pop elements from stack until we find a bigger element for nums2[i].
//    - Store mapping {nums2[i] → next greater} in a HashMap.
//    - Finally, use this map to fill answers for nums1.
//
//------------------------------------------------------------
// ✅ Why Stack + Map?
//------------------------------------------------------------
// - Stack helps in finding the next greater in O(1) amortized per element.
// - HashMap helps in directly answering queries for nums1 in O(1).
//
//------------------------------------------------------------
// 🚀 Code
//------------------------------------------------------------
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) 
    {
        int n = nums2.length;
        int ans[] = new int[nums1.length];

        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        // Traverse nums2 from right to left
        for (int i = n - 1; i >= 0; i--) 
        {
            int curr = nums2[i];

            // Pop all smaller/equal elements
            while (!stack.isEmpty() && stack.peek() <= curr) 
            {
                stack.pop();
            }

            // If stack is empty → no greater element exists
            map.put(curr, stack.isEmpty() ? -1 : stack.peek());

            // Push current element into stack
            stack.push(curr);
        }

        // Fill answers for nums1 using map
        for (int i = 0; i < nums1.length; i++) 
        {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}

//------------------------------------------------------------
// ⏱️ Time Complexity
//------------------------------------------------------------
// - Traversing nums2 → O(n)
// - Each element is pushed & popped at most once → O(n)
// - Filling result for nums1 → O(m) where m = nums1.length
// Total: O(n + m)
//
//------------------------------------------------------------
// 💾 Space Complexity
//------------------------------------------------------------
// - Stack → O(n)
// - HashMap → O(n)
// - Answer array → O(m)
// Total: O(n + m)
//
//------------------------------------------------------------
// 🔑 Summary (Yuvraj Way 😎)
//------------------------------------------------------------
// 1. Use monotonic decreasing stack while traversing nums2 from right.
// 2. Store "next greater" for each element in HashMap.
// 3. Directly answer nums1 queries using the map.
// 4. Time efficient (O(n + m)), avoids brute force scanning.
//```

---

Do you also want me to make the **example dry run** (step-by-step stack changes) inside the template for extra clarity, or keep it clean like above?

/*
Ques link: https://leetcode.com/problems/count-bowl-subarrays/description/
Sol link 1: https://youtu.be/hFSbYJwnksE?si=Wkfa9mss9UjJY8sI
Sol  link  2: https://leetcode.com/problems/count-bowl-subarrays/solutions/7163353/java-stack-previous-greater-next-greater/

Problem:
Given an integer array nums with distinct elements. A subarray is called a bowl if:
1. Its length is at least 3.
2. The minimum of its two ends is strictly greater than the maximum of all elements in between.
Return the number of bowl subarrays in nums.

-------------------------
✔ Solution 1 – Optimal Monotonic Stack Approach
-------------------------

Intuition:
- Use a monotonic stack to efficiently find where a subarray can form a bowl.
- We track indices of elements in the stack and when popping, check if the subarray length is at least 3.
- This ensures every valid subarray is counted exactly once in O(n) time.

Code:
*/

class Solution {
    public long bowlSubarrays(int[] nums) {
        long max = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < nums.length; i++) {
            int right = nums[i];

            while(!stack.isEmpty() && nums[stack.peek()] <= right) {
                int left = stack.pop();

                if(i - left + 1 >= 3) {
                    max++;
                }
            }

            if(!stack.isEmpty() && i - stack.peek() + 1 >= 3) {
                max++;
            }

            stack.push(i);
        }

        return max;
    }
}

/*
Example walkthrough:
Input: nums = [2, 5, 3, 1, 4]
- Index 1 (5): forms a bowl with subarray [5,3,1,4], count incremented.
- Index 2 (3): forms a bowl with [3,1,4], count incremented.
Final output = 2.

Time Complexity:
✔ O(n), because each element is pushed and popped from the stack at most once.

Space Complexity:
✔ O(n), because the stack stores indices.

Final Notes:
✔ This is the optimal approach using a stack.
✔ It avoids checking all subarrays by smartly tracking possible bowl ends.
✔ TC is linear and SC is linear too.
✔ This should be your go-to solution when efficiency is needed.

-------------------------
✔ Solution 2 – Using Next Greater and Previous Greater Arrays
-------------------------

Intuition:
- Precompute for each index the next greater and previous greater element indices.
- Then, for each index, check if using these next or previous greater elements forms a bowl of length at least 3.
- It’s efficient and easy to understand using stacks to compute next/previous greater elements.

Code:
*/

class Solution {
    public long bowlSubarrays(int[] nums) {
        long count = 0;
        
        // Find next greater elements
        int[] nextgreater = nextgreaterelement(nums);
        
        // Find previous greater elements
        int[] prevgreater = prevgreaterelement(nums);

        // Traverse using next greater
        for(int i = 0; i < nums.length; i++) {
            int next = nextgreater[i];
            if(next != -1 && next - i + 1 >= 3) {
                count++;
            }
        }

        // Traverse using previous greater
        for(int i = 0; i < nums.length; i++) {
            int prev = prevgreater[i];
            if(prev != -1 && i - prev + 1 >= 3) {
                count++;
            }
        }

        return count;
    }

    public int[] nextgreaterelement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--) {
            int curr = nums[i];
            while(!stack.isEmpty() && nums[stack.peek()] <= curr) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return res;
    }

    public int[] prevgreaterelement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            while(!stack.isEmpty() && nums[stack.peek()] <= curr) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return res;
    }
}

/*
Example walkthrough:
Input: nums = [2, 5, 3, 1, 4]
- For index 1 (5), the next greater is -1, but previous greater is index 0 (2), so using prevgreater it forms a valid bowl.
- For index 2 (3), the next greater is index 4 (4), forming a valid bowl.
Final output = 2.

Time Complexity:
✔ O(n), because each element is pushed and popped once while computing next/prev greater arrays.

Space Complexity:
✔ O(n), because of the stack and result arrays used.

Final Notes:
✔ This approach is easy to understand and implement.
✔ It precomputes information to avoid redundant checks.
✔ TC and SC are linear, making it efficient for large inputs.

-------------------------
✔ Final Summary (Yuvraj style)
-------------------------

- Both solutions use stacks in clever ways.
- The optimal solution directly counts using a monotonic stack.
- The second solution precomputes next and previous greater elements and then counts valid subarrays.
- Both solutions are O(n) time and space, which is excellent for constraints up to 10^5.
- Use the optimal solution when time matters, but the second solution is easier to understand and debug.
- Keep this template in your notes — it covers intuition, examples, complexity, and implementation fully!

*/

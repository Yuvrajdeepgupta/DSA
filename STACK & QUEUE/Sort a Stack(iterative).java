//que link- https://www.geeksforgeeks.org/problems/sort-a-stack/1
//sol link-https://youtu.be/MOGBRkkOhkY?si=yRRmgjXwEDpLAVG-
/************************************************************************************/


/*
 * Problem: Sort a Stack using Another Stack (GFG)
 * Language: Java
 * 
 * ✅ Intuition (Yuvraj Way):
 * We use the concept of insertion sort.
 * We'll take elements from the input stack and insert them one by one into a new stack (`stack1`)
 * such that `stack1` remains sorted at all times.
 * To insert elements at the right place, we use a temporary stack (`stack2`) to hold bigger elements.
 * 
 * Final stack (stack1) will be sorted in ascending order (smallest at bottom, largest at top).
 * 
 * 🔁 Steps:
 * 1. Pop top of input stack → store in curr
 * 2. While stack1.top > curr → move elements to stack2
 * 3. Push curr into stack2
 * 4. Push everything from stack2 back to stack1
 * 5. Repeat until input stack is empty
 * 
 * ⏱️ Time Complexity: O(n^2)
 * 🧠 Space Complexity: O(n) → uses two extra stacks
 */

class GfG 
{
    public Stack<Integer> sort(Stack<Integer> s) 
    {
        Stack<Integer> stack1 = new Stack<>();         // Final sorted stack
        Deque<Integer> stack2 = new ArrayDeque<>();    // Temporary stack

        while (!s.isEmpty()) 
        {
            int curr = s.pop();  // Take the top element from the input stack

            // Move elements from stack1 to stack2 if they are greater than curr
            while (!stack1.isEmpty() && stack1.peek() > curr) 
            {
                stack2.push(stack1.pop());
            }

            // Insert curr into its correct position
            stack2.push(curr);

            // Move everything back from stack2 to stack1
            while (!stack2.isEmpty()) 
            {
                stack1.push(stack2.pop());
            }
        }

        return stack1;  // This stack is now sorted
    }
}

//que link - https://www.geeksforgeeks.org/problems/sort-a-stack/1
//sol link - https://youtu.be/MOGBRkkOhkY?si=rvnQVlR5YPpf9ItP

/****************************************************************************************/

/*
 * Problem: Sort a Stack using Recursion (GFG)
 * Language: Java
 * 
 * ✅ Intuition (Yuvraj Way):
 * - This approach uses recursion to sort the stack without using any built-in sorting.
 * - The idea is:
 *   1. Pop the top element (currtop)
 *   2. Recursively sort the remaining stack
 *   3. Insert currtop into its correct sorted position using another temporary stack
 * - Think of it like recursion handling the top-to-bottom sorting one element at a time.
 * 
 * 🔁 Steps:
 * 1. If stack has 0 or 1 element → it's already sorted (base case)
 * 2. Else pop top → sort remaining → insert top back in correct position
 * 3. Use another stack to shift elements while inserting
 * 
 * ⏱️ Time Complexity: O(n^2)
 * 🧠 Space Complexity: O(n) (recursive stack + temp stack)
 */

class GfG 
{
    public Stack<Integer> sort(Stack<Integer> s) 
    {
        // Base case: empty or single element stack is already sorted
        if (s.size() <= 1)
        {
            return s;
        }

        // Step 1: Pop the top element
        int currtop = s.pop();

        // Step 2: Recursively sort the remaining stack
        sort(s);

        // Step 3: Insert currtop in its correct position in sorted stack
        Deque<Integer> stack = new ArrayDeque<>();

        // Move elements greater than currtop into temporary stack
        while (!s.isEmpty() && s.peek() > currtop) 
        {
            stack.push(s.pop());
        }

        // Push the current top element into correct position
        s.push(currtop);

        // Move back elements from temporary stack
        while (!stack.isEmpty()) 
        {
            s.push(stack.pop());
        }

        return s;  // Final sorted stack
    }
}


/*
Intuition:
- Use recursion to sort all elements except top.
- Insert top in correct position using stack-shifting logic.
- Works like insertion sort but through recursion.

Base Case:
- If 0 or 1 element → already sorted.

Time: O(n^2)
Space: O(n)
*/

/*
🔗 Question: https://www.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/1
🎥 Solution: https://youtu.be/oCcUNRMl7dA?si=3CTAR53atyUy3Aq0

🧠 Problem:
You are given a stack. Your task is to delete the middle element of it.
Middle is defined as ⌊(size - 1)/2⌋ (0-based indexing).
Use only standard stack operations (push, pop, etc).

------------------------------------------------------------
✅ Intuition:

- Stack is LIFO → no direct access to middle.
- Use recursion to reach the middle element.
- Once there, skip it (i.e., don't push it back).
- During backtracking, push all other elements back.

🧠 Iterative is allowed too using Stack.remove(index) (Java feature).

------------------------------------------------------------
🛠️ Approaches:

🔹 1. ✅ Iterative One-liner using Java Stack:
- Java Stack inherits from Vector, so we can use `.remove(index)` directly.

🔹 2. ✅ Recursive (Best for interviews):
- Recursively pop elements till mid.
- Pop the middle one.
- Push the others back in same order.

------------------------------------------------------------
🧪 Dry Run:
Stack: [1, 2, 3, 4, 5]  (top = 5)
Size = 5 → mid = 2

Recursive steps:
- pop 5 → call remove(index = 1)
- pop 4 → call remove(index = 0)
- pop 3 → now index == 0 → skip pushing it back
- push 4
- push 5

Resulting Stack: [1, 2, 4, 5]

------------------------------------------------------------
✅ Java Code:
*/

// ✅ Iterative Approach
class Solution {
    public void deleteMid(Stack<Integer> s) {
        s.remove((int)(Math.floor((s.size() - 1) / 2)));
    }
}

// ✅ Recursive Approach
class Solution {
    public void deleteMid(Stack<Integer> s) {
        if (s.size() <= 0) return;
        int index = s.size() / 2;
        remove(s, index);
    }

    public void remove(Stack<Integer> s, int index) {
        if (index == 0) {
            s.pop(); // delete mid
            return;
        }
        int store = s.pop();
        remove(s, index - 1);
        s.push(store); // push back other elements
    }
}

------------------------------------------------------------
⏱️ Time Complexity:

🔸 Recursive: O(N) — Each element is popped and pushed once.
🔸 Iterative: O(N) — `Stack.remove(index)` is O(N) since it shifts elements internally.

🧠 Why O(N)? Because we have to reach the mid, and restore the stack.

------------------------------------------------------------
📦 Space Complexity:

🔸 Recursive: O(N) — Due to recursion stack.
🔸 Iterative: O(1) — No extra space (removal is in-place).

------------------------------------------------------------
👑 Summary (Yuvraj Style):

// Mid delete in stack → no direct access → recursion se reach mid → skip mid → backtrack and restore stack
// Java allows remove(index) → works in 1 line (but not usable in interviews with strict rules)


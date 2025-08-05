# Problem Link:
https://leetcode.com/problems/fruits-into-baskets-ii/description/?envType=daily-question&envId=2025-08-05

# Solution Video Link:
https://youtu.be/Ydrwd7ro8po?si=loRzHKpfV78vGizy

---------------------------------------------------------------------------------------------------
## Problem Statement:
We are given two arrays:
- fruits[i] → quantity of the i-th type of fruit.
- baskets[j] → capacity of the j-th basket.

Rules for placing fruits (from left to right):
1. Each fruit type must be placed in the **leftmost available basket** with capacity >= fruit quantity.
2. Each basket can hold only **one type** of fruit.
3. If a fruit cannot be placed in any basket, it remains **unplaced**.

Return the number of **unplaced fruit types**.

---------------------------------------------------------------------------------------------------
## Example 1:
Input:
fruits = [4, 2, 5]
baskets = [3, 5, 4]

Output:
1

Explanation:
- fruit 4 → placed in basket 5 (index 1)
- fruit 2 → placed in basket 3 (index 0)
- fruit 5 → cannot be placed (remaining basket has 4 capacity)

---------------------------------------------------------------------------------------------------
## Constraints:
- n == fruits.length == baskets.length
- 1 <= n <= 100
- 1 <= fruits[i], baskets[i] <= 10^9

---------------------------------------------------------------------------------------------------
## Approaches Discussed:

---------------------------------------------------------------------------------------------------
### 1. Brute Force Approach
**Idea:**
- For each fruit, scan baskets from left to right.
- If basket capacity >= fruit quantity, place fruit there and mark basket as used.
- Continue until all fruits are processed.

**Code:**
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int count = 0;
        for (int curr : fruits) {
            for (int j = 0; j < baskets.length; j++) {
                if (baskets[j] >= curr) {
                    count++;
                    baskets[j] = -100; // mark as used
                    break;
                }
            }
        }
        return fruits.length - count;
    }
}

**Time Complexity:** O(n^2)  
**Space Complexity:** O(1)

---------------------------------------------------------------------------------------------------
### 2. Stack-based Simulation (Iterative)
**Idea:**
- Push baskets into a stack in reverse order so that the leftmost basket is at the top.
- For each fruit:
    - Pop smaller baskets until we find a suitable one.
    - Place fruit by popping the suitable basket.
    - Push back skipped baskets.

**Code:**
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(baskets[i]);
        }

        Deque<Integer> temp = new ArrayDeque<>();
        for (int curr : fruits) {
            while (!stack.isEmpty() && stack.peek() < curr) {
                temp.push(stack.pop());
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }
        return stack.size();
    }
}

**Time Complexity:** O(n^2) worst-case  
**Space Complexity:** O(n) for stacks

---------------------------------------------------------------------------------------------------
### 3. Stack-based Simulation (Recursive)
**Idea:**
- Similar to iterative stack but uses recursion to skip and restore baskets.

**Code:**
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(baskets[i]);
        }

        for (int curr : fruits) {
            remove(stack, curr);
        }
        return stack.size();
    }

    private void remove(Deque<Integer> stack, int curr) {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.peek() >= curr) {
            stack.pop();
            return;
        } else {
            int temp = stack.pop();
            remove(stack, curr);
            stack.push(temp);
            return;
        }
    }
}

**Time Complexity:** O(n^2) worst-case  
**Space Complexity:** O(n) recursion depth + stack

---------------------------------------------------------------------------------------------------
### 4. Optimal Approach → Square Root Decomposition
**Why Square Root Decomposition?**
- Brute force is slow for large n.
- We divide baskets into blocks of size ~√n.
- Store max capacity in each block (`sectors[]`).
- For each fruit:
    1. Check sectors from left to right.
    2. When a block’s max >= fruit size → search inside that block for first fitting basket.
    3. Place fruit, set basket capacity to 0, and update block max.
    4. Break after placing to maintain left-to-right rule.

**Code:**
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;
        int a = (int) Math.ceil(Math.sqrt(n));

        int[] sectors = new int[(n + a - 1) / a];
        fill(sectors, baskets, a);

        int placed = 0;
        for (int curr : fruits) {
            int k = 0;
            for (int i = 0; i < n; i += a) {
                if (sectors[k] >= curr) {
                    for (int j = i; j < Math.min(i + a, n); j++) {
                        if (baskets[j] >= curr) {
                            baskets[j] = 0; // mark used
                            updateBlock(sectors, baskets, a, k);
                            placed++;
                            break;
                        }
                    }
                    break;
                }
                k++;
            }
        }
        return n - placed;
    }

    private void fill(int[] sectors, int[] baskets, int a) {
        int n = baskets.length;
        int k = 0;
        for (int i = 0; i < n; i += a) {
            int max = 0;
            for (int j = i; j < Math.min(i + a, n); j++) {
                max = Math.max(max, baskets[j]);
            }
            sectors[k++] = max;
        }
    }

    private void updateBlock(int[] sectors, int[] baskets, int a, int blockIndex) {
        int n = baskets.length;
        int start = blockIndex * a;
        int end = Math.min(start + a, n);
        int max = 0;
        for (int j = start; j < end; j++) {
            max = Math.max(max, baskets[j]);
        }
        sectors[blockIndex] = max;
    }
}

**Time Complexity:** O(n * √n)  
**Space Complexity:** O(√n) for `sectors[]`

---------------------------------------------------------------------------------------------------
## Yuvraj Way Summary:
Bhai, brute force mein har fruit ke liye poore baskets scan karte ho — O(n^2) lagta hai.  
Stack approach se left-to-right order maintain hota hai but still O(n^2) worst case.  
Square root decomposition mein baskets ko blocks (~√n size) mein tod ke har block ka max store kiya, pehle block level pe check kiya phir block ke andar ghus ke basket search kiya. Basket use hote hi block ka max update kiya.  
Result — har fruit ka processing O(√n) mein ho gaya. Mast tezi mil gayi.

---------------------------------------------------------------------------------------------------

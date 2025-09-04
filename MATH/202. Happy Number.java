LeetCode Problem: Happy Number
Question Link: https://leetcode.com/problems/happy-number/description/
Solution Reference Links: 
1. https://youtu.be/ocDwEjRVDAk?si=WZquREOkoHmquqcY
2. https://youtu.be/LkD0D0Xy-ro?si=7JvqpI03ekC84cA2

--------------------------------------------------------------------------------
Problem Breakdown:
- We are given a number n.
- Replace the number by the sum of the squares of its digits.
- Repeat this process until:
   -> It becomes 1 (then it is a happy number ✅).
   -> It loops endlessly without reaching 1 (not a happy number ❌).
- Task: Return true if n is a happy number, else false.

--------------------------------------------------------------------------------
Intuition:
- Every number either:
   1. Falls into a cycle (never reaching 1).
   2. Eventually reaches 1.
- Example: n=19
   1²+9² = 82
   8²+2² = 68
   6²+8² = 100
   1²+0²+0² = 1  ✅ Happy Number
- Example: n=2
   2²=4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4... (cycle ❌)

So, problem reduces to detecting if we reach 1 or fall into a cycle.

--------------------------------------------------------------------------------
Approach 1: Using Set (Cycle detection with memory)
- Keep track of already seen sums in a HashSet.
- If sum == 1 → return true.
- If sum repeats (already in set) → cycle detected → return false.

Code:
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                int last = n % 10;
                sum += (last * last);
                n /= 10;
            }
            if (set.contains(sum)) return false;
            set.add(sum);
            n = sum;
        }
        return true;
    }
}

Time Complexity: O(log n)  
Space Complexity: O(log n)  

--------------------------------------------------------------------------------
Approach 2: Without Set (Observation of single-digit ending)
- Any number eventually becomes a single-digit sum.
- Only 1 and 7 are "happy single-digits".
- If final single-digit is 1 or 7 → happy.
- Else not happy.

Code:
class Solution {
    public boolean isHappy(int n) {
        while (n > 9) {
            int sum = 0;
            while (n > 0) {
                int last = n % 10;
                sum += (last * last);
                n /= 10;
            }
            n = sum;
        }
        return (n == 1 || n == 7);
    }
}

Time Complexity: O(log n)  
Space Complexity: O(1)  

--------------------------------------------------------------------------------
Approach 3: Recursive Without Set
- Base case:
   -> If n == 1 or n == 7 → return true.
   -> If n is single-digit and not 1 or 7 → return false.
- Otherwise keep recursing with sum of squares.

Code:
class Solution {
    public boolean isHappy(int n) {
        if (n == 1 || n == 7) return true;
        else if (n > 0 && n < 10) return false;

        int sum = 0;
        while (n > 0) {
            int last = n % 10;
            sum += (last * last);
            n /= 10;
        }
        return isHappy(sum);
    }
}

Time Complexity: O(log n)  
Space Complexity: O(log n) (due to recursion stack)  

--------------------------------------------------------------------------------
Approach 4: Optimal (Floyd’s Cycle Detection / Tortoise-Hare)
- Idea: Detect cycle without using extra space.
- Use slow pointer (1 step at a time) and fast pointer (2 steps).
- If fast == 1 → happy.
- If slow == fast (not 1) → cycle → not happy.

Code:
class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = sum(slow);          // move 1 step
            fast = sum(sum(fast));     // move 2 steps
            if (fast == 1) return true;
        } while (slow != fast);
        return false; // cycle detected
    }

    public int sum(int n) {
        int sum = 0;
        while (n > 0) {
            int last = n % 10;
            sum += last * last;
            n /= 10;
        }
        return sum;
    }
}

Time Complexity: O(log n)  
Space Complexity: O(1)  

--------------------------------------------------------------------------------
Dry Run Examples:
Example 1: n=19
19 → 1²+9² = 82  
82 → 8²+2² = 68  
68 → 6²+8² = 100  
100 → 1²+0²+0² = 1 → ✅ Happy Number

Example 2: n=2
2 → 2²=4  
4 → 16  
16 → 1²+6²=37  
37 → 58 → 89 → 145 → 42 → 20 → 4 → cycle ❌ Not Happy Number

--------------------------------------------------------------------------------
Final Comparison:
1. Set Method → Easy to understand, but extra space.
2. Without Set (Observation) → Smart trick, O(1) space.
3. Recursive → Clean, but recursion depth.
4. Floyd’s Cycle Detection → Most optimal (O(log n), O(1)).

--------------------------------------------------------------------------------
Yuvraj Style Summary:
- Happy number is about repeatedly summing squares of digits until 1 or cycle.
- 4 ways to solve:
   → Using Set: detect repeats.
   → Without Set: only 1 or 7 survive.
   → Recursive: neat but stack usage.
   → Optimal Floyd’s: slow-fast cycle detection (best one).
- Choose Floyd’s in interviews for optimality.
- TC ~ O(log n), SC depends on method (best is O(1)).

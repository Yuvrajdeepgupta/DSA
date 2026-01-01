LeetCode: Plus One
Link: https://leetcode.com/problems/plus-one/
Video Sol: https://youtu.be/mStAXMDK6xU?si=EzEck9zxEvLhVrzq

====================================================
PROBLEM OVERVIEW (FOR THE READER)
====================================================
The reader is given an integer array `digits` where:
- Each element represents a single digit (0–9)
- The array represents a non-negative number
- The most significant digit is at index 0

The task is to **add 1 to the number** and return the resulting number as an array.

Examples:
[1,2,3] → [1,2,4]  
[1,2,9] → [1,3,0]  
[9,9,9] → [1,0,0,0]

====================================================
CORE INTUITION (GUPTA JI STYLE)
====================================================
While reading this problem, the reader should think very normally:

- Addition always starts from the **last digit**
- If the last digit is **less than 9**, just add 1 → work done
- If the digit is **9**, it becomes 0 and produces a carry
- That carry may keep moving left
- Worst case: all digits are 9 → number length increases

Basically, this is **manual addition**, the same way we do it on paper.

====================================================
APPROACH 1 – CARRY + LIST (AUTHOR’S SOLUTION)
====================================================
In this approach, the author solves the problem step-by-step:

Thinking:
- Start with `carry = 1` because we want to add 1
- Traverse digits from right to left
- If digit is not 9 → add carry and stop carry
- If digit is 9 → it becomes 0 if carry exists
- Store all digits in a list (reverse order)
- If carry still remains at the end → add extra 1
- Reverse the list to form the final array

Why this approach makes sense:
- Carry handling is very clear
- Edge cases like `[9,9,9]` are handled safely
- Easy to explain in exams or interviews

----------------------------------------------------
CODE (APPROACH 1)
----------------------------------------------------
class Solution 
{
    public int[] plusOne(int[] digits) 
    {
        List<Integer> list=new ArrayList<>();

        int carry = 1;
        for(int i=digits.length-1;i>=0;i--)
        {
            if(digits[i]!=9)
            {
                list.add(digits[i]+carry);
                carry=0;
            }
            else
            {
                if(carry!=1)
                {
                    list.add(digits[i]+carry);
                }
                else
                {
                    list.add(0);
                }
            }
        }

        if(carry==1){list.add(1);}

        return convert(list);
    }

    public int[] convert(List<Integer> list)
    {
        int n=list.size();
        int arr[]=new int[n];

        for(int i=0;i<arr.length;i++)
        {
            arr[i]=list.get(n-i-1);
        }

        return arr;
    }
}

----------------------------------------------------
TIME & SPACE COMPLEXITY
----------------------------------------------------
Time Complexity: O(n)  
Space Complexity: O(n) (extra list used)

====================================================
APPROACH 2 – OPTIMAL IN-PLACE SOLUTION
====================================================
Now the reader should notice a smarter observation:

- If a digit is **less than 9**, no carry will be generated
- So we can increment it and **return immediately**
- Only when digit is 9 do we set it to 0 and continue
- If all digits were 9 → create a new array with leading 1

Why this is optimal:
- No extra data structures
- Early exit in most cases
- Cleaner and more interview-friendly

----------------------------------------------------
CODE (OPTIMAL SOLUTION)
----------------------------------------------------
class Solution 
{
    public int[] plusOne(int[] digits) 
    {
       int n=digits.length;

       for(int i=digits.length-1;i>=0;i--)
       {
            if(digits[i]<9)
            {
                digits[i]++;
                return digits;
            }
            
            digits[i]=0;
       }

       int ans[]=new int[n+1];
       ans[0]=1;
       return ans;
    }
}

----------------------------------------------------
TIME & SPACE COMPLEXITY
----------------------------------------------------
Time Complexity: O(n)  
Space Complexity: O(1) (output array excluded)

====================================================
YUVRAJ STYLE SUMMARY (MUST READ)
====================================================
- Problem is pure **carry handling**
- First solution is **clear and beginner-friendly**
- Second solution is **interview-optimal**
- Always try in-place first, but explain carry logic clearly
- Best interview flow:
  → explain manual addition
  → show carry propagation
  → optimize to in-place solution

Simple logic, clean code, and strong fundamentals — **Gupta Ji approved** ✅

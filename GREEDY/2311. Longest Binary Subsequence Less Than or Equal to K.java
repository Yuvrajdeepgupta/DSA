//link - https://youtu.be/ca04hcfuGlY?si=24863i9mRCvlqYs_

/*
💡 INTUITION:
We want to find the longest subsequence of a binary string such that 
the decimal value of that subsequence is <= k.

👉 We traverse the string from right to left because:
   - The rightmost bit is the least significant bit (LSB), 
     and smaller bits affect the decimal value less.
   - This way, we can greedily add bits without quickly exceeding k.

📚 NEW CONCEPTS / IMPORTANT IDEAS:
- Bit manipulation: using (1L << bit) to represent powers of 2.
- Subsequence: We can skip characters, but order must be maintained.
- Greedy approach: Always include '0', only include '1' if it keeps value <= k.
- Use of `long` to handle large values safely (avoid overflow).

🧠 EXPLANATION:
1. Start from the right end of the string.
2. For every character:
   - If it's '0', always include it (it doesn't increase the value).
   - If it's '1':
     - Compute how much it adds using (1L << current bit position).
     - Add it only if total value stays within k.
3. Keep track of how many bits you’ve added using a `bit` counter.
4. Return the total count of selected characters.
*/


//O(N^2) SOL
class Solution {
    public int longestSubsequence(String str, int k) 
    {
        char s[]=str.toCharArray();
        int n=s.length;
        StringBuilder ans=new StringBuilder();
        boolean flag=true;
        for(int i=n-1;i>=0;i--)
        {
            if(s[i]=='0')
            {
                ans.insert(0,'0');
            }
            else if(s[i]=='1' && flag)
            {
                if(check(ans,k))
                {
                    ans.insert(0,'1');
                }
                else
                {
                    flag=false;
                }
            }
        }

        return ans.length();
    }

  public boolean check(StringBuilder binaryStr, int k)
{
    // Step 1: Create new StringBuilder and prepend '1'
    StringBuilder modified = new StringBuilder();
    modified.append('1');
    modified.append(binaryStr);

    // Step 2: Convert to BigInteger (base 2)
    java.math.BigInteger decimalValue = new java.math.BigInteger(modified.toString(), 2);

    // Step 3: Compare with k
    return decimalValue.compareTo(java.math.BigInteger.valueOf(k)) <= 0;
}

}


//Optimal one
class Solution 
{
    public int longestSubsequence(String s, int k) 
    {
        int n=s.length();
        int count=0;
        for(int i=n-1;i>=0;i--)
        {
            char ch=s.charAt(i);
            if(ch=='0')
            {
                count++;
            }
            else  
            {   
                //if that particular number <= k
                //exponent will go like 0 1 2 3 ...
                int num= 1 * (int)Math.pow(2,(n-1)-i); //fetching that number
                if(num<=k)
                {
                    k-=num;
                    count++;
                }
            }
        }

        return count;
    }
}

//same as optimal bas good for big inputs
class Solution 
{
    public int longestSubsequence(String s, int k) 
    {
        int n=s.length();
        int count=0;
        for(int i=n-1;i>=0;i--)
        {
            char ch=s.charAt(i);
            if(ch=='0')
            {
                count++;
            }
            else  
            {   
                //if that particular number <= k
                //exponent will go like 0 1 2 3 ...
                double num= 1 * (int)Math.pow(2,(n-1)-i); //fetching that number
                if(num<=k)
                {
                    k-=num;
                    count++;
                }
            }
        }

        return count;
    }
}

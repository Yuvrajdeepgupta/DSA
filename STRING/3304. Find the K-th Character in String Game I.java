//que link- https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/description/?envType=daily-question&envId=2025-07-03
//sol link- https://www.youtube.com/watch?v=YIbkt9kDXJY&ab_channel=codestorywithMIK

//STRING BUILDER SOL USING TC- o(K) SC: o(K)

class Solution {
    public char kthCharacter(int k) {
        int idx = k - 1;
        StringBuilder result = new StringBuilder();
        result.append('a');

        while(result.length() < k) {
            int n = result.length();
            for(int i = 0; i < n; i++) {
                char ch = result.charAt(i) == 'z' ? 'a' : (char)(result.charAt(i) + 1);
                result.append(ch);
            }
        }

        return result.charAt(idx);
    }
}

//BETTER SOL
class Solution {
    public char kthCharacter(int k) 
    {
        StringBuilder store=new StringBuilder();

        StringBuilder ans=new StringBuilder();
        ans.append("a");

        int i=0;
        while(ans.length()<k)
        {
            char curr=ans.charAt(i++);
            char toappend=(char)((((curr-'a')+1)%26)+'a');
            
            store.append(toappend);
            if(i>=ans.length())ans.append(store);
        }
        
        return ans.charAt(k-1);
    }
}


//OPTIMIZED AND BEST ONE
class Solution {
    public char kthCharacter(int k) 
    {
        return (char)('a'+Integer.bitCount(k-1));
    }
}

// This works because:
// We're mapping k-th position to number of 1s in binary of (k-1)
// That number tells how deep/complex the pattern is at that point
// So we do: char = 'a' + set bits → gives us 'a', 'b', 'c', etc.

// Used in magical string or recursive pattern questions
// Fast → No need to build full string, just calculate based on index


// Approach 1: Build full string step-by-step using SB (O(K) time + space)
// Approach 2: Slightly optimized by building second half separately
// Approach 3: Genius trick → char = 'a' + set bits in (k-1)
// Why? Because each step follows a binary mirror +1 pattern

// Final Best: (O(1) Time)
//return (char)('a' + Integer.bitCount(k - 1));

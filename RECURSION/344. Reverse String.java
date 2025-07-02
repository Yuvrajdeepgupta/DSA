//Video link-https://youtu.be/V2xavUKCdyY?si=-P1d9hEaUJF3Z-Wx

public class String
{
	public static void main(String[] args) 
	{
		Reverse("abc", 0);
	}
	
	public static void Reverse(String str,int idx)
	{
		if(idx>=str.length()) {return;}
		
		Reverse(str,idx+1);
		System.out.print(str.charAt(idx));
	}
}

//INPUT- "ABC" OUTPUT-"CBA"

//Similar que on leetcode- https://leetcode.com/problems/reverse-string/
class Solution {
    public void reverseString(char[] s) 
    {
        helper(s,0,s.length-1);
    }

    private void helper(char[] s,int left,int right)
    {
        if(left>=right){return;}    //base case

        char temp=s[left];
        s[left]=s[right];       //swapping
        s[right]=temp;

        helper(s,left+1,right-1);   //calling the fn again
    }
}
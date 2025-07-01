//link - https://youtu.be/XpRSv1EFmhU?si=batrUsIk97VK6iK1


//Original sol- using freq array
class Solution 
{
    public int possibleStringCount(String word) 
    {
        int freq[]=new int[26];

        int n=word.length();
        for(int i=1;i<n;i++)
        {
            if(word.charAt(i)==word.charAt(i-1))
            {
                freq[word.charAt(i)-'a']++;
            }
        }

        int length=1;
        
        for(int i=0;i<26;i++)
        {
            if(freq[i]>0)
            {
                length+=freq[i];
            }
        }

        return length;
    }
}

//Optimized -O(n) sol
 class Solution 
{
    public int possibleStringCount(String word) 
    {
        int n=word.length();
        int ans=1;
        for(int i=1;i<n;i++)
        {
            if(word.charAt(i)==word.charAt(i-1))
            {
                ans++;
            }
        }

       return ans;
    }
}
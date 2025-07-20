//que link - https://leetcode.com/contest/biweekly-contest-161/problems/split-array-by-prime-indices/submissions/1703642270/
// video link - 

/*******************************************************************************************/
//brute force
class Solution 
{
    static boolean primes[];
	public static void buildSieve(int n) 
	{
        primes[0] = primes[1] = false;

        for(int i=2;i*i<=n;i++) 
        {
            if (primes[i]) 
            {
                for (int j=i*i;j<=n;j+=i) 
                {
                    primes[j]=false;
                }
            }
        }
    }
	
	public long splitArray(int[] nums) 
    {
        int n=nums.length;
        primes=new boolean[n+1];
        Arrays.fill(primes,true);
        
        buildSieve(n);
        
        long sum1=0,sum2=0;
        for(int i=0;i<n;i++)
        {
        	if(primes[i])
        	{
        		sum1+=nums[i];
        	}
        	else
        	{
        		sum2+=nums[i];
        	}
        }
        
        return Math.abs(sum1-sum2);
    }
}


// ✅ Khandani Template: Split Array Based on Prime Indices
// Problem: Given nums[], split elements into two groups:
// Group 1 → nums[i] where i is prime index
// Group 2 → nums[i] where i is not prime index
// Return: Absolute difference between sum1 and sum2

// ✅ Why use Sieve?
// - Because nums.length ≤ 10^5, and we are checking each index for primality
// - Normal isPrime() is O(√n) and will be too slow for 10^5 checks
// - Sieve builds in O(n log log n) once, then O(1) check per index
// - Best for repeated prime checks ✅

class Solution 
{
    static boolean[] primes;

    // ✅ Build Sieve of Eratosthenes up to n
    public static void buildSieve(int n) 
    {
        primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= n; i++) 
        {
            if (primes[i]) 
            {
                for (int j = i * i; j <= n; j += i) 
                {
                    primes[j] = false;
                }
            }
        }
    }

    public long splitArray(int[] nums) 
    {
        int n = nums.length;
        buildSieve(n);  // ⚡ Build prime index map once

        long sum1 = 0, sum2 = 0;

        for (int i = 0; i < n; i++) 
        {
            if (primes[i])
                sum1 += nums[i];  // 🟢 Prime index
            else
                sum2 += nums[i];  // 🔴 Non-prime index
        }

        return Math.abs(sum1 - sum2);
    }
}


/*
🔗 Question Link: https://leetcode.com/problems/combination-sum-ii/description/
🎥 Solution Link: https://youtu.be/bfKwLi6jtDk?si=AQXFmjssgOFu-adZ

------------------------------------------------
📝 Problem Breakdown:
We are given an array of candidates and a target. We need to find all unique combinations
where the chosen numbers sum up to the target. Each number in candidates can be used at most once.

Important points:
1. Each number can only be used once (not unlimited like Combination Sum I).
2. We must avoid duplicate combinations.
3. Order of numbers doesn’t matter, only the set matters.

------------------------------------------------
💡 Intuition:
- This is a classic backtracking problem.
- We sort the array to group duplicates together.
- While exploring, we skip duplicates (when arr[j] == arr[j-1]) to avoid repeated combinations.
- We keep track of current sum and current path (list).
- Once sum == target → we add the current path to the answer.
- If sum > target → backtrack immediately.

------------------------------------------------
⚙️ Why Sorting?:
- Sorting helps us easily skip duplicates.
- We can use `Arrays.sort(candidates)` normally.
- Here, I implemented a custom counting sort (O(N+K)) because constraints are small (candidates[i] ≤ 50).
- Both work fine, but counting sort is a small optimization in this case.

------------------------------------------------
✅ Code Implementation:
*/

class Solution 
{
    List<List<Integer>> list;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) 
    {
        sort(candidates); // custom counting sort (since values ≤ 50)
        list=new ArrayList<>();
        List<Integer> ans=new ArrayList<>();
        int i=0,sum=0;

        solve(i,sum,candidates,target,ans);
        return list;
    }

    public void solve(int i,int sum,int arr[],int target,List<Integer> ans)
    {
        if(sum==target)
        {
            list.add(new ArrayList<>(ans));
            return;
        }

        if(i>=arr.length || sum>target)
        {
            return;
        }

        for(int j=i;j<arr.length;j++)
        {
            // skip duplicates at same level
            if(j>i && arr[j]==arr[j-1]){continue;}
            int curr=arr[j];

            ans.add(curr);
            sum+=curr;
            solve(j+1,sum,arr,target,ans);
            ans.remove(ans.size()-1);
            sum-=curr;
        }
    }

    // Custom counting sort since candidates[i] ≤ 50
    public void sort(int arr[])
    {
        int freq[]=new int[51];
        for(int i:arr)
        {
            freq[i]++;
        }

        int i=0;
        for(int j=0;j<freq.length;j++)
        {
            while(freq[j]>0)
            {
                arr[i++]=j;
                freq[j]--;
            }
        }
    }
}

/*
------------------------------------------------
⏱️ Time Complexity:
- Sorting: O(N log N) if Arrays.sort() is used.  
- With counting sort (our case): O(N + K), K = 50 → O(N).
- Backtracking:
  In worst case, every number can either be picked or skipped → O(2^N).
- For each valid combination, copying list takes O(k) where k = avg length.
So overall: O(2^N * k).

📦 Space Complexity:
- Recursion depth: O(N) (stack).
- Answer list can hold many combinations in worst case.
So overall: O(N) auxiliary + output storage.

------------------------------------------------
📝 Yuvraj Style Summary:
So basically this is Combination Sum I’s stricter cousin 😅  
- Sort → skip duplicates easily.  
- Use backtracking → try pick/skip.  
- Stop if sum > target.  
- Add answer if sum == target.  
I did a little jugaad using counting sort since max element ≤ 50, but Arrays.sort() would also work.  
Done ✅
*/

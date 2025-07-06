//que link-https://leetcode.com/problems/finding-pairs-with-a-certain-sum/description/?envType=daily-question&envId=2025-07-06

//sol link-https://youtu.be/3yL6gQz4vTc?si=GK431mtSmBaTkVs4


//My solution - already clean and close to optimal

class FindSumPairs 
{
    //Map for nums1
    Map<Integer,Integer> freq;
    //HashMap<Element,Frequency> as nums2 can be large
    Map<Integer,Integer> map;
    //Having our own refrence for the Two Arrays
    int nums1[],nums2[];

    public FindSumPairs(int[] nums1, int[] nums2) 
    {
        // Now both point to same array
        // Any change on this.nums1 affects nums1 (and vice versa)
        this.nums1 = nums1;   //just another ref for the same array's
        this.nums2 = nums2;
        
        //Now initializing our map,freq arrays
        freq=new HashMap<>();
        map=new HashMap<>();

        //populate the data structures

        /*******First->Freq-Array********/
        for(int i:this.nums1)
        {
            freq.merge(i,1,Integer::sum);
        }

        /*******Second->HASHMAP********/
        for(int i:this.nums2)
        {
            map.merge(i,1,Integer::sum);
        }
    }
    
    public void add(int index, int val) 
    {
        int oldvalue=this.nums2[index]; //saving old value

        //updating
        this.nums2[index]+=val;

        int newvalue=this.nums2[index]; //saving new value

        //Modifying map 
        //for new value
        this.map.merge(newvalue,1,Integer::sum);

        //for old value
        this.map.compute(oldvalue,(key,value)->(value==null || value==1) ? null : value-1);
        
        //Summary of this fn-
        // change nums2[index] by val
        // update map: +1 for new value, -1 for old value
        // if old value freq becomes 0, remove it
    }
    
    public int count(int tot) 
    {
        int c=0;    //for count

        for(int i:freq.keySet())
        {
            //traversing over freq array and checking this condition
            if(this.map.containsKey(tot-i))
            {
                //if condition satisfy then add their frequency from freq and map
                c+=(freq.get(i)*map.get(tot-i));
            }
        }

        return c;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */

//IMPORTANT NOTE
// For FindSumPairs → use HashMap ✅

//Map<Integer, Integer> freq = new HashMap<>();

// LinkedHashMap? Only if order matters ❌
// TreeMap? Only if sorting matters ❌


/**********************************************************************************/

//optimal and clever one

class FindSumPairs {
    int[] nums1;
    int[] nums2;

    // freq maps
    Map<Integer,Integer> mp1;
    Map<Integer,Integer> mp2;
    // sorted distinct keys from nums1
    List<Integer> sortedKeys1;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;

        mp1 = new HashMap<>();
        mp2 = new HashMap<>();

        // build freq map for nums1
        for (int x : nums1) {
            mp1.put(x, mp1.getOrDefault(x, 0) + 1);
        }
        // cache & sort the distinct nums1 keys
        sortedKeys1 = new ArrayList<>(mp1.keySet());
        Collections.sort(sortedKeys1);

        // build freq map for nums2
        for (int x : nums2) {
            mp2.put(x, mp2.getOrDefault(x, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int oldVal = nums2[index];
        int newVal = oldVal + val;

        // decrement or remove oldVal
        int countOld = mp2.get(oldVal);
        if (countOld == 1) {
            mp2.remove(oldVal);
        } else {
            mp2.put(oldVal, countOld - 1);
        }

        // increment newVal
        mp2.put(newVal, mp2.getOrDefault(newVal, 0) + 1);

        // commit it to the array
        nums2[index] = newVal;
    }

    public int count(int tot) {
        int res = 0;
        // iterate sorted keys; break as soon as key > tot
        for (int k : sortedKeys1) {
            if (k > tot) break; 
            Integer c2 = mp2.get(tot - k);
            if (c2 != null) {
                res += mp1.get(k) * c2;
            }
        }
        return res;
    }
}

//summary
// mp1: freq map of nums1
// mp2: freq map of nums2
// sortedKeys1: cached sorted unique elements of nums1

// add(): update nums2[index], update mp2

// count(tot): loop sorted nums1 keys only till k > tot
// check if (tot - k) exists in mp2, then add product of freq

/* https://leetcode.com/problems/sum-of-sortable-integers/description/

You are given an integer array nums of length n.

An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially performing the following operations:

Partition nums into consecutive subarrays of length k.
Cyclically rotate each subarray independently any number of times to the left or to the right.
Return an integer denoting the sum of all possible sortable integers k.

 

Example 1:

Input: nums = [3,1,2]

Output: 3

Explanation:​​​​​​​

For n = 3, possible divisors are 1 and 3.
For k = 1: each subarray has one element. No rotation can sort the array.
For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
Only k = 3 is sortable. Hence, the answer is 3.
Example 2:

Input: nums = [7,6,5]

Output: 0

Explanation:

For n = 3, possible divisors are 1 and 3.
For k = 1: each subarray has one element. No rotation can sort the array.
For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
No k is sortable. Hence, the answer is 0.
Example 3:

Input: nums = [5,8]

Output: 3

Explanation:​​​​​​​

For n = 2, possible divisors are 1 and 2.
Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.
 
Constraints:
1 <= n == nums.length <= 105
1 <= nums[i] <= 105
*/

/*
Approach
FOR each k (divisor of n):
    FOR each block:
        check local validity (circular sorted)
        check global order with previous block
*/

/* 
Time Complexity
Finding divisors of n
Loop till √n
O(√n)
For each divisor k → calling check(nums, k)
Array is divided into n/k blocks
Each block calls verify() which scans k elements
So total per k:(n/k) * k = O(n)
Total over all divisors
Number of divisors ≈ O(√n) (worst case)
Each takes O(n)
Final Time Complexity: O(n * √n)
*/

class State {
    boolean sortPossible; // can this segment be made sorted (circularly)?
    int max; // max element in segment
    int min; // min element in segment

    State(boolean sortPossible, int max, int min) {
        this.sortPossible = sortPossible;
        this.min = min;
        this.max = max;
    }
}

class Solution {

    // Check if subarray [left, right] is "circularly sorted"
    // i.e., at most ONE breakpoint allowed
    private State verify(int[] nums, int left, int right) {
        int count = 0; // number of breakpoints (descending pairs)

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Traverse segment and count local breakpoints
        for (int i = left; i < right; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);

            // breakpoint: decreasing order
            if (nums[i] > nums[i + 1])
                count++;
        }

        // circular check: last → first
        // ensures cyclic sorted condition
        if (nums[right] > nums[left])
            count++;

        // include last element in min/max
        min = Math.min(min, nums[right]);
        max = Math.max(max, nums[right]);

        // valid only if ≤ 1 breakpoint
        boolean flag = (count <= 1);

        return new State(flag, max, min);
    }

    // Check if array can be divided into segments of size k
    // such that each segment is sortable AND segments are globally sorted
    private int check(int[] nums, int k) {
        int prevMin = Integer.MAX_VALUE;
        int prevMax = Integer.MIN_VALUE;

        // process each block of size k
        for (int i = 0; i <= nums.length - k; i += k) {

            // validate current segment
            State s = verify(nums, i, i + k - 1);

            int currMax = s.max;
            int currMin = s.min;

            // FAIL CONDITIONS:
            // 1. segment itself not sortable
            // 2. previous segment's max > current segment's min
            // → violates global sorted order
            if (!s.sortPossible || prevMax > currMin)
                return 0;

            // update for next segment comparison
            prevMax = currMax;
            prevMin = currMin;
        }

        // valid k contributes k to answer
        return k;
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // find all divisors of n → possible segment sizes
        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);

                // avoid duplicate when i*i == n
                if (i * i != n)
                    divisors.add(n / i);
            }
        }

        int sum = 0;

        // try each valid segment size k
        for (int k : divisors) {
            sum += check(nums, k);
        }

        return sum;
    }
}
/*
https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
You are given two arrays: deadline[], and profit[], which represent a set of jobs, where each job is associated with a deadline, and a profit. Each job takes 1 unit of time to complete, and only one job can be scheduled at a time. You will earn the profit associated with a job only if it is completed by its deadline.

Your task is to find:

The maximum number of jobs that can be completed within their deadlines.
The total maximum profit earned by completing those jobs.
Examples :

Input: deadline[] = [4, 1, 1, 1], profit[] = [20, 10, 40, 30]
Output: [2, 60]
Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).
Input: deadline[] = [2, 1, 2, 1, 1], profit[] = [100, 19, 27, 25, 15]
Output: [2, 127]
Explanation: Job1 and Job3 can be done with maximum profit of 127 (100+27).
Input: deadline[] = [3, 1, 2, 2], profit[] = [50, 10, 20, 30]
Output: [3, 100]
Explanation: Job1, Job3 and Job4 can be completed with a maximum profit of 100 (50 + 20 + 30).
*/

/* For each job, scan backwards till deadline → worst case O(n * maxDeadline)
If maxDeadline ≈ n, time complexity becomes O(n²).*/
//This approach will give TLE for larger i/p
class Job {
    int jobId;
    int dead;
    int profit;

    Job(int jobId, int dead, int profit) {
        this.jobId = jobId;
        this.dead = dead;
        this.profit = profit;
    }

}

class Solution {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here

        int n = deadline.length;

        Job[] arr = new Job[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Job(i, deadline[i], profit[i]);
        }

        // sort the job wrt to max profit
        Arrays.sort(arr, (a, b) -> Integer.compare(b.profit, a.profit));

        // finding the max deadline among all jobs
        int maxDeadline = arr[0].dead;

        for (int i = 1; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, arr[i].dead);
        }

        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int countJobs = 0, jobProfit = 0;

        for (int i = 0; i < arr.length; i++) {

            for (int j = arr[i].dead; j > 0; j--) {
                if (slots[j] == -1) {
                    slots[j] = i;
                    countJobs++;
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(countJobs);
        result.add(jobProfit);

        return result;

    }
}

// To find the slot we have to user DSU (Disjoint Set)
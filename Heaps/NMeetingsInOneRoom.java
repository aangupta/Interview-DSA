/* https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
You are given timings of n meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and end[i] is the finish time of meeting i. Return the maximum number of meetings that can be accommodated in a single meeting room, when only one meeting can be held in the meeting room at a particular time. 

Note: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Examples :

Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
Output: 4
Explanation: Maximum four meetings can be held with given start and end timings. The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
Output: 1
Explanation: Only one meetings can be held with given start and end timings.
Input: start[] = [1, 2], end[] = [100, 99]
Output: 1

*/

class Solution {
    // Function to find the maximum number of meetings that can
    // be performed in a meeting room.
    public int maxMeetings(int start[], int end[]) {
        // add your code here
        int n = start.length;
        int[][] intervals = new int[n][2]; // make sure to define array like this not n * n else it will give jave heap
                                           // space runtime error

        for (int i = 0; i < n; i++) {
            intervals[i][0] = start[i];
            intervals[i][1] = end[i];
        }

        // sorting wrt to end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1;
        int finish = intervals[0][1];

        for (int i = 1; i < n; i++) {

            int currMeetingStart = intervals[i][0];
            int currMeetingEnd = intervals[i][1];

            if (finish < currMeetingStart) {
                count++;
                finish = currMeetingEnd;
            }
        }

        return count;

    }
}

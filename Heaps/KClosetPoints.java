/*
https://leetcode.com/problems/k-closest-points-to-origin/description/ 
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

*/

class Point {
    int dist;
    int row;
    int col;

    Point(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}

class Solution {

    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;

        PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.dist, p1.dist));

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int dist = x * x + y * y;

            maxHeap.add(new Point(dist, x, y));

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        int index = 0;

        while (!maxHeap.isEmpty()) {
            Point p = maxHeap.poll();
            result[index][0] = p.row;
            result[index][1] = p.col;
            index++;
        }

        return result;
    }
}

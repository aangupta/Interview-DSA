/*

*/

class Solution {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );

        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int stoneY = pq.poll();
            int stoneX = pq.poll();

            if (stoneX != stoneY) pq.offer(stoneY - stoneX);
        }

        return (pq.size() == 1 ? pq.poll() : 0);
    }
}

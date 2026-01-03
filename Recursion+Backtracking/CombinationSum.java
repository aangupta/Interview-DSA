/*Problem: https://leetcode.com/problems/combination-sum/description/
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
Pattern: Classic Backtracking

Time Complexity: O(2^(T/m)) where T = target value, m minimum value in candidates
Each recursive call reduces target
Maximum depth ≈ T / m(worst case when we keep picking the smallest number)
Space Complexity: O(T/m) - recursive stack space
*/

class Solution {

    private void backtrack(int index, List<Integer> curr, List<List<Integer>> result, int[] candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        if (target < 0 || index >= candidates.length)
            return;
        // pruning can be done here
        for (int i = index; i < candidates.length; i++) {
            curr.add(candidates[i]);
            backtrack(i, curr, result, candidates, target - candidates[i]);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        backtrack(0, curr, result, candidates, target);

        return result;
    }
}

/* Bit Optimised version */

class Solution {

    private void backtrack(int index, List<Integer> curr, List<List<Integer>> result, int[] candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        if (target < 0 || index >= candidates.length)
            return;

        for (int i = index; i < candidates.length; i++) {
            // PRUNING
            if (candidates[i] > target)
                break;

            curr.add(candidates[i]);
            backtrack(i, curr, result, candidates, target - candidates[i]);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // MUST
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        backtrack(0, curr, result, candidates, target);

        return result;
    }
}

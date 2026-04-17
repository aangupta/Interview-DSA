
//Brute Force Approach
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                if (isValid(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private boolean isValid(String s, int start, int end) {
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '(')
                count++;
            else
                count--;

            if (count < 0)
                return false; // extra ')'
        }

        return count == 0; // all matched
    }
}

// Optimal Approach - (Nearest Invalid Boundaries concept)
class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() == 0)
            return 0;

        int maxLen = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // base index → helps calculate length from start

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop(); // try to match with previous '('

                if (stack.size() == 0) {
                    stack.push(i); // no matching '(' → reset boundary
                } else {
                    // valid substring exists → length = current index - last unmatched index
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
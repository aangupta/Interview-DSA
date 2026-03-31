/*
https://takeuforward.org/plus/dsa/problems/balanced-paranthesis?subject=dsa

Given string str containing just the characters '(', ')', '{', '}', '[' and ']', check if the input string is valid and return true if the string is balanced otherwise return false.

Example 1

Input: str = “()[{}()]”

Output: True

Explanation: As every open bracket has its corresponding close bracket. Match parentheses are in correct order hence they are balanced.

*/

//TC - O(n) SC - O(n) worst case (if all characters are opening parathesis)
class Solution {

    private boolean isMatched(char open, char close) {
        if ((open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}')) {
            return true;
        }

        // Mismatch
        return false;
    }

    public boolean isValid(String str) {
        int n = str.length();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);

            // open brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {

                if (st.size() == 0)
                    return false;

                char open = st.peek();
                st.pop();

                char close = str.charAt(i);

                if (isMatched(open, close) == false)
                    return false;

            }
        }

        return (st.size() == 0);
    }
}
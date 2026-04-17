/* https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.
 

Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
*/

class Solution {

    // Performs operation on top two operands based on operator
    private int operate(int a, int b, String s) {

        // Apply corresponding arithmetic operation
        if (s.equals("+"))
            return a + b;

        if (s.equals("-"))
            return a - b;

        // Use long to avoid overflow during multiplication
        if (s.equals("*"))
            return (int) ((long) a * (long) b);

        if (s.equals("/"))
            return a / b;

        return -1;
    }

    public int evalRPN(String[] tokens) {

        // Stack stores operands (numbers)
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            // If token is an operator → evaluate using last two operands
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {

                // Second operand comes first (LIFO order)
                int b = stack.pop();

                // First operand comes next
                int a = stack.pop();

                // Perform operation and push result back
                int result = operate(a, b, token);
                stack.push(result);
            }

            // If token is a number → push to stack
            else {
                stack.push(Integer.parseInt(token));
            }
        }

        // Final result remains at top of stack
        return stack.pop();
    }
}
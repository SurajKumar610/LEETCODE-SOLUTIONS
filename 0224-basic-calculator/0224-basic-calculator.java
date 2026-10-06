class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int currentNumber = 0;
        int sign = 1; // 1 for '+', -1 for '-'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            } else if (ch == '+') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } else if (ch == '-') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            } else if (ch == '(') {
                // Save current state before entering parenthesis
                stack.push(result);
                stack.push(sign);
                // Reset for the new scope
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // Finish expression inside parentheses
                result += sign * currentNumber;
                currentNumber = 0;
                // Multiply by sign before '(' and add previous result
                result *= stack.pop();
                result += stack.pop();
            }
        }

        // Add any remaining number
        result += sign * currentNumber;

        return result;
    }
}
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            int len = sb.length();
            // If the stack is non-empty and the top matches the current char, pop it
            if (len > 0 && sb.charAt(len - 1) == c) {
                sb.deleteCharAt(len - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
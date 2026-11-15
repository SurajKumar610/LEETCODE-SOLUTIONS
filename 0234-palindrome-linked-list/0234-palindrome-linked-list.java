class Solution {
    public boolean isPalindrome(ListNode head) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        ListNode curr = head;
        
        // Push all elements onto the stack
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }
        
        // Check for palindrome by comparing stack pop with list nodes
        curr = head;
        while (curr != null) {
            if (curr.val != stack.pop()) {
                return false;
            }
            curr = curr.next;
        }
        
        return true;
    }
}
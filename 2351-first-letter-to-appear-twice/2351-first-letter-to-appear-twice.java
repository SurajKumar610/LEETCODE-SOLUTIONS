class Solution {
    public char repeatedCharacter(String s) {
        boolean[] seen = new boolean[26];

        for(int i=0 ; i<s.length() ; i++){
            char ch = s.charAt(i);
            if(seen[ch - 'a']){
                return ch;
            }
            seen[ch - 'a'] = true;
        }
        return ' ';
    }
}
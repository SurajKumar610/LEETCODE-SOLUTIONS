class Solution {
    public int romanToInt(String s) {
        int tot = 0;
        int prevVal = 0;

        for(int i=s.length()-1 ; i>=0 ; i--){
            int currVal = getValue(s.charAt(i));

            if(currVal < prevVal){
                tot -= currVal;
            }else{
                tot += currVal;
            }

            prevVal = currVal;
        }
        return tot;
    }

    private int getValue(char c){
        switch(c){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
    }
}
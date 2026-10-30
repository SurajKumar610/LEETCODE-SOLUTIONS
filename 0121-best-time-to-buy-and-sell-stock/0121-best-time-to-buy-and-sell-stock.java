class Solution {
    public int maxProfit(int[] prices) {
        int minprice = prices[0];
        int profit = 0;
        
        for(int i=0 ; i<prices.length ; i++){
            int curr_profit = prices[i] - minprice;
            if(curr_profit>profit){
                profit = curr_profit;
            }
            minprice = Math.min(minprice , prices[i]);
        }

        return profit;
    }
}
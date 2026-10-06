class Solution {
    public boolean isSafe(List<StringBuilder> board, int row, int col, int n) 
    {
        for (int j = 0; j < n; j++)
        {
            if (board.get(row).charAt(j) == 'Q')
            {
                return false;
            }
        }
        for (int i = 0; i < n; i++)
        {
            if (board.get(i).charAt(col) == 'Q')
            {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
        {
            if (board.get(i).charAt(j) == 'Q')
            {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j < n; i--, j++)
        {
            if (board.get(i).charAt(j) == 'Q')
            {
                return false;
            }
        }
        return true;
    }

    public void nQueens(List<List<String>> ans, List<StringBuilder> board, int row, int n) 
    {
        if (row == n)
        {
            List<String> snapshot = new ArrayList<>();
            for (StringBuilder rowStr : board)
            {
                snapshot.add(rowStr.toString());
            }
            ans.add(snapshot);
            return;
        }
        for (int j = 0; j < n; j++)
        {
            if (isSafe(board, row, j, n))
            {
                board.get(row).setCharAt(j, 'Q');
                nQueens(ans, board, row + 1, n);
                board.get(row).setCharAt(j, '.');
            }
        }
    }

    public List<List<String>> solveNQueens(int n) 
    {
        List<List<String>> ans = new ArrayList<>();
        List<StringBuilder> board = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++)
            {
                row.append('.');
            }
            board.add(row);
        }
        nQueens(ans, board, 0, n);
        return ans;
    }
}
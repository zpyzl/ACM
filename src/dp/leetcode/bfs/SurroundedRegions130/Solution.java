package dp.leetcode.bfs.SurroundedRegions130;

/**
 * Created by zpy on 2018/5/2.
 */
public class Solution {
    int m;
    int n;
    public void solve(char[][] board) {
        m = board.length;
        if( m == 0 )
            return;
        n = board[0].length;
        if( n == 0 )
            return;

        for( int i = 1 ; i < board.length - 1 ; i++ ){
            for( int j = 1; j < board[i].length - 1; j++ ){
                halfMark(board,i,j);

            }
        }
    }

    private void halfMark(char[][] board, int i, int j) {
        if( board[i][j] == 'x' ) {
            replaceH(board,'x');
            return;
        }
            //TODO m n i j对应？
        if( i >= m || j >= n ) {
            replaceH(board,'o');
            return;
        }
        if( board[i][j] == 'o' ){
            board[i][j] = 'h';
            halfMark(board, i + 1, j);//上面左面都遍历过了
            halfMark(board, i, j + 1);//上面左面都遍历过了
        }
    }

    private void replaceH(char[][] board, char replace) {
        for( int i = 1 ; i < board.length - 1 ; i++ ){
            for( int j = 1; j < board[i].length - 1; j++ ){
                if( board[i][j] == 'h')
                    board[i][j] = replace;
            }
        }
    }


}

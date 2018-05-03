package dp.leetcode.bfs.SurroundedRegions130;

/**
 * Created by zpy on 2018/5/2.
 */
public class Solution {
    int m;
    int n;
    boolean foundO = false;
    boolean failed = false;
    public void solve(char[][] board) {
        m = board.length;
        if( m == 0 )
            return;
        n = board[0].length;
        if( n == 0 )
            return;

        for( int i = 1 ; i < board.length - 1 ; i++ ){
            for( int j = 1; j < board[i].length - 1; j++ ){
                failed = false;
                foundO = false;
                halfMark(board,i,j);
                if( !failed ) {
                    replaceH(board,'X');
                }
            }
        }
    }

    private void halfMark(char[][] board, int i, int j) {
        if( failed || board[i][j] == 'X' ) {
            return;
        }
        if( i >= m - 1 || j >= n - 1) {//碰到边界，失败
            replaceH(board,'O');
            failed = true;
            return;
        }
        if( board[i][j] == 'O' ){
            board[i][j] = 'h';
            foundO = true;
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

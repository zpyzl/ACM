package dp.leetcode.bfs.SurroundedRegions130;

/**
 *
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
        if( m == 1 || n == 1 || m == 2 || n == 2){
            return;
        }
        //把和边界上O相连的O标记成S，表示不改X的。
        // 遍历边界（除了4个角）
        for( int i = 0 ; i < m  ; i++ ) {
            markStable(board, i, 0);
            markStable(board, i, n - 1 );
        }
        for( int i = 0 ; i < n ; i++ ) {
            markStable(board,0,i);
            markStable(board,m - 1,i);
        }
        //剩下的O都可以替换成X
        replace(board, 'O','X');
        replace(board, 'S','O');

    }
    void markStable(char[][] board, int i, int j){
        if( i >= m - 1 || j >= n - 1 || j <= 0 || i <= 0 || board[i][j] == 'X'|| board[i][j] == 'S') {//碰到边界，返回
            return;
        }
        if( board[i][j] == 'O' ) {
            board[i][j] = 'S';
            markStable(board, i+1,j);
            markStable(board, i-1,j);
            markStable(board, i,j+1);
            markStable(board, i,j-1);
        }
    }
    private void replace(char[][] board, char match, char replace) {
        for( int i = 0 ; i < m ; i++ ){
            for( int j = 0; j < n; j++ ){
                if( board[i][j] == match)
                    board[i][j] = replace;
            }
        }
    }


/**
 * 正向思维往往麻烦，试试反向思维。
 * 正向：改X的条件：被X包围：不和边界上的O通
 * 反向：和边界上O通的，都不改，其他都改
 */
}

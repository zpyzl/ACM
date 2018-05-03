package dp.leetcode.bfs.SurroundedRegions130;

/**
 * Created by zhaopengyang on 2018/5/3.
 */
public class CplcSolution {
    /*
            这个正向解法麻烦，Stack Overflow
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
        if( i >= m - 1 || j >= n - 1 || j <= 0 || i <= 0 ) {//碰到边界，失败
            replaceH(board,'O');
            failed = true;
            return;
        }
        if( board[i][j] == 'O' ){
            board[i][j] = 'h';
            foundO = true;
            halfMark(board, i + 1, j);//虽然上面左面都遍历过了，但是从一个节点开始，要判断它还是要综合上下左右的信息。
            halfMark(board, i - 1, j);//或者维护一个遍历过的节点列表
            halfMark(board, i, j + 1);
            halfMark(board, i, j - 1);
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
*/
}

package dp.leetcode.bfs.SurroundedRegions130;

/**
 * Created by zpy on 2018/5/2.
 */
public class Solution {
    public void solve(char[][] board) {

        for( int i = 1 ; i < board.length - 1 ; i++ ){
            for( int j = 1; j < board[i].length - 1; j++ ){
                if( board[i][j] == 'o' ){

                    halfMark();
                }
            }
        }
    }
}

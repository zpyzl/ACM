package dp.leetcode.bfs.NumberOfIslands;


/**
 * Created by zhaopengyang on 2018/5/2.
 */
class Solution {
    int islandNum = 0;
    public int numIslands(char[][] grid) {

        for( int i = 0; i < grid.length ;i++ ){
            scan1Line(grid, grid[i], i);
        }
        return islandNum;
    }

    private void scan1Line(char[][] grid, char[] line, int column){

        for( int i = 0 ; i < line.length ;i++ ) {
            if( grid[column][i] != '0' ) {//是陆地
                //和上面一个岛屿相连，复制岛屿号
                if ( column - 1 >= 0 && grid[column - 1][i] != '0') {
                    grid[column][i] = grid[column - 1][i];
                }if(grid[column][i-1] != '0' ) {//和前一个相连
                    grid[column][i] = grid[column][i-1];
                }else {
                    //不和已有岛屿相连，新增岛屿号
                    islandNum++;
                    grid[column][i] = (char)islandNum;
                }
            }else {//是水
                if (i - 1 >= 0 && grid[column][i - 1] != '0') {//和此行前一个字符相连，
                    if (grid[column][i] == '0') {
                        grid[column][i] = grid[column][i - 1];//当前是0，前一个不是0，复制前一个的
                    } else if (grid[column][i] != grid[column][i - 1]) {
                        //岛屿号不同，统一岛屿号，这里不需要，因为岛屿号没有比较，只需要减少岛屿数
                        islandNum--;
                    }
                }
            }
        }

    }
    /**
     * [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]]
     */

}


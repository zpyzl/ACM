package dp.leetcode.bfs.NumberOfIslands200;

/**
 * Created by zhaopengyang on 2018/5/2.
 */
public class Test {
    public static  void  main(String[] args){
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        Solution solution = new Solution();
        solution.numIslands(grid);
        char[][] grid1 = {
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}};
        solution.numIslands(grid1);

    }
}

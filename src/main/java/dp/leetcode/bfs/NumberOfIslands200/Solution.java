package dp.leetcode.bfs.NumberOfIslands200;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaopengyang on 2018/5/2.
 */
class Solution {
    int islandNum = 0;
    int id = 0;
    Map<Character, List<Character>> sameIslands = new HashMap<>() ;

    public int numIslands(char[][] grid) {
        for( int i = 0; i < grid.length ;i++ ){
            scan1Line(grid, grid[i], i);
        }
        return islandNum;
    }

    private void scan1Line(char[][] grid, char[] line, int column){

        for( int i = 0 ; i < line.length ;i++ ) {
            if( grid[column][i] != '0' ) {//是陆地
                if ( column - 1 >= 0 && grid[column - 1][i] != '0') {//和上面一个岛屿相连，复制岛屿号
                    grid[column][i] = grid[column - 1][i];
                }else if( i - 1 >= 0 && grid[column][i-1] != '0' ) {//和左面的相连
                    grid[column][i] = grid[column][i-1];
                }else {
                    //不和已有岛屿相连，新增岛屿号
                    id++;
                    islandNum++;
                    grid[column][i] = (char)id;
                }
                //上面的和左面的不一样，这个点让两个岛相连
                if( column - 1 >= 0 && i - 1 >= 0 &&
                        grid[column - 1][i] != '0' && grid[column][i-1] != '0'
                        && !equal(grid[column - 1][i], grid[column][i-1] )) {
                    islandNum--;
                    put(grid[column - 1][i], grid[column][i-1]);
                    put(grid[column][i-1], grid[column - 1][i]);
                }
            }
        }
    }
    boolean equal(char a, char b){
        if( a == b ||
                ( sameIslands.containsKey(a) && sameIslands.get(a).contains(b) ) ||
                ( sameIslands.containsKey(b) && sameIslands.get(b).contains(a) ) ){
            return true;
        }
        return false;
    }
    void put(char a, char b){
        if( sameIslands.containsKey(a) ){
            sameIslands.get(a).add(b);
        }else {
            List<Character> sameChars = new LinkedList<>();
            sameChars.add(b);
            sameIslands.put(a, sameChars);
        }
    }

}


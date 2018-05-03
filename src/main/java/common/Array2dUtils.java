package common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaopengyang on 2018/5/3.
 */
public class Array2dUtils {
    public static char[][] convertChars(String s){
        s = removeBracket(s);
        int arraysNum = s.split("],\\[").length;
        int charsNum = StringUtils.countMatches(s,"\"")/2;
        char[][] res = new char[charsNum/arraysNum][arraysNum];
        s = s.replace("[","");
        s = s.replace("]","");
        s = s.replace("'","");
        String[] charStrs = s.split(",");

        int m = 0;
        for( int i = 0; i < res.length ; i++){
            for (int j = 0; j < charsNum/arraysNum; j++ ){
                res[i][j] = charStrs[m].replace("\"","").charAt(0);
                m++;
            }
        }
        return  res;


    }
    public static String removeBracket(String s){
        s = s.substring(1);
        s = s.substring(0, s.length() - 1);
        return  s;
    }
}

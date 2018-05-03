package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaopengyang on 2018/5/3.
 */
public class Array2dUtils {
    public static char[][] convertChars(String s){
        s = removeBracket(s);
        String[] arrayStrings = s.split(",");
        char[][] res = null ;
        int j = 0;
        for( String arrayStr : arrayStrings ){
            s = removeBracket(arrayStr);
            String[] chars = s.split(",");
            char[] line = new char[chars.length];
            if( res == null )
                res = new char[chars.length][arrayStrings.length];
            int i = 0;
            for( String quoteChar : chars){
                char c = quoteChar.replace("\"","").charAt(0);
                line[i] = c;
                i++;
            }
            res[j] = line;
            j++;
        }
        return res;
//        s = s.replace("[","{");
//        s = s.replace("]","}");
//        s = s.replace("\"","'");

    }
    public static String removeBracket(String s){
        s = s.substring(1);
        s = s.substring(0, s.length() - 1);
        return  s;
    }
}

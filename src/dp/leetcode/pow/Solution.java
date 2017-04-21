package dp.leetcode.pow;

import org.junit.Test;

/**
 * Created by zpy on 2017/4/21.
 */
public class Solution {

    public double myPow(double x, int n) {
        if( n == Integer.MIN_VALUE )//cannot use -n
            return 1 / (myPow(x, Integer.MAX_VALUE)*x);
        if( x == 0 )
            return 0;
        if( x == 1)
            return 1;
        if( x == -1 )
            return x % 2 == 0 ? 1 : -1;
        if (n < 0)
            return 1 / (myPow(x, -n));
        if( n == 0 && x != 0)
            return 1;

        double res = 1;
        while( true ) {
            if (n == 2)
                return res * x * x;
            if( n == 1)
                return res * x;
            double square = x * x;
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = (n - 1) / 2;
                res *= x;
            }
            x = square;
        }
    }
    @Test
    public void test1(){
        myPow(2,-2147483648);
    }
}

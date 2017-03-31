package common;

/**
 * Created by zpy on 2017/3/31.
 */
public class PrintUtils {
    public static void print(ListNode h){
        ListNode n = h;
        while(n != null ) {
            System.out.print(n.val);
            n = n.next;
        }
    }
}

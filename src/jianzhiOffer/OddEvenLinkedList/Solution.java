package jianzhiOffer.OddEvenLinkedList;

import org.junit.Test;

/**
 * Created by zpy on 2017/3/30.
 */
public class Solution {

    public ListNode oddEvenList(ListNode head) {
        if( head == null || head.next == null || head.next.next == null ){
            return head;
        }

        //init even list
        ListNode firstEven = head.next;
        ListNode evenTail = firstEven;

        ListNode cursor = head.next.next;
        head.next = cursor;

        while( cursor.next != null && cursor.next.next != null  ){
            evenTail.next = cursor.next;
            evenTail = evenTail.next;

            cursor.next = cursor.next.next;
            cursor = cursor.next;
        }
        evenTail.next = null;
        if( cursor.next != null && cursor.next.next == null ){
            evenTail.next = cursor.next;
        }
        cursor.next = firstEven;

        return head;
    }

    @Test
    public void test1(){
        ListNode h = new ListNode(1);
        ListNode n = h;
        for( int i = 2; i < 8 ; i++) {
            n.next = new ListNode(i);
            n = n.next;
        }
        print( oddEvenList(h));

    }
    private void print(ListNode h){
        ListNode n = h;
        while(n != null ) {
            System.out.print(n.val);
            n = n.next;
        }
    }
}

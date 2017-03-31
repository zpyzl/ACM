package jianzhiOffer.OddEvenLinkedList;

import common.ListNode;
import common.PrintUtils;
import org.junit.Test;

public class Solution {

    public ListNode oddEvenList(ListNode head) {
        if( head == null || head.next == null || head.next.next == null ){
            return head;
        }
        //initial
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        while ( odd.next != null && odd.next.next != null) {
            odd.next = odd.next.next;
            even.next = odd.next.next;
            even = even.next;
            odd = odd.next;
        }
        odd.next = evenHead;
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
        PrintUtils.print( oddEvenList(h));

    }

    @Test
    public void test2(){
        ListNode h = new ListNode(1);
        ListNode n = h;
        for( int i = 2; i < 9 ; i++) {
            n.next = new ListNode(i);
            n = n.next;
        }
        PrintUtils.print( oddEvenList(h));


    }


}

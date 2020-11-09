package com.oneisall.learn.leetcode;

import java.util.LinkedList;

/**
 * 合并两个有序链表
 * <p>
 * //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * // 示例：
 * //
 * // 输入：1->2->4, 1->3->4
 * //输出：1->1->2->3->4->4
 *
 * @author : liuzhicong
 * @version : v1 2020/11/9
 */
@SuppressWarnings("all")
public class T21MergeTwoLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode l = this;
            LinkedList<Integer> arr = new LinkedList<>();
            while (l != null) {
                arr.add(l.val);
                l = l.next;
            }
            return arr.toString();
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 输入：1->2->4, 1->3->4
        // 输出：1->1->2->3->4->4
        ListNode tmpResult = new ListNode(-1);
        ListNode result = tmpResult;
        while (!(l1 == null && l2 == null)) {
            ListNode litele = null;
            if (l1 == null && l2 != null) {
                litele = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l2 == null && l1 != null) {
                litele = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                if (l1.val <= l2.val) {
                    litele = new ListNode(l1.val);
                    l1 = l1.next;
                } else {
                    litele = new ListNode(l2.val);
                    l2 = l2.next;
                }
            }
            tmpResult.next = litele;
            tmpResult = tmpResult.next;
        }
        result = result.next;
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(6)));
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(mergeTwoLists(l1, l2));
    }
}

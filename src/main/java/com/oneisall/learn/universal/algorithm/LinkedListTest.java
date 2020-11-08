package com.oneisall.learn.universal.algorithm;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 链表
 *
 * @author : oneisall
 * @version : v1 2020/5/11 13:25
 */
public class LinkedListTest {
    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(List<Integer> dataList) {
            if (dataList == null || dataList.size() == 0) {
                throw new IllegalArgumentException("初始化参数不正确");
            }
            boolean first = true;
            Node preNode = null;
            for (Integer tmpData : dataList) {
                if (first) {
                    this.data = tmpData;
                    first = false;
                    preNode = this;
                } else {
                    Node currentNode = new Node(tmpData);
                    preNode.next = currentNode;
                    preNode = currentNode;
                }
            }
        }

        public void print() {
            LinkedList<Integer> dataList = new LinkedList<>();
            dataList.add(data);
            Node tmp = next;
            while (tmp != null) {
                dataList.add(tmp.data);
                tmp = tmp.next;
            }
            String join = StringUtils.join(dataList, ",");
            System.out.println(join);
        }
    }


    // 1->2->3->4->5

    /** 循环反转 */
    public static Node reverseByLoop(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;//1
        Node next = null;
        // 迭代到当前节点为null，代表已经结束反转
        while (cur != null) {
            // 找出下一个待反转的存储为 next
            next = cur.next; // 2
            // 反转当前节点
            cur.next = pre; // 1 -> null !!!!!!!!!
            // 在光标指向下一个之前，将当前节点记录为前一个节点
            pre = cur; // 1
            // 光标指向下一个
            cur = next; // 2
        }
        // 循环结束，当前节点为null，头节点为前一个
        return pre;
    }
    // 1->2
    /** 递归反转 */
    static Node reverseByRecursion(Node head) {
        //我们从原链表的头结点开始
        //递归到原链表的尾结点结束。递归到了尾结点的时候，就返回当前结点。
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转下一个
        Node newHead = reverseByRecursion(head.next);
        // 1的下一个2，2的下一个指向1
        head.next.next = head;
        // 1指向null
        head.next = null;
        // 返回新的头,此处新的头肯定是最开始的尾部，即第一步中判断的 return head
        return newHead;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        Node node = new Node(integers);
        node.print();
        Node reverse = reverseByLoop(node);
        reverse.print();

        integers = Lists.newArrayList(1, 2, 3, 4,5);
        Node node2 = new Node(integers);
        node2.print();
        Node reverse2 = reverseByRecursion(node2);
        reverse2.print();
    }

}

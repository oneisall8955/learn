package com.oneisall.learn.universal.algorithm;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author liuzhicong
 **/
public class DeleteNodeTest {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        public static void printLink(Node head) {
            List<Integer> result = Lists.newLinkedList();
            Node tmp = head;
            while (tmp != null) {
                result.add(tmp.data);
                tmp = tmp.next;
            }
            System.out.println(StringUtils.join(result, ","));
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Node.printLink(node1);
        deleteNode(node3);
        Node.printLink(node1);
    }

    private static void deleteNode(Node p) {
        if (p.next == null) {
            // 最后一个
            return;
        }
        // 1->2->3->4->5
        //       p  q
        Node q = p.next;
        p.data = q.data;
        p.next = q.next;
    }
}

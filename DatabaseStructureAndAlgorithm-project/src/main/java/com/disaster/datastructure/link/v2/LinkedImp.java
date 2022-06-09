package com.disaster.datastructure.link.v2;


/*
单向链表的实现
 */
public class LinkedImp<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public LinkedImp() {

    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, l);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    Node<E> node(int index) {
        if (index < size ) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            return null;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("此下标下没有值");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}

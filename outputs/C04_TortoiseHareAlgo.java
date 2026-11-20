package com.thealgorithms.datastructures.lists;

// A singly linked list that uses slow and fast pointers to find its middle element.
public class TortoiseHareAlgo<E> {
    // Each node stores a value and a reference to the next node.
    static final class Node<E> {
        Node<E> next;
        E value;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head = null;

    public TortoiseHareAlgo() {
        head = null;
    }

    public void append(E value) {
        Node<E> newNode = new Node<>(value, null);
        // The first appended node becomes the head of an empty list.
        if (head == null) {
            head = newNode;
            return;
        }
        // Traverse to the last node and link it to the new node.
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Returns the middle value, or the second middle value for an even-sized list.
    public E getMiddle() {
        if (head == null) {
            return null;
        }

        Node<E> slow = head;
        Node<E> fast = head;

        // Move slow one node and fast two nodes per iteration.
        // When fast reaches the end, slow points to the middle node.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.value;
    }

    // Formats the list values in brackets, separated by commas.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
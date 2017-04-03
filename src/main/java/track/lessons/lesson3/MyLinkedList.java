package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {
    private Node start;
    private Node end;

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    private Node getNode(int idx) throws NoSuchElementException {
        if (idx < 0) {
            throw new NoSuchElementException();
        }
        Node current = start;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current;
    }

    public MyLinkedList() {
        start = new Node(null, null, 0);
        end = new Node(null, null, 2);
        listSize = 0;
    }

    @Override
    void add(int item) {
        if (listSize == 0) {
            start.val = item;
            end.prev = start;
            start.next = end;
        } else {
            Node newNode = new Node(end.prev, end, item);
            end.prev.next = newNode;
            end.prev = newNode;
        }
        listSize += 1;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        super.check_idx(idx);
        listSize--;
        Node removed = getNode(idx);
        if (idx == 0) {
            start = removed.next;
            start.prev = null;
        } else {
            removed.prev.next = removed.next;
            removed.next.prev = removed.prev;
        }
        return removed.val;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        super.check_idx(idx);
        Node needed = getNode(idx);
        return needed.val;
    }

    @Override
    public void push(int value) {
        this.add(value);
    }

    @Override
    public int pop() {
        return this.remove(size() - 1);
    }

    @Override
    public void enqueue(int value) {
        this.add(value);
    }

    @Override
    public int dequeu() {
        return this.remove(0);
    }
}

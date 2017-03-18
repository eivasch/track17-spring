package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private int[] list;
    private int listSize;

    public MyArrayList() {
        list = new int[0];
        listSize = 0;
    }

    public MyArrayList(int capacity) {
        list = new int[capacity];
        listSize = capacity;
    }

    @Override
    void add(int item) {
        int newSize = size() + 1;
        int[] newList = new int[newSize];
        System.arraycopy(list, 0, newList, 0, size());
        newList[newSize - 1] = item;
        list = newList;
        listSize = newSize;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= size()) {
            throw new NoSuchElementException();
        }
        int newSize = size() - 1;
        int[] newList = new int[newSize];
        System.arraycopy(list, 0, newList, 0, idx);
        System.arraycopy(list, idx + 1, newList, idx, newSize - idx);
        int removedItem = list[idx];
        list = newList;
        listSize = newSize;
        return removedItem;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= size()) {
            throw new NoSuchElementException();
        }
        return list[idx];
    }

    @Override
    int size() {
        return listSize;
    }
}

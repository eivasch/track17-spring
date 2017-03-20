package track.lessons.lesson3;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class MyLinkedListTest {

    @Test(expected = NoSuchElementException.class)
    public void emptyList() throws Exception {
        List list = new MyLinkedList();
        Assert.assertTrue(list.size() == 0);
        list.get(0);
    }

    @Test
    public void listAdd() throws Exception {
        List list = new MyLinkedList();
        list.add(1);

        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void listAddRemove() throws Exception {
        List list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        Assert.assertEquals(3, list.size());

        Assert.assertEquals(1, list.get(0));
        Assert.assertEquals(2, list.get(1));
        Assert.assertEquals(3, list.get(2));

        list.remove(1);
        Assert.assertEquals(3, list.get(1));
        Assert.assertEquals(1, list.get(0));

        list.remove(1);
        list.remove(0);

        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void listRemove() throws Exception {
        List list = new MyLinkedList();
        list.add(1);
        list.remove(0);

        Assert.assertTrue(list.size() == 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void stackTest() throws Exception {
       Stack stack = new MyLinkedList();
       stack.push(10);
       stack.push(15);

       Assert.assertEquals(15, stack.pop());
       Assert.assertEquals(10, stack.pop());

       stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void queueTest() throws Exception {
        Queue queue = new MyLinkedList();
        queue.enqueue(10);
        queue.enqueue(15);

        Assert.assertEquals(10, queue.dequeu());
        Assert.assertEquals(15, queue.dequeu());

        queue.dequeu();
    }
}
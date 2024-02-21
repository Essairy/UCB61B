
import deque.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertEquals(0, ad.size());
    }

    @Test
    public void testResize() {
        Deque<Integer> ad = new ArrayDeque<>();
        for (int i = 0;i<8;i+=1){
            ad.addFirst(i);
            ad.addLast(-i);
        }
        assertEquals(16, ad.size());
        System.out.println(ad);
    }

}

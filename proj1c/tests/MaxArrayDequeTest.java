import deque.Deque;
import deque.MaxArrayDeque;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void testEmptySize() {
        Deque<Integer> ad = new MaxArrayDeque<>();
        assertEquals(0, ad.size());
    }

    @Test
    public void testResize() {
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>();
        for (int i = 0;i<8;i+=1){
            ad.addFirst(i);
            ad.addLast(-i);
        }
        assertEquals(16, ad.size());
        System.out.println(ad);
    }

    @Test
    public void testMax() {
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>();
        for (int i = 0;i<8;i+=1){
            ad.addFirst(i);
            ad.addLast(-i);
        }
        assertEquals(7, ad.max().intValue());
        System.out.println(ad);
    }
}

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertEquals(0, ad.size());
    }

    @Test
    public void testAdd() {
        Deque<Integer> ad = new ArrayDeque<>();
        for(int i = 0;i<6;i+=1){
            ad.addFirst(i);
            ad.addLast(i);
        }
        assertEquals(12, ad.size());
    }

}

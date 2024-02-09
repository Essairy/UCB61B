import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>{

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    private int arraySize;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        arraySize = 10;
        items = (T[]) new Object[arraySize];
    }

    private void resize(){
        T[] newItems = (T[]) new Object[arraySize*2];
        int pos = 1;
        for (int i = (nextFirst+1)%arraySize;i!=(nextLast+arraySize-1)%arraySize;i=(i+1)%arraySize){
            newItems[pos] = items[i];
            pos+=1;
        }
        nextFirst = 0;
        nextLast = pos;
        arraySize = arraySize*2;
        items = newItems;
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = (nextFirst+arraySize-1)%arraySize;
        size++;
        if (size == arraySize){
            resize();
        }
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = (nextLast+1)%arraySize;
        size++;
        if (size == arraySize){
            resize();
        }
    }

    public List<T> toList(){
        List<T> newList = new ArrayList<>();
        for (int i = (nextFirst+1)%arraySize;i!=(nextLast+arraySize-1)%arraySize;i=(i+1)%arraySize){
            newList.add(items[i]);
        }
        return newList;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T removeFirst(){
        T removeItem = items[(nextFirst+1)%arraySize];
        size--;
        nextFirst = (nextFirst+1)%arraySize;
        return removeItem;
    }

    public T removeLast(){
        T removeItem = items[(nextLast-1+arraySize)%arraySize];
        size--;
        nextLast = (nextLast-1+arraySize)%arraySize;
        return removeItem;
    }

    public T get(int index){
        return items[index];
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }
}

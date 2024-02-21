package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque<T> implements Deque<T> {
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
        for (int i = (nextFirst+1)%arraySize;pos<=size;i=(i+1)%arraySize){
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
        int pos = 1;
        for (int i = (nextFirst+1)%arraySize;pos<=size;i=(i+1)%arraySize){
            newList.add(items[i]);
            pos += 1;
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
        return items[(index+nextFirst+1)%arraySize];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    public static void main(String[] args) {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = (nextFirst+1)%arraySize;
        }

        public boolean hasNext() {
            return wizPos != nextLast;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos =(wizPos + 1)%arraySize;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) { return true; }
        if (obj instanceof ArrayDeque otherSet) {
            if (this.size != otherSet.size) { return false; }
            for (int i = 0;i<this.size;i+=1){
                if (this.get(i)!=otherSet.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

}

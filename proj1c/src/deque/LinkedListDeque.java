package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args){

        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        Deque<String> lld2 = new LinkedListDeque<>();

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
        System.out.println(lld2);
    }

    private Tnode sentinel;

    private int size;

    public LinkedListDeque(){
        sentinel = new Tnode();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Tnode wizPos;

        public LinkedListIterator() {
            wizPos = sentinel.next;
        }

        public boolean hasNext() {
            return wizPos != sentinel;
        }

        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    public class Tnode{
        public T item;
        public Tnode next;
        public Tnode before;

        public Tnode(){
            next = this;
            before = this;
        }

        public Tnode(T x){
            item = x;
            next = this;
            before = this;
        }
    }

    @Override
    public void addFirst(T x) {
        Tnode newNode = new Tnode(x);
        newNode.next = sentinel.next;
        sentinel.next.before = newNode;
        sentinel.next = newNode;
        newNode.before = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Tnode newNode = new Tnode(x);
        sentinel.before.next = newNode;
        newNode.before = sentinel.before;
        newNode.next = sentinel;
        sentinel.before = newNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> TList = new ArrayList<>();
        Tnode point = sentinel.next;
        while(point.item != null){
            TList.add(point.item);
            point = point.next;
        }
        return TList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        T item = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        Tnode pos = sentinel;
        for (int i = 0; i < index; i++){
            pos = pos.next;
        }
        return pos.item;
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) { return true; }
        if (obj instanceof LinkedListDeque otherSet) {
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

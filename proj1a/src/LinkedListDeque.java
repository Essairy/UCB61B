import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args){
        Deque<Integer> lld = new LinkedListDeque<>();
    }

    private Tnode sentinel;

    private int size;

    public LinkedListDeque(){
        sentinel = new Tnode();
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
        return null;
    }
}


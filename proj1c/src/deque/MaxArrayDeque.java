package deque;

import net.sf.saxon.functions.Minimax;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    public Comparator<T> myComparator;

    public class Comparator<T> implements Comparable<T> {
        @Override
        public int compareTo(T o) {
            return this.toString().compareTo(o.toString());
        }

        public int compare(T maxItem, T localItem) {
            return maxItem.toString().compareTo(localItem.toString());
        }
    }

    public Comparator<T> getMaxComparator(){
        return new Comparator<T>();
    }

    public MaxArrayDeque(){
        myComparator = getMaxComparator();
    }

    public MaxArrayDeque(Comparator<T> c){
        myComparator = c;
    }

    public T max(){
        T maxItem = get(0);
       for (int i = 1;i<size();i+=1){
           T localItem = get(i);
           if (myComparator.compare(maxItem,localItem)<0){
               maxItem = localItem;
           }
       }
       return maxItem;
    }

    public T max(Comparator<T> c){
        T maxItem = get(0);
        for (int i = 1;i<size();i+=1){
            T localItem = get(i);
            if (c.compare(maxItem,localItem)<0){
                maxItem = localItem;
            }
        }
        return maxItem;
    }
}
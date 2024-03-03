package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private int Capacity = 16;
    private double loadFactor = 0.75;

    private int nodeNum = 0;

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(Capacity);
        int i;
        for (i = 0;i<Capacity;i+=1){
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        this.Capacity = initialCapacity;
        buckets = createTable(Capacity);
        int i;
        for (i = 0;i<Capacity;i+=1){
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.Capacity = initialCapacity;
        buckets = createTable(Capacity);
        int i;
        for (i = 0;i<Capacity;i+=1){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new LinkedList[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    public int getHashMod(K Key, int modNum){
        return Math.floorMod(Key.hashCode(),modNum);
    }

    private void reSize(){
        Collection<Node>[] newBuckets = createTable(Capacity*2);
        int i;
        for (i = 0;i<Capacity*2;i+=1){
            newBuckets[i] = createBucket();
        }
        Set<K> oldSet = keySet();
        for (K oldKey:oldSet){
            int newHash = getHashMod(oldKey,Capacity*2);
            newBuckets[newHash].add(createNode(oldKey,get(oldKey)));
        }
        buckets = newBuckets;
        Capacity *= 2;
    }

    @Override
    public void put(K key, V value) {
        int keyHash = getHashMod(key,Capacity);
        for(Node node:buckets[keyHash]){
            if (node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        buckets[keyHash].add(createNode(key,value));
        nodeNum += 1;
        if (nodeNum*1.0/Capacity > loadFactor){
            reSize();
        }
    }

    @Override
    public V get(K key) {
        int keyHash = getHashMod(key,Capacity);
        for(Node node:buckets[keyHash]){
            if (node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int keyHash = getHashMod(key,Capacity);
        for(Node node:buckets[keyHash]){
            if (node.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return nodeNum;
    }

    @Override
    public void clear() {
        buckets = createTable(Capacity);
        nodeNum = 0;
        int i;
        for (i = 0;i<Capacity;i+=1){
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        int i;
        for (i = 0 ;i<Capacity; i+=1){
            for (Node n:buckets[i]){
                keySet.add(n.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        int keyHash = getHashMod(key,Capacity);
        V value = null;
        for (Node n:buckets[keyHash]){
            if (n.key.equals(key)){
                value = n.value;
                buckets[keyHash].remove(n);
            }
        }
        nodeNum -= 1;
        return value;
    }

    private class hashMapIterator implements Iterator<K> {

        K[] newArray = (K[]) keySet().toArray();

        int size = newArray.length;

        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos<size;
        }

        @Override
        public K next() {
            K retureK = newArray[pos];
            pos += 1;
            return retureK;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }
}

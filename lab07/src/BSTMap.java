import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private int size = 0;
    private class Node{
        private K key;
        private V value;
        private Node left,right;
        private int size;

        public Node(K key,V value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }


    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root,key,value);
        size = size(root);
    }

    private Node put(Node x,K key,V value){
        if (x == null) return new Node(key,value,1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left,key,value);
        else if (cmp>0) x.right = put(x.right,key,value);
        else x.value = value;
        x.size = 1+size(x.left)+size(x.right);
        return x;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root,key);
    }

    private V get(Node currentNode,K key){
        if ( currentNode == null) return null;
        int cmp = key.compareTo(currentNode.key);
        if      (cmp < 0) return get(currentNode.left, key);
        else if (cmp > 0) return get(currentNode.right, key);
        else              return currentNode.value;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return containsKey(root,key);
    }

    private boolean containsKey(Node currentNode,K key) {
        if (currentNode == null){
            return false;
        }
        int cmp = key.compareTo(currentNode.key);
        if (cmp>0){
            return containsKey(currentNode.right,key);
        } else if (cmp <0) {
            return containsKey(currentNode.left,key);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }


    private int size(Node currentNode) {
        if (currentNode == null) return 0;
        return 1+size(currentNode.right)+size(currentNode.left);
    }

    @Override
    public void clear() {
        clear(root);
        root = null;
        size = 0;
    }

    private void clear(Node currentNode){
        currentNode.right = null;
        currentNode.left = null;
        currentNode.value = null;
        currentNode.key = null;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

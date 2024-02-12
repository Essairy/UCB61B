
public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    private IntNode Sentinal;
    public int size;

    public SLList() {
        Sentinal = new IntNode(63,null);
        size = 0;
    }
    public SLList(int x){
        Sentinal = new IntNode(63,null);
        Sentinal.next = new IntNode(x, Sentinal.next);
        size = 1;
    }

    public void addFirst(int x){
        Sentinal.next = new IntNode(x,Sentinal.next);
        size++;
    }

    public int getFirst(){
        return Sentinal.next.item;
    }

    public void addLast(int x) {
        IntNode p = Sentinal;
        while(p.next!=null){
            p = p.next;
        }
        p.next = new IntNode(x,null);
        size++;
    }

    public static int size(IntNode p){
        if (p.next==null){
            return 1;
        }
        return 1+size(p.next);
    }

    public int size() {
        return size(Sentinal.next);
    }

    public static void main(String[] args){
        SLList ILnull = new SLList();
        ILnull.addLast(3);
        SLList IL = new SLList(3);
        IL.addFirst(4);
        IL.addFirst(5);
        IL.addLast(1);
        System.out.println(IL.getFirst());
        System.out.println(IL.size());
        System.out.println(IL.size);
        System.out.println(ILnull.size);
        System.out.println(ILnull.getFirst());
    }
}

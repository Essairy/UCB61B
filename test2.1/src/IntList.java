public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f,IntList r){
        first = f;
        rest = r;
    }

    public int size(){
        if (rest == null){
            return 1;
        }
        return 1+this.rest.size();
    }

    public int Interativesize(){
        IntList p = this;
        int totalSize = 0;
        while(p != null){
            totalSize++;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int i){
        if (i == 0){
            return this.first;
        }
        return rest.get(i-1);
    }

    public void addFirst(int x){
        int temp = this.first;
        IntList middle = new IntList(temp,null);
        if (this.rest != null) {
            middle.rest = this.rest;
        }
        this.rest = middle;
        this.first = x;
    }


    public static void main(String[] args){
        IntList L = new IntList(12,null);
        L = new IntList(15,L);
        L = new IntList(24,L);
        L.addFirst(18);
        System.out.println(L.size());
        System.out.println(L.get(0));
    }
}

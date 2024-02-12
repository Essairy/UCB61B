public class starTriangle {
    public static void main(String[] args){
        System.out.println(Stutter("Hello"));
    }
    public static void printIndexed(String s){
        String out = "";
        for(int i = 0;i<s.length();i++){
            out += s.charAt(i);
            out += s.length()-i-1;
        }
        System.out.println(out);
    }

    public static String Stutter(String s){
        String out = "";
        for(int i = 0; i< s.length(); i++){
            out += s.charAt(i);
            out += s.charAt(i);
        }
        return out;
    }

    public static int quadrant(int x, int y){
        if (x == 0 || y==0){
            return 0;
        }
        if (x>0 && y>0){
            return 1;
        } else if (x>0) {
            return 4;
        } else if (y>0) {
            return 2;
        } else {
            return 3;
        }
    }
    
}

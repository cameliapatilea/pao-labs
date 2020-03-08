import java.util.Scanner;
import java.util.*;

public class lab2_ex1 {
    public static void main(String[] args) {
        Vector v;
        v = new Vector();
        double suma = 0;
        while(true){
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            if(x != -1){
                v.add(x);
                suma = suma  + x;
            }
            else break;
        }
        int n = v.size();

        System.out.println(suma /n);
    }
}

import java.util.Scanner;
import java.util.*;

public class lab2_ex2 {

    public static void main(String[] args) {

        Vector a, b;
        a = new Vector();
        b = new Vector();

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i = 0; i < n; i++)
        {
            int x = sc.nextInt();
            if(x % 2 == 0)
                a.add(x);
            else b.add(x);
        }
        if(a.size() > b.size())
        {
            System.out.println(a);
        }
        else System.out.println(b);
    }
}

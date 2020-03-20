import java.util.Scanner;
import java.util.*;

public class lab4_ex2 {
    public static String palindrom(String str){
        StringBuilder rev = new StringBuilder(str);
        return rev.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Vector<String> a = new Vector<String>();
        Vector<String> b = new Vector<String>();
        String input;

        for(int i = 0; i < n; i++)
        {
            input = scan.next();
            a.add(input);
        }
        int maxi = 0;
        String palindromMax = "";
        for(int i= 0; i < n; i++)
        {
            if(a.get(i).equals(palindrom(a.get(i))))
            {
               b.add(a.get(i));
               palindromMax = a.get(i);
               if(palindromMax.length() >  maxi)
               {
                   maxi = palindromMax.length();
               }
            }


        }
        if(n == 0)
            System.out.println("Numarul de cuvinte introduse de la tastatura este 0.");
        else{
            System.out.println("Palindromul cu lungimea maxima este:");
            System.out.println(palindromMax);
            System.out.println("Lungimea cuvantului este:");
            System.out.println(palindromMax.length());
        }

    }
}

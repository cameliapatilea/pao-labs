import java.util.Scanner;

public class lab1 {
    public static void ex1(Scanner sc){
        System.out.println("Exercitiul 1:");
        int n = sc.nextInt();
        for(int i=0; i <= n; i++)
            if(i %2 == 0)
                System.out.print(i + " ");
    }

    public static void ex2(Scanner sc){
        System.out.println(" ");
        System.out.println("Exercitiul 2:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        if(a > b)
            System.out.println(a);
        else
            System.out.println(b);
    }

    public static void ex3(Scanner sc){

        System.out.println("Exercitiul 3:");
        int x = sc.nextInt();
        for(int d = 1; d <=  x; d++)
            if(x %d == 0)
                System.out.print(d + " ");

    }

    public static void ex4(Scanner sc){
        System.out.println(" ");
        System.out.println("Exercitiul 4:");
        int nr = sc.nextInt();
        long factorial = 1;
        if(nr == 0 || nr == 1)
            System.out.println(1);
        else{
            for(int i = 1;  i  <= nr; i++)
                factorial = factorial * i;
        }
        System.out.println(factorial);

    }

    public static void ex5(Scanner sc){
        System.out.println(" ");
        System.out.println("Exercitiul 5:");
        int x = sc.nextInt();
        boolean ok = true;
        for(int d = 2; d <= x / 2; d++)
            if(x % d == 0)
                ok = false;
        System.out.println(ok);

    }

    public static void ex6(Scanner sc){
        System.out.println(" ");
        System.out.println("Exercitiul 6:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        long maxi = 1;
        for(int i = 1; i <= b; i++)
            maxi = maxi * a;
        System.out.println(maxi);
    }

    public static void ex7(Scanner sc){
        System.out.println(" ");
        System.out.println("Exercitiul 7:");
        int n1 = 0;
        int n2 = 1;
        int n3 = 0;
        int count = 2;
        int n = sc.nextInt();
        while(count <= n){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            count++;
        }
        System.out.println(n1);

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ex1(sc);
        ex2(sc);
        ex3(sc);
        ex4(sc);
        ex5(sc);
        ex6(sc);
        ex7(sc);
        sc.close();
    }
}

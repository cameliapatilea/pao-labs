import java.util.Scanner;



public class lab4_ex3 {
    public static int prim(int nr){
        int ok = 1;
        for(int d = 2; d <= nr / 2; d++)
            if(nr%d == 0)
                ok = 0;
         return ok;
    }
    public static void main(String[] args) {
        int[][] imagine= new int[100][100];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x;

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                x = sc.nextInt();
                imagine[i][j] = x;
            }

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                System.out.print(imagine[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                if(imagine[i][j]  == 1 || imagine[i][j] == 0 || prim(imagine[i][j]) == 0)
                    imagine[i][j] = 1;
                else imagine[i][j] = 0;
            }
        System.out.println('\n');
            System.out.println("Imaginea binara:");
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                System.out.print(imagine[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                if(imagine[i][j] == 1)
                    cnt++;

            }
        System.out.println("Dimensiunea obiectelor din imagine este:");
            System.out.println(cnt);
    }
}

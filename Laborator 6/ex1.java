import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;


public class ex1 {
    public static void main(String[] args) throws ValoriDepasite {
        int n = 5;
        Scanner sc = new Scanner(System.in);
        try {
            FileWriter w = new FileWriter("output.txt");
            while (n != 0) {
                try{
                    n--;
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (x >= y) {
                        throw new ValoriDepasite("Introduceti alte valori");
                    }

                    w.write(String.valueOf(x));
                    w.write(" ");
                    w.write(String.valueOf(y));
                    w.write("\n");
                }
                catch (ValoriDepasite e) {
                    System.out.println("Introduceti alte 2 valori");
                }
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
        }

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ex3 {
    public static void main(String[] args) {
        Vector<String> minori = new Vector<>();
        Vector<String> majori = new Vector<>( );
        int nr = 0;
        try{
            File myObj = new File("input.txt");
            Scanner sc = new Scanner(myObj);
            while(sc.hasNextLine()){
                nr++;
                String data = sc.nextLine();
                String[] persoana = data.split(" ");
                int varsta = Integer.parseInt(persoana[2]);
                if(varsta >= 18){
                    majori.add(data);
                }
                else{
                    minori.add(data);
                }
            }
            sc.close();
            System.out.println("Numarul de clienti este " + nr);
            System.out.println("Numarul clientilor minori " + minori.size());
            System.out.println("Clientii minori: ");
            System.out.println(minori);
            System.out.println("Numarul clientilor majori " + majori.size());
            System.out.println("Clientii majori: ");
            System.out.println(majori);
        }
        catch(FileNotFoundException e){
            System.out.println("A aparut o eroare");
        e.printStackTrace();
        }
    }
}

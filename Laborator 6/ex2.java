

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Scanner;
import java.util.Vector;

public class ex2 {
    public static String user;
    public static String pass;
    ex2(){
        this.user = "";
        this.pass = "";
    }
    public static StringBuilder citesteLinie(FileInputStream file) throws IOException{
           StringBuilder input = new StringBuilder();
           int ch;
           while((ch  = file.read()) != '\n'){
               if (ch == -1 ){
                   if(input.length() == 0)
                   return null;
                   break;
               }
               input.append((char) ch);
           }
           input.deleteCharAt(input.length() - 1);
          return input;
    }
    public static String citesteTastatura(Scanner sc){
        System.out.println("Username: ");
        user = sc.nextLine();
        System.out.println("Parola: ");
        pass = sc.nextLine();
        String ok = gasit(user, pass);
        return ok;
    }
    public static String gasit(String user, String pass){
        FileInputStream file = null;
        try{
            file = new FileInputStream("parole.txt");
            int userOK = 0;
            StringBuilder data = null;
           while((data = citesteLinie(file)) != null){
               String[] str = data.toString().split(" ");
               if(str.length == 2)
               {
                   if(str[0].compareTo(user) == 0 && str[1].compareTo(pass) == 0){
                       return "Acces permis";
                   }
                   else if(str[0].compareTo(user) == 0 && str[1].compareTo(pass) != 0){
                       userOK = 1;
                   }
               }
           }

        if(userOK == 1) return "Parola gresita";

        }
        catch(IOException e){
            System.out.println("Nu s-a putut deschide fisierul");
        }
        finally{
            try{
                if(file != null) file.close();
            }
            catch(IOException e){
                System.out.println("A intervenit o eroare");
            }

        }
        return "Nu a fost gasit";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 5; i++)
        {
            String ok = citesteTastatura(sc);
            if(ok.compareTo("Acces permis") == 0){

                System.out.println("Acces permis");
                return;
            }
            if (ok.compareTo("Parola gresita") == 0){
                for(int j = 0; j < 3; j++){
                    int incercari = 3 - j;
                    System.out.println("Parola gresita. Incercari ramase: " + incercari);
                    String parola = sc.nextLine();
                    String okParola = gasit(user, parola);
                    if(okParola.compareTo("Acces permis") == 0){
                        System.out.println(okParola);
                        return;
                    }
                }
                System.out.println("Introdu username si parola din nou");
            }
        }
        System.out.println("Cont blocat");
    }
}

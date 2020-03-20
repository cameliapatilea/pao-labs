import java.util.Arrays;

public class lab4_ex1 {


    public static String sortString(String inputStr){
        char temp[] = inputStr.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }

    public static void main(String[] args) {
        String sir1 = "mara";
        String sir2 = "amara";
        String sir1Sortat = sortString(sir1);
        String sir2Sortat = sortString(sir2);

        if(sir1Sortat.equals(sir2Sortat))
            System.out.println("Cele 2 siruri sunt anagrame");
        else System.out.println("Cele doua siruri nu sunt anagrame");

    }
}

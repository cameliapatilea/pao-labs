import java.util.Scanner;
import java.util.Vector;

public class lab2_ex4 {

    public static class Student{
        private int nota;
        private String nume;

        public Student(){
            this.nume = "";
            this.nota = 0;
        }
        public Student(int nota, String nume){
            this.nume = nume;
            this.nota = nota;
        }
        public int getNota()
        {
            return nota;
        }
        public String getNume(){
            return nume;
        }
        public void setNume(String nume){
            this.nume = nume;
        }
        public void setNota(int nota){
            this.nota = nota;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector Std = new Vector();

        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++)
        {
            Student S = new Student();
            String linie = sc.nextLine();
            String[] Value = linie.split(" ");
            S.setNume(Value[0]);
            S.setNota(Integer.parseInt(Value[1]));
            Std.add(S);
        }

            for(int i = 0; i < n; i++)
            {
                Student S = new Student();
                S = (Student) Std.get(i);
                System.out.println(S.getNume() +" "+ S.getNota());
            }
    }
}

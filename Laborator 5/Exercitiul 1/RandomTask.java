import java.util.Random;

public class RandomTask implements Task {
    public String mesaj;
    @Override
    public void execute() {
        System.out.println(this.mesaj);
    }

    RandomTask(){
        mesaj = "Mesaj de afisat in fluxul de iesire.";
    }
    public void afiseaza(){
        System.out.println(this.mesaj);
    }

    public static void main(String[] args) {
        RandomTask obj = new RandomTask();
        obj.execute();
        obj.afiseaza();
    }
}

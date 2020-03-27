import javax.annotation.processing.SupportedSourceVersion;
import java.lang.Math;

public class RandomOutTask implements Task {
    public double x;
    @Override
    public void execute() {
        System.out.println("Mesajul care contine numarul random " + this.x);
    }
    RandomOutTask(){
       double rand = (int)(Math.random() * 100);
       this.x = rand;
        System.out.println(rand);
    }
    public void afiseaza()
    {
        System.out.println("Mesajul care contine numarul random " + this.x);
    }

    public static void main(String[] args) {
        RandomOutTask obj = new RandomOutTask();
        obj.execute();
        obj.afiseaza();
    }
}

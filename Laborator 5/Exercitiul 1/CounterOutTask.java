public class CounterOutTask implements Task {
    public int x;
    @Override
    public void execute() {

    }
    CounterOutTask(){
        this.x = 12;
    }
    public void increment(){
        this.x++;
        System.out.println("Numarul x = "  + this.x);
    }

    public static void main(String[] args) {
        CounterOutTask obj = new CounterOutTask();
        for(int i = 1; i <= 10; i++)
            obj.increment();
    }
}

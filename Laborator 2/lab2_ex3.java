public class lab2_ex3 {
    public static class Camion{
        private int nivelCombustibil;
        private String denumireFirmaCamion;

        public Camion(){
            this.nivelCombustibil = 0;
            this.denumireFirmaCamion = "";
        }
        public Camion(int x, String S){
            this.nivelCombustibil = x;
            this.denumireFirmaCamion = S;
        }
        public int getnivelCombustibil(){
            return nivelCombustibil;
        }
        public String getDenumireFirmaCamion(){
            return denumireFirmaCamion;
        }
        
        public void setnivelCombustibil(int nivelCombustibil){
            this.nivelCombustibil = nivelCombustibil;
        }
        public void setDenumireFirmaCamion(String denumireFirmaCamion){
            this.denumireFirmaCamion = denumireFirmaCamion;
        }
        
        public void mergeCamionul(){
            if(nivelCombustibil > 0)
            nivelCombustibil--;
            if(nivelCombustibil > 0)
            {
                System.out.println("Merge camionul " + denumireFirmaCamion);
            }
            else {
                System.out.println("Nu mai merge camionul " + denumireFirmaCamion);
            }

        }
    }

    public static void main(String[] args) {
        Camion C = new Camion(10, "ARO");
        for(int i = 0; i < 10; i++)
            C.mergeCamionul();
        Camion C1 = new Camion();
        C1.setnivelCombustibil(5);
        C1.setDenumireFirmaCamion("Mircea");
        for(int i = 0; i < 10; i++)
            C1.mergeCamionul();
    }
}

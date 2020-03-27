public class Album implements Comparable<Album>{
    public String nume;
    public int anPublicare;
    public double rating;
    Album(){
        this.nume = "";
        this.anPublicare = 0;
        this.rating = 0;
    }
    Album(String nume, int anPublicare, double rating){
        this.nume = nume;
        this.anPublicare = anPublicare;
        this.rating = rating;
    }
    public void set_nume(String nume)
    {
        this.nume = nume;
    }
    public void set_an(int an)
    {
        this.anPublicare = an;
    }
    public void set_rating(double rating)
    {
        this.rating = rating;
    }
    public String get_nume()
    {
        return this.nume;
    }
    public int get_an()
    {
        return this.anPublicare;
    }
    public double get_rating()
    {
        return this.rating;
    }

    @Override
        public int compareTo(Album obj) {
        if(this.nume.compareTo(obj.nume) == 0){
            if(this.rating == obj.rating)
                return 0;
            else if(this.rating > obj.rating)
                return 1;
            else return -1;
        }
        else if(this.nume.compareTo(obj.nume) < 0)
        {
            return -1;
        }
        else
        {
            return 1;
        }

    }
}

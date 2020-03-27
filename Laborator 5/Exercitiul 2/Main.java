import java.util.Collections;
import java.util.Vector;

public class Main extends Album {

    public static void main(String[] args) {
        Vector<Album> vect = new Vector<Album>();
        Album a1 = new Album();
        Album a2 = new Album();
        Album a3 = new Album();
        Album a4 = new Album();
        Album a5 = new Album();

        a1.set_nume("Dangerous");
        a1.set_an(1991);
        a1.set_rating(4.1);

        a2.set_nume("Thriller");
        a2.set_an(1982);
        a2.set_rating(4.6);

        a3.set_nume("Bad");
        a3.set_an(1987);
        a3.set_rating(4.9);

        a4.set_nume("This is it");
        a4.set_an(2009);
        a4.set_rating(5);

        a5.set_nume("Dangerous");
        a5.set_an(1979);
        a5.set_rating(4.0);

        vect.add(a1);
        vect.add(a2);
        vect.add(a3);
        vect.add(a4);
        vect.add(a5);

        for(int i = 0; i < vect.size(); i++)
            System.out.println("Numele albumului: " + vect.get(i).get_nume() + ", anul aparitiei " + vect.get(i).get_an() + ", rating " + vect.get(i).get_rating());

        System.out.println(" ");

        Collections.sort(vect);

        for(int i = 0; i < vect.size(); i++)
            System.out.println("Numele albumului: " + vect.get(i).get_nume() + ", anul aparitiei " + vect.get(i).get_an() + ", rating " + vect.get(i).get_rating());
   }


}

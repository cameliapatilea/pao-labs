package Helpers;

import Entities.Pacient;
import com.opencsv.CSVReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class ReadWriteService{

        private static ReadWriteService instance = null;

        private ReadWriteService(){

        }
        public static ReadWriteService getInstance(){
            if(instance == null){
                instance = new ReadWriteService();
            }
            return instance;
        }

        public static List<List<String>> citire(String caleFisier) throws IOException {
           List<List<String>> matrice = new ArrayList<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(caleFisier));
                 CSVReader csvReader = new CSVReader(reader);) {

                String[] linie;
                linie = csvReader.readNext();
                while ((linie = csvReader.readNext()) != null) {
                   matrice.add(new ArrayList<String>(Arrays.asList(linie)));
                }

            }
            return matrice;
        }

        public static void afisare(String caleFisier, String[] header, List<List<String>> listaObiecte){
            return;
        }


}

package Helpers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuditService implements Runnable{
    private static AuditService instance = null;

    private AuditService(){

    }
    public static AuditService getInstance(){
        if(instance == null){
            instance = new AuditService();
        }
        return instance;
    }



    public static List<String> citireCSVAudit(String caleFisier) {
        List<String> matrice = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(caleFisier));
             CSVReader csvReader = new CSVReader(reader);) {
            String[] linie;
            linie = csvReader.readNext();
            while ((linie = csvReader.readNext()) != null) {
                matrice.add(String.join(" ", linie));
            }

        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
        return matrice;
    }

    public static void scriereCSVAudit(String caleFisier, String[] header, List<String> listaObiecte){
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(caleFisier));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ){

            csvWriter.writeNext(header);
            for(int i = 0; i < listaObiecte.size(); i++)
            {
                String[] arr = listaObiecte.get(i).split(" ");
                csvWriter.writeNext(arr);
            }
        }
        catch (IOException e){

        }
    }

    @Override
    public void run() {

    }
}

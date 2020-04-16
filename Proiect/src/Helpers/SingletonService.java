package Helpers;

import java.nio.file.Path;
import java.util.List;

public class SingletonService {
    private static SingletonService instance = null;

    private SingletonService(){

    }
    public static SingletonService getInstance(){
        if(instance == null){
            instance = new SingletonService();
        }
        return instance;
    }
    public static List<List<String>> citire(String caleFisier){
        return null;
    }
    public static void afisare(String caleFisier, String[] header, List<List<String>> listaObiecte){
        return;
    }
}

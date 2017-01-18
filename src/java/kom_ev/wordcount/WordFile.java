package kom_ev.wordcount;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by EKomarov on 18.01.2017.
 */

public class WordFile {

    public static void main(String[] args) {
        WordFile newFile = new WordFile();
        newFile.execute(args[0], args[1]);
    }

    private boolean execute(String fileName, String lang) {
        try {
            String[] temp = parseFile(readFile(fileName), lang);
            System.out.println(mapFile(temp).mapSort((o1, o2) -> o2.getValue() - o1.getValue()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String[] parseFile(String fileData, String lang) throws IOException {
        fileData = fileData.replace(System.getProperty("line.separator"), " ").toLowerCase();
        fileData = fileData.replaceAll("[^а-яёА-ЯЁ ]","").replaceAll("  "," ");
        return fileData.split(" ");
    }

    private HashMapToSortedList<String, Integer> mapFile(String[] parsedFileData){
        HashMapToSortedList<String, Integer> mapFile = new HashMapToSortedList<> ();
        for(String word : parsedFileData){
            if(!mapFile.containsKey(word))
                mapFile.put(word, 1);
            else{
                int count = mapFile.get(word);
                mapFile.put(word, count + 1);
            }
        }
        return mapFile;
    }

    private String readFile(String directory) throws IOException {
//        String readFile = Files.readAllLines(Paths.get(directory), Charset.forName("cp1251")).toString();
//        System.out.println(readFile);
//        String (readFile.getBytes("UTF-8"), Charset.forName("UTF-8"));
//        System.out.println(Files.readAllLines(Paths.get(directory), Charset.forName("cp1251")).toString());
        return Files.readAllLines(Paths.get(directory), Charset.forName("cp1251")).toString();
    }
}

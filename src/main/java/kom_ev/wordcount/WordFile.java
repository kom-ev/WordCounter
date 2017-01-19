package kom_ev.wordcount;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by EKomarov on 18.01.2017.
 */

public class WordFile {

    public static void main(String[] args) {
        WordFile newFile = new WordFile();
        newFile.execute(args[0], args[1]);
    }

    private boolean execute(String fileName, String encoding) {
        try {
            String[] temp = parseFile(readFile(fileName, encoding));
            List<Map.Entry<String, Integer>> sortedList = mapFile(temp).mapSort((o1, o2) -> o2.getValue() - o1.getValue());
            System.out.println(sortedList);
            try(  PrintWriter out = new PrintWriter(fileName.substring(0,fileName.lastIndexOf("\\") + 1)+
                                                             fileName.substring(fileName.lastIndexOf("\\") + 1,
                                                             fileName.lastIndexOf(".")) + "_words.txt")){
                for (Map.Entry<String, Integer> map : sortedList)
                out.println(map);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String[] parseFile(String fileData) throws IOException {
        fileData = fileData.replace(System.getProperty("line.separator"), " ").toLowerCase();
        fileData = fileData.replaceAll("[\\p{Graph}—«»…–?:\uFEFF ]","").replaceAll("  +"," ").trim();
        System.out.println(fileData);
        return fileData.split(" ");
    }

    private HashMapToSortedList<String, Integer> mapFile(String[] parsedFileData){
        HashMapToSortedList<String, Integer> mapFile = new HashMapToSortedList<> ();
        for(String word : parsedFileData){
            if(!mapFile.containsKey(word))
                mapFile.put(word.trim(), 1);
            else{
                int count = mapFile.get(word);
                mapFile.put(word.trim(), count + 1);
            }
        }
//        System.out.println(mapFile.get(""));
        return mapFile;
    }

    private String readFile(String directory, String encoding) throws IOException {
            return Files.readAllLines(Paths.get(directory), Charset.forName(encoding)).toString();
    }
}

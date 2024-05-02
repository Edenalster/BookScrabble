package test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class IOSearcher {
    public static boolean search(String word, String... filenames) throws IOException {
        for (String filename : filenames) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = reader.readLine()) != null) {
                    if ((line.contains(word))) {
                        return true; //word found in this file
                    }

                }
            } catch (IOException e) {
                throw e;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return false; //word not found in any file
    }
}




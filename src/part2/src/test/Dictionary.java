package test;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;



public class Dictionary {
    private final CacheManager existWC;
    private final CacheManager nonexistWC;
    private final BloomFilter bloomFilter;
    private final String[] textfile;
    public Dictionary(String...filenames){
        existWC=new CacheManager(40,new LRU());
        nonexistWC=new CacheManager(100,new LFU());
        bloomFilter=new BloomFilter(256,"MD5","SHA1");
        textfile=filenames;

        for (String fileName : filenames) {
            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(" ");
                    for (String word : words) {
                        bloomFilter.add(word);
                    }
                }
                fr.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public boolean query(String word) {
        if (existWC.query(word)) {
            return true;
        }
        if (nonexistWC.query(word)) {
            return false;
        }
        boolean isInBloomFilter = bloomFilter.contains(word);

        if (isInBloomFilter) {
            existWC.add(word);
        } else {
            nonexistWC.add(word);
        }

        return isInBloomFilter;
    }
    public boolean challenge(String word) {
        boolean isFound = false;
        try {
            isFound = IOSearcher.search(word, textfile);

            if (isFound) {
                existWC.add(word);
            } else {
                nonexistWC.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isFound;
    }

}






package test;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;


public class LFU implements CacheReplacementPolicy {
    //private final LinkedHashSet<String> cache;
    private final LinkedHashMap<String, Integer> cache;
    private int capacity;

    //private int count;
    public LFU() {
        this.cache = new LinkedHashMap<>();
        this.capacity = 0;
    }

    @Override
    public void add(String word) {
        if (cache.containsKey(word)) {
            cache.put(word, cache.get(word) + 1);
        } else {
            cache.put(word, 1);
        }
        capacity = 1;
    }

    @Override
    public String remove() throws NoSuchElementException {
        for (Map.Entry<String, Integer> entry : cache.entrySet()) {
            if (entry.getValue() == capacity) {
                String remove = entry.getKey();
                if (remove != null) {
                    capacity++;
                    return remove;
                }
            }
        }
        throw new NoSuchElementException("Not found");
    }
    public LinkedHashMap<String,Integer> getCache(){
        return cache;
    }
}

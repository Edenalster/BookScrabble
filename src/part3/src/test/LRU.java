package test;
import java.util.LinkedHashSet;
import java.util.Iterator;


public class LRU implements CacheReplacementPolicy {
    private final LinkedHashSet<String> cache;
    private final int capacity;

    public LRU(){
        this.cache=new LinkedHashSet<>();
        this.capacity=100;
    }
    @Override
    public void add(String word) {
        if (cache.contains(word)) {
            cache.remove(word);
        } else if (cache.size() >= capacity) {
            Iterator<String> iterator = cache.iterator();
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
        cache.add(word);
    }

    @Override
    public String remove() {
        Iterator<String> iterator = cache.iterator();
        String word = iterator.next();
        iterator.remove();
        return word;
    }
}




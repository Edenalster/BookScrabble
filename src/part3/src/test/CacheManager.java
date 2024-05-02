package test;
import java.util.LinkedHashSet;


public class CacheManager {
    private final CacheReplacementPolicy crp;
    private final LinkedHashSet<String> cache;
    private final int maxSize;
    public CacheManager(int size, CacheReplacementPolicy crp){
        this.maxSize=size;
        this.crp=crp;
        this.cache=new LinkedHashSet<>();
    }
    public boolean query(String word){
        return cache.contains(word);
    }
    public void add(String word){
        if(!query(word)){
            crp.add(word);
            cache.add(word);
            if (cache.size()>maxSize){
                String removedW= crp.remove();
                cache.remove(removedW);
            }
        }
    }
	
	

}

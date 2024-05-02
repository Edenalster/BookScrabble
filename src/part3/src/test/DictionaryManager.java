package test;
import java.util.HashMap;
import java.util.Map;


public class DictionaryManager  {
   private static DictionaryManager dictionaryManager=null;
   private final Map<String,Dictionary> dictionaryMap;
   private DictionaryManager(){
       dictionaryMap=new HashMap<>();
   }
   public static DictionaryManager get(){
       if(dictionaryManager==null){
           dictionaryManager =new DictionaryManager();
       }
       return dictionaryManager;
   }
    private synchronized Dictionary getDictionary(String book) {
        return dictionaryMap.computeIfAbsent(book, Dictionary::new);
    }
   public boolean query(String ... args){
       String word=args[args.length-1];
       boolean wordExist=false;

       for (int i=0; i<args.length-1;i++){
           Dictionary dictionary=getDictionary(args[i]);
           wordExist|=dictionary.query(word);

       }
       return wordExist;
   }
   public boolean challenge(String... args){
       String word= args[args.length-1];
       boolean wordExist=false;

       for(int i=0; i<args.length-1; i++){
          Dictionary dictionary=getDictionary(args[i]);
          wordExist|= dictionary.challenge(word);
       }
       return wordExist;
   }
   public int getSize(){
       return dictionaryMap.size();
   }


}

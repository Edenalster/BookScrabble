package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    private final int bitArrayl;
    private final BitSet bitArray;
    private final MessageDigest[] hashFunc;
    public BloomFilter( int length, String...algorithms){
        this.bitArrayl=length;
        this.bitArray=new BitSet(bitArrayl);
        this.hashFunc=new MessageDigest[algorithms.length];
        for(int i=0; i<algorithms.length;i++){
            try {
                hashFunc[i] = MessageDigest.getInstance(algorithms[i]);
            }catch (NoSuchAlgorithmException e){
                System.err.println("Error initializing hash func: "+e.getMessage());
            }
        }
    }
    public void add(String str){
        for(MessageDigest md: hashFunc){
            byte[] hash=md.digest(str.getBytes());
            int index= Math.abs(new BigInteger(hash).intValue())%bitArrayl;
            bitArray.set(index,true);
        }
    }
    public boolean contains(String str){
        for(MessageDigest md: hashFunc){
            byte[] hash=md.digest(str.getBytes());
            int index= Math.abs(new BigInteger(hash).intValue())% bitArrayl;
            if(!bitArray.get(index)){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for( int i=0; i<bitArray.length();i++){
            sb.append(bitArray.get(i) ? "1" : "0");
        }
        return sb.toString();
    }
	


}

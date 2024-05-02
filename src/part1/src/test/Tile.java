package test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
public class Tile {
    public final char letter;
    public final int score;
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    public static Tile createTile(char letter, int score) {
        return new Tile(letter, score);
    }

    public char getLetter() {
        return this.letter;
    }

    /*public int getScore() {
        return score;
    }*/

    @Override
    public int hashCode() {

        return Objects.hash(letter, score);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o){
            return true;
        } else {
            return false;
        }
    }

    public static class Bag {
        private static Bag bag = null;
        private final int[] quan ={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final int[] maxquan ={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final Tile[] tiles = new Tile[26];
        private final Random random=new Random();

        private Bag() {
            tiles[0]=Tile.createTile('A',1);
            tiles[1]=Tile.createTile('B',3);
            tiles[2]=Tile.createTile('C',3);
            tiles[3]=Tile.createTile('D',2);
            tiles[4]=Tile.createTile('E',1);
            tiles[5]=Tile.createTile('F',4);
            tiles[6]=Tile.createTile('G',2);
            tiles[7]=Tile.createTile('H',4);
            tiles[8]=Tile.createTile('I',1);
            tiles[9]=Tile.createTile('J',8);
            tiles[10]=Tile.createTile('K',5);
            tiles[11]=Tile.createTile('L',1);
            tiles[12]=Tile.createTile('M',3);
            tiles[13]=Tile.createTile('N',1);
            tiles[14]=Tile.createTile('O',1);
            tiles[15]=Tile.createTile('P',3);
            tiles[16]=Tile.createTile('Q',10);
            tiles[17]=Tile.createTile('R',1);
            tiles[18]=Tile.createTile('S',1);
            tiles[19]=Tile.createTile('T',1);
            tiles[20]=Tile.createTile('U',1);
            tiles[21]=Tile.createTile('V',4);
            tiles[22]=Tile.createTile('W',4);
            tiles[23]=Tile.createTile('X',8);
            tiles[24]=Tile.createTile('Y',4);
            tiles[25]=Tile.createTile('Z',10);

        }

        public static Bag getBag() {
            if (bag == null) {
                bag = new Bag();
            }
            return bag;
        }

        public Tile getRand() {
            int i = random.nextInt(26);
           if(quan[i]==0) {
               return null;
           }
           Tile tile=tiles[i];
           quan[i]--;
           return tile;
        }

        public Tile getTile(char l) {
            for(int i=0;i<26;i++){
                if(tiles[i].getLetter()==l && quan[i]>0){
                    quan[i]--;
                    return tiles[i];
                }
            }
            return null;
        }
        public void put(Tile tile1) {
            if(tile1==null){
                return;
            }
            for(int i=0;i<26;i++){
                if(tiles[i].getLetter()==tile1.getLetter()){
                    if(quan[i]<maxquan[i]){
                        quan[i]++;
                        return;
                    }
                }
            }
        }

        public int size() {
            int sum=0;
            for( int i=0; i<26;i++){
                sum=sum+quan[i];
            }
            return sum;
        }

        public int[] getQuantities() {
            return quan.clone();
        }

    }

}


